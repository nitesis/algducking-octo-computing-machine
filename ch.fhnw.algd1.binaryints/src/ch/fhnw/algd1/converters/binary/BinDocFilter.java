package ch.fhnw.algd1.converters.binary;
/*
 * Created on 04.09.2014
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * @author Wolfgang Weck
 */
public class BinDocFilter extends DocumentFilter {
	private final int nofDigits;
	private final String zeros;

	public BinDocFilter() {
		this(0);
	}

	public BinDocFilter(int nofDigits) {
		this.nofDigits = nofDigits;
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < nofDigits; i++) {
			s.append('0');
		}
		zeros = s.toString();
	}

	private boolean isOK(String s) {
		int i = 0, l = s.length();
		while (i < l && isBit(s.charAt(i))) {
			i++;
		}
		return i == l;
	}

	private boolean isBit(char c) {
		return c == '0' || c == '1';
	}

	@Override
	public void remove(FilterBypass fb, int offset, int length)
			throws BadLocationException {
		super.remove(fb, offset, length);
		if (nofDigits > 0) {
			super.insertString(fb, 0, zeros.substring(0, length), fb.getDocument()
					.getDefaultRootElement().getAttributes());
		}
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string,
			AttributeSet attr) throws BadLocationException {
		if (isOK(string)) {
			int l = string.length();
			if (nofDigits > 0) {
				if (offset + l > nofDigits) string = string.substring(0, nofDigits
						- offset);
				super.replace(fb, offset, string.length(), string, attr);
			} else super.insertString(fb, offset, string, attr);
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,
			AttributeSet attrs) throws BadLocationException {
		if (isOK(text)) {
			int l = text.length();
			if (nofDigits > 0) {
				if (offset == nofDigits) {
					if (l > nofDigits) text = text.substring(0, nofDigits);
					super.remove(fb, 0, text.length());
					super.replace(fb, offset - text.length(), 0, text, attrs);
				} else {
					if (offset + l > nofDigits) text = text.substring(0, nofDigits
							- offset);
					super.replace(fb, offset, text.length(), text, attrs);
				}
			} else super.replace(fb, offset, length, text, attrs);
		}
	}
}