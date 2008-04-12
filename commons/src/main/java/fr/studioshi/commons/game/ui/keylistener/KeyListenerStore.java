package fr.studioshi.commons.game.ui.keylistener;

import java.util.HashMap;


public class KeyListenerStore {
	private static HashMap<Integer, KeyHandler> listenerMap;

	public static void init() {
	}

	public static KeyHandler getListener(Integer uiType) {
		return listenerMap.get(uiType);
	}
}
