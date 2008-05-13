package fr.studioshi.commons.game.model;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.VisualEntity;

public abstract class GraphicElement implements VisualEntity {
	protected Coordinates coords;

	public GraphicElement() {
	}

	public GraphicElement(Coordinates coords) {
		super();
		this.coords = coords;
	}

	public Coordinates getCoords() {
		return coords;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

	public void render(Graphics g) {
		draw(g, coords.getPosX(), coords.getPosY());
	}

	public void render(Graphics g, int offsetX, int offsetY) {
		draw(g, coords.getPosX() + offsetX, coords.getPosY() + offsetY);
	}

	protected abstract void draw(Graphics g, int x, int y);

}
