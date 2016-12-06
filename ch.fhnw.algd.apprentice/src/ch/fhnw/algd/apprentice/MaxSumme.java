package ch.fhnw.algd.apprentice;

public class MaxSumme {

	public static void main(String[] args) {
		int folge [] = new int [3];
		folge[0] = 1;
		folge[1] = -1;
		folge [2] = 2;
		System.out.println(maxSumme(folge));

	}
	
	private static int maxSumme(int [] folge) {
		int maxSumme = 0;
		for (int von = 0; von < folge.length; von++){
			for (int bis = von; bis < folge.length; bis++) {
				int summe = 0;
				for (int i = von; von <= bis; von++) {
					summe += folge[i];
					maxSumme = Math.max(summe, maxSumme);
				}
			}
		}
		
		return maxSumme;
	}

}
