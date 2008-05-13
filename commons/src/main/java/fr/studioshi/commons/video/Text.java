package fr.studioshi.commons.video;

import java.awt.Font;
import java.awt.Graphics;

import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.GraphicElement;

public class Text extends GraphicElement {
	/** The image to be drawn for this sprite */
	private String text;

	private Font font = null;

	public Text(String text, Coordinates coords) {
		super(coords);
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public String getText() {
		return text;
	}

	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param graphics
	 *            The graphics context on which to draw the sprite
	 * @param x
	 *            The x location at which to draw the sprite
	 * @param y
	 *            The y location at which to draw the sprite
	 */
	protected void draw(Graphics graphics, int x, int y) {
		if (font != null) {
			graphics.setFont(font);
		}
		graphics.drawString(text, x, y);
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void setText(String text) {
		this.text = text;
	}
}
