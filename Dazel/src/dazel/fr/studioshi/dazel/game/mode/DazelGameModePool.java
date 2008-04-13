package fr.studioshi.dazel.game.mode;

import fr.studioshi.commons.game.mode.GameMode;
import fr.studioshi.commons.game.mode.GameModePool;
import fr.studioshi.dazel.game.util.DazelConstants;

public class DazelGameModePool extends GameModePool {

	@Override
	protected GameMode<?> initGameMode(Integer mode) {
		switch (mode) {
		case DazelConstants.MODE_MAIN_MENU:
			return getMenu(mode);
		case DazelConstants.MODE_GAME_SCREEN:
			return getGameScreen(mode);
		default:
			// mettre une meilleure exception ici
			throw new RuntimeException();
		}
	
	}

	private GameMode<?> getGameScreen(Integer mode) {
		GameMode<?> gameScreenMode = new GameScreenMode(mode);
		modePool.put(DazelConstants.MODE_GAME_SCREEN, gameScreenMode);
		return gameScreenMode;
	}

	private GameMode<?> getMenu(Integer mode) {
		GameMode<?> menuMode = new MenuMode(mode);
		modePool.put(DazelConstants.MODE_MAIN_MENU, menuMode);
		return menuMode;
	}

}
