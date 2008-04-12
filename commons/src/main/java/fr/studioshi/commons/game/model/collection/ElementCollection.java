package fr.studioshi.commons.game.model.collection;

import java.util.ArrayList;
import java.util.List;

public class ElementCollection<T> {

	protected List<T> elements;

	public ElementCollection() {
		this.elements = new ArrayList<T>();
	}

	public ElementCollection(List<T> list) {
		this.elements = list;
	}

	public boolean add(T visualElement) {
		return this.elements.add(visualElement);
	}

}
