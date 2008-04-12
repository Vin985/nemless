package fr.studioshi.commons.game.model;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.VisualEntity;

public abstract class VisualRender implements VisualEntity {

	protected GraphicElement graphicElement;
	
	public GraphicElement getGraphics() {
		return graphicElement;
	}

	public void render(Graphics graphics) {
		graphicElement.render(graphics);
	}

	public void setGraphics(GraphicElement graphics) {
		this.graphicElement = graphics;
	}

}
