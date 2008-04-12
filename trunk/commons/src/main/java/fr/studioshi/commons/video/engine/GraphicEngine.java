package fr.studioshi.commons.video.engine;

import java.awt.Canvas;
import java.awt.Graphics;

import fr.studioshi.commons.game.Game;

public abstract class GraphicEngine {

	protected Graphics graphics;

	public abstract void destroy();

	public abstract void init(Canvas displaySurface);

	public abstract void render(Game game);

	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

}
