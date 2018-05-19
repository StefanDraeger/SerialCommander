package de.draegerit.serialcommander.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import de.draegerit.serialcommander.util.EAppIcons;
import de.draegerit.serialcommander.util.ResourceUtil;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuItem beendenMItem;
	private JMenuItem neueVerbindungMItem;
	
	private JMenu fenster;
	private JMenu verbindung;
	private JMenu ansicht;
	private JMenuItem aktivesFensterSchliessenMItem;
	
	private JTextField commandTextField;
	private JButton sendCommandButton;
	private JTextArea konsoleTextArea;
	private JPanel commandPanel;
	
	private JButton exitButton;
	private JButton newButton;
	private JButton removeButton;
	private JButton commandButton;
	
	private JTabbedPane tabPane;
		
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

	private JTabbedPane createKonsole() {
		tabPane = new JTabbedPane();
				
		tabPane.addTab("Test123  ",ResourceUtil.getIcon(EAppIcons.TAB), createTerminal(),"Tooltip");
		tabPane.addTab("Test123  ",ResourceUtil.getIcon(EAppIcons.TAB), createTerminal(),"Tooltip");
		
		return tabPane;
	}

	private JPanel createTerminal() {
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
		toolBar.setFloatable(false);
		
		exitButton = generateToolBarButton("Beenden", EAppIcons.EXIT);
		toolBar.add(exitButton);
		
		toolBar.addSeparator();
		
		newButton = generateToolBarButton("Neu", EAppIcons.NEU);
		toolBar.add(newButton);
		
		removeButton = generateToolBarButton("Schlieﬂen", EAppIcons.REMOVE);
		toolBar.add(removeButton);
		
		
//		commandButton = new JButton(ResourceUtil.getIcon(EAppIcons.COMMANDLINE));
//		commandButton.setText("Commandozeile");
//		commandButton.setVerticalTextPosition(SwingConstants.BOTTOM);
//		commandButton.setHorizontalTextPosition(SwingConstants.CENTER);
//		toolBar.add(commandButton);

		return toolBar;
	}
	
	private JButton generateToolBarButton(String text, EAppIcons icon) {
		JButton btn = new JButton(ResourceUtil.getIcon(icon));
		btn.setText(text);
		btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);	
		btn.setPreferredSize(new Dimension(75,60));
		return btn;
	}

	private JMenuBar generateMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		menu = new JMenu("Datei");
		beendenMItem = new JMenuItem("Beenden");
		menu.add(beendenMItem);
		menuBar.add(menu);
		
		fenster = new JMenu("Fenster");
		aktivesFensterSchliessenMItem = new JMenuItem("aktives Fenster schlieﬂen");
		fenster.add(aktivesFensterSchliessenMItem);
		menuBar.add(fenster);
		
		verbindung = new JMenu("Verbindung");
		neueVerbindungMItem = new JMenuItem("Neu...");
		verbindung.add(neueVerbindungMItem);
		menuBar.add(verbindung);
		
		ansicht = new JMenu("Ansicht");
		menuBar.add(ansicht);
		
		return menuBar;
	}

	public JMenuItem getBeendenMItem() {
		return beendenMItem;
	}

	public JTextArea getKonsoleTextArea() {
		return konsoleTextArea;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JMenuItem getAktivesFensterSchliessenMItem() {
		return aktivesFensterSchliessenMItem;
	}

	public JTabbedPane getTabPane() {
		return tabPane;
	}

	public JMenu getFenster() {
		return fenster;
	}

	public JButton getCommandButton() {
		return commandButton;
	}
	
	public JButton getNewButton() {
		return newButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

}
