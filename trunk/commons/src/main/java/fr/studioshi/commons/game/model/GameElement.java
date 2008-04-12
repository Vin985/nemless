package fr.studioshi.commons.game.model;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.GameEntity;
import fr.studioshi.commons.game.entities.SoundEntity;
import fr.studioshi.commons.game.entities.VisualEntity;

public abstract class GameElement implements VisualEntity, GameEntity,
		SoundEntity {

	protected VisualRender visualRender;

	protected SoundRender soundRender;

	public SoundRender getSoundRender() {
		return soundRender;
	}

	public VisualRender getVisualRender() {
		return visualRender;
	}

	public void playSound() {
		if (soundRender != null) {
			soundRender.playSound();
		}
	}

	public void process() {
	}

	public void render(Graphics graphics) {
		if (visualRender != null) {
			visualRender.render(graphics);
		}
	}

	public void setSoundRender(SoundRender soundRender) {
		this.soundRender = soundRender;
	}

	public void setVisualRender(VisualRender visualRender) {
		this.visualRender = visualRender;
	}
}
