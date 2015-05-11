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
		heap = new HeapNode [capacity];
	}

	/**
	 * Returns the number of elements in this priority queue.
	 * 
	 * @return the number of elements in this priority queue.
	 */
	@Override
	public int size() {
		// TODO return number of elements currently contained in the heap
		return size;
	}

	/**
	 * Check whether the heap is empty.
	 * 
	 * @return true if there are no items in the heap.
	 */
	@Override
	public boolean isEmpty() {
		// TODO return true if no element is in the heap
		return (size == 0);
	}

	/**
	 * Check whether the heap is full.
	 * 
	 * @return true if no further elements can be inserted into the heap.
	 */
	@Override
	public boolean isFull() {
		
		return (size == heap.length);
	}

	/**
	 * Make the heap (logically) empty.
	 */
	@Override
	public void clear() {
		// TODO clear the heap from all elements
		size = 0;
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
		
		heap[size] = new HeapNode<K>(element, priority);
		size++;
		siftUp(size-1);
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
		K min = heap[0].element;
		heap[0] = heap[size-1];
		size--;
		siftDown(0);
		
		return min;
	}

	/**
	 * Internal method to let an element sift down in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftDown(int start) {
		int succ = 2 * start + 1; // linker Nachfolger
		if (succ + 1 < size && heap[succ].priority > heap[succ + 1].priority) {
			succ++; // nimmt das rechte, ist wie (2 * start + 2)
			while (succ < size && heap[start].priority > heap[succ].priority) {
				HeapNode<K> temp = heap[start];
				heap[start] = heap[succ];
				heap[succ] = temp;
				start = succ;
				// ...und jetzt wird noch die Invariante etabliert
				succ = 2 * start + 1;
				if (succ + 1 < size && heap[succ].priority > heap[succ + 1].priority) {
					succ++;
				}
			}
		}
		
	}

	/**
	 * Internal method to let an element sift up in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftUp(int start) {
		// TODO implement sift up for element at start
		int pred = (start - 1)/2;
		while (start != 0 && heap[pred].priority > heap[start].priority) {
			HeapNode<K> temp = heap[start];
			heap[start] = heap[pred];
			heap[pred] = temp;
		}
	}

	/**
	 * Erzeugt ein neues long[] Array und kopiert die Werte der Priorit�ten aus
	 * dem Heaparray dort hinein. Die Gr�sse des zur�ckgegebenen Arrays soll der
	 * Anzahl Elemente in der Queue entsprechen (= size()). An der Position 0 soll
	 * die kleinste Priorit�t (= Priorit�t des Wurzelelementes) stehen.
	 * 
	 * @return Array mit allen Priorit�ten
	 */
	@Override
	public long[] toLongArray() {
		// TODO return array with all the priorities currently in the heap. Use
		// order of storage. Put root element at position 0.
		long[] l = new long[size];
		for (int i = 0; i < size; i++){
			l[i] = heap[i].priority;
		}
		return l;
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
