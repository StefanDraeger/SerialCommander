package de.draegerit.serialcommander.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;

import de.draegerit.serialcommander.util.EAppIcons;
import de.draegerit.serialcommander.util.ResourceUtil;

public class NewConnectionDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 4335795621883314942L;

	private static final String OK_BUTTON = "OK_BUTTON";
	private static final String ABORT_BUTTON = "ABORT_BUTTON";

	private View view;

	public NewConnectionDialog(View view) {
		super();
		this.view = view;
		initForm();
	}

	private void initForm() {
		this.setTitle("Neue Verbindung...");
		this.setBounds(0, 0, 600, 450);
		this.setLocationRelativeTo(view);
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.add(createContent(), BorderLayout.CENTER);
		this.add(createBottomPane(), BorderLayout.SOUTH);
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
		JPanel panel = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createMenuList(), panel);
		splitPane.setDividerLocation(150);
		return splitPane;
	}

	private JList<ConnectionItem> createMenuList() {
		ConnectionItem[] data = { new ConnectionItem("serieller Port ", EAppIcons.PLUG) };

		JList<ConnectionItem> listView = new JList<ConnectionItem>(data);
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
		this.dispose();
	}

}
