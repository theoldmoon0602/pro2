import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GradeProcessor {	
	private Path scorePath;
	private Path resultPath;
	private Map<String, Boolean> settings;
	private List<String> records = Arrays.asList("sum", "average", "fuka");
	
	/**
	 * 
	 * @param score string 成績が記述されたcsvファイルのパス
	 * @param settings string 設定が書かれたiniファイルのパス
	 * @param result string 結果を出力するcsvファイルのパス
	 * @throws IOException 
	 */
	public GradeProcessor(String score, String settings, String result) {
		scorePath = Paths.get(score);
		resultPath = Paths.get(result);
		
		this.settings = new HashMap<>();
		
		// デフォルトの設定
		for (String key : records) {
			this.settings.put(key, false);
		}
		
		// iniファイルの設定で上書き
		try (Stream<String>lines = Files.lines(Paths.get(settings))) {
			lines.forEach(l -> {
				String[] xs = l.split(" ");
				this.settings.put(xs[0], xs[2].equals("yes"));
			});
		} catch (IOException e) {
			// 例外catchしないといけないけど握りつぶしておいてよさそう
		}				
	}
	
	
	/**
	 * 全部やる
	 */
	public void process() {
		try (BufferedReader br = Files.newBufferedReader(scorePath);
				BufferedWriter bw = Files.newBufferedWriter(resultPath)) {
			
			// 先頭行は特別扱いする
			String top = br.readLine();
			
			// 設定によって出力するレコードを決める
			for (String key : records) {
				if (settings.get(key)) {
					top = top + "," + key.substring(0, 1).toUpperCase() + key.substring(1); 
				}
			}
			
			bw.write(top);
			bw.newLine();
			
			// 各個人についてやる
			while (true) {
				String l = br.readLine();
				if (l == null) {
					break;
				}
				
				// 処理して書き込み
				processStudent(l, bw);
			}
			
		} catch (IOException e) {
			// 例外つぶす人
		}
	}
	
	/**
	 * ある一人についてやる
	 * @throws IOException 
	 */
	public void processStudent(String l, BufferedWriter bw) throws IOException {
		List<Integer> scores = new ArrayList<>();
		String[] xs = l.split(",");
		for (int i = 1; i < xs.length; i++) {
			scores.add(Integer.valueOf(xs[i]));
		}
		
		for (String key : records) {
			if (!settings.get(key)) {
				continue;
			}
			
			if (key.equals("sum")) {
				l = l + "," + scores.stream().mapToInt(i -> i).sum();
			}
			else if (key.equals("average")) {
				l = l + "," + scores.stream().mapToInt(i -> i).average().getAsDouble();
			}
			else if (key.equals("fuka")) {
				l = l + "," + scores.stream().filter(i -> i < 60).count();
 			}
		}
		bw.write(l);
		bw.newLine();
	}
}
