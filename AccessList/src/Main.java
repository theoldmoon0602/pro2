import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		String fname = null;
		try (Scanner stdin = new Scanner(System.in)) {
			fname = stdin.nextLine();
		}
		Map<String, Integer> cnts = new TreeMap<>();
		
		try (Scanner f = new Scanner(new FileReader(fname))) {
			while (true) {
				if (! f.hasNextLine()) {
					break;
				}
				String s = f.nextLine();
				String p = s.split(" ")[0];
				if (cnts.containsKey(p)) {
					cnts.put(p, cnts.get(p) + 1);
				}
				else{
					cnts.put(p, 1);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		for (Map.Entry<String, Integer> e : cnts.entrySet()) {
			System.out.println(String.format("%s: %d time(s)", e.getKey(), e.getValue()));
		}
		
	}
}
