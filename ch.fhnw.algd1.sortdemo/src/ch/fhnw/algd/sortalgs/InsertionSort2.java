package ch.fhnw.algd.sortalgs;

import ch.fhnw.algd.sortdemo.framework.SortAlg;
import ch.fhnw.algd.sortdemo.framework.SortData;



public class InsertionSort2 implements SortAlg {
	public void run(SortData data) {
		int n = data.size();
		for (int i = 1; i < n; i++) {
			int l = -1, r = i; // t = data.size()-1; // t = a[i]
				while (data.less(l, i) && data.less(i, r) ) { // a[l] < a[i] && a[r] >= a[i]
					int m = (l + r) / 2;
						if (data.less(m, i)) { // a[m] < t
							l = m;
						} else { 
							r = m;
						}
				}
				for (int j = i; j > r; j--) { 
					data.swap(j, j-1);// a[j] = a[j - 1];	
					data.swap(r, i);// a[r] = t;
				}
		}
	}
}
