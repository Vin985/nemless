package fr.studioshi.commons.video.sprite;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public abstract class SpriteLoader {

	public abstract void loadSprites(HashMap<String, Sprite> sprites);

	protected abstract String getResourcePath(String name);

	protected SimpleSprite loadSprite(String name) {
		Image image = loadImage(name);
		// create a simple sprite
		SimpleSprite newSprite = new SimpleSprite(image);
		return newSprite;
	}

	protected AnimatedSprite loadAnimatedSprite(String[] imageNames, int delay,
			boolean loop) {
		Image[] images = new Image[imageNames.length];

		for (int i = 0; i < imageNames.length; i++) {
			images[i] = loadImage(imageNames[i]);
		}

		AnimatedSprite sprite = new AnimatedSprite(images, delay, loop);

		return sprite;
	}

	protected Image loadImage(String name) {
		// otherwise, go away and grab the sprite from the resource
		// loader
		BufferedImage sourceImage = null;

		try {
			// The ClassLoader.getResource() ensures we get the sprite
			// from the appropriate place, this helps with deploying the game
			// with things like webstart. You could equally do a file look
			// up here.

			String spritePath = getResourcePath(name);
			URL url = this.getClass().getClassLoader().getResource(spritePath);

			if (url == null) {
				fail("Can't find sprite: " + spritePath);
			}

			// use ImageIO to read the image in
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			fail("Failed to load: " + name);
		}

		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),
				sourceImage.getHeight(), Transparency.BITMASK);

		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage, 0, 0, null);

		return image;
	}

	/**
	 * Utility method to handle resource loading failure
	 * 
	 * @param message
	 *            The message to display on failure
	 */
	protected void fail(String message) {
		// we're pretty dramatic here, if a resource isn't available
		// we dump the message and exit the game
		System.err.println(message);
		System.exit(0);
	}

}
