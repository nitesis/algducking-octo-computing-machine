package ch.fhnw.algd1.search.binsearchfirst;

public class BinSearchFirstDescending {

	public static void main(String[] args) {
		
		int [] a = {15, 14, 13, 13, 13, 8, 5, 3, 0};
		int value = 13;
//		binSearchFirst(a, value);
		System.out.println(binSearchFirst(a, value));
		System.out.println(binSearchLast(a, value));

	}
	
public static int binSearchFirst(int[] data, int value) {
		
		int l = -1, r = data.length;
		
		while (l + 1 != r) {
			int m = (l + r) / 2;
			
			if (data[m] > value) {
				l = m;
			} else {
				r = m;
			}
		}
		return r;
	}



public static int binSearchLast(int[] data, int value) {
	
	int l = -1, r = data.length;
	
	while (l + 1 != r) {
		int m = (l + r) / 2;
		
		if (data[m] < value) {
			r = m;
		} else {
			l = m;
		}
	}
	return l;
}

}
