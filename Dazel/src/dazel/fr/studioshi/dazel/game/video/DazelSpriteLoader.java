package fr.studioshi.dazel.game.video;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import fr.studioshi.commons.video.Sprite;
import fr.studioshi.commons.video.SpriteLoader;
import fr.studioshi.dazel.game.util.ConfigurationLoader;
import fr.studioshi.dazel.game.util.DazelConstants;
import fr.studioshi.dazel.game.util.EntityKeys;
import fr.studioshi.dazel.game.util.SpriteUtil;
import fr.studioshi.dazel.game.util.StringPool;

public class DazelSpriteLoader implements SpriteLoader {

	public void loadSprites(HashMap<String, Sprite> sprites) {
		sprites.put(EntityKeys.TRIFORCE_MENU,
				loadSprite(SpriteUtil.SPRITE_TRIFORCE_MENU));
		sprites.put(EntityKeys.LINK_TEST,
				loadSprite(SpriteUtil.SPRITE_LINK_TEST));

	}

	public Sprite loadSprite(String key) {
		// otherwise, go away and grab the sprite from the resource
		// loader
		BufferedImage sourceImage = null;

		try {
			// The ClassLoader.getResource() ensures we get the sprite
			// from the appropriate place, this helps with deploying the game
			// with things like webstart. You could equally do a file look
			// up here.

			String spritePath = ConfigurationLoader.getSpritesDir()
					+ StringPool.SLASH + key + DazelConstants.SPRITE_EXT_PNG;
			URL url = this.getClass().getClassLoader().getResource(spritePath);

			if (url == null) {
				fail("Can't find sprite: " + spritePath);
			}

			// use ImageIO to read the image in
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			fail("Failed to load: " + key);
		}

		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),
				sourceImage.getHeight(), Transparency.BITMASK);

		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage, 0, 0, null);

		// create a sprite, add it the cache then return it
		Sprite newSprite = new Sprite(image);
		return newSprite;
	}

	/**
	 * Utility method to handle resource loading failure
	 * 
	 * @param message
	 *            The message to display on failure
	 */
	private void fail(String message) {
		// we're pretty dramatic here, if a resource isn't available
		// we dump the message and exit the game
		System.err.println(message);
		System.exit(0);
	}

}
