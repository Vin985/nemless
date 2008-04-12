package fr.studioshi.commons.game.model;

import java.awt.Graphics;

import fr.studioshi.commons.video.Sprite;

public class SpriteRender extends GraphicElement {

	protected Sprite sprite;

	public SpriteRender(Sprite sprite, Coordinates coords) {
		this.coords = coords;
		sprite.setCoords(this.coords);
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void render(Graphics graphics) {
		sprite.render(graphics);
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
