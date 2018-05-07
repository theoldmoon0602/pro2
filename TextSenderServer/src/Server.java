import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
		int port = 55555;
		System.out.println("接続の受け付けを開始します.");
		while (true) {
			try (ServerSocket server = new ServerSocket(port);
					Socket sock = server.accept();
					BufferedReader reader= new BufferedReader(new InputStreamReader(sock.getInputStream()));
					PrintWriter writer = new PrintWriter(sock.getOutputStream(), true)) {
				
				int lines = 0;
				try {
					 lines = Integer.parseInt(reader.readLine());
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				String[] datas = new String[lines];
				
				for (int i = 0; i < lines; i++) {
					datas[i] = reader.readLine();
					System.out.println("[+]DBG:" + datas[i]);
				}
				
				System.out.println("受信データ: " + lines);
				for (int i = 0; i < lines; i++) {
					System.out.println("受信データ: " + datas[i]);
				}
				
				writer.println("データ受け取りました");
				
				System.out.println("切断します");
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
