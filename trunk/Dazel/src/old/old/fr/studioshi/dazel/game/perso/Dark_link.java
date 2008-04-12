/*
 * Created on 18 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import old.fr.studioshi.dazel.game.items.Element;
import old.fr.studioshi.dazel.game.items.Projectile;
import old.fr.studioshi.dazel.game.ui.Game_screen;
import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Administrateur
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Dark_link extends Monstre{

	/**
	 * @param type
	 * @param health
	 * @param direction
	 * @param under_attack
	 * @param enemy_face
	 * @param coord
	 */
	 private int cpt_wait_;

	    private int looking_to_;

	private int is_near_;
	
	private boolean has_attacked_;
	public Dark_link(String type, int health, int direction, boolean under_attack, boolean enemy_face, Coordinates coord) {
		super(type, health, direction, under_attack, enemy_face, coord);
		// TODO Auto-generated constructor stub
	}

	public int abs(int i)
	{
		if (i < 0)
			return -i;
		else
			return i;
	}
	
	
	public void get_position(Game_screen inter)
	{
		int i = inter.getHeros_().getCoord().get_x();
		int j = inter.getHeros_().getCoord().get_y();
		int x = coord_.get_x();
		int y = coord_.get_y();
		int k, l;
        int dir;

        k = i - x * 16;
        l = j - y * 16;
        dir = k - l;
        
        if (dir >= 0 && k >= 0 && k < 45 || (dir >= 0 && k < 0 && l < 0)) 
		looking_to_ = 4;
		else if (dir >= 0 && k >= 0 && l >= 0 || (dir >= 0 && k >= 45 && l < 0)) 
		looking_to_ = 3;
		else if (dir < 0 && k > 0 && l >= 0 || (dir < 0 && k < 0 && l >= 45))
			looking_to_ = 2;
		else if (dir < 0 && k <= 0 && l >= 0 && l < 45
                || (dir < 0 && k < 0 && l < 0))
			looking_to_ = 1;
		
		if (abs(x - i/16) > 5 && abs(y - j/16) < 3)
			is_near_ = 0;
	}
	
	  public void move_to_down(Game_screen inter) {
        Element e;

//        if (check_case_move(inter, 2)) {
        if (coord_.get_y() + 1 <= 10) {
            if (print_y_ + 1 + 16 <= 11 * 16)
                print_y_ += 1;
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x(), coord_.get_y() + 1));
                if ((print_y_ + 8) / 16 > coord_.get_y() && e == null) {
                    coord_.add_y(1);
                    inter.getMap_().setElement(new Coordinates(coord_.get_x(), coord_.get_y() - 1));
                    inter.getMap_().setElement(this);
                }
            }
    }

    public void move_to_left(Game_screen inter) {
        Element e;

//        if (check_case_move(inter, -1)) {
        if (coord_.get_x() - 1 >= 0) {
            if (print_x_ - 1 >= 0)
                print_x_ -= 1;
          
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x() - 1, coord_.get_y()));
                if ((print_x_ + 8) / 16 < coord_.get_x() && e == null) {
                    coord_.add_x(-1);
                    inter.getMap_().setElement(new Coordinates(coord_.get_x() + 1, coord_.get_y()));
                    inter.getMap_().setElement(this);
                }
            }
    }

    public void move_to_right(Game_screen inter) {
        Element e;

//        if (check_case_move(inter, 1)) {
        if (coord_.get_x() + 1 <= 15) {
            if (print_x_ + 1 + 16 <= 256)
                print_x_ += 1;
           
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x() + 1, coord_.get_y()));
                if ((print_x_ + 8) / 16 > coord_.get_x() && e == null) {
                    coord_.add_x(1);
                    inter.getMap_().setElement(new Coordinates(coord_.get_x() - 1, coord_.get_y()));
                    inter.getMap_().setElement(this);
                }
            }
        
    }

    public void move_to_up(Game_screen inter) {
        Element e;

//        if (check_case_move(inter, -2)) {
        if (coord_.get_y() - 1 >= 0) {
            if (print_y_ - 1 >= 0)
                print_y_ -= 1;
           
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x(), coord_.get_y() - 1));
                if ((print_y_ + 8) / 16 < coord_.get_y() && e == null) {
                    coord_.add_y(-1);
                    inter.getMap_().setElement(new Coordinates(coord_.get_x(), coord_.get_y() + 1));
                    inter.getMap_().setElement(this);
                }
            }
        
    }

    public void distant_move(Game_screen inter) {
        Element e;
int dir = inter.getRandom_().nextInt(5);


        
        switch (dir) {
        case 0:
        	if (coord_.get_y() < 7)
            move_to_up(inter);
            break;
        case 1:
        	if (coord_.get_x() < 9 )
            move_to_right(inter);
            break;
        case 2:
        	if (coord_.get_y() > 2)
            move_to_down(inter);
            break;
        case 3:
        	if (coord_.get_x() > 5)
            move_to_left(inter);
            break;
        }
    }
	
	public void shoot(Game_screen inter){
		int i = inter.getHeros_().getCoord().get_x();
		int j = inter.getHeros_().getCoord().get_y();
		
		if (abs(coord_.get_x() - i / 16) <= 1)
			{
			if (coord_.get_y() - j / 16 < 0 && has_attacked_==false)
			{
				inter.getMap_().setElement(	new Projectile(new Coordinates(coord_.get_x(), coord_.get_y() +1), 3,
                        (coord_.get_x()) * 16,
                        (coord_.get_y() + 1) * 16, this,
                        "arrow", null));
				has_attacked_ = true;
			}
                else if (coord_.get_y() - j / 16 > 0 && has_attacked_==false)
                {
                	inter.getMap_().setElement(new Projectile(new Coordinates(coord_.get_x(), coord_.get_y() -1), 1,
                            (coord_.get_x()) * 16,
                            (coord_.get_y() - 1) * 16, this,
                            "arrow", null));
                	has_attacked_ = true;
                }
			}
                else if (abs(coord_.get_y() -j / 16)<=1)
                {
                	if (coord_.get_x() - i/16 < 0 && has_attacked_==false)
                	{
                		inter.getMap_().setElement(	new Projectile(new Coordinates(coord_.get_x()+1, coord_.get_y()), 2,
                                (coord_.get_x()+1) * 16,
                                (coord_.get_y() ) * 16, this,
                                "arrow", null));
                		has_attacked_ = true;
                	}
                	else if (coord_.get_x() - i/16 > 0&& has_attacked_==false)
                	{
                		inter.getMap_().setElement(		new Projectile(new Coordinates(coord_.get_x()-1, coord_.get_y()), 4,
                                (coord_.get_x()-1) * 16,
                                (coord_.get_y() ) * 16, this,
                                "arrow", null));
                		has_attacked_ = true;
                	}
                }
	}
	
	 public void take_damage(Game_screen inter, String weapon, Perso enemy) {
        if (weapon.equals("staff"))
            health_--;
        if (weapon.equals("arrow"))
            ;
        if (weapon.equals("bomb"))
            health_ = 0;
    }
	
	
	}
	

