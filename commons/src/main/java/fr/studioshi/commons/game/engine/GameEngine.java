package fr.studioshi.commons.game.engine;

import fr.studioshi.commons.game.entities.ActionEntity;
import fr.studioshi.commons.game.model.GameObjects;

public abstract class GameEngine implements ActionEntity {

	public GameEngine(GameObjects gameObjects) {
		this.gameObjects = gameObjects;
	}

	protected GameObjects gameObjects;

	public GameObjects getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(GameObjects gameObjects) {
		this.gameObjects = gameObjects;
	}

}
