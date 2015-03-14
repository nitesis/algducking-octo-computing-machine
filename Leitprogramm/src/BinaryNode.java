/**
 * ETH Zürich; Leitprogramm; Binäre Suchbäume ----- 
 * Das ist die Klasse eines einzelnen Knotens des Binärbaums. 
 * @author Björn Steffen, Timur Erdag, Christina Class
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
     * getter Methode für den Schlüssel
     * @return den Schlüsselwert
     */
    public int getKey()
    {
        return key;
    }
    
    /**
     * setter Methode für den Schlüssel
     * @param value der neue Schlüsselwert
     */
    public void setKey(int value)
    {
        key = value;
    }
    
    
    /**
     * getter Methode für das rechte Kind
     * @return rechtes Kind
     */
    public BinaryNode getRight()
    {
        return right;
    }
    
    /**
     * setter Methode für das rechte Kind
     * @param newRight Referenz auf neues rechtes Kind
     */
    public void setRight(BinaryNode newRight)
    {
        right=newRight;
    }
    
    /**
     * getter Methode für das linke Kind
     * @return linkes Kind
     */
    public BinaryNode getLeft()
    {
        return left;
    }
    
    /**
     * setter Methode für das linke Kind
     * @param newLeft Referenz auf neues linkes Kind
     */
    public void setLeft(BinaryNode newLeft)
    {
        left=newLeft;
    }
    
    /**
     * Ausgabe des Binärbaumes in der preorder Reihenfolge. Diese Methode 
     * ist von Ihnen in Aufgabe 3.3 des Leitprogramms zu implementieren.
     */
    // Implementiere diese Methode nach den Vorgaben der Aufgabe.
    // Geben Sie dazu für jeden Knoten den Schlüssel aus,
    // gefolgt von einem Leerzeichen.
    // beachten Sie: der Algrithmus, der auf S. 27 des Leitprogramms beschrieben
    // ist, hat einen Parameter (vom Typ Knoten).
    // Sie müssen also eine weitere Methode preorder() definieren, und diese in 
    // preorderPrint() aufrufen
    // für eine Ausgabe OHNE anschliessenden Zeilenumbruch verwenden Sie bitte:
    // System.out.print()
    
    public void preorderPrint() 
    {   
    }
}

