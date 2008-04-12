package fr.studioshi.commons.game.model;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.GameEntity;

public abstract class GameElement implements GameEntity {

	protected VisualRenderer visualRenderer;

	protected SoundPlayer soundPlayer;

	public void playSound() {
		if (soundPlayer != null) {
			soundPlayer.playSound();
		}
	}

	public void process() {
	}

	public void render(Graphics graphics) {
		if (visualRenderer != null) {
			visualRenderer.render(graphics);
		}
	}

	public VisualRenderer getVisualRenderer() {
		return visualRenderer;
	}

	public void setVisualRenderer(VisualRenderer visualRenderer) {
		this.visualRenderer = visualRenderer;
	}

	public SoundPlayer getSoundPlayer() {
		return soundPlayer;
	}

	public void setSoundPlayer(SoundPlayer soundPlayer) {
		this.soundPlayer = soundPlayer;
	}

}
