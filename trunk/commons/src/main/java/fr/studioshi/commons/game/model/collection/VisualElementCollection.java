package fr.studioshi.commons.game.model.collection;

import java.awt.Graphics;
import java.util.List;

import fr.studioshi.commons.game.entities.VisualEntity;

public class VisualElementCollection<T extends VisualEntity> extends ElementCollection<T>  implements VisualEntity {

	public VisualElementCollection() {
		super();
	}

	public VisualElementCollection(List<T> list) {
		super(list);
	}

	public void render(Graphics graphics) {
		for (T visualElement : elements) {
			visualElement.render(graphics);
		}
	}

}
