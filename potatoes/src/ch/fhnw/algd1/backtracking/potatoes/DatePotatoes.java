package ch.fhnw.algd1.backtracking.potatoes;

public class DatePotatoes {

	private static int[] weights = new int[] { 10, 5, 2, 1, 5, 10 }; //Rucksack?
	
	private static int[][] teller = new int[weights.length / 2][weights.length - (weights.length / 2)];  
	private static int minDifference = Integer.MAX_VALUE;
	private static int summe = 0;
	private static int weight1 = 0;

	
	public static void solve() { 
		solve(0); 
	}
	
	private static void solve(int i) {

		if(i < weights.length) {
			// mit Kartoffel
			weight1 = weight1 + weights[i];
			solve(i + 1);
			// ohne Kartoffel
			weight1 = weight1 - weights[i];
			solve(i + 1);
			
		} else if (Math.abs(summe - 2*weight1) < minDifference){
			minDifference = Math.abs(summe - 2*weight1);
		}

		
	}


	private static void printResult() {
		System.out.println("Minimal weight difference is: "+ minDifference);

	}
	
	public static void main(String[] args) {
		
//		Gesamtgewicht aller Kartoffeln
		for(int toffel : weights) {
			summe += toffel;	
		}
		solve();
		printResult();
	}



}
