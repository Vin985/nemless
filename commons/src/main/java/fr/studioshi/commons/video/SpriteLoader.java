package fr.studioshi.commons.video;

import java.util.HashMap;

public interface SpriteLoader {

	public void loadSprites(HashMap<String, Sprite> sprites);
	
	public Sprite loadSprite(String key);
	
}
