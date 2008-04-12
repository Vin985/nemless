package fr.studioshi.commons.video;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import fr.studioshi.commons.game.keylistener.KeyHandler;

public abstract class GameWindow extends Frame {

	private static final long serialVersionUID = 7367414966572180602L;

	private Canvas displaySurface;

	public GameWindow(KeyHandler keyListener, String name, int width,
			int height) {
		super(name);

		// Put the window in the center of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);

		// Initialize the displaySurface and add it to the frame
		initDisplaySurface(width, height);
		add(displaySurface);
		pack();

		// Add window and key listeners
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		addKeyListener(keyListener);

		// Display the window and ignore repaint messages.
		setVisible(true);
		setIgnoreRepaint(true);

	}

	public Canvas getDisplaySurface() {
		return displaySurface;
	}

	private void initDisplaySurface(int width, int height) {
		displaySurface = new Canvas();
		displaySurface.setBounds(0, 0, width, height);
		displaySurface.setIgnoreRepaint(true);
		displaySurface.requestFocus();
	}

}
