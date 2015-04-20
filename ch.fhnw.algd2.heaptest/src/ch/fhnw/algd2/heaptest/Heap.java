package ch.fhnw.algd2.heaptest;
/* Heap als Implementierung einer Priority Queue */
class Heap<K> implements PriorityQueue<K> {
	private HeapNode<K>[] heap; // Array to store the heap elements
	private int size; // position of last insertion into the heap

	/**
	 * Construct the binary heap.
	 * 
	 * @param size
	 *          how many items the heap can store
	 */
	@SuppressWarnings("unchecked")
	Heap(int capacity) {
		// TODO allocate array of proper size and assign to heap
	}

	/**
	 * Returns the number of elements in this priority queue.
	 * 
	 * @return the number of elements in this priority queue.
	 */
	@Override
	public int size() {
		// TODO return number of elements currently contained in the heap
		return 0;
	}

	/**
	 * Check whether the heap is empty.
	 * 
	 * @return true if there are no items in the heap.
	 */
	@Override
	public boolean isEmpty() {
		// TODO return true if no element is in the heap
		return false;
	}

	/**
	 * Check whether the heap is full.
	 * 
	 * @return true if no further elements can be inserted into the heap.
	 */
	@Override
	public boolean isFull() {
		// TODO return true if no further element can be inserted to the heap
		return false;
	}

	/**
	 * Make the heap (logically) empty.
	 */
	@Override
	public void clear() {
		// TODO clear the heap from all elements
	}

	/**
	 * Add to the priority queue, maintaining heap order. Duplicates and null
	 * values are allowed. Small values of the argument priority means high
	 * priority, Large values means low priority.
	 * 
	 * @param element
	 *          the item to insert
	 * @param priority
	 *          the priority to be assigned to the item element
	 * @exception QueueFullException
	 *              if the heap is full
	 */
	@Override
	public void add(K element, long priority) throws QueueFullException {
		// TODO add the item element with the priority priority to the heap
	}

	/**
	 * Removes and returns the item with highest priority (smallest priority
	 * value) from the queue, maintaining heap order.
	 * 
	 * @return the item with highest priority (smallest priority value)
	 * @throws QueueEmptyException
	 *           if the queue is empty
	 */
	@Override
	public K removeMin() throws QueueEmptyException {
		// TODO return the element from the heap's root and remove the element from
		// the heap
		return null;
	}

	/**
	 * Internal method to let an element sift down in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftDown(int start) {
		// TODO implement sift down for element at start
	}

	/**
	 * Internal method to let an element sift up in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftUp(int start) {
		// TODO implement sift up for element at start
	}

	/**
	 * Erzeugt ein neues long[] Array und kopiert die Werte der Prioritäten aus
	 * dem Heaparray dort hinein. Die Grösse des zurückgegebenen Arrays soll der
	 * Anzahl Elemente in der Queue entsprechen (= size()). An der Position 0 soll
	 * die kleinste Priorität (= Priorität des Wurzelelementes) stehen.
	 * 
	 * @return Array mit allen Prioritäten
	 */
	@Override
	public long[] toLongArray() {
		// TODO return array with all the priorities currently in the heap. Use
		// order of storage. Put root element at position 0.
		return null;
	}

	private static class HeapNode<K> {
		private final K element;
		private final long priority;

		HeapNode(K element, long priority) {
			this.element = element;
			this.priority = priority;
		}
	}
}
