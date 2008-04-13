package fr.studioshi.dazel.game.elements.hero;

import fr.studioshi.commons.game.keylistener.Events;
import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.game.model.GameObject;
import fr.studioshi.dazel.game.elements.hero.render.video.Link2DRender;
import fr.studioshi.dazel.game.util.DazelConstants;

public class Link extends GameObject {

	private int MOVEMENT_SPEED = 5;

	public Link(Coordinates coords) {
		this.coords = coords;
		this.visualRenderer = new Link2DRender(this, coords);
	}

	public void process() {
		Events events = Events.getInstance();

		// Mouvement a gauche
		if (events.isKeyLeftPressed()) {
			int movement = coords.getPosX() - MOVEMENT_SPEED;
			int newPosX = movement > 0 ? movement : 0;
			coords.setPosX(newPosX);
		// Mouvement a droite
		} else if (events.isKeyRightPressed()) {
			int movement = coords.getPosX() + MOVEMENT_SPEED;
			int newPosX = movement + 16 < DazelConstants.WINDOW_WIDTH ? movement
					: DazelConstants.WINDOW_WIDTH - 16;
			coords.setPosX(newPosX);
		// Mouvement en haut
		} else if (events.isKeyUpPressed()) {
			int movement = coords.getPosY() - MOVEMENT_SPEED;
			int newPosY = movement > 0 ? movement : 0;
			coords.setPosY(newPosY);
			System.out.println(newPosY);
		// Mouvement en bas
		} else if (events.isKeyDownPressed()) {
			int movement = coords.getPosY() + MOVEMENT_SPEED;
			int newPosY = movement + 16 < DazelConstants.WINDOW_HEIGHT ? movement
					: DazelConstants.WINDOW_HEIGHT - 16;
			coords.setPosY(newPosY);
		}
	}
}
