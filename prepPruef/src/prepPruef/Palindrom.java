package prepPruef;

public class Palindrom {

	public static void main(String[] args) {
		
		String wort = "Sugus";
		System.out.println(isPalindrom(wort));

	}
	
	public static boolean isPalindrom(String wort) {
		int i = 0;
		while (i<=wort.length()/2 && wort.charAt(0)==wort.charAt(wort.length()-i-1)) {
			i++;
			return true;
		}
		return false;
		
	}

}
