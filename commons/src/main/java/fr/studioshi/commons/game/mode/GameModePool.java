package fr.studioshi.commons.game.mode;

import java.util.HashMap;

import fr.studioshi.commons.game.Game;

public abstract class GameModePool {

	private static GameModePool instance;

	public static GameModePool getInstance() {
		if (instance != null) {
			return instance;
		} else {
			// TODO
			throw new RuntimeException("Call init with desired class");
		}
	}

	public static void init(Class<? extends GameModePool> clazz) {
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected HashMap<Integer, GameMode> modePool = new HashMap<Integer, GameMode>();

	public GameMode getMode(Integer mode, Game game) {
		GameMode gameMode = modePool.get(mode);
		if (gameMode == null) {
			gameMode = initGameMode(mode, game);
		}
		return gameMode;
	}

	protected abstract GameMode initGameMode(Integer mode, Game game);

}
