
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		
		String fname = null;
		int code = -1;
		try (Scanner stdin = new Scanner(System.in)) {
			fname = stdin.nextLine();
			code = stdin.nextInt();
		}
		
		final String c = " " + String.valueOf(code) + " ";
		
		try (Stream<String> a = Files.lines(Paths.get(fname))) {
			long cnt = a.filter(s -> s.indexOf(c) >= 0).count();
			System.out.println(cnt);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
	}
}
