package fr.studioshi.commons.video.sprite;

import java.util.HashMap;

import fr.studioshi.commons.game.model.Coordinates;

/**
 * A resource manager for sprites in the game. Its often quite important how and
 * where you get your game resources from. In most cases it makes sense to have
 * a central resource loader that goes away, gets your resources and caches them
 * for future use.
 * <p>
 * [singleton]
 * <p>
 * 
 * @author Kevin Glass
 */
public class SpriteStore {
	/** The single instance of this class */
	private static SpriteStore instance = new SpriteStore();

	/**
	 * Get the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static SpriteStore getInstance() {
		return instance;
	}

	private SpriteLoader spriteLoader;

	/** The cached sprite map, from reference to sprite instance */
	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

	/**
	 * Retrieve a sprite from the store
	 * 
	 * @param key
	 *            The reference to the image to use for the sprite
	 * @return A sprite instance containing an accelerate image of the request
	 *         reference
	 */
	public Sprite getSprite(String key) {
		// if we've already got the sprite in the cache
		// then just return the existing version
		if (sprites.get(key) != null) {
			return sprites.get(key);
		}

		Sprite sprite = spriteLoader.loadSprite(key);
		sprites.put(key, sprite);

		return sprite;
	}
	
	public Sprite getSprite(String key, Coordinates coords) {
		Sprite sprite = getSprite(key);
		sprite.setCoords(coords);
		return sprite;
	}

	public void init(SpriteLoader spriteLoader) {
		this.spriteLoader = spriteLoader;
		this.spriteLoader.loadSprites(sprites);
	}
}