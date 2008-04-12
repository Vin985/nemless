package fr.studioshi.dazel.game.util;

import java.util.ResourceBundle;

public class ConfigurationLoader {

	private static ResourceBundle rb = ResourceBundle
			.getBundle(DazelConstants.DAZEL_CONF_FILE);

	public static String getProperty(String key) {
		return rb.getString(key);
	}

	public static String getSpritesDir() {
		return rb.getString(ConfKeys.DAZEL_SPRITES_DIR);
	}

}
