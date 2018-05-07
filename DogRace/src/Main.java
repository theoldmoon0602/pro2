
public class Main {
	public static void main(String[] args) {
		Dog[] dogs = new Dog[] {
			new Dog("Alice", 10.0, 1),
			new Dog("Bob", 20.0, 2),
			new Dog("Charlie", 30.0, 3),
			new Dog("Dave", 30.0, 3)
		};
		Dog.init();
		System.out.println("レース開始!");
		for (int i = 0; i < dogs.length; i++) {
			dogs[i].start();
		}
		
	}
}
