
public class HeavyThread extends Thread {
	private String name;
	private int interval;
	
	public HeavyThread(String name, int interval) {
		this.name = name;
		this.interval = interval;
	}
	
	public void run() {
		for (int i = 5; i > 0; i--) {
			try {
				Thread.sleep(interval);
			}
			catch (InterruptedException e) {
				
			}
			System.out.println(name + ":" + i);
		}
	}
	
}
