package fr.studioshi.commons.game.engine;

import java.util.HashMap;

import fr.studioshi.commons.game.model.GameObjects;

public abstract class GameEnginePool {

	private static GameEnginePool instance;

	public static GameEnginePool getInstance() {
		if (instance != null) {
			return instance;
		} else {
			throw new RuntimeException("Call init with desired class");
		}
	}

	public static void init(Class<? extends GameEnginePool> clazz) {
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

	protected HashMap<Integer, GameEngine> engineMap = new HashMap<Integer, GameEngine>();

	public GameEngine getEngine(Integer engineType, GameObjects objects) {
		GameEngine engine = engineMap.get(engineType);
		if (engine == null) {
			engine = initEngine(engineType, objects);
		}
		return engine;
	}

	protected abstract GameEngine initEngine(Integer engineType,
			GameObjects objects);

}
