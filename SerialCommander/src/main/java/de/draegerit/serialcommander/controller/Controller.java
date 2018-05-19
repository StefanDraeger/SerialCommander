package de.draegerit.serialcommander.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import de.draegerit.serialcommander.view.NewConnectionDialog;
import de.draegerit.serialcommander.view.View;

public class Controller implements ActionListener {

	private static final String BEENDEN_MITEM = "BEENDEN_MITEM";
	private static final String AKIVESFENSTER_MITEM = "AKIVESFENSTER_MITEM";
	private static final String EXIT_BUTTON = "EXIT_BUTTON";
	private static final String COMMAND_BUTTON = "COMMAND_BUTTON";
	private static final String NEU_BUTTON = "NEU_BUTTON";
	private static final String REMOVE_BUTTON = "REMOVE_BUTTON";

	private View view = new View();

	public Controller() {
		super();
		initListeners();
	}

	private void initListeners() {
		this.view.getBeendenMItem().addActionListener(this);
		this.view.getBeendenMItem().setActionCommand(BEENDEN_MITEM);
		this.view.getExitButton().addActionListener(this);
		this.view.getExitButton().setActionCommand(EXIT_BUTTON);
		this.view.getAktivesFensterSchliessenMItem().addActionListener(this);
		this.view.getAktivesFensterSchliessenMItem().setActionCommand(AKIVESFENSTER_MITEM);

		this.view.getNewButton().addActionListener(this);
		this.view.getNewButton().setActionCommand(NEU_BUTTON);

		this.view.getRemoveButton().addActionListener(this);
		this.view.getRemoveButton().setActionCommand(REMOVE_BUTTON);

		this.view.getFenster().addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				disableIfAllTabsClose();

			}

			@Override
			public void menuDeselected(MenuEvent e) {
				System.out.println("menuDeselected");
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				System.out.println("menuCanceled");
			}
		});
	}

	private void disableIfAllTabsClose() {
		boolean allTabsClosed = view.getTabPane().getTabCount() == 0;
		this.view.getAktivesFensterSchliessenMItem().setEnabled(!allTabsClosed);
		this.view.getRemoveButton().setEnabled(!allTabsClosed);
	}

	public void showView() {
		view.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case REMOVE_BUTTON:
		case AKIVESFENSTER_MITEM:
			closeActiveTab();
			break;
		case EXIT_BUTTON:
		case BEENDEN_MITEM:
			System.exit(0);
			break;
		case NEU_BUTTON:
			new NewConnectionDialog(this.view);
			break;
		}

	}

	private void closeActiveTab() {
		int selectedIndex = this.view.getTabPane().getSelectedIndex();
		if (selectedIndex > -1) {
			this.view.getTabPane().remove(selectedIndex);
		}
		disableIfAllTabsClose();
	}

}
