/*
 * Created on 28 juin 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package old.fr.studioshi.dazel.game.items;

import java.awt.Image;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;

import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;

import fr.studioshi.common.game.model.Coordinates;
import fr.studioshi.dazel.game.util.ConfKeys;
import fr.studioshi.dazel.game.util.ConfigurationLoader;
import fr.studioshi.dazel.game.util.DazelConstants;
import fr.studioshi.dazel.game.util.StringPool;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Element {

	public class Light extends Element {

		protected boolean on_;

		/**
		 * @param obstacle
		 * @param coord
		 * @param type
		 * @param on
		 */
		public Light(boolean obstacle, Coordinates coord, String type, boolean on,
				Element hide) {
			super(obstacle, coord.get_x(), coord.get_y(), type, hide, null);
			on_ = on;
		}

		/**
		 * @return
		 */
		public boolean is_on() {
			return on_;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see map.Element#print()
		 */
		public void print() {
		}

		/**
		 * @param on
		 */
		public void set_on(boolean on) {
			on_ = on;
		}
	}

	/**
	 * @author Vincent
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public class Plain extends Element {

		/**
		 * @param obstacle
		 * @param coord
		 * @param type
		 */
		public Plain(boolean obstacle, Coordinates coord, String type, Element hide) {
			super(obstacle, coord.get_x(), coord.get_y(), type, hide, null);
		}

		public void draw(Interface inter) {
			inter.getG2_().drawImage(
					getDemoImage(ConfigurationLoader
							.getProperty(ConfKeys.DAZEL_SPRITES_DIR)
							+ StringPool.SLASH
							+ DazelConstants.SPRITES_MAP_DIR
							+ StringPool.SLASH
							+ getType()
							+ DazelConstants.SPRITE_EXT_PNG, inter),
					getCoord().get_x() * 16, getCoord().get_y() * 16, inter);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see map.Element#print()
		 */
		public void print() {
		}
	}

	/**
	 * @author Vincent
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public class Rock extends Element {

		protected int upable_;

		/**
		 * @param obstacle
		 * @param coord
		 * @param type
		 * @param upable
		 */
		public Rock(boolean obstacle, Coordinates coord, String type, int upable,
				Element hide) {
			super(obstacle, coord.get_x(), coord.get_y(), type, hide, null);
			upable_ = upable;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see map.Element#print()
		 */
		public void print() {
		}

		/**
		 * @return
		 */
		public int set_upable() {
			return upable_;
		}

		/**
		 * @param upable
		 */
		public void set_upable(int upable) {
			upable_ = upable;
		}
	}

	/**
	 * @author Vincent
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */

	public class Tree extends Element {

		protected boolean burn_;

		boolean burnable_;

		/**
		 * @param obstacle
		 * @param coord
		 * @param type
		 * @param burn
		 * @param burnable
		 */
		public Tree(boolean obstacle, Coordinates coord, String type, boolean burn,
				boolean burnable, Element hide) {
			super(obstacle, coord.get_x(), coord.get_y(), type, hide, null);
			burn_ = burn;
			burnable_ = burnable;
		}

		/**
		 * @return
		 */
		public boolean is_burn() {
			return burn_;
		}

		/**
		 * @return
		 */
		public boolean is_burnable() {
			return burnable_;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see map.Element#print()
		 */
		public void print() {
		}

		/**
		 * @param burn
		 */
		public void set_burn(boolean burn) {
			burn_ = burn;
		}

		/**
		 * @param burnable
		 */
		public void set_burnable(boolean burnable) {
			burnable_ = burnable;
		}
	}

	/**
	 * @author Vincent
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */

	protected Coordinates coord_;

	protected Element hide_;

	protected boolean is_monster_;

	boolean obstacle_;

	String type_, link_;

	/**
	 * @param obstacle
	 * @param coord
	 * @param type
	 */
	public Element(boolean obstacle, int x, int y, String type, Element hide,
			String link) {
		obstacle_ = obstacle;
		coord_ = new Coordinates(x, y);
		type_ = type;
		hide_ = hide;
		link_ = link;
		is_monster_ = false;
	}

	public Element() {
		coord_ = new Coordinates(0, 0);
		hide_ = new Element(false, 0, 0, "plain", null, null);
	}

	/**
	 * 
	 */
	public void destroy() {
	}

	public void setLink(String s) {
		link_ = s;
	}

	public String getLink() {
		return link_;
	}

	public void draw(Interface inter, String s) {
		inter
				.getG2_()
				.drawImage(
						getDemoImage(ConfigurationLoader.getSpritesDir() + s
								+ getType() + DazelConstants.SPRITE_EXT_PNG, inter),
						getCoord().get_x() * 16, getCoord().get_y() * 16, 16,
						16, inter);
	}

	/**
	 * @return
	 */
	public Coordinates getCoord() {
		return coord_;
	}

	/**
	 * @param name
	 * @param inter
	 * @return
	 */
	public Image getDemoImage(String name, Interface inter) {
		URL url = null;
		try {
			url = new URL("file:" + System.getProperty("user.dir") + name);

		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
		Image img = inter.getToolkit().getImage(url);
		try {
			MediaTracker tracker = new MediaTracker(inter);
			tracker.addImage(img, 0);
			tracker.waitForID(0);
		} catch (Exception e) {
		}
		return img;
	}

	/**
	 * @return
	 */
	public Element getHide() {
		return hide_;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type_;
	}

	/**
	 * @return
	 */
	public boolean is_obstacle() {
		return obstacle_;
	}

	/**
	 * @return Returns the is_monster_.
	 */
	public boolean isIs_monster_() {
		return this.is_monster_;
	}

	/**
	 * 
	 */
	public void move() {
	}

	/**
	 * 
	 */
	public void print(Game_screen inter, String s) {
		draw(inter, s);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setCoord(int x, int y) {
		coord_.set_x(x);
		coord_.set_y(y);
	}

	public void setX(int x) {
		coord_.set_x(x);
		if (hide_ != null)
			hide_.setX(x);
	}

	public void setY(int y) {
		coord_.set_y(y);
		if (hide_ != null)
			hide_.setY(y);
	}

	public void setHide(Element h) {
		hide_ = h;
	}

	/**
	 * @param is_monster_
	 *            The is_monster_ to set.
	 */
	public void setIs_monster_(boolean is_monster_) {
		this.is_monster_ = is_monster_;
	}

	/**
	 * @param obs
	 */
	public void setObstacle(boolean obs) {
		obstacle_ = obs;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		type_ = type;
	}

	/**
	 * 
	 */
	public void up() {
	}
}