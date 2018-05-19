package de.draegerit.serialcommander.util;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class ResourceUtil {

	private ResourceUtil() {
		// leer
	}

	public static ImageIcon getImageIcon(EAppIcons appIcon) {
		URL url = getResource("images/" + appIcon.getFilename());
		ImageIcon imageIcon = new ImageIcon(url);
		return imageIcon;
	}
	
	public static Icon getIcon(EAppIcons appIcon) {
		URL url = getResource("images/" + appIcon.getFilename());
		ImageIcon imageIcon = new ImageIcon(url);
		return imageIcon;
	}

	private static URL getResource(String filename) {
		URL url = ResourceUtil.class.getClassLoader().getResource(filename);
		return url;
	}

}
