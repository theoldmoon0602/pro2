
public class Main {

	public static void main(String[] args) {
		Simulator[] simulators = {
				new Simulator("A", 10),
				new Simulator("B", 15),
				new Simulator("C", 20),
				new Simulator("D", 20),
				new Simulator("E", 25)
		};
		
		for (Simulator simulator : simulators) {
			simulator.start();
		}
	}

}
