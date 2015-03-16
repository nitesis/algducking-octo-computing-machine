public interface Tree<K extends Comparable<? super K>, E> {
	/**
	 * Immutable interface to represent nodes of binary trees
	 */
	interface Node<K extends Comparable<? super K>, E> {
		K getKey();

		Node<K, E> getLeft();

		Node<K, E> getRight();
	}

	/**
	 * Searches an item in the tree.
	 * 
	 * @param key
	 *          the key to search for.
	 * @return the matching item or null if not found.
	 */
	public E search(K key);

	/**
	 * Computes the number of nodes in the tree
	 * 
	 * @return size of the tree.
	 */
	public int size();

	/**
	 * Computes the height of the tree
	 * 
	 * @return height of the tree.
	 */
	public int height();

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Insert a value into the tree; if an element is already stored under the
	 * given key the element is replaced by the new one.
	 * 
	 * @param key
	 *          key with which the specified element is to be associated.
	 * @param elelent
	 *          elelent to be inserted into the tree.
	 */
	public void insert(K key, E element);

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param key
	 *          the item to remove.
	 */
	public void remove(K key);

	/**
	 * Return a reference to the root of the tree.
	 * 
	 * @return root of the tree
	 */
	public Node<K, E> getRoot();
}
