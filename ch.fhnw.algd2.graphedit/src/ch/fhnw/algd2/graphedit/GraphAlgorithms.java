package ch.fhnw.algd2.graphedit;

import java.util.Set;

public interface GraphAlgorithms {

	interface TopSort {
		/**
		 * Topologically sort the vertices of this graph. All output is done on the
		 * console. If this graph is not a DAG (i.e. it has cycles) then a
		 * corresponding message will appear on the console. Otherwise, the console
		 * will display a possible topological order of the vertices.
		 */
		public void sort();
	}

	interface DFS<K> {
		/**
		 * Perform a depth-first-search on this graph, starting at the given vertex.
		 * A DFS order will be printed to the console and a spanning tree
		 * (represented by a newly constructed graph object) will be returned. This
		 * method works on both, directed and undirected graphs!
		 * 
		 * @param startVertex
		 *          begin the depth-first-search here.
		 */
		public Graph<K> traverse(K startVertex);
	}

	interface ShortestPath<K> {
		public Graph<K> getShortestPath(K from, K to);
	}

	interface GarbageCollector<K> {
		public Set<K> GCMark(Set<K> anchors);

		public Graph<K> GCSweep(Set<K> marked);
	}
}
