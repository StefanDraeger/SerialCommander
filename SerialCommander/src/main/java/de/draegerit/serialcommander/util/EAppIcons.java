package de.draegerit.serialcommander.util;

public enum EAppIcons {
	COMMANDLINE("commandline.png"), EXIT("exit.png"), TAB("plug.png"), NEU("new.png"), REMOVE("remove.png"), OK("ok.png"), ABORT("abort.png"),PLUG("plug.png");

	private String filename;

	EAppIcons(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

}
