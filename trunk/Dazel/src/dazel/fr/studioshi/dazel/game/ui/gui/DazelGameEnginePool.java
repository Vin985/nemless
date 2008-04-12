package fr.studioshi.dazel.game.ui.gui;

import fr.studioshi.commons.game.engine.GameEngine;
import fr.studioshi.commons.game.engine.GameEnginePool;
import fr.studioshi.commons.game.model.GameObjects;
import fr.studioshi.dazel.game.util.DazelConstants;

public class DazelGameEnginePool extends GameEnginePool {

	@Override
	protected GameEngine initEngine(Integer engineType, GameObjects objects) {
		switch (engineType) {
		case DazelConstants.MODE_MAIN_MENU:
			return getMenuEngine(objects);
		case DazelConstants.MODE_GAME_SCREEN:
			return getGameEngine(objects);
		default:
			// mettre une meilleure exception ici
			throw new RuntimeException();
		}
	}

	private GameEngine getGameEngine(GameObjects objects) {
		GameScreenEngine gameScreenGUI = new GameScreenEngine(objects);
		engineMap.put(DazelConstants.MODE_GAME_SCREEN, gameScreenGUI);
		return gameScreenGUI;
	}

	private GameEngine getMenuEngine(GameObjects objects) {
		MenuEngine menuGUI = new MenuEngine(objects);
		engineMap.put(DazelConstants.MODE_MAIN_MENU, menuGUI);
		return menuGUI;
	}

}
