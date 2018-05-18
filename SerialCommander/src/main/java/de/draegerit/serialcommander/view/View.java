package de.draegerit.serialcommander.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuItem beendenMItem;
	private JTextField commandTextField;
	private JButton sendCommandButton;
	private JTextArea konsoleTextArea;
	private JPanel commandPanel;
	

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
		this.add(createKonsole(),BorderLayout.CENTER);
	}

	private JPanel createKonsole() {
		JPanel konsolePanel = new JPanel(new BorderLayout());
		
		konsoleTextArea = new JTextArea();
		konsoleTextArea.setEditable(false);
		konsolePanel.add(konsoleTextArea,BorderLayout.CENTER);
		
		commandPanel = new JPanel(new BorderLayout());
		commandTextField = new JTextField();
		commandPanel.add(commandTextField,BorderLayout.CENTER);
		sendCommandButton = new JButton("Senden");
		sendCommandButton.setPreferredSize(new Dimension(90,20));
		commandPanel.add(sendCommandButton,BorderLayout.EAST);
		konsolePanel.add(commandPanel,BorderLayout.NORTH);
		
		return konsolePanel;
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

	public JTextArea getKonsoleTextArea() {
		return konsoleTextArea;
	}

	public void setKonsoleTextArea(JTextArea konsoleTextArea) {
		this.konsoleTextArea = konsoleTextArea;
	}

}
