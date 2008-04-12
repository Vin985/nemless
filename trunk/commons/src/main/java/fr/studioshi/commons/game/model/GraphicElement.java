package fr.studioshi.commons.game.model;

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

}
