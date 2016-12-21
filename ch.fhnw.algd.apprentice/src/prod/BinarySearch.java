package prod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class BinarySearch {

	public static void main(String[] args) {
		
		List<Integer> numbersUnsorted = new ArrayList<Integer>(); 
		numbersUnsorted.add(1);
		numbersUnsorted.add(0);
		numbersUnsorted.add(8);
		numbersUnsorted.add(5);
		numbersUnsorted.add(2);
		
		//int quantity = ((Object) numbers).getQuantity();
		int i = 0;
		for (Integer number: numbersUnsorted) {
			System.out.println("Number" + ++i + ": " + number);
		}
			
		int [] numbers = new int [10]; 
		
		for (int j = 1; j < 10; j++) {
			numbers[j] = j;
		}
		
		System.out.println(binarySearch (5, numbers));
		}
	
	
	public static int binarySearch (int suchSchluessel, int [] numbers) {
		
		
		int links = -1;
		int rechts = numbers.length;
		while (links + 1 != rechts) {
			int mitte = (links + rechts) / 2;
			if (numbers[mitte] < suchSchluessel) {
				links = mitte;
			}
			else if (numbers[mitte] > suchSchluessel)
			{
				rechts = mitte;
			}
			else {
				return mitte;
			}
		}
		return -1;
	}
//	class QuickSort<E extends Comparable<E>> {
//		private void sort(E[] sammlung, int links, int rechts) {
//			int auf = links; //linke Grenze
//			int ab = rechts; //rechte Grenze
//			final E ausgewaehlt = sammlung[(links + rechts) / 2]; //ausgewaehltes Element
//			
//			do {
//				//so lange sammlung[auf] < ausgewählt
//				while (sammlung[auf].compareTo(ausgewaehlt) < 0) {
//					auf ++; //suchen groesseres Element von links an
//				}
//				//so lange ausgewählt < sammlung[ab]
//				while (ausgewaehlt.compareTo(sammlung[ab]) < 0) {
//					ab --; //suchen kleineres Element von rechts an
//				}
//				if (auf <= ab) {
//					//austauschen von auf und ab
//					final E temp = sammlung[auf];
//					sammlung[auf] = sammlung[ab];
//					sammlung[ab] = temp;
//					//linke und rechte Grenze verschieben
//					auf ++;
//					ab --;
//				}
//			} while (auf <= ab); // Ueberschneidung
//			if (links <= ab) {
//				sort(sammlung, links, ab); //linke Hälfte sortieren
//			} 
//			if (auf < rechts) {
//				sort(sammlung, rechts, auf); //rechte Hälfte sortieren
//			}
//		}
//	}//end of class QuickSort
//	
//	public void sort(E[] sammlung) {
//		sort(sammlung, 0, sammlung.length-1); //ganze Reihe sortieren
//	}
	
}
