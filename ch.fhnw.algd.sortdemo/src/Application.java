import java.awt.Frame;
import java.awt.Panel;

import ch.fhnw.algd.sortdemo.awtframe.AWTFrame;
import ch.fhnw.algd.sortdemo.viewpicker.ViewPicker;

/*
 * Created on 21.03.2014
 */
/**
 * @author Wolfgang Weck
 */
@SuppressWarnings("serial")
public final class Application extends Frame {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration.populateAlgDirectory();
		Configuration.populateViewDirectory();
		Configuration.populateDataPresetterDirectory();
		openAWTGUI();
	}

	private static void openAWTGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ViewPicker.selectViewAndOpen(new ViewPicker.ViewOpener() {
					@Override
					public void open(Panel p) {
						try {
							new AWTFrame(p).setVisible(true);
						}
						catch (Exception e) {
							System.exit(ABORT);
						}
					}
				});
			}
		});
	}
}
