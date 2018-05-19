package de.draegerit.serialcommander.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
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

	private JPanel contentPanel;
	private JList<ConnectionItem> listView;

	private Integer[] baudrate = { 300, 600, 1200, 1800, 2400, 3600, 4800, 7200, 9600, 14400, 19200, 28800, 38400,
			57600, 115200, 230400 };
	private Integer[] dataBits = { 5, 6, 7, 8 };
	private String[] parity = { "none", "odd", "even" };
	private Float[] stopBits = { 1f, 1.5f, 2f };

	private View view;

	public NewConnectionDialog(View view) {
		super();
		this.view = view;
		initForm();
	}

	private void initForm() {
		this.setTitle("Neue Verbindung...");
		this.setBounds(0, 0, 550, 450);
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
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createMenuList(), contentPanel);
		splitPane.setDividerLocation(150);
		return splitPane;
	}

	private JList<ConnectionItem> createMenuList() {
		ConnectionItem[] data = { new ConnectionItem("serieller Port ", EAppIcons.PLUG, ConnectionItemType.SERIAL) };

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
				ConnectionItem selectedValue = listView.getSelectedValue();
				if (selectedValue != null) {
					contentPanel.removeAll();
					switch (selectedValue.getConnectionItemType()) {
					case SERIAL:
						showSerialSettings();
						break;
					default:
						break;

					}
				}
			}

			private void showSerialSettings() {
				JPanel pnl = new JPanel(new GridLayout(0, 3));

				pnl.add(createLabel("Port:"));
				JComboBox<String> portCombobox = new JComboBox<String>();
				pnl.add(portCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_PORT));

				pnl.add(new JLabel("Baudrate:"));
				JComboBox<Integer> baudrateCombobox = new JComboBox<Integer>(baudrate);
				pnl.add(baudrateCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_BAUDRATE));

				pnl.add(new JLabel("Data Bits:"));
				JComboBox<Integer> dataBitsCombobox = new JComboBox<Integer>(dataBits);
				pnl.add(dataBitsCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_DATABITS));

				pnl.add(new JLabel("Parity:"));
				JComboBox<String> parityCombobox = new JComboBox<String>(parity);
				pnl.add(parityCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_PARITY));

				pnl.add(new JLabel("Stop Bits:"));
				JComboBox<Float> stopBitsCombobox = new JComboBox<Float>(stopBits);
				pnl.add(stopBitsCombobox);
				pnl.add(createHelpImage(EHelp.SETTINGS_STOPBITS));

				contentPanel.add(pnl);
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
				lbl.setPreferredSize(new Dimension(100, 20));
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
		this.view.createNewConnectionTab(readSettings());
		this.dispose();
	}

	private ConnectionSettings readSettings() {
		
		return null;
	}

}
