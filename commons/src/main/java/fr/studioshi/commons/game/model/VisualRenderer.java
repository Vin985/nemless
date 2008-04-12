package fr.studioshi.commons.game.model;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.VisualEntity;

public abstract class VisualRenderer implements VisualEntity {

	protected VisualEntity visualEntity;

	public void render(Graphics graphics) {
		visualEntity.render(graphics);
	}

}
