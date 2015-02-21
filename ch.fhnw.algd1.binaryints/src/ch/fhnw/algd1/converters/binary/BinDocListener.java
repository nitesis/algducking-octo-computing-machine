package ch.fhnw.algd1.converters.binary;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import ch.fhnw.algd1.converters.base.AbstractDocListener;
import ch.fhnw.algd1.converters.base.ConverterModel;

/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public class BinDocListener extends AbstractDocListener<Integer> {
	public BinDocListener(ConverterModel<Integer> model) {
		super(model);
	}

	@Override
	protected void anyUpdate(DocumentEvent e) {
		try {
			final Document doc = e.getDocument();
			if (doc.getLength() > 0) {
				final int x = BinConverter.fromString(doc.getText(0, doc.getLength()));
				updateModel(e, x);
			}
		}
		catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
}
