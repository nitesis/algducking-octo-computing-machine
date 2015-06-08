package ch.fhnw.algd2.graphedit;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WeightedGraphImpl<K> implements WeightedGraph<K>,
		Cloneable {
	protected Graph<K> graph;
	private Map<EdgeKey<K>, Double> weights;

	static class EdgeKey<K> {
		K from, to;

		EdgeKey(K from, K to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public boolean equals(Object x) {
			return x instanceof EdgeKey && ((EdgeKey<?>)x).from.equals(from)
					&& ((EdgeKey<?>)x).to.equals(to);
		}

		@Override
		public int hashCode() {
			return from.hashCode() + to.hashCode();
		}
	}

	public WeightedGraphImpl(Graph<K> graph) {
		this.graph = graph;
		weights = new HashMap<EdgeKey<K>, Double>();
	}

	@SuppressWarnings("unchecked")
	public WeightedGraphImpl(boolean directed) {
		try {
			Constructor<?> c = Class.forName("ch.fhnw.algd2.graphedit.AdjListGraph")
					.getConstructor(new Class[] { Boolean.TYPE });
			graph = (Graph<K>)c.newInstance(new Object[] { new Boolean(directed) });
			weights = new HashMap<WeightedGraphImpl.EdgeKey<K>, Double>();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public final boolean addEdge(K from, K to) {
		return false;
	}

	@Override
	public boolean addEdge(K from, K to, double w) {
		boolean res = graph.addEdge(from, to);
		if (res) {
			weights.put(new EdgeKey<K>(from, to), w);
			if (!isDirected()) {
				weights.put(new EdgeKey<K>(to, from), w);
			}
		}
		return res;
	}

	@Override
	public double getEdgeWeight(K from, K to) {
		Double x = weights.get(new EdgeKey<K>(from, to));
		if (x != null) return x;
		else return 0;
	}

	@Override
	public boolean addVertex(K vertex) {
		return graph.addVertex(vertex);
	}

	@Override
	public boolean removeEdge(K from, K to) {
		weights.remove(new EdgeKey<K>(from, to));
		if (!isDirected()) weights.remove(new EdgeKey<K>(to, from));
		return graph.removeEdge(from, to);
	}

	@Override
	public boolean isDirected() {
		return graph.isDirected();
	}

	@Override
	public int getNofVertices() {
		return graph.getNofVertices();
	}

	@Override
	public int getNofEdges() {
		return graph.getNofEdges();
	}

	@Override
	public Set<K> getVertices() {
		return graph.getVertices();
	}

	@Override
	public Set<K> getAdjacentVertices(K vertex) {
		return graph.getAdjacentVertices(vertex);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		WeightedGraphImpl<K> g = null;
		try {
			g = (WeightedGraphImpl<K>)super.clone();
		}
		catch (CloneNotSupportedException e) {}
		g.graph = (Graph<K>)g.graph.clone();
		g.weights = (Map<EdgeKey<K>, Double>)((HashMap<EdgeKey<K>, Double>)g.weights)
				.clone();
		return g;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("weighted ");
		if (isDirected()) sb.append("di");
		sb.append("graph:\n");
		for (K from : getVertices()) {
			sb.append(from + ":\t");
			Iterator<K> eit = getAdjacentVertices(from).iterator();
			if (eit.hasNext()) {
				sb.append(eit.next());
				while (eit.hasNext()) {
					K to = eit.next();
					sb.append(", " + to);
					sb.append("(" + getEdgeWeight(from, to) + ")");
				}
			}
			sb.append('\n');
		}
		sb.append('\n');
		return sb.toString();
	}
}
