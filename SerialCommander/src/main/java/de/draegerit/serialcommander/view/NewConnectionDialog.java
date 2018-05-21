package de.draegerit.serialcommander.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.draegerit.serialcommander.XMouseListener;
import de.draegerit.serialcommander.util.EAppIcons;
import de.draegerit.serialcommander.util.ResourceUtil;
import de.draegerit.serialcommander.view.ConnectionItem.ConnectionItemType;

public class NewConnectionDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 4335795621883314942L;

	private static final String OK_BUTTON = "OK_BUTTON";
	private static final String ABORT_BUTTON = "ABORT_BUTTON";

	private ConnectionSettings settings = new ConnectionSettings();

	private JPanel contentPanel;
	private JList<ConnectionItem> listView;

	private Integer[] baudrate = { 300, 600, 1200, 1800, 2400, 3600, 4800, 7200, 9600, 14400, 19200, 28800, 38400,
			57600, 115200, 230400 };
	private Integer[] dataBits = { 5, 6, 7, 8 };
	private String[] parity = { "none", "odd", "even" };
	private Float[] stopBits = { 1f, 1.5f, 2f };

	private String[] datumFormat = { "MM.DD.YYYY HH:mm:SS", "HH:mm:SS", "YYYY/MM/DD HH:mm:SS" };

	private View view;

	public NewConnectionDialog(View view) {
		super();
		this.view = view;
		initForm();
	}

	private void initForm() {
		this.setTitle("Neue Verbindung...");
		this.setBounds(0, 0, 750, 450);
		this.setLocationRelativeTo(view);
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.add(createContent(), BorderLayout.CENTER);
		this.add(createBottomPane(), BorderLayout.SOUTH);
		listView.setSelectedIndex(1);
		this.setVisible(true);
	}

	private JPanel createBottomPane() {
		JButton okBtn = new JButton("OK");
		okBtn.setIcon(ResourceUtil.getIcon(EAppIcons.OK));
		okBtn.addActionListener(this);
		okBtn.setActionCommand(OK_BUTTON);

		JButton abortBtn = new JButton("Abbrechen");
		abortBtn.setIcon(ResourceUtil.getIcon(EAppIcons.ABORT));
		abortBtn.addActionListener(this);
		abortBtn.setActionCommand(ABORT_BUTTON);

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(okBtn);
		panel.add(abortBtn);
		return panel;
	}

	private JSplitPane createContent() {
		contentPanel = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createMenuList(),
				new JScrollPane(contentPanel));
		splitPane.setDividerLocation(190);
		return splitPane;
	}

	private JList<ConnectionItem> createMenuList() {
		ConnectionItem[] data = {
				new ConnectionItem("Allgemeine Einstellungen ", EAppIcons.COMMANDLINE, ConnectionItemType.GENERAL),
				new ConnectionItem("serieller Port ", EAppIcons.PLUG, ConnectionItemType.SERIAL),
				new ConnectionItem("Logger ", EAppIcons.LOGFILE, ConnectionItemType.LOGFILE) };

		listView = new JList<ConnectionItem>(data);
		listView.setCellRenderer(new ListCellRenderer<ConnectionItem>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends ConnectionItem> list, ConnectionItem value,
					int index, boolean isSelected, boolean cellHasFocus) {
				JLabel lbl = new JLabel();
				lbl.setText(value.getText());
				lbl.setIcon(ResourceUtil.getIcon(value.getIcon()));
				lbl.setOpaque(true);
				lbl.setBackground(cellHasFocus ? new Color(224, 224, 255) : Color.WHITE);
				return lbl;
			}
		});
		listView.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					ConnectionItem selectedValue = listView.getSelectedValue();
					if (selectedValue != null) {
						contentPanel.removeAll();
						contentPanel.repaint();
						switch (selectedValue.getConnectionItemType()) {
						case GENERAL:
							showGeneralSettings();
							break;
						case SERIAL:
							showSerialSettings();
							break;
						case LOGFILE:
							showLogfile();
							break;
						default:
							break;

						}
						contentPanel.updateUI();
					}
				}
			}

			private void showSerialSettings() {
				JPanel pnl = new JPanel(new GridLayout(0, 3));

				pnl.add(createLabel("Port:"));
				JComboBox<String> portCombobox = new JComboBox<String>();
				portCombobox.setSelectedItem(settings.getPort());
				portCombobox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						String port = (String) e.getItem();
						settings.setPort(port);
					}
				});
				pnl.add(portCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_PORT));

				pnl.add(new JLabel("Baudrate:"));
				JComboBox<Integer> baudrateCombobox = new JComboBox<Integer>(baudrate);
				baudrateCombobox.setSelectedItem(settings.getBaudrate());
				baudrateCombobox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						int baudrate = (int) e.getItem();
						settings.setBaudrate(baudrate);
					}
				});
				pnl.add(baudrateCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_BAUDRATE));

				pnl.add(new JLabel("Data Bits:"));
				JComboBox<Integer> dataBitsCombobox = new JComboBox<Integer>(dataBits);
				dataBitsCombobox.setSelectedItem(settings.getDataBits());
				dataBitsCombobox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						int dataBits = (int) e.getItem();
						settings.setDataBits(dataBits);
					}
				});
				pnl.add(dataBitsCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_DATABITS));

				pnl.add(new JLabel("Parity:"));
				JComboBox<String> parityCombobox = new JComboBox<String>(parity);
				parityCombobox.setSelectedItem(settings.getParity());
				parityCombobox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						String parity = (String) e.getItem();
						settings.setParity(parity);
					}
				});
				pnl.add(parityCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_PARITY));

				pnl.add(new JLabel("Stop Bits:"));
				JComboBox<Float> stopBitsCombobox = new JComboBox<Float>(stopBits);
				stopBitsCombobox.setSelectedItem(settings.getStopBits());
				stopBitsCombobox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						float stopBits = (float) e.getItem();
						settings.setStopBits(stopBits);
					}
				});
				pnl.add(stopBitsCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_STOPBITS));

				contentPanel.add(pnl);
			}

			private void showGeneralSettings() {
				JPanel pnl = new JPanel(new GridLayout(0, 3));
				pnl.add(createLabel("Tab Titel:"));
				final JTextField tabTextField = new JTextField(settings.getTabText());
				tabTextField.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void keyReleased(KeyEvent e) {
						settings.setTabText(tabTextField.getText());
					}

					@Override
					public void keyPressed(KeyEvent e) {
					}
				});
				pnl.add(tabTextField);
				pnl.add(createHelpImage(EHelp.TAB_TITEL));
				contentPanel.add(pnl);
			}

			protected void showLogfile() {
				JPanel pnl = new JPanel(new GridLayout(0, 3));
				pnl.add(createLabel("loggen aktivieren"));
				JCheckBox logginAktivierenCheckBox = new JCheckBox(" ");
				pnl.add(logginAktivierenCheckBox);
				pnl.add(createHelpImage(EHelp.LOGFILE_AKTIVE));

				pnl.add(createLabel("Datumsformat:"));
				final JLabel filenameLbl = new JLabel(generateDateiname());
				JComboBox<String> datumsformatCombobox = new JComboBox<String>(datumFormat);
				datumsformatCombobox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						String datumFormat = (String) e.getItem();
						settings.setDatumFormat(datumFormat);
						filenameLbl.setText(generateDateiname());
					}
				});
				pnl.add(datumsformatCombobox);
				pnl.add(createHelpImage(EHelp.LOGFILE_NAME));

				pnl.add(createLabel("Dateiname:"));
				
				filenameLbl.setFont(new Font("Arial", Font.PLAIN, 10));
				pnl.add(filenameLbl);
				pnl.add(createHelpImage(EHelp.LOGFILE_FILENAME));

				contentPanel.add(pnl);
			}

			private String generateDateiname() {
				return String.format("%s_%s", settings.getDatumFormat(), settings.getTabText());
			}

			private JLabel createHelpImage(final EHelp setting) {
				JLabel lbl = new JLabel();
				lbl.setIcon(ResourceUtil.getIcon(EAppIcons.HELP));
				lbl.addMouseListener(new XMouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						view.showHelp(setting, (JComponent) e.getSource());
					}

				});
				return lbl;
			}

			private JLabel createLabel(String text) {
				JLabel lbl = new JLabel(text);
				lbl.setPreferredSize(new Dimension(80, 20));
				return lbl;
			}

		});

		return listView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case OK_BUTTON:
			saveAndDispose();
			break;
		case ABORT_BUTTON:
			this.dispose();
			break;
		}

	}

	private void saveAndDispose() {
		if (settingsValid()) {
			this.view.createNewConnectionTab(this.settings);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(view, "Bitte prüfe deine Einstellungen!","Fehler...", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean settingsValid() {
		return settings.getPort() != null;
	}

}
