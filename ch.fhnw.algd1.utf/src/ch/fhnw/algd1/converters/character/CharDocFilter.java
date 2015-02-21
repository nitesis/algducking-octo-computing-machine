package ch.fhnw.algd1.converters.character;

/*
 * Created on 04.09.2014
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * @author Wolfgang Weck
 */
public class CharDocFilter extends DocumentFilter {
	@Override
	public void remove(FilterBypass fb, int offset, int length)
			throws BadLocationException {
		super.remove(fb, offset, length);
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string,
			AttributeSet attr) throws BadLocationException {
		try {
			super.replace(fb, 0, fb.getDocument().getLength(), string, attr);
		}
		catch (Exception e) {}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,
			AttributeSet attrs) throws BadLocationException {
		try {
			super.replace(fb, 0, fb.getDocument().getLength(), text, attrs);
		}
		catch (Exception e) {}
	}
}