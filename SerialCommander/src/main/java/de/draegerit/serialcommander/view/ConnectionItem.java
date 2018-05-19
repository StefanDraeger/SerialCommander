package de.draegerit.serialcommander.view;

import de.draegerit.serialcommander.util.EAppIcons;

public class ConnectionItem {

	enum ConnectionItemType {
		SERIAL;
	}

	private String text;

	private EAppIcons icon;

	private ConnectionItemType connectionItemType;

	public ConnectionItem(String text, EAppIcons icon, ConnectionItemType connectionItemType) {
		super();
		this.text = text;
		this.icon = icon;
		this.connectionItemType = connectionItemType;
	}

	public String getText() {
		return text;
	}

	public EAppIcons getIcon() {
		return icon;
	}

	public ConnectionItemType getConnectionItemType() {
		return connectionItemType;
	}

}
