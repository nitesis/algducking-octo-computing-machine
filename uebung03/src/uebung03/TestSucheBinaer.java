package uebung03;

public class TestSucheBinaer {

	public static void main(String[] args) {
		
		int[] a = new int[10];
		
		for(int i=0; i<a.length; i++){
			a[i]=i;
		}
		for(int x: a){
			System.out.print(x);
		}
		
		System.out.println(binSearch(a, 5));
	}
		
	static int binSearch(int[] data, int value){ 
//		int binSearch = 0;
		int zuKlein = -1, zuGross = data.length;
		
		while ( zuKlein + 1 != zuGross){
			int binSearch = (zuKlein + zuGross)/2;
			if (data[binSearch] < value){
				zuKlein = binSearch;
			}else if(data[binSearch] > value){
				zuGross = binSearch;
			}else{
				return binSearch;
			}
		}
		
				return 100;
	}
	
}