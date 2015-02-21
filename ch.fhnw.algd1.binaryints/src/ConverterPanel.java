import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import ch.fhnw.algd1.converters.base.ConverterModel;
import ch.fhnw.algd1.converters.binary.BinDocFilter;
import ch.fhnw.algd1.converters.binary.BinDocListener;
import ch.fhnw.algd1.converters.binary.BinUpdater;
import ch.fhnw.algd1.converters.decimal.DecDocFilter;
import ch.fhnw.algd1.converters.decimal.DecDocListener;
import ch.fhnw.algd1.converters.decimal.DecUpdater;

/*
 * Created on 04.09.2014
 */
/**
 * @author Wolfgang Weck
 */
@SuppressWarnings("serial")
public class ConverterPanel extends Panel {
	private static final int nofBits = 8;
	private static final Font fnt = new Font("", Font.PLAIN, 20);
	private final JTextField dec, bin;
	private final ConverterModel<Integer> model = new ConverterModel<>();

	ConverterPanel() {
		setLayout(new GridLayout(2, 2, 10, 10));
		final int min = Integer.MAX_VALUE << (nofBits - 1), max = ~min;
		dec = newTextField(new DecDocFilter(min, max), new DecDocListener(model),
				"0");
		model.add(new DecUpdater(dec.getDocument()));
		add(newLabel("Decimal"));
		add(dec);
		bin = newTextField(new BinDocFilter(nofBits), new BinDocListener(model),
				zeroPattern(nofBits));
		model.add(new BinUpdater(bin.getDocument(), nofBits));
		add(newLabel("Binary"));
		add(bin);
	}

	private String zeroPattern(int n) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < n; i++) {
			s.append("0");
		}
		return s.toString();
	}

	private JTextField newTextField(DocumentFilter filter,
			DocumentListener listener, String initVal) {
		final JTextField f = new JTextField(initVal);
		f.setFont(fnt);
		f.setFocusable(true);
		f.setHorizontalAlignment(SwingConstants.RIGHT);
		f.setDoubleBuffered(true);
		f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == (char)23) System.exit(0);
			}
		});
		final PlainDocument doc = (PlainDocument)f.getDocument();
		doc.setDocumentFilter(filter);
		doc.addDocumentListener(listener);
		return f;
	}

	private Label newLabel(String text) {
		final Label l = new Label(text);
		l.setFont(fnt);
		return l;
	}
}
