package fr.studioshi.commons.video;

import java.awt.Graphics;
import java.awt.Image;

import fr.studioshi.commons.game.model.GraphicElement;

/**
 * A sprite to be displayed on the screen. Note that a sprite contains no state
 * information, i.e. its just the image and not the location. This allows us to
 * use a single sprite in lots of different places without having to store
 * multiple copies of the image.
 * 
 * @author Kevin Glass
 */
// Prendre en Compte les sprites animes et le changement de couleur selon la
// tunique ou autre
public class Sprite extends GraphicElement {
	/** The image to be drawn for this sprite */
	private Image image;

	/**
	 * Create a new sprite based on an image
	 * 
	 * @param image
	 *            The image that is this sprite
	 */
	public Sprite(Image image) {
		super();
		this.image = image;
	}

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return image.getHeight(null);
	}

	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param g
	 *            The graphics context on which to draw the sprite
	 * @param x
	 *            The x location at which to draw the sprite
	 * @param y
	 *            The y location at which to draw the sprite
	 */
	public void render(Graphics g) {
		g.drawImage(image, coords.getPosX(), coords.getPosY(), null);
		// TODO remove comments
		// g.setColor(Color.BLACK);
		// g.fillRect(coords.getPosX(), coords.getPosY(), 1, 1);
	}
}