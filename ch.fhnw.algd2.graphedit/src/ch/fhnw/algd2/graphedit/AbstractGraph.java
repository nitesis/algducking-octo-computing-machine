package ch.fhnw.algd2.graphedit;

import java.util.*;

public abstract class AbstractGraph<K> implements Graph<K> {

	private boolean directed;

	protected AbstractGraph(boolean directed) {
		this.directed = directed;
	}

	@Override
	public final boolean isDirected() {
		return directed;
	}

	@Override
	public int getNofVertices() {
		return getVertices().size();
	}

	@Override
	public int getNofEdges() {
		int c = 0;
		for (K v : getVertices()) {
			c += getAdjacentVertices(v).size();
		}
		if (!isDirected())
			c /= 2;
		return c;
	}

	@Override
	public abstract Object clone();

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("unweighted ");
		if (isDirected())
			sb.append("di");
		sb.append("graph:\n");

		for (K from : getVertices()) {
			sb.append(from + ":\t");
			Iterator<K> eit = getAdjacentVertices(from).iterator();
			if (eit.hasNext()) {
				sb.append(eit.next());
				while (eit.hasNext()) {
					sb.append(", " + eit.next());
				}
			}
			sb.append('\n');
		}
		sb.append('\n');
		return sb.toString();
	}
}
