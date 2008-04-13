package fr.studioshi.dazel.game;

import fr.studioshi.commons.game.Game;
import fr.studioshi.commons.game.engine.GameEnginePool;
import fr.studioshi.commons.game.keylistener.Events;
import fr.studioshi.commons.game.mode.GameMode;
import fr.studioshi.commons.game.mode.GameModePool;
import fr.studioshi.commons.video.SpriteStore;
import fr.studioshi.dazel.game.mode.DazelGameModePool;
import fr.studioshi.dazel.game.ui.gui.DazelGameEnginePool;
import fr.studioshi.dazel.game.util.DazelConstants;
import fr.studioshi.dazel.game.video.DazelGameWindow;
import fr.studioshi.dazel.game.video.DazelSpriteLoader;

public class DazelGame extends Game {

	public DazelGame() {

		// Initialisation des stores
		SpriteStore.getInstance().init(new DazelSpriteLoader());

		// Initialisation des pools
		GameEnginePool.init(DazelGameEnginePool.class);
		GameModePool.init(DazelGameModePool.class);

		gameMode = GameModePool.getInstance().getMode(
				DazelConstants.MODE_MAIN_MENU);
		gameWindow = new DazelGameWindow(gameMode.getKeyListener());
	}

	public void process() {
		while (isRunning) {
			try {
				Thread.sleep(10);
				gameMode.process();
				if (gameMode.getChangeMode() == DazelConstants.MODE_EXIT) {
					isRunning = false;
					break;
				}
				if (gameMode.getChangeMode() != DazelConstants.MODE_DEFAULT) {
					setGameMode(GameModePool.getInstance().getMode(
							gameMode.getChangeMode()));
					gameMode.setChangeMode(DazelConstants.MODE_DEFAULT);
				}
				Events.getInstance().reset();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gameWindow.dispose();
	}

	public void setGameMode(GameMode<?> gameMode) {
		gameWindow.removeKeyListener(this.gameMode.getKeyListener());
		this.gameMode = gameMode;
		gameWindow.addKeyListener(gameMode.getKeyListener());

	}

}
