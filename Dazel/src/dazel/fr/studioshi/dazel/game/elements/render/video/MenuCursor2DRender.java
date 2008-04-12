package fr.studioshi.dazel.game.elements.render.video;

import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.SpriteRender;
import fr.studioshi.commons.game.model.VisualRenderer;
import fr.studioshi.commons.video.SpriteStore;
import fr.studioshi.dazel.game.util.EntityKeys;

public class MenuCursor2DRender extends VisualRenderer {

	public MenuCursor2DRender(Coordinates coords) {
		visualEntity = new SpriteRender(SpriteStore.getInstance().getSprite(
				EntityKeys.TRIFORCE_MENU), coords);
	}
}
