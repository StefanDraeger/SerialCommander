package de.draegerit.serialcommander.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.draegerit.serialcommander.view.View;

public class Controller implements ActionListener {

	private static final String BEENDEN_MITEM = "BEENDEN_MITEM";
	private View view = new View();

	public Controller() {
		super();
		initListeners();
	}

	private void initListeners() {
		this.view.getBeendenMItem().addActionListener(this);
		this.view.getBeendenMItem().setActionCommand(BEENDEN_MITEM);
	}

	public void showView() {
		view.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case BEENDEN_MITEM:
			break;
		}

	}

}
