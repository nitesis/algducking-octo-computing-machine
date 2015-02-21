package ch.fhnw.algd1.converters.base;
import javax.swing.text.Document;

/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public interface Updater<E> {
	Document getDocument();

	void update(E value);
}
