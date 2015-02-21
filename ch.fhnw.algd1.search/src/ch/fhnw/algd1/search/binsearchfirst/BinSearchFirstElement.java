package ch.fhnw.algd1.search.binsearchfirst;

public class BinSearchFirstElement {
	
	public static final int NOT_FOUND = -1;
	public static int binSearch(int[] data, int value) {
		
		int l = -1, r = data.length;
		
		while (l + 1 != r) {
			int m = (l + r) / 2;
			
			if (data[m] < value) {
				l = m;
			} else if (data[m] > value)  {
				r = m;
			} else {
				return m;
			}
		}
		return NOT_FOUND;
	}
}