package fr.studioshi.commons.game.ui.gui;

import java.awt.Graphics;

import fr.studioshi.commons.game.Game;
import fr.studioshi.commons.game.model.GameElement;

public abstract class GUI extends GameElement {

	protected Game game;

	public GUI(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void render(Graphics graphics) {
		if (visualRender == null) {
			throw new RuntimeException(
					"Graphical interface has no render defined!");
		}
		super.render(graphics);
	}

}
