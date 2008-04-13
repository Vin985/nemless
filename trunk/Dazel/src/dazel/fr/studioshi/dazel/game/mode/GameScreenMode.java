package fr.studioshi.dazel.game.mode;

import fr.studioshi.commons.game.engine.GameEnginePool;
import fr.studioshi.commons.game.keylistener.Events;
import fr.studioshi.commons.game.mode.GameMode;
import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.dazel.game.elements.hero.Link;
import fr.studioshi.dazel.game.ui.gui.DazelGameObjects;
import fr.studioshi.dazel.game.ui.gui.render.video.GameScreen2DRenderer;
import fr.studioshi.dazel.game.ui.keylisteners.DazelKeyHandler;
import fr.studioshi.dazel.game.util.DazelConstants;

public class GameScreenMode extends GameMode<DazelGameObjects> {

	public GameScreenMode(int gameMode) {
		super(gameMode);
		setChangeMode(DazelConstants.MODE_DEFAULT);
		setKeyListener(new DazelKeyHandler());
		setEngine(GameEnginePool.getInstance().getEngine(
				DazelConstants.MODE_GAME_SCREEN, gameObjects));
		setVisualRenderer(new GameScreen2DRenderer(gameObjects));
	}

	@Override
	public void process() {
		if (Events.getInstance().isKeyEscPressed()) {
			setChangeMode(DazelConstants.MODE_MAIN_MENU);
		} else {
			super.process();
		}
	}

	@Override
	public void initGameObjects() {
		DazelGameObjects dazelObjects = new DazelGameObjects();
		dazelObjects.setLink(new Link(new Coordinates(110, 100)));
		gameObjects = dazelObjects;
	}

}
