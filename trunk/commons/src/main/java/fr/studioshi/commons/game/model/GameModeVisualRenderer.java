package fr.studioshi.commons.game.model;

public abstract class GameModeVisualRenderer extends VisualRenderer {

	protected GameObjects gameObjects;

	public GameModeVisualRenderer(GameObjects objects) {
		this.gameObjects = objects;
	}

	public GameObjects getObjects() {
		return gameObjects;
	}

	public void setObjects(GameObjects objects) {
		this.gameObjects = objects;
	}

}
