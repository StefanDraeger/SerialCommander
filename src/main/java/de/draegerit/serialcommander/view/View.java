package de.draegerit.serialcommander.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	public View() {
		super();
		initForm();
	}

	private void initForm() {
		this.setTitle("SerialCommander Vers. 0.1 by Draeger-IT.blog");
		this.setBounds(0, 0, 500, 500);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(generateMenuBar());
	}

	private JMenuBar generateMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		return menuBar;
	}

}
