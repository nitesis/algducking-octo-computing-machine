package princeton;

public class RecusiveF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(f(0));
//		result 2
	}
	
	public static int f(int x) {
		   if (x > 5) return x - 4;
		   else return f(f(x+5));
		}

}
