package fr.studioshi.commons.video;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import com.dnsalias.java.timer.AdvancedTimer;

import fr.studioshi.commons.game.Game;
import fr.studioshi.commons.game.model.Coordinates;
import fr.studioshi.commons.video.engine.GraphicEngine;

public class RenderThread extends Thread {

	private Game game;

	private GraphicEngine graphicEngine;

	/** The strategy that allows us to use accelerate page flipping */
	private BufferStrategy strategy;

	private Graphics2D graphics;

	private boolean done_ = false;

	public RenderThread(Game game) {
		super();
		this.game = game;
		Canvas displaySurface = game.getGameWindow().getDisplaySurface();
		displaySurface.createBufferStrategy(2);
		strategy = displaySurface.getBufferStrategy();
		graphics = (Graphics2D) strategy.getDrawGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
	}

	public RenderThread(GraphicEngine graphicEngine) {
		super();
		this.graphicEngine = graphicEngine;
	}

	/**
	 * @return Returns the done_.
	 */
	public boolean isDone_() {
		return done_;
	}

	@Override
	public void run() {
		int fps = 0;
		AdvancedTimer timer = new AdvancedTimer();
		timer.start();

		long loopStart = 0;
		long loopDuration = 0;
		long fpsStartTime = timer.getClockTicks();

		// Recuperer le fps ailleurs. (Game ou creer un module de fps a part)
		long refreshRate = AdvancedTimer.getTicksPerSecond() / 50; // DazelConstants.FPS;

		Text fpsDisplay = new Text(fps + " fps", new Coordinates(20, 35));
		fpsDisplay.setFont(new Font("Arial", Font.BOLD, 15));

		while (game.isRunning()) {
			try {
				loopStart = timer.getClockTicks();

				// check if we have been looping for more than 1 sec
				if (timer.getClockTicks() - fpsStartTime > AdvancedTimer
						.getTicksPerSecond()) {
					fpsDisplay.setText(fps + " fps");
					fpsStartTime = timer.getClockTicks();
					fps = 0;
				}
				game.render(graphics);

				// display fps
				graphics.setColor(Color.YELLOW);
				fpsDisplay.render(graphics);

				// show graphics
				strategy.show();
				fps++;

				// trace duration of loop
				loopDuration = timer.getClockTicks() - loopStart;

				// We aim for a speed of 50 fps. If loop has taken too long,
				// don't sleep. Else sleep just enough to limit screen rendering
				if (loopDuration < refreshRate) {
					timer.sleep(refreshRate - loopDuration);
				} else {
					System.out.println("too slow");
				}
			} catch (RuntimeException re) {
				throw re;
			} catch (Exception ie) {
			}
		}
	}

	/**
	 * @param done_
	 *            The done_ to set.
	 */
	public void setDone_(boolean done_) {
		this.done_ = done_;
	}

}
