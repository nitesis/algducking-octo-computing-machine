package ch.fhnw.algd2.graphedit;

import ch.fhnw.algd2.graphedit.GraphPanel.Node;

public class GraphEditor {
	static boolean packFrame = false;

	public static void main(String[] args) {
		GraphEditorFrame frame = new GraphEditorFrame(new Factory());
		//Validate frames that have preset sizes
		//Pack frames that have useful preferred size info, e.g. from their layout
		if (packFrame)
			frame.pack();
		else
			frame.validate();
		frame.setVisible(true);
	}
	
	static class Factory implements GraphEditorFrame.GraphFactory {
		@Override
		public Graph<Node> newGraph(boolean directed) {
			return new AdjListGraph<Node>(directed);
		}
	}
} 