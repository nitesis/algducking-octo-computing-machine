package prod;

public class ChessBoard {

	public static void main(String[] args) {
		chessBoard();

	}
	
	public static void chessBoard () {

		char zeile = '8';
		while (zeile >= '1') {
			char spalte = 'A';
			while (spalte <= 'H') {
				//zeigt alle Elemente in einer Reihe an
				System.out.print("" + zeile + spalte + " ");
				spalte = (char) (spalte + 1);
			}
			//sorgt für Zeilenumbruch nach jeder Zeile
			System.out.println();
			zeile = (char) (zeile - 1);
		} 
	}

}
