package fr.studioshi.dazel.game.ui.gui;

import fr.studioshi.commons.game.engine.GameEngine;
import fr.studioshi.commons.game.model.GameObjects;

public class GameScreenEngine extends GameEngine {

	public GameScreenEngine(GameObjects gameObjects) {
		super(gameObjects);
	}

	public void process() {
		((DazelGameObjects) gameObjects).getLink().process();
	}

}
