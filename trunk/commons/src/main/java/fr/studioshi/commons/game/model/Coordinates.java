/*
 * Created on 28 juin 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.studioshi.commons.game.model;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Coordinates {

	private int posX;

	private int posY;

	public Coordinates(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public void addX(int x) {
		this.posX += x;
	}

	public void addY(int y) {
		this.posY += y;
	}

	public boolean equal(Coordinates coords) {
		if (coords.getPosX() == this.getPosX()
				&& coords.getPosY() == this.getPosY())
			return true;
		return false;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
