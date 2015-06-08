package ch.fhnw.algd2.graphedit;

import java.util.*;

public final class AdjListGraph<K> extends AbstractGraph<K> {
	private static class Vertex<K> {
		K data;
		List<Vertex<K>> adjList = new LinkedList<Vertex<K>>();

		Vertex(K vertex) {
			data = vertex;
		}

		boolean addEdgeTo(Vertex<K> to) {
			return (adjList.contains(to)) ? false : adjList.add(to);
		}
	}
	private Map<K, Vertex<K>> vertices;

	public AdjListGraph() { // default constructor
		this(false);
	}

	public AdjListGraph(boolean directed) {
		super(directed);
		vertices = new HashMap<K, Vertex<K>>();
	}

	public AdjListGraph(AdjListGraph<K> orig) { // copy
																															// constructor
		// TODO Loeschen Sie folgende Zeile und programmieren Sie
		// einen Konstruktor, der eine Kopie von orig erstellt.
		this(false);
	}

	@Override
	public boolean addVertex(K vertex) {
		if (vertex != null && !vertices.containsKey(vertex)) {
			// TODO Einfuegen des neuen Knotens in HashMap
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addEdge(K from, K to) {
		Vertex<K> vf = vertices.get(from);
		Vertex<K> vt = vertices.get(to);
		if (vf != null && vt != null && !vf.adjList.contains(vt)) {
			// TODO Kante einfuegen, es muss dabei unterschieden werden, ob der
			// Graph gerichtet ist oder nicht.
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeEdge(K from, K to) {
		Vertex<K> vf = vertices.get(from);
		Vertex<K> vt = vertices.get(to);
		if (vf != null && vt != null && vf.adjList.contains(vt)) {
			// TODO Kante loeschen, es muss dabei unterschieden werden, ob der
			// Graph gerichtet ist oder nicht.
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getNofVertices() {
		// TODO Ersetzen Sie die folgende Zeile durch eine effizientere
		// Implementation
		return super.getNofVertices();
	}

	@Override
	public int getNofEdges() {
		// TODO Ersetzen Sie die folgende Zeile durch eine effizientere
		// Implementation
		return super.getNofEdges();
	}

	@Override
	public Set<K> getVertices() {
		return new HashSet<K>(vertices.keySet());
	}

	@Override
	public Set<K> getAdjacentVertices(K vertex) {
		Set<K> set = new HashSet<K>();
		// TODO Alle data-Objekte, die in den benachbarten
		// Knoten gespeichert sind, in set einfügen
		return set;
	}

	@Override
	public Object clone() {
		return new AdjListGraph<K>(this);
	}
}
