package de.draegerit.serialcommander;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.draegerit.serialcommander.controller.Controller;

public class Main {

	private static Controller controller;
	
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		controller = new Controller();
		controller.showView();
	}

}
