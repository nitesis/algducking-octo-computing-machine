package uebung01;

public class DivModNeg {

	public static void main(String[] args) {
		
		int x1 = 7;
		int x2 = -7;
		int y1 = 3;
		int y2 = -3;
		
		System.out.println("7div3 = " + x1/y1);
		System.out.println("7div-3 = " + x1/y2);
		System.out.println("-7div3 = " + x2/y1);
		System.out.println("-7div-3 = " + x2/y2);
		
		System.out.println("7mod3 = " + x1%y1);
		System.out.println("7mod-3 = " + x1%y2);
		System.out.println("-7mod3 = " + x2%y1);
		System.out.println("-7mod-3 = " + x2%y2);
		System.out.println("0%2 = " + 0%2);
		System.out.println(" 7/2 = " + 7/2);

	}

}
