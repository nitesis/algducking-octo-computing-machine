package uebung03;

public class TestSequSuche {

	public static void main(String[] args) {
		
		int[] a = new int[10];
		
		for(int i=0; i<a.length; i++){
			a[i]=(int)(Math.random()*10-5);
		}
		
		for(int x: a){
			System.out.print(x);
			
		}
//		Suchen, ob mind. eine 0 im Array
		int i = 0;
		boolean hasNull;
		while (i < a.length && !(a[i]==0)){
			i++;
			
		}
		
		hasNull=i<a.length;
		System.out.println(" Mindestens eine Null enthalten: " + hasNull);
		
//		Prüfen, ob alle Werte im Array grösser als 0 sind
		int j = 0;
		boolean hasNegativ;
		while (j < a.length && !(a[j]<0)){
			j++;
			
		}
		
		hasNegativ=j<a.length;
		System.out.println("Alle Werte positiv: "+ !(hasNegativ));
		
//		Prüfen, welche die erste 2er-Potenz grösser 123456 ist
		int k = 1;
		boolean isGroesser;
		while (!(Math.pow(2, k) > 123456)){
			k++;
			
		}
		
		isGroesser = Math.pow(2, k) > 123456;
		System.out.println((Math.pow(2, k)) + " ist erster Wert grösser als 123456: "+ !(isGroesser));
		
		
	}

}
