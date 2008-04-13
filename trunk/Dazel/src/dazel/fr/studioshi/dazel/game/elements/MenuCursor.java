package fr.studioshi.dazel.game.elements;

import fr.studioshi.commons.game.keylistener.Events;
import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.GameObject;
import fr.studioshi.dazel.game.elements.render.video.MenuCursor2DRender;
import fr.studioshi.dazel.game.util.DazelConstants;

public class MenuCursor extends GameObject {

	// deplacement du curseur en px
	private static final int CURSOR_MOVEMENT = 20;

	private int cursorPosition = 0;

	public MenuCursor(Coordinates coords) {
		this.coords = coords;
		this.visualRenderer = new MenuCursor2DRender(this, coords);
	}

	public void process() {
		Events events = Events.getInstance();

		if (events.isKeyDownPressed()
				&& cursorPosition + 1 < DazelConstants.MENU_OPTIONS_NUMBER) {
			coords.addY(CURSOR_MOVEMENT);
			cursorPosition++;
		} else if (events.isKeyUpPressed() && cursorPosition - 1 >= 0) {
			coords.addY(-CURSOR_MOVEMENT);
			cursorPosition--;
		}
	}

	public int getCursorPosition() {
		return cursorPosition;
	}

	public void setCursorPosition(int cursorPosition) {
		this.cursorPosition = cursorPosition;
	}

}
