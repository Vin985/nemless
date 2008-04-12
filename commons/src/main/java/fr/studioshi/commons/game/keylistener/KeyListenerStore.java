package fr.studioshi.commons.game.keylistener;

import java.util.HashMap;

public class KeyListenerStore {
	private static HashMap<Integer, KeyHandler> listenerMap;

	public static KeyHandler getListener(Integer uiType) {
		return listenerMap.get(uiType);
	}

	public static void init() {
	}
}
