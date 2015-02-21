package ch.fhnw.algd1.search.sentinelsearch;

public class SentinelSearch {
	public static boolean exists(int[] data, int value) {
		
		int i = 0;
		int temp = data[data.length-1];
		data[data.length-1] = value; 
		
		while(data[i]!=value && temp!=value){
			i++;		
		}
		data[data.length-1] = temp;
		return (data[i]==value || (temp==value));
		
		
	}

	public static int firstIndex(int[] data, int value) {
		// TODO: Find first occurrence on an element in data (advanced challenge)
		return -1;
	}
}