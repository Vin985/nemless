/*
 * Created on 11 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import old.fr.studioshi.dazel.game.items.Element;
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
public class Moblin extends Monstre {

    private int cpt_;

    private int cpt_flee_;

    private int cpt_rdm_;

    private int cpt_wait_;

    private char looking_to_;

    private int xp_;

    public Moblin(String type, int health, int direction, boolean under_attack,
            boolean enemy_face, Coordinates coord) {
        super(type, health, direction, under_attack, enemy_face, coord);
        looking_to_ = 'd';
        setHas_hit_(false);
        cpt_wait_ = 5;
        xp_ = 30;
        cpt_flee_ = 5;
    }

    public Moblin() {
        super("moblin", 8, 1, false, false, new Coordinates(0, 0));
        looking_to_ = 'd';
        setHas_hit_(false);
        cpt_wait_ = 5;
        xp_ = 30;
        cpt_flee_ = 5;
    }

    /**
     *  
     */

    public void attack(Game_screen inter) {
        int i = coord_.get_x();
        int j = coord_.get_y();
        int k, l;
        int dir;

        k = inter.getHeros_().getCoord().get_y() - coord_.get_y() * 16;
        l = inter.getHeros_().getCoord().get_x() - coord_.get_x() * 16;
        dir = k - l;
        if (dir >= 0 && k >= 0 && k < 45 || (dir >= 0 && k < 0 && l < 0)) {
            looking_to_ = 'l';
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i - 1, j), 4,
                            (coord_.get_x() - 1) * 16, coord_.get_y() * 16,
                            this, "pike_red", null));
            return;
        }
        if (dir >= 0 && k >= 0 && l >= 0 || (dir >= 0 && k >= 45 && l < 0)) {
            looking_to_ = 'd';
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j + 1), 3, coord_.get_x() * 16,
                            (coord_.get_y() + 1) * 16, this, "pike_red", null));
            return;
        }

        if (dir < 0 && k > 0 && l >= 0 || (dir < 0 && k < 0 && l >= 45)) {
            looking_to_ = 'r';
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i + 1, j), 2,
                            (coord_.get_x() + 1) * 16, coord_.get_y() * 16,
                            this, "pike_red", null));
            return;
        }
        if (dir < 0 && k <= 0 && l >= 0 && l < 45
                || (dir < 0 && k < 0 && l < 0)) {
            looking_to_ = 'u';
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j - 1), 1, coord_.get_x() * 16,
                            (coord_.get_y() - 1) * 16, this, "pike_red", null));
            return;
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

        if (dir == 1 || dir == -1) {
            if ((dir == 1 && coord_.get_x() == 15)
                    || (dir == -1 && coord_.get_x() == 0))
                return false;
            i = print_x_ / 16 + (dir + 1) / 2;
            j = (print_y_ + 8) / 16;
        } else {
            if ((dir == -2 && coord_.get_y() == 0)
                    || (dir == 2 && coord_.get_y() == 10))
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
        case 'u':
            draw_up(inter);
            break;
        case 'd':
            draw_down(inter);
            break;
        case 'l':
            draw_left(inter);
            break;
        case 'r':
            draw_right(inter);
            break;
        }
    }

    public void draw_down(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/moblin/moblin_down.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_down_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/moblin/moblin_down_2.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_left(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/moblin/moblin_left.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_left_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/moblin/moblin_left_2.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_med(Interface inter) {
        switch (looking_to_) {
        case 'u':
            draw_up_med(inter);
            break;
        case 'd':
            draw_down_med(inter);
            break;
        case 'l':
            draw_left_med(inter);
            break;
        case 'r':
            draw_right_med(inter);
            break;
        }
    }

    public void draw_right(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/moblin/moblin_right.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_right_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/moblin/moblin_right_2.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_up(Interface inter) {
        inter.getG2_()
                .drawImage(
                        getDemoImage(
                                "/resources/sprites/characters/moblin/moblin_up.png",
                                inter), print_x_, print_y_, inter);
    }

    public void draw_up_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/moblin/moblin_up_2.png",
                        inter), print_x_, print_y_, inter);
    }

    public void flee(Game_screen inter, int len, int larg) {
        Element e = null;
        int i = inter.getHeros_().getCoord().get_x() - ((coord_.get_x()) * 16);
        int j = inter.getHeros_().getCoord().get_y() - ((coord_.get_y()) * 16);

        if (i <= len * 20 && i >= -52 * len && j <= larg * 20
                && j >= -52 * larg) {
            if (i < 0) {
                move_to_right(inter);
            }
            if (j > 0) {
                move_to_up(inter);
            }
            if (i > 0) {
                move_to_left(inter);
            }
            if (j < 0) {
                move_to_down(inter);
            }
        }

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
                if ((print_y_ + 8) / 16 > coord_.get_y()
                        && e == null
                        && inter.getMap_().getMonsters_(
                                new Coordinates(coord_.get_x(), coord_.get_y() + 1)) == null) {
                    coord_.add_y(1);
                    //                    inter.getMap_().setMonsters_(new Coord(coord_.get_x(),
                    // coord_.get_y() - 1));
                    //                    inter.getMap_().setMonsters_(this);
                }
            }

            looking_to_ = 'd';
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
                if ((print_x_ + 8) / 16 < coord_.get_x()
                        && e == null
                        && inter.getMap_().getMonsters_(
                                new Coordinates(coord_.get_x() - 1, coord_.get_y())) == null) {
                    coord_.add_x(-1);
                    //                    inter.getMap_().setMonsters_(this);
                    //                    inter.getMap_().setMonsters_(new Coord(coord_.get_x() +
                    // 1, coord_.get_y()));
                }
            }

            looking_to_ = 'l';
        }

    }

    public void move_to_right(Game_screen inter) {
        Element e;

        if (check_case_move(inter, 1)) {
            if (coord_.get_x() + 1 <= 15) {
                if (print_x_ + 1 + 16 <= 256)
                    print_x_ += 1;

                e = inter.getMap_().getElement(
                        new Coordinates(coord_.get_x() + 1, coord_.get_y()));
                if ((print_x_ + 8) / 16 > coord_.get_x()
                        && e == null
                        && inter.getMap_().getMonsters_(
                                new Coordinates(coord_.get_x() + 1, coord_.get_y())) == null) {
                    coord_.add_x(1);
                    //                    inter.getMap_().setMonsters_(this);
                    //                    inter.getMap_().setMonsters_(new Coord(coord_.get_x() -
                    // 1, coord_.get_y()));
                }
            }

            looking_to_ = 'r';
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
                if ((print_y_ + 8) / 16 < coord_.get_y()
                        && e == null
                        && inter.getMap_().getMonsters_(
                                new Coordinates(coord_.get_x(), coord_.get_y() - 1)) == null) {
                    coord_.add_y(-1);
                    //                    inter.getMap_().setMonsters_(this);
                    //                    inter.getMap_().setMonsters_(new Coord(coord_.get_x(),
                    // coord_.get_y() + 1));
                }
            }

            looking_to_ = 'd';
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
        if (cpt_ == 40) {
            attack(inter);
            draw(inter);
            while (cpt_ >= 20)
                cpt_--;
        }
        if (under_attack_ == true) {
            flee(inter, 3, 3);
            draw(inter);
            cpt_flee_--;
            if (cpt_flee_ == 0) {
                under_attack_ = false;
                cpt_flee_ = 100;
            }

        }
        draw(inter);
        move(inter, direction_);
        if (cpt_ == 0) {
            cpt_ = 75;
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

    public void take_damage(Game_screen inter, String weapon, Perso enemy) {
        call(inter);
        int i = inter.getRandom_().nextInt(100);
        under_attack_ = true;
        if (weapon.equals("staff"))
            health_--;
        if (weapon.equals("sword"))
            health_ -= 4;
        if (weapon.equals("sword_wood"))
            health_ -= 3;
        if (weapon.equals("arrow"))
            health_ -= 2;
        if (weapon.equals("fire"))
            health_ -= 4;
        if (weapon.equals("ice"))
            health_ -= 4;
        if (weapon.equals("death") && i <= 70)
            health_ = 0;
        if (weapon.equals("bomb"))
            health_ = 0;
        if (health_ <= 0)
            die(inter, enemy);
    }

}