package fr.studioshi.commons.game.mode;

import java.awt.Graphics;

import fr.studioshi.commons.game.engine.GameEngine;
import fr.studioshi.commons.game.keylistener.KeyHandler;
import fr.studioshi.commons.game.model.GameElement;
import fr.studioshi.commons.game.model.GameObjects;

public abstract class GameMode<T extends GameObjects> extends GameElement {

	protected int gameMode;

	protected int changeMode;

	protected GameEngine engine;

	protected KeyHandler keyListener;

	protected T gameObjects;

	public GameMode(int mode) {
		this.gameMode = mode;
		initGameObjects();
	}

	public abstract void initGameObjects();

	@Override
	public void playSound() {
		soundPlayer.playSound();
	}

	@Override
	public void process() {
		engine.process();
	}

	@Override
	public void render(Graphics graphics) {
		visualRenderer.render(graphics);
	}

	public GameEngine getEngine() {
		return engine;
	}

	public int getChangeMode() {
		return changeMode;
	}

	public T getGameObjects() {
		return gameObjects;
	}

	public int getGameMode() {
		return gameMode;
	}

	public KeyHandler getKeyListener() {
		return keyListener;
	}

	public void setChangeMode(int changeMode) {
		this.changeMode = changeMode;
	}

	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public void setKeyListener(KeyHandler keyListener) {
		this.keyListener = keyListener;
	}

	public void setGameObjects(T gameObjects) {
		this.gameObjects = gameObjects;
	}
}
