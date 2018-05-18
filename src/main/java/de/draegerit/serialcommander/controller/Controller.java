package de.draegerit.serialcommander.controller;

import de.draegerit.serialcommander.view.View;

public class Controller {

	private View view = new View();
	
	public void showView() {
		view.setVisible(true);
	}

}
