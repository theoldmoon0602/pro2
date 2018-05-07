import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		String setting = stdIn.next();//設定ファイル
		String score = "score.csv"; //成績データ
		String result = "result.csv"; //結果
		
		GradeProcessor gp = new GradeProcessor(score, setting, result); //コンストラクタ
		gp.process(); //score.csvをsettingに従って処理し，結果をresult.csvに出力
		
		stdIn.close();
		
		System.out.println("----result----");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(result)));
			
			String s;
			while((s = br.readLine()) != null)
				System.out.println(s);
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}