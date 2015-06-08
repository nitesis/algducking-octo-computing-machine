package ch.fhnw.algd2.graphedit;

public interface WeightedGraph<K> extends Graph<K> {

	/**
	 * Adds a new edge from the vertex <tt>from</tt> to the vertex <tt>to</tt> with weight <tt>w</tt>.
	 * <tt>from</tt> and <tt>to</tt> must already be vertices of this graph.
	 * If the graph is undirected, then both <tt>from</tt> is reachable from <tt>to</tt> over 
	 * an edge with weight <tt>w</tt> and <tt>to</tt> is reachable from <tt>from</tt> over
	 * an edge with weight <tt>w</tt>.
	 * There can be no multiple edges between two vertices, i.e. if there exists already an
	 * edge between <tt>from</tt> and <tt>to</tt>, nothing will be done.
	 * @param from add a new edge from the vertex <tt>from</tt>.
	 * @param to   add a new edge to the vertex <tt>to</tt>.
	 * @param weight	weight of the new edge between <tt>from</tt> and <tt>to</tt>.
	 * @return false if <tt>from</tt> or <tt>to</tt> do not exist or 
	 * if there is already an edge from <tt>from</tt> to <tt>to</tt>,
	 * returns true otherwise (edge was successfully added).
	 */
	public boolean addEdge(K from, K to, double w);
	
	/**
	 * Returns the weight of the edge between the vertex <tt>from</tt> and <tt>to</tt>.
	 * If there is no such vertex or <tt>from</tt> or <tt>to</tt> do not
	 * exist 0.0 will be returned. 
	 * @param from start vertex.
	 * @param to   end vertex.
	 * @return weight of the edge between the vertex <tt>from</tt> and <tt>to</tt>.
	 */	
	public double getEdgeWeight(K from, K to);
}
