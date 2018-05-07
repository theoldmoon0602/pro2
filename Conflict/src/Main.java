
public class Main {
	public static void main(String[] args) {
		RankManager rm = new RankManager();
		rm.setRank(1);
		
		Dog[] dogs = new Dog[] {
			new Dog("Alice", 100, 1, rm),
			new Dog("Bob", 100, 1, rm),
			new Dog("Charlie", 100, 1, rm),
			new Dog("Dave", 100, 1, rm)
		};
		System.out.println("レース開始!");
		for (int i = 0; i < dogs.length; i++) {
			dogs[i].start();
		}
		
	}
}
