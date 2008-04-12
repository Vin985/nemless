/*
 * Created on 10 juil. 2004
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
public class Knight extends Monstre {

    /**
     * @param type
     * @param health
     * @param direction
     * @param under_attack
     * @param enemy_face
     * @param call
     * @param coord
     */

    private int cpt_;

    private int cpt2_;
    
    private int cpt_rdm_;

    private int cpt_wait_;

    private int looking_to_;

    private int xp_;

    public Knight(String type, int health, int direction, boolean under_attack,
            boolean enemy_face, Coordinates coord) {
        super(type, health, direction, under_attack, enemy_face, coord);
        looking_to_ = 3;
        setHas_hit_(false);
        cpt_wait_ = 5;
        xp_ = 30;
        cpt_ = 25;
        cpt2_ = 25;
    }
    
    public Knight()
    {
        super("knight", 16, 1, false, false, new Coordinates(0, 0));
        looking_to_ = 3;
        setHas_hit_(false);
        cpt_wait_ = 5;
        xp_ = 30;
        cpt_ = 25;
        cpt2_ = 25;
    }

    /**
     *  
     */

    public void attack(Game_screen inter, int len, int larg) {
        Element e = null;
        int i, j;

        cpt_wait_--;
        if (cpt_wait_ < 0) {
            cpt_wait_ = 0;
        }
        i = inter.getHeros_().getCoord().get_x()/16 - ((coord_.get_x()));
        j = inter.getHeros_().getCoord().get_y()/16 - ((coord_.get_y()) );
        if (i <= len  && i >=  -1 *len && j <= larg 
                && j >= -1 * larg) {
            if (has_hit_) {
                cpt_wait_ = 70;
                setHas_hit_(false);
            }
            if (i > 0 && cpt_wait_ == 0) {
                move_to_right(inter);
            } else if (i < 0 && cpt_wait_ == 0) {
                move_to_left(inter);
            }
            if (j < 0 && cpt_wait_ == 0) {
                move_to_up(inter);
            } else if (j > 0 && cpt_wait_ == 0) {
                move_to_down(inter);

            }
        }

    }

    public void call(Game_screen inter) {
        Element e = null;
        int i, j;
        Monstre b = null;

        for (i = 0; i < 16; i++)
            for (j = 0; j < 10; j++) {
                e = inter.getMap_().getMonsters_(new Coordinates(i, j));
                if (e != null && e.getType().equals("knight")) {
                    b = (Knight) e;
                    b.is_called_ = true;
                }
            }
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#die()
     */

    public boolean check_case_move(Game_screen inter, int dir) {
        int i, j;
        Element e = null;

        System.out.println(coord_.get_y());
        if (dir == 1 || dir == -1) {
        	if ((dir == 1 && coord_.get_x() == 15) || (dir == -1 && coord_.get_x() == 0))
        		return false;
            i = print_x_ / 16 + (dir + 1) / 2;
//        	i = coord_.get_x() + (dir+1) / 2;
            j = (print_y_ + 8) / 16;
        } else {
        	if ((dir == -2 && coord_.get_y() == 0) || ( dir == 2 && coord_.get_y() == 10))
        		return false;
            i = (print_x_ + 8) / 16;
            j = print_y_ / 16 + (dir + 2) / 4;
        }
        e = inter.getMap_().getElement(new Coordinates(i, j));
        if (inter.getMap_().getDecor(new Coordinates(i, j)).is_obstacle()
                || (e != null && e.getType().equals("rupee")))

        {
            return false;
        }
        return true;
    }

    public void die(Game_screen inter, Perso enemy) {
        int spawn = inter.getRandom_().nextInt(45);
        if (enemy instanceof Joueur) {
            Joueur enemy_ = (Joueur) enemy;
            enemy_.add_xp(xp_);
        }
        if (spawn < 6)
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(), "rupee",
                            null, null));
        if (spawn >= 6 && spawn < 9)
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(),
                            "rupee_blue", null, null));
        if (spawn >= 9 && spawn < 13)
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(),
                            "little_heart", null, null));
        if (spawn >= 13 && spawn < 19)
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(),
                            "little_heart_red", null, null));
        
       if (spawn >= 19)
            inter.getMap_().setMonsters_(coord_);
    }
    /**
     *  
     */

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

        /*
         * inter.getG2_().drawImage( getDemoImage("/Sprites/Items/here.png",
         * inter), coord_.get_x()*16, coord_.get_y() * 16, inter);
         */

    }

    public void draw_down(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/knight/knight_down.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_down_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/knight/knight_2_down.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_left(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/knight/knight_left.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_left_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/knight/knight_2_left.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_med(Interface inter) {
        switch (looking_to_) {
        case 1:
            draw_up_med(inter);
            break;
        case 3:
            draw_down_med(inter);
            break;
        case 4:
            draw_left_med(inter);
            break;
        case 2:
            draw_right_med(inter);
            break;
        }
    }

    public void draw_right(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/knight/knight_right.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_right_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/knight/knight_2_right.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_up(Interface inter) {
        inter.getG2_()
                .drawImage(
                        getDemoImage(
                                "/resources/sprites/characters/knight/knight_up.png",
                                inter), print_x_, print_y_, inter);
    }

    public void draw_up_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/knight/knight_2_up.png",
                        inter), print_x_, print_y_, inter);
    }

    /**
     * @return Returns the looking_to_.
     */
    public int getLooking_to_() {
        return this.looking_to_;
    }

    /**
     * @return
     */
    public boolean is_call() {
        return is_called_;
    }

    /**
     *  
     */
    public void move(Game_screen inter, int dir) {
        Element e;

        switch (dir) {
        case 0:
            move_to_up(inter);
            break;
        case 1:
            move_to_right(inter);
            break;
        case 2:
            move_to_down(inter);
            break;
        case 3:
            move_to_left(inter);
            break;
        }
    }

    public void move_to_down(Game_screen inter) {
        Element e;

        if (check_case_move(inter, 2)) {
        if (coord_.get_y() + 1 <= 10) {
            if (print_y_ + 1 + 16 <= 11 * 16)
                print_y_ += 1;
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x(), coord_.get_y() + 1));
                if ((print_y_ + 8) / 16 > coord_.get_y() && e == null&&  inter.getMap_().getMonsters_(new Coordinates(coord_.get_x(), coord_.get_y()+1)) == null) {
                    coord_.add_y(1);
//                    inter.getMap_().setMonsters_(new Coord(coord_.get_x(), coord_.get_y() - 1));
//                    inter.getMap_().setMonsters_(this);
                }
            }
        looking_to_ = 3;
        }
    }

    public void move_to_left(Game_screen inter) {
        Element e;

        if (check_case_move(inter, -1)) {
        if (coord_.get_x() - 1 >= 0) {
            if (print_x_ - 1 >= 0)
                print_x_ -= 1;
          
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x() - 1, coord_.get_y()));
                if ((print_x_ + 8) / 16 < coord_.get_x() && e == null&&  inter.getMap_().getMonsters_(new Coordinates(coord_.get_x()-1, coord_.get_y())) == null) {
                    coord_.add_x(-1);
//                    inter.getMap_().setMonsters_(new Coord(coord_.get_x() + 1, coord_.get_y()));
//                    inter.getMap_().setMonsters_(this);
                }
            }
        looking_to_ = 4;
    }
    }

    public void move_to_right(Game_screen inter) {
        Element e;

        if (check_case_move(inter, 1)) {
        if (coord_.get_x() + 1 <= 15) {
            if (print_x_ + 1 + 16 <= 256)
                print_x_ += 1;
           
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x() + 1, coord_.get_y())) ;
                if ((print_x_ + 8) / 16 > coord_.get_x() && e == null&&  inter.getMap_().getMonsters_(new Coordinates(coord_.get_x()+1, coord_.get_y())) == null) {
                    coord_.add_x(1);
//                    inter.getMap_().setMonsters_(new Coord(coord_.get_x() - 1, coord_.get_y()));
//                    inter.getMap_().setMonsters_(this);
                }
            }
        
        looking_to_ = 2;
        }
    }

    public void move_to_up(Game_screen inter) {
        Element e;

        if (check_case_move(inter, -2)) {
        if (coord_.get_y() - 1 >= 0) {
            if (print_y_ - 1 >= 0)
                print_y_ -= 1;
           
                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x(), coord_.get_y() - 1));
                if ((print_y_ + 8) / 16 < coord_.get_y() && e == null &&  inter.getMap_().getMonsters_(new Coordinates(coord_.get_x(), coord_.get_y() - 1)) == null) {
                    coord_.add_y(-1);
//                    inter.getMap_().setMonsters_(new Coord(coord_.get_x(), coord_.get_y() + 1));
//                    inter.getMap_().setMonsters_(this);
                }
            }
        looking_to_ = 1;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#print()
     */
    public void print(Game_screen inter, String s) {

//      inter.getG2_().drawImage(
//		                getDemoImage("/Sprites/Items/here.png", inter),
//		                coord_.get_x() * 16, coord_.get_y() * 16, inter);

        Element e = null;
        if (cpt_rdm_ == 0) {
            direction_ = inter.getRandom_().nextInt(6);
            cpt_rdm_ = 25;
        } else
            cpt_rdm_--;
        if (is_call())
            attack(inter, 7, 7);
        else
            attack(inter, 3, 3);
        draw(inter);
        move(inter, direction_);
        if (cpt_ == 0) {
            cpt_ = 50;
            draw(inter);
        } else if (cpt_ < 12) {
            draw_med(inter);
            cpt_ -= 1;
        } else {
            draw(inter);
            cpt_ -= 1;
        }
    }

    /**
     * @param call
     */
    public void set_call(boolean call) {
        is_called_ = call;
    }

    /**
     * @param looking_to_
     *            The looking_to_ to set.
     */
    public void setLooking_to_(int looking_to_) {
        this.looking_to_ = looking_to_;
    }

    public void take_damage(Game_screen inter, String weapon, Perso enemy) {
        int i = inter.getRandom_().nextInt(100);
        
    	call(inter);
        if (weapon.equals("staff"))
            health_--;
        if (weapon.equals("sword"))
            health_ -= 8;
        if (weapon.equals("sword_wood"))
        	health_-=4 ;
        if (weapon.equals("arrow"))
            health_  -=4;
        if (weapon.equals("fire"))
            health_ -= 8 ;
        if (weapon.equals("ice"))
            health_ -= 4;
        if (weapon.equals("death") && i <= 40)
            health_ = 0;
        if (weapon.equals("bomb"))
            health_ = 8;
        if (health_ == 0)
            die(inter, enemy);
    }

    /**
     *  
     */
    public void watch() {
    }
}

