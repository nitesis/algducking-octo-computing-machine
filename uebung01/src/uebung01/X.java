package uebung01;

public class X {
	static int x;

	public static void main(String[] args) {
		x = 0;
		System.out.println(!m() | m() || m() | !m());
		System.out.println(x);
	}

	private static boolean m() {
		x++;
		return false;
	}
}
