/*
 * Created on 28 juin 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package old.fr.studioshi.dazel.game.items;

import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.ImageIcon;

import old.fr.studioshi.dazel.editor.Draw;
import old.fr.studioshi.dazel.game.perso.Monstre;
import old.fr.studioshi.dazel.game.ui.Game_screen;

import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Minimap {

	protected Coordinates coord_;

	private LinkedList decor_;

	private LinkedList elt_;

	private ListIterator it_;

	private ListIterator it2_;

	boolean light_;

	private LinkedList monsters_;

	private LinkedList weapons_;

	public Minimap() {
		coord_ = new Coordinates(0, 0);
		decor_ = new LinkedList();
		elt_ = new LinkedList();
		monsters_ = new LinkedList();
		weapons_ = new LinkedList();
	}

	/**
	 * @param x
	 * @param y
	 * @param light
	 * @param type
	 * @param map
	 */
	public Minimap(int x, int y, boolean light) {
		coord_ = new Coordinates(x, y);
		light_ = light;
		decor_ = new LinkedList();
		elt_ = new LinkedList();
		monsters_ = new LinkedList();
		weapons_ = new LinkedList();
		it_ = null;
	}

	/**
	 * @return
	 */
	public Coordinates getCoord() {
		return coord_;
	}

	/**
	 * @param coord
	 * @return
	 */
	public Element getDecor(Coordinates coord) {
		ListIterator it = decor_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (coord.equal(tmp.coord_)) {
				if (tmp instanceof Block)
					return (Block) tmp;
				return tmp;
			}
		}
		return new Element(false, coord.get_x(), coord.get_y(), "plain",
				getHide(coord), null);
	}

	public LinkedList getDecors() {
		return decor_;
	}

	/**
	 * @param coord
	 * @return
	 */
	public Element getElement(Coordinates coord) {
		ListIterator it = elt_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (tmp != null)
				if (coord.equal(tmp.coord_))
					return tmp;
		}
		return null;
	}

	public LinkedList getElts() {
		return elt_;
	}

	public Element getHide(Coordinates coord) {
		ListIterator it = decor_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (coord.equal(tmp.coord_))
				return tmp.getHide();
		}
		return new Element(false, coord.get_x(), coord.get_y(), "plain", null,
				null);
	}

	/**
	 * @return
	 */
	public boolean getLight() {
		return light_;
	}

	public synchronized Monstre getMonsters_(Coordinates coord) {
		ListIterator it = monsters_.listIterator();

		while (it.hasNext()) {
			Monstre tmp = (Monstre) it.next();
			if (tmp != null) {
				if (coord.equal(tmp.coord_))
					return tmp;
			}
		}
		return null;
	}

	public LinkedList getMonsts() {
		return monsters_;
	}

	public synchronized Element getWeapons_(Coordinates coord) {
		ListIterator it = weapons_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (tmp != null) {
				if (coord.equal(tmp.coord_))
					return tmp;
			}
		}
		return null;
	}

	/**
	 * @param pimage
	 * @param size
	 * @param type
	 */
	public void print(Draw pimage) {
		ListIterator it = elt_.listIterator();

		print_decor(pimage);
		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			pimage.myDraw(new ImageIcon("resources/sprites/items/editor/"
					+ tmp.getType() + ".png"), new Coordinates(
					tmp.getCoord().get_x() * 32 + 10,
					tmp.getCoord().get_y() * 32 + 7));
		}
		print_monsters(pimage);
	}

	/**
	 * 
	 */

	public synchronized void print(Game_screen inter) {
		it_ = elt_.listIterator();

		while (it_.hasNext()) {
			Element tmp = (Element) it_.next();
			if (tmp != null) {
				if (tmp.hide_ != null)
					tmp.hide_.print(inter, "Items/");
				tmp.print(inter, "Items/");
			} else
				it_.remove();
		}

	}

	/**
	 * @param pimage
	 * @param size
	 */
	public void print_decor(Draw pimage) {
		ListIterator it = decor_.listIterator();
		ListIterator it2 = decor_.listIterator();

		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 11; j++)
				pimage.myDraw(new ImageIcon("resources/sprites/map/plain.png"),
						new Coordinates(i * 32 + 10, j * 32 + 7));

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			pimage.myDraw(new ImageIcon("resources/sprites/map/"
					+ tmp.getType() + ".png"), new Coordinates(
					tmp.getCoord().get_x() * 32 + 10,
					tmp.getCoord().get_y() * 32 + 7));
		}

		while (it2.hasNext()) {
			Element tmp = (Element) it2.next();
			pimage.myDraw(new ImageIcon("resources/sprites/dungeon/"
					+ tmp.getType() + ".png"), new Coordinates(
					tmp.getCoord().get_x() * 32 + 10,
					tmp.getCoord().get_y() * 32 + 7));
		}

	}

	public void print_decor(Game_screen inter) {
		ListIterator it = decor_.listIterator();

		// System.out.println(decor_.size());
		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 11; j++)
				new Element(false, i, j, "plain", new Element(false, i, j,
						"plain", null, null), null).print(inter, "map/");

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (tmp != null) {
				if (tmp.hide_ != null) {
					if (inter.get_Map_().getName().equals("donjon1")
							|| inter.get_Map_().getName().equals("donjon2")
							|| inter.get_Map_().getName().equals("donjon3"))
						tmp.hide_.print(inter, "dungeon/");
					else
						tmp.hide_.print(inter, "map/");
				}
				if (inter.get_Map_().getName().equals("donjon1")
						|| inter.get_Map_().getName().equals("donjon2")
						|| inter.get_Map_().getName().equals("donjon3"))
					tmp.print(inter, "dungeon/");
				else
					tmp.print(inter, "map/");
			}
		}
	}

	/**
	 * @param pimage
	 * @param size
	 */
	public void print_hide(Draw pimage) {
		ListIterator it = decor_.listIterator();
		ListIterator it2 = decor_.listIterator();

		for (int i = 0; i < 16; i++)
			for (int j = 0; j < 11; j++)
				pimage.myDraw(new ImageIcon("resources/sprites/map/plain.png"),
						new Coordinates(i * 32 + 10, j * 32 + 7));

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			pimage.myDraw(new ImageIcon("resources/sprites/map/"
					+ tmp.getHide().getType() + ".png"), new Coordinates(tmp
					.getCoord().get_x() * 32 + 10,
					tmp.getCoord().get_y() * 32 + 7));
		}

		while (it2.hasNext()) {
			Element tmp = (Element) it2.next();
			pimage.myDraw(new ImageIcon("resources/sprites/dungeon/"
					+ tmp.getHide().getType() + ".png"), new Coordinates(tmp
					.getCoord().get_x() * 32 + 10,
					tmp.getCoord().get_y() * 32 + 7));
		}

		/*
		 * while (it3.hasNext()) { Element tmp = (Element) it3.next();
		 * pimage.myDraw(new ImageIcon("resources/sprites/Characters/editor/" +
		 * tmp.getHide().getType() + ".png"), new Coord(tmp .getCoord().get_x()
		 * size * 16 + 10, tmp.getCoord().get_y() * size * 16 + 7)); }
		 */}

	public void print_monsters(Draw pimage) {
		ListIterator it = monsters_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			pimage.myDraw(new ImageIcon("resources/sprites/characters/editor/"
					+ tmp.getType() + ".png"), new Coordinates(
					tmp.getCoord().get_x() * 32 + 10,
					tmp.getCoord().get_y() * 32 + 7));
		}
	}

	public synchronized void print_monsters(Game_screen inter) {
		it2_ = monsters_.listIterator();

		try {
			// System.out.println("mons: " + monsters_.size());
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int i = 0;
		while (it2_.hasNext()) {
			Element tmp = (Element) it2_.next();
			if (tmp != null) {
				tmp.print(inter, "Items/");
				// if (tmp instanceof Blob){
				// inter.getG2_().drawImage(
				// inter
				// .getDemoImage("/resources/sprites/items/here.png",
				// inter), tmp.coord_.get_x() * 16,
				// tmp.coord_.get_y() * 16, inter);
				// }
			} else
				it2_.remove();
			i++;
		}
	}

	public synchronized void print_weapons(Game_screen inter) {
		ListIterator it = weapons_.listIterator();

		try {
			// System.out.println("mons: " + weapons_.size());
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (tmp != null) {
				if (tmp.hide_ != null)
					tmp.hide_.print(inter, "items/");
				tmp.print(inter, "items/");
			} else
				it.remove();
		}
	}

	/**
	 * @param e
	 * @param coord
	 */
	public void setDecor(Coordinates coord) {
		ListIterator it = decor_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (coord.equal(tmp.coord_))
				decor_.remove(tmp);
		}
	}

	public void setDecor(Element e) {
		ListIterator it = decor_.listIterator();
		boolean added = false;

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (e.getCoord().equal(tmp.coord_)) {
				it.set(e);
				added = true;
			}
		}
		if (!added)
			decor_.add(e);
	}

	/**
	 * @param e
	 * @param coord
	 */
	public void setElement(Coordinates coord) {
		it_ = elt_.listIterator();

		while (it_.hasNext()) {
			Element tmp = (Element) it_.next();
			if (tmp != null)
				if (coord.equal(tmp.coord_)) {
					it_.set(null);
					return;
				}
		}
	}

	public void setElement(Element e) {
		it_ = elt_.listIterator();

		while (it_.hasNext()) {
			Element tmp = (Element) it_.next();
			if (tmp != null)
				if (e.getCoord().equal(tmp.coord_)) {
					it_.set(e);
					return;
				}
		}
		it_.add(e);
	}

	public void setHide(Coordinates coord) {
		ListIterator it = decor_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (coord.equal(tmp.coord_))
				tmp.setHide(null);
			return;
		}
	}

	public void setHide(Element e) {
		ListIterator it = decor_.listIterator();
		boolean added = false;

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (e.getCoord().equal(tmp.coord_)) {
				tmp.setHide(e);
				added = true;
				return;
			}
		}
		if (!added) {
			getDecor(e.coord_).setHide(e);
			decor_.add(getDecor(e.coord_));
		}
	}

	/**
	 * @param light
	 */
	public void setLight(boolean light) {
		light_ = light;
	}

	public synchronized void setMonsters_(Coordinates coord) {
		ListIterator it = monsters_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (tmp != null) {
				if (coord.equal(tmp.coord_)) {
					it.set(null);
					return;
				}
			}
		}
	}

	public synchronized void setMonsters_(Element e) {
		it2_ = monsters_.listIterator();

		while (it2_.hasNext()) {
			Element tmp = (Element) it2_.next();
			if (tmp != null) {
				if (e.getCoord().equal(tmp.coord_)) {
					it2_.set(e);
					return;
				}
			}
		}
		it2_.add(e);
	}

	public synchronized void setWeapons_(Coordinates coord) {
		it2_ = weapons_.listIterator();

		while (it2_.hasNext()) {
			Element tmp = (Element) it2_.next();
			if (tmp != null) {
				if (coord.equal(tmp.coord_)) {
					it2_.set(null);
					return;
				}
			}
		}
	}

	public synchronized void setWeapons_(Element e) {
		ListIterator it = weapons_.listIterator();

		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			if (tmp != null) {
				if (e.getCoord().equal(tmp.coord_)) {
					it.set(e);
					return;
				}
			}
		}
		it.add(e);
	}

	public void setX(int x) {
		coord_.set_x(x);
	}

	public void setY(int y) {
		coord_.set_y(y);

	}
}