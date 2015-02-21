package ch.fhnw.algd1.converters.character;

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
public class CharUpdater implements Updater<Integer> {
	private final Document doc;

	public CharUpdater(Document doc) {
		this.doc = doc;
	}

	public Document getDocument() {
		return doc;
	}

	public void update(Integer val) {
		if (val > 0x10ffff || val == 0xffff) val = 0;
		try {
			((PlainDocument)doc).replace(0, doc.getLength(), CharConverter
					.toString(val), doc.getDefaultRootElement().getAttributes());
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
