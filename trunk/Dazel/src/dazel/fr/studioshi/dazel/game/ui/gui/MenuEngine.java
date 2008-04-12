package fr.studioshi.dazel.game.ui.gui;

import fr.studioshi.commons.game.engine.GameEngine;
import fr.studioshi.commons.game.model.GameObjects;

public class MenuEngine extends GameEngine {

	private static final long serialVersionUID = 1857204049954442145L;

	public MenuEngine(GameObjects objects) {
		super(objects);
	}

	public void process() {
		DazelMenuObjects menuObjects = (DazelMenuObjects) gameObjects;
		menuObjects.getCursor().process();
	}

}
