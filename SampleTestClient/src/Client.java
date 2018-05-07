import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 55555;
		
		try (Socket sock = new Socket(host, port)) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
			
			writer.println("はろー");
			String result = reader.readLine();
			System.out.println("サーバからの返信: " + result);
			
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
