package fr.studioshi.dazel.game.ui.gui;

import fr.studioshi.commons.game.model.GameObjects;
import fr.studioshi.commons.game.model.collection.VisualElementCollection;
import fr.studioshi.commons.video.Text;
import fr.studioshi.dazel.game.elements.MenuCursor;

public class DazelMenuObjects extends GameObjects {

	private MenuCursor cursor;

	private VisualElementCollection<Text> texts;

	public MenuCursor getCursor() {
		return cursor;
	}

	public void setCursor(MenuCursor cursor) {
		this.cursor = cursor;
	}

	public VisualElementCollection<Text> getTexts() {
		return texts;
	}

	public void setTexts(VisualElementCollection<Text> texts) {
		this.texts = texts;
	}

}
