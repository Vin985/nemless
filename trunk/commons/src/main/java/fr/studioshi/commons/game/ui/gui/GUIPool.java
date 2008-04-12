package fr.studioshi.commons.game.ui.gui;

import java.util.HashMap;

import fr.studioshi.commons.game.Game;

public abstract class GUIPool {

	private static GUIPool instance;

	public static GUIPool getInstance() {
		if (instance != null) {
			return instance;
		} else {
			throw new RuntimeException("Call init with desired class");
		}
	}

	public static void init(Class<? extends GUIPool> clazz) {
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

	protected HashMap<Integer, GUI> guiMap = new HashMap<Integer, GUI>();

	public GUI getGUI(Integer guiType, Game game) {
		GUI gui = guiMap.get(guiType);
		if (gui == null) {
			gui = initGui(guiType, game);
		}
		return gui;
	}

	protected abstract GUI initGui(Integer guiType, Game game);

}
