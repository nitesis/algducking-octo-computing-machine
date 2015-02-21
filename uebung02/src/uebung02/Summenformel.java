package uebung02;

public class Summenformel {

	public static void main(String[] args) {
		
		int n = 4;
		double x = 0; 
		double s = Math.pow(2, n);
		
		while (n >= 0) {
		   x = x + (int)(Math.pow(2, (n-1)));
		   n--;   
		} 
		 
		s = s - x;
		System.out.println(s);

	}

}
