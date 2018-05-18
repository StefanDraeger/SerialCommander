package de.draegerit.serialcommander.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuItem beendenMItem;

	public View() {
		super();
		initForm();
	}

	private void initForm() {
		this.setTitle("SerialCommander Vers. 0.1 by Draeger-IT.blog");
		this.setBounds(0, 0, 500, 500);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(generateMenuBar());
		this.setLayout(new BorderLayout());
		this.add(createToolBar(),BorderLayout.NORTH);
	}

	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		return toolBar;
	}

	private JMenuBar generateMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		menu = new JMenu("Datei");
		setBeendenMItem(new JMenuItem("Beenden"));
		menu.add(getBeendenMItem());
		menuBar.add(menu);
		
		menu = new JMenu("Verbindung");
		menuBar.add(menu);
		
		return menuBar;
	}

	public JMenuItem getBeendenMItem() {
		return beendenMItem;
	}

	public void setBeendenMItem(JMenuItem beendenMItem) {
		this.beendenMItem = beendenMItem;
	}

}
