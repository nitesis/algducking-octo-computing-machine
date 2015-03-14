/**
 * ETH Z�rich; Leitprogramm; Bin�re Suchb�ume ----- 
 * Das ist die Klasse eines einzelnen Knotens des Bin�rbaums. 
 * @author Bj�rn Steffen, Timur Erdag, Christina Class
 * @version 1.0 
 */
public class BinaryNode {
    private int key;
    private BinaryNode left, right;
    
    public BinaryNode( int key ) {
        this.key = key;
        left=null;
        right=null;
    }
    
    public BinaryNode( int key, BinaryNode left, BinaryNode right ) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
    
    /**
     * getter Methode f�r den Schl�ssel
     * @return den Schl�sselwert
     */
    public int getKey()
    {
        return key;
    }
    
    /**
     * setter Methode f�r den Schl�ssel
     * @param value der neue Schl�sselwert
     */
    public void setKey(int value)
    {
        key = value;
    }
    
    
    /**
     * getter Methode f�r das rechte Kind
     * @return rechtes Kind
     */
    public BinaryNode getRight()
    {
        return right;
    }
    
    /**
     * setter Methode f�r das rechte Kind
     * @param newRight Referenz auf neues rechtes Kind
     */
    public void setRight(BinaryNode newRight)
    {
        right=newRight;
    }
    
    /**
     * getter Methode f�r das linke Kind
     * @return linkes Kind
     */
    public BinaryNode getLeft()
    {
        return left;
    }
    
    /**
     * setter Methode f�r das linke Kind
     * @param newLeft Referenz auf neues linkes Kind
     */
    public void setLeft(BinaryNode newLeft)
    {
        left=newLeft;
    }
    
    /**
     * Ausgabe des Bin�rbaumes in der preorder Reihenfolge. Diese Methode 
     * ist von Ihnen in Aufgabe 3.3 des Leitprogramms zu implementieren.
     */
    // Implementiere diese Methode nach den Vorgaben der Aufgabe.
    // Geben Sie dazu f�r jeden Knoten den Schl�ssel aus,
    // gefolgt von einem Leerzeichen.
    // beachten Sie: der Algrithmus, der auf S. 27 des Leitprogramms beschrieben
    // ist, hat einen Parameter (vom Typ Knoten).
    // Sie m�ssen also eine weitere Methode preorder() definieren, und diese in 
    // preorderPrint() aufrufen
    // f�r eine Ausgabe OHNE anschliessenden Zeilenumbruch verwenden Sie bitte:
    // System.out.print()
    
    public void preorder(BinaryNode node) {
    	if (node != null) {
    		System.out.print(node.key + " ");
    		preorder(node.left);
    		preorder(node.right);
    	}
    }
    public void preorderPrint() 
    {
    	preorder(this);
    }
}

