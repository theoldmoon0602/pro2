import java.util.Random;

public class Simulator extends Thread {
	private int waitmsec;
	private String name;
	private int num;
	public Simulator(String name, int num) {
		this.name = name;
		this.num = num;
		this.waitmsec = (new Random()).nextInt(500) + 500; // 500 - 999
	}
	
	public void run() {
		synchronized (System.out) {
			System.out.println("演算器" + name + "は演算中");
		}
		
		// wait
		try {
			Thread.sleep(waitmsec);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		synchronized (System.out) {		
			System.out.print("演算器" + name + "[");
			for (int i = 0; i <= num; i++) {
				System.out.print(i);
				if (i != num) {
					System.out.print(",");
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			System.out.println("]");
		}
		
	}
}
