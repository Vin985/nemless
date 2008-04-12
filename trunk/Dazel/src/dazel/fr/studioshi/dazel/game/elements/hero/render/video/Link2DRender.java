package fr.studioshi.dazel.game.elements.hero.render.video;

import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.SpriteRender;
import fr.studioshi.commons.game.model.VisualRenderer;
import fr.studioshi.commons.video.SpriteStore;
import fr.studioshi.dazel.game.util.EntityKeys;

public class Link2DRender extends VisualRenderer {

	public Link2DRender(Coordinates coords) {
		visualEntity = new SpriteRender(SpriteStore.getInstance().getSprite(
				EntityKeys.LINK_TEST), coords);
	}

}
