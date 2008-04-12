/*
 * Created on 17 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import old.fr.studioshi.dazel.game.items.Projectile;
import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;
import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Administrateur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Eye_guard extends Monstre {

	/**
	 * @param type
	 * @param health
	 * @param direction
	 * @param under_attack
	 * @param enemy_face
	 * @param coord
	 */

	private int looking_to_;

	private int cpt_;
	
	private boolean has_attacked_;
	
	public Eye_guard(String type, int health, int direction,
			boolean under_attack, boolean enemy_face, Coordinates coord) {
		super(type, health, direction, under_attack, enemy_face, coord);
		looking_to_ = 3;
cpt_ = 20;
has_attacked_ = false;
	}

	public Eye_guard() {
		super("eye_guard", 8, 1, false, false, new Coordinates(0, 0));
		looking_to_ = 3;
cpt_ = 20;
has_attacked_ = false;
	}
	
	public int abs(int i)
	{
		if (i < 0)
			return (0 - i);
		else 
			return i;
	}
	
	
public void attack(Game_screen inter)
	{
		int i = inter.getHeros_().getCoord().get_x();
		int j = inter.getHeros_().getCoord().get_y();
		
		if (abs(coord_.get_x() - i / 16) <= 1)
			{
			if (coord_.get_y() - j / 16 < 0 && looking_to_ == 3 && has_attacked_==false)
			{
				inter.getMap_().setWeapons_(	new Projectile(new Coordinates(coord_.get_x(), coord_.get_y() +1), 3,
                        (coord_.get_x()) * 16,
                        (coord_.get_y() + 1) * 16, this,
                        "magician_fireball_blue", null));
				has_attacked_ = true;
			}
                else if (coord_.get_y() - j / 16 > 0 && looking_to_ == 1&& has_attacked_==false)
                {
                	inter.getMap_().setWeapons_(new Projectile(new Coordinates(coord_.get_x(), coord_.get_y() -1), 1,
                            (coord_.get_x()) * 16,
                            (coord_.get_y() - 1) * 16, this,
                            "magician_fireball_blue", null));
                	has_attacked_ = true;
                }
			}
                else if (abs(coord_.get_y() -j / 16)<=1)
                {
                	if (coord_.get_x() - i/16 < 0 && looking_to_ == 2&& has_attacked_==false)
                	{
                		inter.getMap_().setWeapons_(	new Projectile(new Coordinates(coord_.get_x()+1, coord_.get_y()), 2,
                                (coord_.get_x()+1) * 16,
                                (coord_.get_y() ) * 16, this,
                                "magician_fireball_blue", null));
                		has_attacked_ = true;
                	}
                	else if (coord_.get_x() - i/16 > 0 && looking_to_ == 4&& has_attacked_==false)
                	{
                		inter.getMap_().setWeapons_(		new Projectile(new Coordinates(coord_.get_x()-1, coord_.get_y()), 4,
                                (coord_.get_x()-1) * 16,
                                (coord_.get_y() ) * 16, this,
                                "magician_fireball_blue", null));
                		has_attacked_ = true;
                	}
                }
	}

public void move(){
	has_attacked_ = false;
	if (looking_to_ == 1)
		looking_to_ = 2;
	else if (looking_to_ == 2)
		looking_to_ = 3;
	else if (looking_to_ == 3)
		looking_to_ = 4;
	else if (looking_to_ == 4)
		looking_to_ = 1;
}
               	


public void draw_down(Interface inter) {
    inter.getG2_().drawImage(
            getDemoImage("/resources/sprites/characters/eye_guard/eye_guard_down.png",
                    inter), print_x_, print_y_, inter);
}


public void draw_left(Interface inter) {
    inter.getG2_().drawImage(
            getDemoImage("/resources/sprites/characters/eye_guard/eye_guard_left.png",
                    inter), print_x_, print_y_, inter);
}

public void draw(Interface inter) {
    switch (looking_to_) {
    case 1:
        draw_up(inter);
        break;
    case 3:
        draw_down(inter);
        break;
    case 4:
        draw_left(inter);
        break;
    case 2:
        draw_right(inter);
        break;
    }
}

public void draw_right(Interface inter) {
    inter.getG2_().drawImage(
            getDemoImage("/resources/sprites/characters/eye_guard/eye_guard_right.png",
                    inter), print_x_, print_y_, inter);
}

public void draw_up(Interface inter) {
    inter.getG2_()
            .drawImage(
                    getDemoImage(
                            "/resources/sprites/characters/eye_guard/eye_guard_up.png",
                            inter), print_x_, print_y_, inter);
}


public void print(Game_screen inter, String s) {

	if (cpt_ == 0)
	{
		cpt_ = 20;
		move();
draw(inter);
	}
	cpt_--;
	attack(inter);
	draw(inter);
}
	
	
	
	
	}