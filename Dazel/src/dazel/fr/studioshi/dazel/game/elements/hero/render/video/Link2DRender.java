package fr.studioshi.dazel.game.elements.hero.render.video;

import java.awt.Graphics;

import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.VisualRenderer;
import fr.studioshi.commons.video.sprite.Sprite;
import fr.studioshi.commons.video.sprite.SpriteStore;
import fr.studioshi.dazel.game.elements.hero.Link;
import fr.studioshi.dazel.game.util.EntityKeys;

public class Link2DRender extends VisualRenderer<Link> {

	private Sprite sprite;

	public Link2DRender(Link element, Coordinates coords) {
		super(element);
		sprite = SpriteStore.getInstance().getSprite(EntityKeys.LINK_TEST,
				coords);
	}

	public void render(Graphics graphics) {
		sprite.render(graphics);
	}

}
