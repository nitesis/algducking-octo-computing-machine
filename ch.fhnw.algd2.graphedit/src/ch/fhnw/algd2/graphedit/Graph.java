package ch.fhnw.algd2.graphedit;

import java.util.Set;

public interface Graph<K> {

	/**
	 * Adds a new vertex to this graph. Vertices are unique within a graph.
	 * 
	 * @param vertex
	 *          the new node to be added.
	 * @return whether a new vertex could be added to this graph.
	 */
	public boolean addVertex(K vertex);

	/**
	 * Adds a new edge from the vertex <tt>from</tt> to the vertex <tt>to</tt>.
	 * <tt>from</tt> and <tt>to</tt> must already be vertices of this graph.
	 * If the graph is undirected, then an edge from <tt>to</tt> to
	 * <tt>from</tt> will be inserted as well. There can be no multiple edges
	 * between two vertices, i.e. if there exists already an edge between
	 * <tt>from</tt> and <tt>to</tt>, nothing will be done.
	 * 
	 * @param from
	 *          add a new edge from the vertex <tt>from</tt>.
	 * @param to
	 *          add a new edge to the vertex <tt>to</tt>.
	 * @return false if <tt>from</tt> or <tt>to</tt> do not exist or if there
	 *         is already an edge from <tt>from</tt> to <tt>to</tt>, returns
	 *         true otherwise (edge was successfully added).
	 */
	public boolean addEdge(K from, K to);

	/**
	 * Removes the edge between the vertex <tt>from</tt> and <tt>to</tt>. If
	 * there is no such vertex or <tt>from</tt> or <tt>to</tt> do not exist
	 * nothing will be done. If the graph is undirected, then the edge from
	 * <tt>to</tt> to <tt>from</tt> will be removed as well.
	 * 
	 * @param from
	 *          remove edge from the vertex <tt>from</tt>.
	 * @param to
	 *          remove edge to the vertex <tt>to</tt>.
	 * @return false if <tt>from</tt> or <tt>to</tt> do not exist or if there
	 *         was no edge from <tt>from</tt> to <tt>to</tt>, returns true
	 *         otherwise (edge between <tt>from</tt> and <tt>to</tt> was
	 *         successfully removed).
	 */
	public boolean removeEdge(K from, K to);

	/**
	 * Is this graph directed?
	 * 
	 * @return true if the graph is directed, false otherwise
	 */
	public boolean isDirected();

	/**
	 * Returns the number of vertices of this graph.
	 * 
	 * @return the number of vertices.
	 */
	public int getNofVertices();

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return the number of edges.
	 */
	public int getNofEdges();

	/**
	 * Returns a set of all objects refered to by all vertices in this graph.
	 */
	public Set<K> getVertices();

	/**
	 * Returns a set of all adjacent vertices of <tt>vertex</tt>.
	 * 
	 * @param vertex
	 *          we are interested in the adjacent vertices of <tt>vertex</tt>.
	 * @return a set of all adjacent vertices of <tt>vertex</tt>.
	 */
	public Set<K> getAdjacentVertices(K vertex);

	/**
	 * Creates and returns a copy of this object.
	 * 
	 * @return a clone of this graph.
	 */
	public Object clone();
}
