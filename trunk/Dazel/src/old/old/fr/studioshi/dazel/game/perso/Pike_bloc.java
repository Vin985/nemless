/*
 * Created on 17 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import old.fr.studioshi.dazel.game.items.Element;
import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;
import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Administrateur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class Pike_bloc extends Monstre {

	private int cpt_;

	private int going_to_;

	public Pike_bloc(String type, int health, int direction,
			boolean under_attack, boolean enemy_face, Coordinates coord) {
		super(type, health, direction, under_attack, enemy_face, coord);
		cpt_ = 15;
		going_to_ = 0;
	}
	
	public Pike_bloc() {
		super("pike_bloc", 8, 1, false, false, new Coordinates(0, 0));
		cpt_ = 15;
		going_to_ = 0;
	}

	public int abs(int i)
	{
		if (i < 0)
			return (0 - i);
		else 
			return i;
	}
	
	
	public void attack(Game_screen inter) {
		int i = inter.getHeros_().getCoord().get_x();
		int j = inter.getHeros_().getCoord().get_y();
		
		if (abs(coord_.get_x() - i / 16) <= 1 && coord_.get_y() != j / 16
				&& going_to_ == 0) {
			if (coord_.get_y() - j / 16 < 0) {
				going_to_ = 3;
			} else {
				going_to_ = 1;
			}
		} else if (abs (coord_.get_y() -j / 16)<=1 && coord_.get_x() != i / 16
				&& going_to_ == 0)
			if (coord_.get_x() - i / 16 < 0) {
				going_to_ = 2;
			} else {
				going_to_ = 4;
			}
	}

	public void draw(Interface inter) {
		inter.getG2_().drawImage(
				getDemoImage("/resources/sprites/characters/pike-bloc.png", inter),
				print_x_, print_y_, inter);
	}

	public void move_to_up(Game_screen inter) {
		int i = coord_.get_x();
		int j = coord_.get_y();
		Element e;

		//		System.out.println(j);
		if (j >= 1) {
			e = inter.getMap_().getElement(new Coordinates(i, j - 1));

			print_y_ -= 6;

			if ((print_y_ + 8) / 16 < coord_.get_y() && e == null) {
				coord_.add_y(-1);
//				inter.getMap_().setElement(this);
//				inter.getMap_().setElement(new Coord(coord_.get_x(), coord_.get_y() + 1));
				if (e != null){
					if (cpt_ == 0){
						going_to_ = 0;
						cpt_ = 15;
						}
						cpt_--;
				}
			}
		}
		if (j == 0){
			if (cpt_ == 0){
				going_to_ = 0;
				cpt_ = 15;
				}
				cpt_--;
			}
	}

	public void move_to_down(Game_screen inter) {
		int i = coord_.get_x();
		int j = coord_.get_y();
		Element e;

		if (j <= 9) {
			e = inter.getMap_().getElement(new Coordinates(i, j + 1));

			print_y_ += 6;

			if ((print_y_ + 8) / 16 > coord_.get_y() && e == null) {
				coord_.add_y(1);
//				inter.getMap_().setElement(new Coord(coord_.get_x(), coord_.get_y() - 1));
//				inter.getMap_().setElement(this);

			}
			if (e != null){
				if (cpt_ == 0){
					going_to_ = 0;
					cpt_ = 15;
					}
					cpt_--;
		}}
		if (j == 10){
			if (cpt_ == 0){
				going_to_ = 0;
				cpt_ = 15;
				}
				cpt_--;
		}

		}

	public void move_to_right(Game_screen inter) {
		int i = coord_.get_x();
		int j = coord_.get_y();
		Element e;

		if (i <= 14) {
			e = inter.getMap_().getElement(new Coordinates(i + 1, j));

			print_x_ += 6;
			if ((print_x_ + 8) / 16 > coord_.get_x() && e == null) {
				coord_.add_x(1);
//				inter.getMap_().setElement(this);
//				inter.getMap_().setElement(new Coord(coord_.get_x() - 1, coord_.get_y()));
			}
			if (e != null){
				if (cpt_ == 0){
					going_to_ = 0;
					cpt_ = 15;
					}
					cpt_--;
		}}
		if (i == 15){
			if (cpt_ == 0){
				going_to_ = 0;
				cpt_ = 15;
				}
				cpt_--;
	}}

	public void move_to_left(Game_screen inter) {
		int i = coord_.get_x();
		int j = coord_.get_y();
		Element e;

		if (i >= 1) {
			e = inter.getMap_().getElement(new Coordinates(i - 1, j));
			print_x_ -= 6;

			if ((print_x_ + 8) / 16 < coord_.get_x() && e == null) {
				coord_.add_x(-1);
//				inter.getMap_().setElement(this);
//				inter.getMap_().setElement(new Coord(coord_.get_x() + 1, coord_.get_y()));
			}
		if (e != null){
			if (cpt_ == 0){
				going_to_ = 0;
				cpt_ = 15;
				}
				cpt_--;
		}}
		if ( i == 0){
			if (cpt_ == 0){
				going_to_ = 0;
				cpt_ = 15;
				}
				cpt_--;
	}}

	public void print(Game_screen inter, String s) {
//		System.out.println(going_to_);
		attack(inter);
		if (going_to_ == 1)
			move_to_up(inter);
		else if (going_to_ == 2)
			move_to_right(inter);
		else if (going_to_ == 3)
			move_to_down(inter);
		else if (going_to_ == 4)
			move_to_left(inter);
		draw(inter);

	}

}