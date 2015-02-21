/*
 * Created on 21.03.2014
 */
package ch.fhnw.algd.sortalgs;

import ch.fhnw.algd.sortdemo.framework.SortAlg;
import ch.fhnw.algd.sortdemo.framework.SortData;

public class SelectionSort implements SortAlg {
	public void run(SortData data) {
		int n = data.size();
		for (int i = 0; i < n - 1; i++) {
			int min = i;
		for (int j = i + 1; j < n; j++) {
			if (data.less(j, min)){
				min = j;
			}
			data.swap(min, i);
		}	
		}
	}
}