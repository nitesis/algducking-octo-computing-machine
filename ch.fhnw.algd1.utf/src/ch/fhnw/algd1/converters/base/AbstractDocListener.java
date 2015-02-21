package ch.fhnw.algd1.converters.base;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * Created on 05.09.2014
 */
/**
 * @author Wolfgang Weck
 */
public abstract class AbstractDocListener<E> implements DocumentListener {
	private final ConverterModel<E> model;

	public AbstractDocListener(ConverterModel<E> model) {
		this.model = model;
	}

	public void insertUpdate(DocumentEvent e) {
		anyUpdate(e);
	}

	public void removeUpdate(DocumentEvent e) {
		anyUpdate(e);
	}

	public void changedUpdate(DocumentEvent e) {
		anyUpdate(e);
	}

	protected void anyUpdate(DocumentEvent e) {}

	protected final void updateModel(DocumentEvent e, E value) {
		model.updateAllExceptSource(e.getDocument(), value);
	}
}
