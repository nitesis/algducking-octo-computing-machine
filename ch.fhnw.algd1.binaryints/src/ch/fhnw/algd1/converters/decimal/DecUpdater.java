package ch.fhnw.algd1.converters.decimal;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.fhnw.algd1.converters.base.Updater;

/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public class DecUpdater implements Updater<Integer> {
	private final Document doc;

	public DecUpdater(Document doc) {
		this.doc = doc;
	}

	public Document getDocument() {
		return doc;
	}

	public void update(Integer val) {
		try {
			((PlainDocument)doc).replace(0, doc.getLength(), DecConverter
					.toString(val), doc.getDefaultRootElement().getAttributes());
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
