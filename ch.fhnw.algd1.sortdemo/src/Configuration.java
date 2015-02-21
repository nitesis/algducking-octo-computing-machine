import ch.fhnw.algd.sortdemo.directories.SortAlgDirectory;
import ch.fhnw.algd.sortdemo.directories.SortViewDirectory;

/*
 * Created on 21.03.2014
 */
/**
 * @author Wolfgang Weck
 */
public final class Configuration {
	public static void populateAlgDirectory() {
		SortAlgDirectory
				.add("InsertionSort", "ch.fhnw.algd.sortalgs.InsertionSort");
		SortAlgDirectory
				.add("SelectionSort", "ch.fhnw.algd.sortalgs.SelectionSort");
		SortAlgDirectory.add("BubbleSort", "ch.fhnw.algd.sortalgs.BubbleSort");
//		SortAlgDirectory.add("QuickSort", "ch.fhnw.algd.sortalgs.QuickSort");
//		SortAlgDirectory.add("HeapSort", "ch.fhnw.algd.sortalgs.HeapSort");
		SortAlgDirectory.add("InsertionSort Demo",
				"ch.fhnw.algd.sortdemo.algorithms.InsertionSort");
		SortAlgDirectory.add("SelectionSort Demo",
				"ch.fhnw.algd.sortdemo.algorithms.SelectionSort");
		SortAlgDirectory.add("BubbleSort Demo",
				"ch.fhnw.algd.sortdemo.algorithms.BubbleSort");
//		SortAlgDirectory.add("QuickSort Demo",
//				"ch.fhnw.algd.sortdemo.algorithms.QuickSort");
//		SortAlgDirectory.add("HeapSort Demo",
//				"ch.fhnw.algd.sortdemo.algorithms.HeapSort");
//		SortAlgDirectory.add("SmoothSort Demo",
//				"ch.fhnw.algd.sortdemo.algorithms.SmoothSort");
//		SortAlgDirectory.add("InsertionSort2", "ch.fhnw.algd.sortalgs.InsertionSort2");
	}

	public static void populateViewDirectory() {
		SortViewDirectory.add("Graphical Sort View",
				"ch.fhnw.algd.sortdemo.simpleview.View");
		SortViewDirectory.add("Numerical Sort View",
				"ch.fhnw.algd.sortdemo.awt.numericalview.View");
		// SortViewDirectory.add("Timing Sort View",
		// "ch.fhnw.algd.sortdemo.awt.timerview.View");
	}
}
