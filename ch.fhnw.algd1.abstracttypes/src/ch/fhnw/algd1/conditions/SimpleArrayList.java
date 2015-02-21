package ch.fhnw.algd1.conditions;

import java.util.AbstractList;
import java.util.List;

public class SimpleArrayList<E> extends AbstractList<E> implements List<E> {
	private int minArrLen = 16;
	private Object[] arr = new Object[minArrLen];
	private int size = 0;

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// while the index is not out of range
		if (!(index < 0 || index >= size())) {
			// Gib den Wert an Position index zurück
			return (E) arr[index];
		} else {
			// sonst wirf eine Exception
			throw new IndexOutOfBoundsException(
					"Index ist ausserhalb des Arrays");
		}
	}

	@Override
	public E set(int index, E element) {
		E old = get(index); // includes index checking
		arr[index] = element;
		return old;
	}

	@Override
	// Verschiebt Element an Stelle index und setzt alle folgenden rechts
	// daneben
	public void add(int index, E element) throws IndexOutOfBoundsException{
		if (!(index < 0 || index > size())) {
			// verschiebt alle Plätze rechts neben index eins weiter nach rechts
			// beginnend von der Stelle am weitesten rechts
			for (int i = size() - 1; i >= index; i--) {
				arr[i + 1] = arr[i];
			}
			// Setzt element an die jetzt frei gewordene Stelle index
			arr[index] = element;
			// Bei jedem add wird die Grösse um 1 erhöht
			size++;
		}else {
			// sonst wirf eine Exception
			throw new IndexOutOfBoundsException(
					"Index ist ausserhalb des Arrays");
		}
		

		// Falls aktuelle Grösse gleich der Arraygrösse
		if (size == arr.length) {
			// erstelle neues Array mit doppelter Länge
			Object[] arrNew = new Object[2 * arr.length];
			// und packe den Inhalt aus altem Array in das neu erzeugte
			for (int i = 0; i < arr.length; i++) {
				arrNew[i] = arr[i];
			}
			// und weise dieses Array der Variablen des alten Arrays zu
			arr = arrNew;
		}
	}

	@Override
	public E remove(int index) {
		E old = get(index); // includes index checking
		while (index + 1 < size) {
			arr[index] = arr[index + 1];
			index++;
		}
		size--;
		// TODO falls gew�nscht, Gr�sse reduzieren
		return old;
	}
}
