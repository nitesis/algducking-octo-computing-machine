package prod;

public class Fakultaet {

	public static void main(String[] args) {
		int ergebnis = fakultaetRecursive(3);
		System.out.println(ergebnis);
	}
	
	public static int fakultaetRecursive(int n) {
		if (n == 1) {
			return 1;
			
		} else {
			return n * fakultaetRecursive(n - 1);
		}
	}

}
