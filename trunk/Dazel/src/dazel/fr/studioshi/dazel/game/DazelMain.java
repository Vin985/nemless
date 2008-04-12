/*
 * Created on 3 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.studioshi.dazel.game;

import fr.studioshi.commons.video.RenderThread;
import fr.studioshi.commons.video.engine.Graphic2DEngine;

/**
 * @author Sylvain
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DazelMain {

	public static void main(String[] args) {
		DazelGame game = new DazelGame();
		RenderThread render = new RenderThread(game, new Graphic2DEngine());
		render.start();
		game.process();
	}
}