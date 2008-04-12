package fr.studioshi.commons.game;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.GameObjects;
import fr.studioshi.commons.game.mode.GameMode;
import fr.studioshi.commons.game.model.GameElement;
import fr.studioshi.commons.video.GameWindow;

public abstract class Game extends GameElement {

	protected int newMode;

	protected GameMode gameMode;

	protected GameWindow gameWindow;

	protected GameObjects gameObjects;

	protected boolean isRunning = true;

	public GameMode getGameMode() {
		return gameMode;
	}

	public GameObjects getGameObjects() {
		return gameObjects;
	}

	public GameWindow getGameWindow() {
		return gameWindow;
	}

	public int getNewMode() {
		return newMode;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public void setGameObjects(GameObjects gameObjects) {
		this.gameObjects = gameObjects;
	}

	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}

	public void setNewMode(int mode) {
		this.newMode = mode;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void render(Graphics graphics) {
		gameMode.render(graphics);
	}

	public void playSound() {
		gameMode.playSound();
	}
}
