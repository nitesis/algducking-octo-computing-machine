package prod;

public class Hanoi {

	public static void main(String[] args) {
		hanoi(3, "A", "B", "C");

	}
	
	public static void hanoi (int n, String src, String dest, String buffer) {
		//überträgt n Scheiben von src zu dest mit Hilfe von buffer
		if (n > 0) {
		hanoi (n - 1, src, buffer, dest);
		System.out.println("Bewegung von " + src + " zu " + dest);
		hanoi (n - 1, buffer, dest, src);
		}
	}

}
