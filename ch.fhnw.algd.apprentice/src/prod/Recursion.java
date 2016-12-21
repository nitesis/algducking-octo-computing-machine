package prod;

public class Recursion {

	public static void main(String[] args) {
		countdown(6);

	}
	
	public static void countdown(int number) {
		if (number > 0) {
			System.out.println("Die Nummer lautet: " + number);
			number--;
			countdown(number);
		}
	}

}
