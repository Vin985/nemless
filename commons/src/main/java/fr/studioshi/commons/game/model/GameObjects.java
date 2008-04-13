package fr.studioshi.commons.game.model;

import java.awt.Graphics;

import fr.studioshi.commons.game.entities.VisualEntity;

/**
 * This class should be extended and contain all objects that are part of the
 * game, should it be maps, gui, characters, rocks etc... It should NOT provide
 * an implementation of render(). Leave that to the GameModeVisualRenderer
 * 
 * @author Sylvain
 * 
 */
public abstract class GameObjects implements VisualEntity {

	public void render(Graphics graphics) {
		// DO NOTHING!!!!
	}

}
