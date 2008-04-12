/*
 * Created on 1 juil. 2004
 */
 
package old.fr.studioshi.dazel.editor;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Endy
 * Draw the icon for MenuFile and the Editmap for editor
 */
public class Draw extends JPanel
{
	/**
	 * @param im
	 * @param s
	 * Ctor. init image (plain). s is equal to icon or "" depending if icon must be drawed or the editmap
	 */
	public Draw(ImageIcon im, String s)
	{
		s_ = s;
		icon_ = im;
		edit_ = new ImageIcon("Sprites/Map/plain.png");
		plain_ = new ImageIcon("Sprites/Map/plain.png");
		border_ = new ImageIcon("Sprites/bordure.png");
	}

	/**
	 * @param im
	 * @param coord
	 * @param size
	 * Redraw on an image at coord
	 */
	public void myDraw(ImageIcon im, Coordinates coord)
	{
		icon_ = im;
		getGraphics().drawImage(icon_.getImage(), coord.get_x(), coord.get_y(), 32, 32, this);
	}
	
	/**
	 * @param g
	 * First draw for an image
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		if (s_ == "icon")
		{
			g.drawImage(border_.getImage(), 318, 8, 36, 2, this);
			g.drawImage(border_.getImage(), 318, 8, 2, 36, this);
			g.drawImage(border_.getImage(), 318, 42, 36, 2, this);
			g.drawImage(border_.getImage(), 352, 8, 2, 36, this);
			g.drawImage(plain_.getImage(), 320, 10, 32, 32, this);
			g.drawImage(icon_.getImage(), 320, 10, 32, 32, this);
		}
		else
		{
			g.drawImage(border_.getImage(), 8, 5, 516, 356, this);
			g.drawImage(edit_.getImage(), 10, 7, 512, 352, this);
		}
	}
	
	protected
		ImageIcon	edit_, icon_, plain_, border_;
		String		s_;
}
