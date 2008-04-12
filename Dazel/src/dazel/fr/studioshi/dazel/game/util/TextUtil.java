package fr.studioshi.dazel.game.util;

import java.util.ResourceBundle;

public class TextUtil {
	private static ResourceBundle rb = ResourceBundle
	.getBundle(DazelConstants.DAZEL_TEXT_FILE);
	
	public static String getProperty(String key) {
		return rb.getString(key);
	}
}
