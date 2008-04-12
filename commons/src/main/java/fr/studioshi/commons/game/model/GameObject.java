package fr.studioshi.commons.game.model;

public abstract class GameObject extends GameElement {

	protected Coordinates coords;

	public Coordinates getCoords() {
		return coords;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

}
