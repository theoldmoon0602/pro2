import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		int port = 55555;
		while (true) {
			try (ServerSocket server = new ServerSocket(port)) {
				Socket sock = server.accept();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
				
				String data = reader.readLine();
				System.out.println("受信データ: " + data);
				writer.println("データ受け取りました");
				
				sock.close();
				
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
