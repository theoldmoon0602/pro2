import java.util.Random;

public class Dog extends Thread {
	private String name;
	private double stride;
	private int pitch;
	private int time;		// ゴールまでの時間。getTimeとかで順位を図ろうと思ったけどリアルタイムに出力するならいらない
	
	private RankManager rm;

	public Dog(String name, double stride, int pitch, RankManager rm) {
		this.name = name;
		this.stride = stride;
		this.pitch = pitch;
		
		this.rm = rm;
	}
	
	
	public void run() {
		double prev = 0;
		double road = 0;
		int condition = 0;
		Random rd = new Random();
		
		for (time = 0; ; time++) {
			if (time % pitch != 0) {
				continue;
			}
			
			if (condition > 0) {
				condition--;
				if (condition == 0) {
					System.out.println(name + "は絶好調じゃなくなった");
				}
			}
			if (condition == 0 && rd.nextInt(100) == 0) {
				System.out.println(name + "は絶好調!!");
				condition = 10;
			}
			
			road += (condition > 0) ? stride * 2 : stride;
			
			if (prev < 200 && 200 <= road) {
				System.out.println(name + "が200mに到達!");
			}
			if (prev < 400 && 400 <= road) {
				System.out.println(name + "が400mに到達!");
			}
			if (600 <= road) {
				synchronized (rm) {
					int rank = rm.getRank();
					
					System.out.println(name + "が" + rank +  "位でゴール!!");
					rm.next();
				}
				
				break;
			}
			
			prev = road;
		}
	}
}
