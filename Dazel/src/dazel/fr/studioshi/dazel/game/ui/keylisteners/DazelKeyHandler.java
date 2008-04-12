package fr.studioshi.dazel.game.ui.keylisteners;

import java.awt.event.KeyEvent;

import fr.studioshi.commons.game.keylistener.KeyHandler;

public class DazelKeyHandler extends KeyHandler {

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			events.setKeyDownPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			events.setKeyUpPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			events.setKeyLeftPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			events.setKeyRightPressed(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			events.setKeyDownPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			events.setKeyUpPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			events.setKeyLeftPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			events.setKeyRightPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			events.setKeyEnterPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			events.setKeyEscPressed(true);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

}
