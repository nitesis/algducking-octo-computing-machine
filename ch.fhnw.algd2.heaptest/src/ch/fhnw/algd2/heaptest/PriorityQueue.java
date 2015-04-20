package ch.fhnw.algd2.heaptest;
public interface PriorityQueue<K> {
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
	void add(K element, long priority) throws QueueFullException;

	/**
	 * Removes and returns the item with highest priority (smallest priority
	 * value) from the queue, maintaining heap order.
	 * 
	 * @return the item with highest priority (smallest priority value)
	 * @throws QueueEmptyException
	 *           if the queue is empty
	 */
	K removeMin() throws QueueEmptyException;

	/**
	 * Returns the number of elements in this priority queue.
	 * 
	 * @return the number of elements in this priority queue.
	 */
	int size();

	/**
	 * Check whether the heap is empty.
	 * 
	 * @return true if there are no items in the heap.
	 */
	boolean isEmpty();

	/**
	 * Check whether the heap is full.
	 * 
	 * @return true if no further elements can be inserted into the heap.
	 */
	boolean isFull();

	/**
	 * Make the heap (logically) empty.
	 */
	void clear();

	/**
	 * Erzeugt ein neues long[] Array und kopiert die Werte der Prioritäten aus
	 * dem Heaparray dort hinein. Die Grösse des zurückgegebenen Arrays soll der
	 * Anzahl Elemente in der Queue entsprechen (= size()). An der Position 0 soll
	 * die kleinste Priorität (= Priorität des Wurzelelementes) stehen.
	 * 
	 * @return Array mit allen Prioritäten
	 */
	long[] toLongArray();
}

class QueueFullException extends Exception {
	QueueFullException() {
		super("Priority queue is full");
	}
}

class QueueEmptyException extends Exception {
	QueueEmptyException() {
		super("Priority queue is empty");
	}
}
