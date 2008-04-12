package fr.studioshi.commons.game;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.GameEntity;
import fr.studioshi.commons.game.mode.GameMode;
import fr.studioshi.commons.video.GameWindow;

/**
 * This is the core game class. It keeps track of the mode the game is in and if
 * it is running. It also has the gameWindow
 * 
 * @author Sylvain
 * 
 */
public abstract class Game implements GameEntity {

	protected boolean isRunning = true;

	protected GameMode gameMode;
	protected GameWindow gameWindow;

	public void playSound() {
		gameMode.playSound();
	}

	public void process() {
		gameMode.process();
	}

	public void render(Graphics graphics) {
		gameMode.render(graphics);
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public GameWindow getGameWindow() {
		return gameWindow;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
