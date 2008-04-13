package fr.studioshi.dazel.game.mode;

import fr.studioshi.commons.game.engine.GameEnginePool;
import fr.studioshi.commons.game.keylistener.Events;
import fr.studioshi.commons.game.mode.GameMode;
import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.collection.VisualElementCollection;
import fr.studioshi.commons.video.Text;
import fr.studioshi.dazel.game.elements.MenuCursor;
import fr.studioshi.dazel.game.ui.gui.DazelMenuObjects;
import fr.studioshi.dazel.game.ui.gui.render.video.Menu2DRenderer;
import fr.studioshi.dazel.game.ui.keylisteners.DazelKeyHandler;
import fr.studioshi.dazel.game.util.DazelConstants;

public class MenuMode extends GameMode<DazelMenuObjects> {

	public MenuMode(int mode) {
		super(mode);
		setChangeMode(DazelConstants.MODE_DEFAULT);
		setKeyListener(new DazelKeyHandler());
		setEngine(GameEnginePool.getInstance().getEngine(
				DazelConstants.MODE_MAIN_MENU, gameObjects));
		setVisualRenderer(new Menu2DRenderer(gameObjects));
	}

	@Override
	public void initGameObjects() {

		MenuCursor cursor = new MenuCursor(new Coordinates(80, 110));
		VisualElementCollection<Text> texts = new VisualElementCollection<Text>();

		Text newGame = new Text("menu.newgame", new Coordinates(110, 120));
		Text exit = new Text("menu.exit", new Coordinates(110, 140));
		texts.add(newGame);
		texts.add(exit);

		DazelMenuObjects objects = new DazelMenuObjects();
		objects.setCursor(cursor);
		objects.setTexts(texts);

		gameObjects = objects;

	}

	@Override
	public void process() {
		Events events = Events.getInstance();
		DazelMenuObjects menuObjects = (DazelMenuObjects) gameObjects;

		// Changement de mode
		if (events.isKeyEnterPressed()) {
			switch (menuObjects.getCursor().getCursorPosition()) {
			case 0:
				setChangeMode(DazelConstants.MODE_GAME_SCREEN);
				break;
			case 1:
				setChangeMode(DazelConstants.MODE_EXIT);
				break;
			default:
				break;// On ne fait rien
			}
		} else if (events.isKeyEscPressed()) {
			setChangeMode(DazelConstants.MODE_EXIT);
		} else {
			// Mise a jour du curseur
			engine.process();
		}
	}

}
