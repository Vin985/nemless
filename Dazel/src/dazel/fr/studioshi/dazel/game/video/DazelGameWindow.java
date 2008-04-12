package fr.studioshi.dazel.game.video;

import fr.studioshi.commons.game.keylistener.KeyHandler;
import fr.studioshi.commons.video.GameWindow;
import fr.studioshi.dazel.game.util.ConfKeys;
import fr.studioshi.dazel.game.util.ConfigurationLoader;
import fr.studioshi.dazel.game.util.DazelConstants;

public class DazelGameWindow extends GameWindow {

	private static final long serialVersionUID = 2453501823143717959L;

	public DazelGameWindow(KeyHandler keyListener) {
		super(keyListener, ConfigurationLoader
				.getProperty(ConfKeys.DAZEL_VERSION),
				DazelConstants.WINDOW_WIDTH, DazelConstants.WINDOW_HEIGHT);

	}
}
