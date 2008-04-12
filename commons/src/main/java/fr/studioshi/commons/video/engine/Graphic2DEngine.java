package fr.studioshi.commons.video.engine;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import fr.studioshi.commons.game.Game;

public class Graphic2DEngine extends GraphicEngine {

	/** The strategy that allows us to use accelerate page flipping */
	private BufferStrategy strategy;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(Canvas displaySurface) {
		displaySurface.createBufferStrategy(2);
		strategy = displaySurface.getBufferStrategy();
		graphics = strategy.getDrawGraphics();
		((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

	}

	public void render(Game game) {
		game.render(graphics);
		strategy.show();
	}

}
