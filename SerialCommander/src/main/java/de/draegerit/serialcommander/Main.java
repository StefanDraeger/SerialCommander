package de.draegerit.serialcommander;

import de.draegerit.serialcommander.controller.Controller;

public class Main {

	private static Controller controller;
	
	public static void main(String[] args) {
		controller = new Controller();
		controller.showView();
	}

}
