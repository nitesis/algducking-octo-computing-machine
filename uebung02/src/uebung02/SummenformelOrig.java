package uebung02;

public class SummenformelOrig {

	public static void main(String[] args) {
		
		int n = 80;
		double x = 1, s = Math.pow(2, n + 1); 
		
		while (n >= 0) {
		   s = s - x;
		   x = x * 2;
		   n--;
		} System.out.println(s);

	}

}
