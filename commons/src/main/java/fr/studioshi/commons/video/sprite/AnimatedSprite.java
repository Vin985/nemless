package fr.studioshi.commons.video.sprite;

import java.awt.Graphics;
import java.awt.Image;

public class AnimatedSprite extends Sprite {

	private int height[];
	private int width[];
	private Image[] image;
	private int delay;
	private int counter = 0;
	private boolean loop;

	private int frames = 0;

	public AnimatedSprite(Image[] image, int delay, boolean loop) {
		this.image = image;
		this.loop = loop;
		this.delay = delay;

		width = new int[image.length];
		height = new int[image.length];

		for (int i = 0; i < image.length; i++) {
			width[i] = image[i].getWidth(null);
			height[i] = image[i].getHeight(null);
		}
	}

	@Override
	public int getWidth() {
		if (counter >= image.length) {
			return 0;
		}

		return width[counter];
	}

	@Override
	public int getHeight() {
		if (counter >= image.length) {
			return 0;
		}

		return height[counter];
	}

	@Override
	protected void draw(Graphics g, int x, int y) {
		if (counter >= image.length) {
			return;
		}

		g.drawImage(image[counter], x, y, null);

		frames++;

		if (frames >= delay) {
			if (loop) {
				counter = (counter + 1) % image.length;
			} else {
				counter++;
			}

			frames = 0;
		}
	}
}
