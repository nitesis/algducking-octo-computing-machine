package ch.fhnw.algd1.converters.binary;

import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.fhnw.algd1.converters.base.Updater;

/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public class BinUpdater implements Updater<Integer> {
	private final Document doc;
	private final int nofBits, min, max;

	public BinUpdater(Document doc, int nofBits) {
		this.doc = doc;
		this.nofBits = nofBits;
		min = Integer.MAX_VALUE << (nofBits - 1);
		max = ~min;
	}

	public Document getDocument() {
		return doc;
	}

	public void update(Integer val) {
		try {
			if (val >= min && val <= max) {
				String s = BinConverter.toString(val);
				if (s.length() != nofBits) throw new Exception(
						"Result of BinConverter.toString must have length " + nofBits
								+ " but has " + s.length());
				((PlainDocument)doc).replace(0, doc.getLength(), s, doc
						.getDefaultRootElement().getAttributes());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
