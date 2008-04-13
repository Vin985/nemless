package fr.studioshi.dazel.game.ui.gui.render.video;

import java.awt.Color;
import java.awt.Graphics;

import fr.studioshi.commons.game.model.GameModeVisualRenderer;
import fr.studioshi.dazel.game.ui.gui.DazelGameObjects;
import fr.studioshi.dazel.game.util.DazelConstants;

public class GameScreen2DRenderer extends
		GameModeVisualRenderer<DazelGameObjects> {

	public GameScreen2DRenderer(DazelGameObjects objects) {
		super(objects);
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, DazelConstants.WINDOW_WIDTH,
				DazelConstants.WINDOW_HEIGHT);
		element.getLink().render(graphics);
	}

}
