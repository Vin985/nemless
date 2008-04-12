package fr.studioshi.dazel.game.ui.gui.render.video;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import fr.studioshi.commons.game.model.GameModeVisualRenderer;
import fr.studioshi.commons.game.model.GameObjects;
import fr.studioshi.dazel.game.ui.gui.DazelMenuObjects;
import fr.studioshi.dazel.game.util.DazelConstants;

public class Menu2DRenderer extends GameModeVisualRenderer {

	public Menu2DRenderer(GameObjects objects) {
		super(objects);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, DazelConstants.WINDOW_WIDTH,
				DazelConstants.WINDOW_HEIGHT);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.BOLD, 15));

		DazelMenuObjects objects =(DazelMenuObjects)gameObjects;
		objects.getCursor().render(graphics);
		objects.getTexts().render(graphics);
	}
}
