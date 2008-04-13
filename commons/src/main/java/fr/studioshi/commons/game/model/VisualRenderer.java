package fr.studioshi.commons.game.model;

import fr.studioshi.commons.game.entities.VisualEntity;

public abstract class VisualRenderer<T extends VisualEntity> implements VisualEntity{

	protected T element;

	public VisualRenderer(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

}
