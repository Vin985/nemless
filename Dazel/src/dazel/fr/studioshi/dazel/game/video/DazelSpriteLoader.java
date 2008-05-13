package fr.studioshi.dazel.game.video;

import java.util.HashMap;

import fr.studioshi.commons.video.sprite.Sprite;
import fr.studioshi.commons.video.sprite.SpriteLoader;
import fr.studioshi.dazel.game.util.ConfigurationLoader;
import fr.studioshi.dazel.game.util.DazelConstants;
import fr.studioshi.dazel.game.util.EntityKeys;
import fr.studioshi.dazel.game.util.SpriteUtil;
import fr.studioshi.dazel.game.util.StringPool;

public class DazelSpriteLoader extends SpriteLoader {

	@Override
	public void loadSprites(HashMap<String, Sprite> sprites) {
		sprites.put(EntityKeys.TRIFORCE_MENU,
				loadSprite(SpriteUtil.SPRITE_TRIFORCE_MENU));
		sprites.put(EntityKeys.LINK_TEST,
				loadSprite(SpriteUtil.SPRITE_LINK_TEST));
		sprites.put("AnimatedTest", loadAnimatedSprite(new String[] {
				SpriteUtil.SPRITE_TRIFORCE_MENU, SpriteUtil.SPRITE_LINK_TEST },
				500, true));

	}

	@Override
	protected String getResourcePath(String name) {
		return ConfigurationLoader.getSpritesDir() + StringPool.SLASH + name
				+ DazelConstants.SPRITE_EXT_PNG;
	}

}
