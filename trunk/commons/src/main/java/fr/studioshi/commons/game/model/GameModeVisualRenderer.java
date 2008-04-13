package fr.studioshi.commons.game.model;

public abstract class GameModeVisualRenderer<T extends GameObjects> extends
		VisualRenderer<T> {

	public GameModeVisualRenderer(T element) {
		super(element);
	}

}
