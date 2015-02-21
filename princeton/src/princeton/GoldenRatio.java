package princeton;

public class GoldenRatio {

	public static void main(String[] args) {

		int n = Integer.parseInt(args[0]);
		
		System.out.println(goldenRatio(n));
	}
	
	public static double goldenRatio(int i) {
		if (i == 0) {
			return 1;
		}
		return 1.0 + 1.0 / goldenRatio(i - 1);

	}

}
