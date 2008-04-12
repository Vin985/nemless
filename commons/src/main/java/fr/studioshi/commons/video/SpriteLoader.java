package fr.studioshi.commons.video;

import java.util.HashMap;

public interface SpriteLoader {

	public Sprite loadSprite(String key);

	public void loadSprites(HashMap<String, Sprite> sprites);

}
