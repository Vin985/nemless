package fr.studioshi.dazel.game.elements.render.video;

import java.awt.Graphics;

import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.VisualRenderer;
import fr.studioshi.commons.video.sprite.Sprite;
import fr.studioshi.commons.video.sprite.SpriteStore;
import fr.studioshi.dazel.game.elements.MenuCursor;
import fr.studioshi.dazel.game.util.EntityKeys;

public class MenuCursor2DRender extends VisualRenderer<MenuCursor> {

	private Sprite cursor;

	public MenuCursor2DRender(MenuCursor cursor, Coordinates coords) {
		super(cursor);
		this.cursor = SpriteStore.getInstance().getSprite(
				EntityKeys.TRIFORCE_MENU, coords);
	}

	public void render(Graphics graphics) {
		cursor.render(graphics);
	}
}
