/**
 * ETH Zürich, Leitprogramm, Binäre Suchbäume ----
 * In dieser Klasse wird ein Binärbaum erzeugt und die Methode preorderPrint()
 * aufgerufen. Sie müssen an dieser Klasse nichts verändern, rufen Sie einfach
 * die main-Methode auf. So können Sie Ihre Implementation testen.
 *
 * @author Björn Steffen, Timur Erdag, Christina Class
 */

public class Aufgabe3_3 
{
    /**
     * In dieser Methode wird ein Binärbaum erzeugt und Ihre Implementation
     * von preorderPrint() aufgerufen. 
     * Danach wird die korrekte Ausgabe angezeigt, so dass Sie einfach 
     * überprüfen können, ob Sie die Methode richtig implementiert haben.
     */
    public static void main( String[] args ) {
        System.out.println( "Aufgabe 3.3\n*****\n" );
        BinaryNode root = makeTree();
    
        System.out.println( "Ihre Ausgabe:" );
        // Implementiere die folgende Methode nach den Vorgaben der Aufgabe.
        root.preorderPrint();

        System.out.println( "\nDie korrekte Ausgabe:" );
        System.out.println( "1 2 3 4 5 6 7 8 9 10" );
    }

    
    // Wenn die Attribute in der Klasse BinaryNode
    // public wären, wäre der Aufbau des Baumes etwas intuitiver
    // Tradeoff zwischen Information Hiding und Implementation
    private static BinaryNode makeTree() 
    {
        BinaryNode root = new BinaryNode(1);
        BinaryNode node2 = new BinaryNode(2);
        BinaryNode node3 = new BinaryNode(3);
        BinaryNode node4 = new BinaryNode(4);
        BinaryNode node5 = new BinaryNode(5);
        BinaryNode node6 = new BinaryNode(6);
        BinaryNode node7 = new BinaryNode(7);
        BinaryNode node8 = new BinaryNode(8);
        BinaryNode node9 = new BinaryNode(9);
        BinaryNode node10 = new BinaryNode(10);
        
        root.setLeft(node2);
        node2.setLeft(node3);
        node2.setRight(node4);
        root.setRight(node5);
        node5.setRight(node6);
        node6.setLeft(node7);
        node6.setRight(node8);
        node8.setLeft(node9);
        node8.setRight(node10);
        
        /* 
         * Im Falle von public Attributen würde der Aufbau des Baumes
         * folgendermassen ablaufen
            root.left new BinaryNode( 2 ));
            root.left.left = new BinaryNode( 3 );
            root.left.right = new BinaryNode( 4 );
            root.right = new BinaryNode( 5 );
            root.right.right = new BinaryNode( 6 );
            root.right.right.left = new BinaryNode( 7 );
            root.right.right.right = new BinaryNode( 8 );
            root.right.right.right.left = new BinaryNode( 9 );
            root.right.right.right.right = new BinaryNode( 10 );
        */
        return root;
    }
}