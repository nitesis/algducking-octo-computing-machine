/*
 * Created on 21.03.2014
 */
package ch.fhnw.algd.sortalgs;

import ch.fhnw.algd.sortdemo.framework.SortAlg;
import ch.fhnw.algd.sortdemo.framework.SortData;

public class BubbleSort implements SortAlg {
	public void run(SortData data) {
		
		int n = data.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = n - 1; j > i; j--) { 
					if (data.less(j, j-1)) {
						data.swap(j, j-1);; 
					}
			} 
		}
	}	
}
