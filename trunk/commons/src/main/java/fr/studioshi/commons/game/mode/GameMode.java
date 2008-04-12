package fr.studioshi.commons.game.mode;

import java.awt.Graphics;

import fr.studioshi.commons.game.model.GameElement;
import fr.studioshi.commons.game.ui.gui.GUI;
import fr.studioshi.commons.game.ui.keylistener.KeyHandler;

public abstract class GameMode extends GameElement {

	protected int gameMode;

	protected GUI gui;

	protected KeyHandler keyListener;

	public int getGameMode() {
		return gameMode;
	}

	public GUI getGui() {
		return gui;
	}

	public KeyHandler getKeyListener() {
		return keyListener;
	}

	public void playSound() {
		gui.playSound();
	}

	public void process() {
		gui.process();
	}

	public void render(Graphics graphics) {
		gui.render(graphics);
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public void setKeyListener(KeyHandler keyListener) {
		this.keyListener = keyListener;
	}
}
