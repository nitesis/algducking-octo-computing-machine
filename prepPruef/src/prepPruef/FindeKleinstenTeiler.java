package prepPruef;
/*
 * Finds smallest common number between 1000 and 2000 with %221 and %17
 */
public class FindeKleinstenTeiler {

	public static void main(String[] args) {
		
		int i = 1000;
//		int teiler = 222;
			
			while (i<2001 && !(i%17==0 && i%221==0)) {
				
				i++;
			}
			if(i<2001){
		System.out.println(i);
			}else{
				System.out.println("nixi");
			}

	}

}
