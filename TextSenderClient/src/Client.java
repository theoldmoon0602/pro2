import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 55555;
				
		try (Scanner stdin = new Scanner(System.in);
				Socket sock = new Socket(host, port);
				BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				PrintWriter writer = new PrintWriter(sock.getOutputStream(), true)) {			
			System.out.printf("%s:%dへ接続完了\n", host, port);
			
			System.out.print("送る行数: ");
			int lines = stdin.nextInt();
			stdin.skip(stdin.delimiter());
			writer.println(lines);
			
			for (int i = 0; i < lines; i++) {
				String line = stdin.nextLine();
				writer.println(line);
			}

			String result = reader.readLine();
			System.out.println("サーバからの返信: " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
