/*
 * Created on 10 juil. 2004
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
public class Magician extends Monstre {

    private char color_;

    private int cpt_;

    private int cpt_rdm_;

    protected boolean is_true_;

    /**
     * @param type
     * @param health
     * @param direction
     * @param under_attack
     * @param enemy_face
     * @param coord
     */
    public Magician(String type, int health, int direction,
            boolean under_attack, boolean enemy_face, Coordinates coord, boolean real) {
        super(type, health, direction, under_attack, enemy_face, coord);
        is_true_ = real;
        cpt_ = 100;
        cpt_rdm_ = 5;
        color_ = 'b';
        xp_ = 50;
    }

    public Magician() {
        super("magician", 12, 1, false, false, new Coordinates(0, 0));
        is_true_ = false;
        cpt_ = 100;
        cpt_rdm_ = 5;
        color_ = 'b';
        xp_ = 50;
    }
    
    /**
     *  
     */
    public synchronized void attack(Game_screen inter) {
        int i = coord_.get_x();
        int j = coord_.get_y();
        int k, l;
        int dir;

        k = inter.getHeros_().getCoord().get_y() - j * 16;
        l = inter.getHeros_().getCoord().get_x() - i * 16;
        dir = k - l;
        if (dir >= 0 && k >= 0 && k < 45 || (dir >= 0 && k < 0 && l < 0)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i - 1, j), 4,
                            (i - 1) * 16, j * 16,
                            this, "magician_fireball_blue", null));
            return;
        }
        if (dir >= 0 && k >= 0 && l >= 0 || (dir >= 0 && k >= 45 && l < 0)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j + 1), 3, i * 16,
                            (j + 1) * 16, this,
                            "magician_fireball_blue", null));
            return;
        }

        if (dir < 0 && k > 0 && l >= 0 || (dir < 0 && k < 0 && l >= 45)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i + 1, j), 2,
                            (i + 1) * 16, j * 16,
                            this, "magician_fireball_blue", null));
            return;
        }
        if (dir < 0 && k <= 0 && l >= 0 && l < 45
                || (dir < 0 && k < 0 && l < 0)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j - 1), 1, i * 16,
                            (j - 1) * 16, this,
                            "magician_fireball_blue", null));
            return;
        }
    }

    public synchronized int check_case_move(Game_screen inter, int x, int y) {
        Element e = null;

        e = inter.getMap_().getElement(new Coordinates(x, y));

        if (e == null
                && ((inter.getHeros_().getCoord().get_x() + 8) / 16 != x || (inter
                        .getHeros_().getCoord().get_y() + 8) / 16 != y)
						&& inter.getMap_().getMonsters_(new Coordinates(x,y)) == null)
            return 0;
        else
            return 1;
    }

    public void die(Game_screen inter, Perso enemy) {
        int i, j;
        Element e;

        if (enemy instanceof Joueur) {
            Joueur enemy_ = (Joueur) enemy;
            enemy_.add_xp(xp_);
        }
        for (i = 0; i < 16; i++)
            for (j = 0; j < 11; j++) {
                e = inter.getMap_().getMonsters_(new Coordinates(i, j));
                if (e != null && e.getType().equals("magician")) {
                    inter.getMap_().setMonsters_(new Coordinates(i, j));
                }
            }
        inter.getMap_().setMonsters_(coord_);
    }

    public void draw_blue(Interface inter, int i, int j) {
        if (i < 0)
            draw_down_blue(inter);
        else if (j <= 0)
            draw_left_blue(inter);
        else
            draw_right_blue(inter);
    }

    public void draw_down_blue(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage(
                        "/resources/sprites/characters/magician/magician_up_blue.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_down_red(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage(
                        "/resources/sprites/characters/magician/magician_up_red.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_left_blue(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage(
                        "/resources/sprites/characters/magician/magician_left_blue.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_left_med_blue(Interface inter) {
        inter
                .getG2_()
                .drawImage(
                        getDemoImage(
                                "/resources/sprites/characters/magician/magician_2_left_blue.png",
                                inter), print_x_, print_y_, inter);
    }

    public void draw_left_med_red(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage(
                        "/resources/sprites/characters/magician/magician_2_left_red.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_left_red(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage(
                        "/resources/sprites/characters/magician/magician_left_red.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_red(Interface inter, int i, int j) {
        if (i < 0)
            draw_down_red(inter);
        else if (j <= 0)
            draw_left_red(inter);
        else
            draw_right_red(inter);
    }

    public void draw_right_blue(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage(
                        "/resources/sprites/characters/magician/magician_right_blue.png",
                        inter), print_x_, print_y_, inter);
    }

    public void draw_right_med_blue(Interface inter) {
        inter
                .getG2_()
                .drawImage(
                        getDemoImage(
                                "/resources/sprites/characters/magician/magician_2_right_blue.png",
                                inter), print_x_, print_y_, inter);
    }

    public void draw_right_med_red(Interface inter) {
        inter
                .getG2_()
                .drawImage(
                        getDemoImage(
                                "/resources/sprites/characters/magician/magician_2_right_red.png",
                                inter), print_x_, print_y_, inter);
    }

    public void draw_right_red(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage(
                        "/resources/sprites/characters/magician/magician_right_red.png",
                        inter), print_x_, print_y_, inter);
    }

    /**
     * @return
     */
    public boolean get_true() {
        return is_true_;
    }

    public void setReal(boolean r)
    {
    	is_true_ = r;
    }
    
    public synchronized void move(Game_screen inter) {
        int i, j;
        int prec_x, prec_y;

        prec_x = coord_.get_x();
        prec_y = coord_.get_y();
        i = inter.getRandom_().nextInt(15);
        j = inter.getRandom_().nextInt(10);
        while (check_case_move(inter, i, j) != 0) {
            i = inter.getRandom_().nextInt(15);
            j = inter.getRandom_().nextInt(10);
        }
        coord_.set_x(i);
        coord_.set_y(j);
        print_x_ = coord_.get_x() * 16;
        print_y_ = coord_.get_y() * 16;
        inter.getMap_().setMonsters_(this);
        inter.getMap_().setMonsters_(new Coordinates(prec_x, prec_y));
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#print()
     */
    public synchronized void print(Game_screen inter, String s) {

        int i = inter.getHeros_().getCoord().get_y() - coord_.get_y() * 16;
        int j = inter.getHeros_().getCoord().get_x() - coord_.get_x() * 16;

        if (cpt_ == 0) {
            cpt_ = 100;
            color_ = 'b';
            move(inter);
        }
        if (cpt_ == 50) {
            attack(inter);
        }
        cpt_--;
        if (color_ == 'b')
            draw_blue(inter, i, j);
        else
            draw_red(inter, i, j);
    }

    /**
     * @param set
     */
    public void set_true(boolean set) {
        is_true_ = set;
    }

    public void take_damage(Game_screen inter, String weapon, Perso enemy) {
        //        System.out.println(weapon);
        if (is_true_) {
        	
        	int i = inter.getRandom_().nextInt(100);
            color_ = 'r';
            if (weapon.equals("staff"))
                health_--;
            if (weapon.equals("sword"))
                health_ -= 6;
            if (weapon.equals("sword_wood"))
            	health_-= 2;
            if (weapon.equals("arrow"))
                health_ --;
            if (weapon.equals("death") && i <= 40)
                health_ = 0;
            if (health_ == 0)
                die(inter, enemy);
        }
    }

    /**
     *  
     */
    public void watch() {
    }

}