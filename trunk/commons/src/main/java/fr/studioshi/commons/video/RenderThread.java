package fr.studioshi.commons.video;

import fr.studioshi.commons.game.Game;
import fr.studioshi.commons.video.engine.GraphicEngine;

public class RenderThread extends Thread {

	private Game game;

	private GraphicEngine graphicEngine;

	public RenderThread(Game game, GraphicEngine graphicEngine) {
		this.game = game;
		this.graphicEngine = graphicEngine;
		graphicEngine.init(game.getGameWindow().getDisplaySurface());
	}

	@Override
	public void run() {
		// int fps = 0;
		// AdvancedTimer timer = new AdvancedTimer();
		// timer.start();
		//
		// long loopStart = 0;
		// long loopDuration = 0;
		// long fpsStartTime = timer.getClockTicks();
		//
		// // Recuperer le fps ailleurs. (Game ou creer un module de fps a part)
		// long refreshRate = AdvancedTimer.getTicksPerSecond() / 50; //
		// DazelConstants.FPS;
		//
		// Text fpsDisplay = new Text(fps + " fps", new Coordinates(20, 35));
		// fpsDisplay.setFont(new Font("Arial", Font.BOLD, 15));

		while (game.isRunning()) {
			try {
				// loopStart = timer.getClockTicks();
				//
				// // check if we have been looping for more than 1 sec
				// if (timer.getClockTicks() - fpsStartTime > AdvancedTimer
				// .getTicksPerSecond()) {
				// fpsDisplay.setText(fps + " fps");
				// fpsStartTime = timer.getClockTicks();
				// fps = 0;
				// }
				graphicEngine.render(game);

				// display fps
				// graphics.setColor(Color.YELLOW);
				// fpsDisplay.render(graphics);

				// show graphics
				// fps++;
				//
				// // trace duration of loop
				// loopDuration = timer.getClockTicks() - loopStart;
				//
				// // We aim for a speed of 50 fps. If loop has taken too long,
				// // don't sleep. Else sleep just enough to limit screen
				// rendering
				// if (loopDuration < refreshRate) {
				// timer.sleep(refreshRate - loopDuration);
				// } else {
				// System.out.println("too slow");
				// }
			} catch (RuntimeException re) {
				throw re;
			} catch (Exception ie) {
			}
		}
	}

}
