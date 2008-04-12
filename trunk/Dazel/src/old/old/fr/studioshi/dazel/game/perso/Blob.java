/*
 * Created on 4 juil. 2004
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
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class Blob extends Monstre {

    private int cpt_;

    private int cpt_rdm_;

    private int cpt_wait_;

    private int xp_;

    /**
     * @param type
     * @param health
     * @param direction
     * @param under_attack
     * @param enemy_face
     * @param coord
     */
    public Blob(String type, int health, int direction, boolean under_attack,
            boolean enemy_face, Coordinates coord) {
        super(type, health, direction, under_attack, enemy_face, coord);
        xp_ = 20;
        cpt_ = 10;
        cpt_rdm_ = 3;
        cpt_wait_ = 5;
        setHas_hit_(false);
    }
    
    public Blob()
    {
    	super("blob", 4, 1, false, false, new Coordinates(0, 0));	
    	   xp_ = 20;
           cpt_ = 10;
           cpt_rdm_ = 3;
           cpt_wait_ = 5;
           setHas_hit_(false);
    }

    public void attack(Game_screen inter, int len, int larg) {
        int i = inter.getHeros_().getCoord().get_x() - ((coord_.get_x()) * 16);
        int j = inter.getHeros_().getCoord().get_y() - ((coord_.get_y()) * 16);

        cpt_wait_--;
        if (cpt_wait_ < 0) {
            cpt_wait_ = 0;
        }

        if (i <= len * 20 && i >= -20 * len && j <= larg * 20
                && j >= -20 * larg) {
            if (has_hit_) {
                cpt_wait_ = 75;
                setHas_hit_(false);
            }
            if (i > 0 && cpt_wait_ == 0) {
                move_to_right(inter);
            }
            if (j < 0 && cpt_wait_ == 0) {
                move_to_up(inter);
            }
            if (i < 0 && cpt_wait_ == 0) {
                move_to_left(inter);
            }
            if (j > 0 && cpt_wait_ == 0) {
                move_to_down(inter);
            }
        }

    }

    /**
     *  
     */

    public int check_case(Game_screen inter) {
        Element e = null;
        Monstre b;
        int i = coord_.get_x();
        int j = coord_.get_y();
        if ((e = inter.getMap_().getElement(new Coordinates(i, j))) != null) {
            if (e.getType().equals("rupee") || (e.isIs_monster_())) {
                return 1;
            }
        }
        return 0;
    }

    public boolean check_case_move(Game_screen inter, int dir) {
        int i, j;
        Element e = null;

        if (dir / 10 == 1 || dir / 10 == -1) {
            if ((dir == 1 && coord_.get_x() == 15)
                    || (dir == -1 && coord_.get_x() == 0))
                return false;
            i = print_x_ / 16 + (dir / 10 + 1) / 2;
            j = (print_y_ + 8) / 16;
        } else {
            if ((dir == -2 && coord_.get_y() == 0)
                    || (dir == 2 && coord_.get_y() == 10))
                return false;
            i = (print_x_ + 8) / 16;
            j = print_y_ / 16 + (dir / 10 + 2) / 4;
        }
        e = inter.getMap_().getElement(new Coordinates(i, j));
        if (inter.getMap_().getDecor(new Coordinates(i, j)).is_obstacle()
                || (e != null && e.getType().equals("rupee")))
               // || inter.getMap_().getMonsters_(new Coord(i, j)) != null)

        {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#die()
     */
    public void die(Game_screen inter, Perso enemy) {
        int spawn = inter.getRandom_().nextInt(45);
        if (enemy instanceof Joueur) {
            Joueur enemy_ = (Joueur) enemy;
            enemy_.add_xp(xp_);
        }
        if (hide_ != null){
            hide_.setCoord(coord_.get_x(), coord_.get_y());
            inter.getMap_().setMonsters_(coord_);
            inter.getMap_().setElement(hide_);
        }
        else{
        if (spawn < 6) {
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(), "rupee",
                            null, null));
            inter.getMap_().setMonsters_(coord_);
        }
        if (spawn >= 6 && spawn < 9) {
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(),
                            "rupee_blue", null, null));
            inter.getMap_().setMonsters_(coord_);
        }
        if (spawn >= 9 && spawn < 13) {
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(),
                            "little_heart", null, null));
            inter.getMap_().setMonsters_(coord_);
        }
        if (spawn >= 13 && spawn < 19) {
            inter.getMap_().setElement(
                    new Element(false, coord_.get_x(), coord_.get_y(),
                            "little_heart_red", null, null));
            inter.getMap_().setMonsters_(coord_);
        }
        if (spawn >= 19)
            inter.getMap_().setMonsters_(coord_);
        }
    }

    public void draw(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/" + getType()
                        + "/mini_blob.png", inter), print_x_, print_y_, inter);
    }

    public void draw_med(Interface inter) {
        inter.getG2_()
                .drawImage(
                        getDemoImage("/resources/sprites/characters/" + getType()
                                + "/mini_blob_2.png", inter), print_x_,
                        print_y_, inter);
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

        int i = coord_.get_x();
        int j = coord_.get_y();

        if (check_case_move(inter, 20)) {
            if (coord_.get_y() + 1 <= 10) {
                if (print_y_ + 1 + 16 <= 11 * 16)
                    print_y_ += 1;

                //            e = inter.getMap_().getElement(
                //                    new Coord(coord_.get_x(), coord_.get_y() + 1));

                if ((print_y_ + 8) / 16 > coord_.get_y()
                        && inter.getMap_().getMonsters_(new Coordinates(i, j + 1)) == null) {
                    coord_.add_y(1);
//                    inter.getMap_().setMonsters_(
//                            new Coord(coord_.get_x(), coord_.get_y() - 1));
//                    inter.getMap_().setMonsters_(this);
                }
//>>>>>>> 1.52
            }
        }

    }

    public void move_to_left(Game_screen inter) {
        Element e;
//<<<<<<< Blob.java
//int i = coord_.get_x();
//int j = coord_.get_y();
//        
//                if (check_case_move(inter, -10)) {
//        if (coord_.get_x() - 1 >= 0) {
//            if (print_x_ - 1 >= 0)
//                print_x_ -= 1;
//
////            e = inter.getMap_().getElement(
////                    new Coord(coord_.get_x() - 1, coord_.get_y()));
//            if ((print_x_ + 8) / 16 < coord_.get_x() && inter.getMap_().getMonsters_(new Coord(i-1, j)) == null ){
//                coord_.add_x(-1);
//                inter.getMap_().setMonsters_(
//                        new Coord(coord_.get_x() + 1, coord_.get_y()));
//                inter.getMap_().setMonsters_(this);
//=======
        int i = coord_.get_x();
        int j = coord_.get_y();

        if (check_case_move(inter, -10)) {
            if (coord_.get_x() - 1 >= 0) {
                if (print_x_ - 1 >= 0)
                    print_x_ -= 1;

                //            e = inter.getMap_().getElement(
                //                    new Coord(coord_.get_x() - 1, coord_.get_y()));
                if ((print_x_ + 8) / 16 < coord_.get_x()
                        && inter.getMap_().getMonsters_(new Coordinates(i - 1, j)) == null) {
                    coord_.add_x(-1);
//                    inter.getMap_().setMonsters_(
//                            new Coord(coord_.get_x() + 1, coord_.get_y()));
//                    inter.getMap_().setMonsters_(this);
                }
//>>>>>>> 1.52
            }
        }
    }

    public void move_to_right(Game_screen inter) {
        Element e;
//<<<<<<< Blob.java
//int i = coord_.get_x();
//int j = coord_.get_y();
//        
//        
//                if (check_case_move(inter, 10)) {
//        if (coord_.get_x() + 1 <= 15) {
//            if (print_x_ + 1 + 16 <= 256)
//                print_x_ += 1;
//
////            e = inter.getMap_().getElement(
////                    new Coord(coord_.get_x() + 1, coord_.get_y()));
//            if ((print_x_ + 8) / 16 > coord_.get_x()&& inter.getMap_().getMonsters_(new Coord(i-1,j)) == null ) {
//                coord_.add_x(1);
//                inter.getMap_().setMonsters_(
//                        new Coord(coord_.get_x() - 1, coord_.get_y()));
//                inter.getMap_().setMonsters_(this);
//=======
        int i = coord_.get_x();
        int j = coord_.get_y();

        if (check_case_move(inter, 10)) {
            if (coord_.get_x() + 1 <= 15) {
                if (print_x_ + 1 + 16 <= 256)
                    print_x_ += 1;

                //            e = inter.getMap_().getElement(
                //                    new Coord(coord_.get_x() + 1, coord_.get_y()));
                if ((print_x_ + 8) / 16 > coord_.get_x()
                        && inter.getMap_().getMonsters_(new Coordinates(i + 1, j)) == null) {
                    coord_.add_x(1);
//                    inter.getMap_().setMonsters_(
//                            new Coord(coord_.get_x() - 1, coord_.get_y()));
//                    inter.getMap_().setMonsters_(this);
                }
//>>>>>>> 1.52
            }
        }
    }

    public void move_to_up(Game_screen inter) {
        Element e;
        int i = coord_.get_x();
        int j = coord_.get_y();
//<<<<<<< Blob.java
//        
//        
//                if (check_case_move(inter, -20)) {
//        if (coord_.get_y() - 1 >= 0) {
//            if (print_y_ - 1 >= 0)
//                print_y_ -= 1;
//
////            e = inter.getMap_().getElement(
////                    new Coord(coord_.get_x(), coord_.get_y() - 1));
//            if ((print_y_ + 8) / 16 < coord_.get_y() && inter.getMap_().getMonsters_(new Coord(i,j+1)) == null) {
//                coord_.add_y(-1);
//                inter.getMap_().setMonsters_(
//                        new Coord(coord_.get_x(), coord_.get_y() + 1));
//                inter.getMap_().setMonsters_(this);
//=======

        if (check_case_move(inter, -20)) {
            if (coord_.get_y() - 1 >= 0) {
                if (print_y_ - 1 >= 0)
                    print_y_ -= 1;

                //            e = inter.getMap_().getElement(
                //                    new Coord(coord_.get_x(), coord_.get_y() - 1));
                if ((print_y_ + 8) / 16 < coord_.get_y()
                        && inter.getMap_().getMonsters_(new Coordinates(i, j - 1)) == null) {
                    coord_.add_y(-1);
//                    inter.getMap_().setMonsters_(
//                            new Coord(coord_.get_x(), coord_.get_y() + 1));
//                    inter.getMap_().setMonsters_(this);
                }
//>>>>>>> 1.52
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#print()
     */
    public void print(Game_screen inter, String s) {

        Element e = null;
        if (cpt_rdm_ == 0) {
            direction_ = inter.getRandom_().nextInt(10);
            cpt_rdm_ = 5;
        } else
            cpt_rdm_--;
        attack(inter, 1, 1);
        draw(inter);
        move(inter, direction_);
        if (cpt_ == 0) {
            cpt_ = 25;
            draw(inter);
        } else if (cpt_ < 12) {
            draw_med(inter);
            cpt_ -= 1;
        } else {
            draw(inter);
            cpt_ -= 1;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#take_damage(screen.Game_screen, java.lang.String)
     */
    public void take_damage(Game_screen inter, String weapon, Perso enemy) {
        if (weapon.equals("staff"))
            health_-= 2;
        if (weapon.equals("sword"))
            health_ = 0;
        if (weapon.equals("sword_wood"))
        	health_-= 2;
        if (weapon.equals("arrow"))
            health_ = 0;
        if (weapon.equals("fire"))
            health_ = 0;
        if (weapon.equals("ice"))
            health_ = 0;
        if (weapon.equals("death"))
            health_ = 0;
        if (weapon.equals("bomb"))
            health_ = 0;
        if (health_ == 0)
            die(inter, enemy);
    }

    /**
     *  
     */
    public void watch() {
    }
}