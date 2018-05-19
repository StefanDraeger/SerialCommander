package de.draegerit.serialcommander.view;

import de.draegerit.serialcommander.util.EAppIcons;

public class ConnectionItem {

	private String text;
	
	private EAppIcons icon;

	public ConnectionItem(String text, EAppIcons icon) {
		super();
		this.text = text;
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public EAppIcons getIcon() {
		return icon;
	}

	public void setIcon(EAppIcons icon) {
		this.icon = icon;
	}
	
}
