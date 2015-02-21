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
import ch.fhnw.algd1.converters.character.CharDocFilter;
import ch.fhnw.algd1.converters.character.CharDocListener;
import ch.fhnw.algd1.converters.character.CharUpdater;
import ch.fhnw.algd1.converters.decimal.DecDocFilter;
import ch.fhnw.algd1.converters.decimal.DecDocListener;
import ch.fhnw.algd1.converters.decimal.DecUpdater;
import ch.fhnw.algd1.converters.hex.HexDocFilter;
import ch.fhnw.algd1.converters.hex.HexDocListener;
import ch.fhnw.algd1.converters.hex.HexUpdater;
import ch.fhnw.algd1.converters.utf8.UTF8DocFilter;
import ch.fhnw.algd1.converters.utf8.UTF8DocListener;
import ch.fhnw.algd1.converters.utf8.UTF8Updater;

/*
 * Created on 04.09.2014
 */
/**
 * @author Wolfgang Weck
 */
@SuppressWarnings("serial")
public class ConverterPanel extends Panel {
	private static final int nofBits = 21;
	private static final Font fnt = new Font("", Font.PLAIN, 20);
	private final JTextField ch, dec, hex, utf8;
	private final ConverterModel<Integer> model = new ConverterModel<>();

	ConverterPanel() {
		setLayout(new GridLayout(4, 2, 10, 10));
		final int min = 0, max = (1 << (nofBits)) - 1;
		ch = newTextField(new CharDocFilter(), new CharDocListener(model), "");
		model.add(new CharUpdater(ch.getDocument()));
		add(newLabel("Character"));
		add(ch);
		dec = newTextField(new DecDocFilter(min, max), new DecDocListener(model),
				"0");
		model.add(new DecUpdater(dec.getDocument()));
		add(newLabel("Decimal"));
		add(dec);
		hex = newTextField(new HexDocFilter(min, max), new HexDocListener(model),
				"0");
		model.add(new HexUpdater(hex.getDocument()));
		add(newLabel("Hexadecimal"));
		add(hex);
		utf8 = newTextField(new UTF8DocFilter(),
				new UTF8DocListener(model), "0");
		model.add(new UTF8Updater(utf8.getDocument()));
		add(newLabel("UTF-8"));
		add(utf8);
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
