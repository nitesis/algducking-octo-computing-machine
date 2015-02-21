/*
 * Created on Dec 5, 2013
 */
package ch.fhnw.algd1.sudoku;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Wolfgang Weck
 */
public final class SudokuGUI {
	private final SudokuObservableModel model;
	private final SudokuChecker checker;
	private final SudokuSolver solver;
	private final SudokuPanel panel;
	private final SudokuDelayer delayer;
	private final TextField statusText;
	private final List<Component> components = new LinkedList<Component>();
	private boolean running;

	public SudokuGUI(final SudokuObservableModel model,
			final SudokuChecker checker, final SudokuSolver solver,
			final SudokuSample sample) {
		this.model = model;
		this.checker = checker;
		delayer = new SudokuDelayer();
		this.solver = solver;
		Frame f = new Frame();
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setLayout(new BorderLayout());
		panel = new SudokuPanel(model);
		model.addListener(delayer);
		components.add(panel);
		f.add(panel, BorderLayout.CENTER);
		Panel p0 = new Panel();
		f.add(p0, BorderLayout.SOUTH);
		p0.setLayout(new GridLayout(3, 2));
		final Font fnt = new Font("", 0, 16);
		Button b = new Button("Solve");
		b.setFont(fnt);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startThread(new SolvingThread());
			}
		});
		p0.add(b);
		components.add(b);
		b = new Button("Count Solutions");
		b.setFont(fnt);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startThread(new CountingThread());
			}
		});
		p0.add(b);
		components.add(b);
		b = new Button("Clear");
		b.setFont(fnt);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearStatus();
				final int size = model.size();
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						model.clear(i, j);
					}
				}
			}
		});
		p0.add(b);
		components.add(b);
		b = new Button("Set Sample");
		b.setFont(fnt);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearStatus();
				sample.set(model);
			}
		});
		p0.add(b);
		components.add(b);
		final int min = 0, max = 23, vis = 1, init = max - vis;
		final Scrollbar speed = new Scrollbar(Scrollbar.HORIZONTAL, init, vis, min,
				max);
		delayer.setDelay(max - init - vis);
		speed.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				final int val = speed.getMaximum() - e.getValue()
						- speed.getVisibleAmount();
				delayer.setDelay(val);
				if (running) panel.ignoreUpdates(val == 0);
			}
		});
		p0.add(speed);
		statusText = new TextField();
		statusText.setFont(fnt);
		statusText.setEditable(false);
		components.add(statusText);
		p0.add(statusText);
		f.pack();
		setStatus("© Wolfgang Weck, 2013");
		f.setVisible(true);
	}

	private void setStatus(String msg) {
		statusText.setText(msg);
		model.addListener(new ClearStatusListener());
	}

	private void clearStatus() {
		statusText.setText("");
	}

	private void enableAll(boolean enable) {
		for (Component c : components) {
			c.setEnabled(enable);
		}
	}

	private void startThread(Thread t) {
		running = true;
		if (delayer.getDelay() == 0) {
			panel.ignoreUpdates(true);
		} else {
			delayer.setActive(true);
		}
		enableAll(false);
		clearStatus();
		t.start();
	}

	private void threadDone(String msg) {
		setStatus(msg);
		panel.ignoreUpdates(false);
		delayer.setActive(false);
		panel.updateAllFields();
		enableAll(true);
		running = false;
	}

	private class ClearStatusListener implements SudokuListener {
		public void updated(int i, int j, int to) {
			clearStatus();
			model.removeListener(this);
		}
	}

	private class SolvingThread extends Thread {
		@Override
		public void run() {
			boolean found = false;
			try {
				if (checker.allOK(model)) {
					found = solver.solved(model);
				}
			}
			finally {
				threadDone(found ? "solved" : "not solved");
			}
		}
	}

	private class CountingThread extends Thread {
		@Override
		public void run() {
			int n = 0;
			try {
				if (checker.allOK(model)) {
					n = solver.nofSolutions(model);
				}
			}
			finally {
				threadDone((n >= SudokuSolver.MAX ? ">=" : "") + n + " solutions found");
			}
		}
	}
}
