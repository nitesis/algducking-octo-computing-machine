/**
 * ETH Zürich, Leitprogramm, Binäre Suchbäume ----
 * In dieser Klasse wird ein Binärbaum erzeugt und die Methode preorderPrint()
 * aufgerufen. Sie müssen an dieser Klasse nichts verändern, rufen Sie einfach
 * die main-Methode auf. So können Sie Ihre Implementation testen.
 *
 * @author Björn Steffen, Timur Erdag, Christina Class
 */
public class Aufgabe4_3 {

    public static void main( String[] args ) {
        System.out.println( "Aufgabe 4.3\n*****\n" );
        BinSearchTree root = makeTree();
    
        System.out.println("Suche nach 18 (vorhanden) ergibt: "+ root.search(root,18));
        System.out.println("Suche nach 27 (nicht vorhanden) ergibt: "+ root.search(root,27));
        System.out.println("Suche nach 4 (vorhanden) ergibt: "+ root.search(root,4));
        System.out.println("Suche nach 7 (2-mal vorhanden) ergibt: "+ root.search(root,7));
 
    }

    private static BinSearchTree makeTree() {
        BinSearchTree root = new BinSearchTree(6);
        BinSearchTree node1 = new BinSearchTree(3);
        BinSearchTree node2 = new BinSearchTree(2);
        BinSearchTree node3 = new BinSearchTree(4);
        BinSearchTree node4 = new BinSearchTree(7);
        BinSearchTree node5 = new BinSearchTree(10);
        BinSearchTree node6 = new BinSearchTree(7);
        BinSearchTree node7 = new BinSearchTree(18);
        BinSearchTree node8 = new BinSearchTree(14);
        BinSearchTree node9 = new BinSearchTree(20);

        root.setLeft(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        root.setRight(node4);
        node4.setRight(node5);
        node5.setLeft(node6);
        node5.setRight(node7);
        node7.setLeft(node8);
        node7.setRight(node9);

        return root;
    }
}