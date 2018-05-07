import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Main {
	public static String toggleCase(String s) {
		StringBuilder sb = new StringBuilder(s.length());
		
		for (char c : s.toCharArray()) {
			if (Character.isUpperCase(c)) {
				sb.append(Character.toLowerCase(c));
			}
			else if (Character.isLowerCase(c)) {
				sb.append(Character.toUpperCase(c));
			}
			else {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String fname = null;
		try (Scanner stdin = new Scanner(System.in)) {
			fname = stdin.nextLine();
		} 
		
		String line = null;
		try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				line = toggleCase(line);
				System.out.println(line);
			}
			
		} catch (Exception e) {
			System.out.printf("File: %s not found.", fname);
			return;
		}
		
	}
}
