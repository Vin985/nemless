/*
 * Created on 13 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import java.util.Random;

import old.fr.studioshi.dazel.game.items.Element;
import old.fr.studioshi.dazel.game.items.Projectile;
import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;

import fr.studioshi.common.game.model.Coordinates;
import fr.studioshi.dazel.game.sound.PlayThread;

/**
 * @author Administrateur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Giant_spider extends Monstre {

    protected boolean already_printed_;

    private int cpt_ = 1000;

    private int cpt_rdm_ = 6;

    protected boolean is_true_;

    private int limit_;

    private Giant_spider otherleft_;

    private Giant_spider otherright_;

    private int cpt_invinc_;
    
    /**
     * @param type
     * @param health
     * @param direction
     * @param under_attack
     * @param enemy_face
     * @param coord
     */
    public Giant_spider(String type, int health, int direction,
            boolean under_attack, boolean enemy_face, Coordinates coord) {
        super(type, health, direction, under_attack, enemy_face, coord);
        cpt_ = 100;
        is_true_ = true;
        print_x_ = print_x_ - 16;
        limit_ = 700;
        otherleft_ = null;
        otherright_ = null;
        cpt_invinc_ = 25;
    }
    
    public Giant_spider() {
        super("giant_spider", 15, 1, false, false, new Coordinates(0, 0));
        cpt_ = 100;
        is_true_ = true;
        print_x_ = print_x_ - 16;
        limit_ = 700;
        otherleft_ = null;
        otherright_ = null;
        cpt_invinc_ = 25;
    }

    public void attack(Game_screen inter) {
        Random r = new Random();
        int i = r.nextInt(100);

        if (i > 90)
            invoke(inter);
        else if (i > 75 && i < 90) {
            inter.getMap_().setWeapons_(
                    new Projectile(
                            new Coordinates(coord_.get_x(), coord_.get_y() + 1), 3,
                            coord_.get_x() * 16, (coord_.get_y() + 1) * 16,
                            this, "magician_fireball_blue", null));
        }
    }

    public int check_case_move_down(Game_screen inter, int x, int y) {

        if ((inter.getMap_().getElement(new Coordinates(x, y + 1)) == null)
                && (inter.getMap_().getElement(new Coordinates(x - 1, y + 1)) == null)
                && (inter.getMap_().getElement(new Coordinates(x + 1, y + 1)) == null)
                && (inter.getMap_().getMonsters_(new Coordinates(x, y + 1)) == null)
                && (inter.getMap_().getMonsters_(new Coordinates(x - 1, y + 1)) == null)
                && (inter.getMap_().getMonsters_(new Coordinates(x + 1, y + 1)) == null))
            return 0;
        else
            return 1;
    }

    public int check_case_move_left(Game_screen inter, int x, int y) {

        if (inter.getMap_().getElement(new Coordinates(x - 2, y)) == null
                && (inter.getMap_().getMonsters_(new Coordinates(x - 2, y)) == null))
            return 0;
        else
            return 1;
    }

    public int check_case_move_right(Game_screen inter, int x, int y) {

        if (inter.getMap_().getElement(new Coordinates(x + 2, y)) == null
                && (inter.getMap_().getMonsters_(new Coordinates(x + 2, y)) == null))
            return 0;
        else
            return 1;
    }

    public int check_case_move_up(Game_screen inter, int x, int y) {

        if ((inter.getMap_().getElement(new Coordinates(x + 1, y - 1)) == null)
                && (inter.getMap_().getElement(new Coordinates(x, y - 1)) == null)
                && (inter.getMap_().getElement(new Coordinates(x - 1, y - 1)) == null)
                && (inter.getMap_().getMonsters_(new Coordinates(x + 1, y - 1)) == null)
                && (inter.getMap_().getMonsters_(new Coordinates(x, y - 1)) == null)
                && (inter.getMap_().getMonsters_(new Coordinates(x - 1, y - 1)) == null))
            return 0;
        else
            return 1;
    }

    public void die(Game_screen inter, Perso enemy) {
        int i, j;
        Element e;

        if (inter.getAudio_() != null)
            inter.getAudio_().stop();
        if (inter.getOption_().isSound_on_()) {
            PlayThread t = new PlayThread(inter, 0);
        }
        if (enemy instanceof Joueur) {
            Joueur enemy_ = (Joueur) enemy;
            enemy_.add_xp(xp_);
        }
        for (i = 1; i < 15; i++)
            for (j = 1; j < 10; j++) {
                e = inter.getMap_().getMonsters_(new Coordinates(i, j));
                if (e != null && e.getType().equals("spider")) {
                    inter.getMap_().setMonsters_(new Coordinates(i, j));
                }
            }
        inter.getMap_().setMonsters_(coord_);
        inter.getMap_().setMonsters_(
               new Coordinates(coord_.get_x() - 1, coord_.get_y()));
        inter.getMap_().setMonsters_(
                new Coordinates(coord_.get_x() + 1, coord_.get_y()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#die()
     */
    public void draw(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/blue-giant-spider1.png", inter),
                print_x_, print_y_, inter);
    }

    public void draw_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/blue-giant-spider5.png", inter),
                print_x_, print_y_, inter);
    }

    /**
     * @return Returns the otherleft_.
     */
    public Giant_spider getOtherleft_() {
        return this.otherleft_;
    }

    /**
     * @return Returns the otherright_.
     */
    public Giant_spider getOtherright_() {
        return this.otherright_;
    }

    public void invoke(Game_screen inter) {
        int i = inter.getRandom_().nextInt(14) + 1;
        int j = inter.getRandom_().nextInt(9) + 1;

        while (inter.getMap_().getElement(new Coordinates(i, j)) != null
                && inter.getMap_().getMonsters_(new Coordinates(i, j)) != null) {
            i = inter.getRandom_().nextInt(13);
            j = inter.getRandom_().nextInt(8);
        }

        inter.getMap_()
                .setMonsters_(
                        new Spider("spider", 4, 1, false, false, false,
                                new Coordinates(i, j)));
    }

    /**
     * @return Returns the is_true_.
     */
    public boolean isIs_true_() {
        return this.is_true_;
    }

    public void move(Game_screen inter, int dir) {
        Element e;

        int i = coord_.get_x();
        int j = coord_.get_y();

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
        int i = coord_.get_x();
        int j = coord_.get_y();

        if (j + 1 < 10 && check_case_move_down(inter, i, j) == 0) {
            if (print_y_ + 2 + 16 <= 10 * 16)
                print_y_ += 2;
            if ((print_y_ + 8) / 16 > j) {
                coord_.add_y(1);
                otherright_.coord_.add_y(1);
                otherleft_.coord_.add_y(1);
            }
        }
    }

    public void move_to_left(Game_screen inter) {

        Element e;
        int i = coord_.get_x();
        int j = coord_.get_y();

        if (i - 2 > 0 && check_case_move_left(inter, i, j) == 0) {
            if (print_x_ - 2 >= 16)
                print_x_ -= 2;
            if ((print_x_ + 24) / 16 < coord_.get_x() /* && e == null */) {
                otherleft_.coord_.add_x(-1);
                coord_.add_x(-1);
                otherright_.coord_.add_x(-1);
            }
        }
    }

    public void move_to_right(Game_screen inter) {
        Element e;

        int i = coord_.get_x();
        int j = coord_.get_y();
        if (i + 2 < 15 && check_case_move_right(inter, i, j) == 0) {
            if (print_x_ + 2 + 48 <= 240)
                print_x_ += 2;
            if ((print_x_ + 24) / 16 > coord_.get_x()/* && e == null */) {
                otherright_.coord_.add_x(1);
                coord_.add_x(1);
                otherleft_.coord_.add_x(1);
            }
        }
    }

    public void move_to_up(Game_screen inter) {
        int i = coord_.get_x();
        int j = coord_.get_y();

        if (j - 2 > 0 && check_case_move_up(inter, i, j) == 0) {
            if (print_y_ - 2 >= 16)
                print_y_ -= 2;
            if ((print_y_ + 8) / 16 < j) {
                coord_.add_y(-1);
                otherright_.coord_.add_y(-1);
                otherleft_.coord_.add_y(-1);
            }
        }
    }

    public void print(Game_screen inter, String s) {

        if (is_true_) {
        	cpt_invinc_ --;
            if (cpt_rdm_ == 0) {
                direction_ = inter.getRandom_().nextInt(6);
                cpt_rdm_ = 5;
                draw(inter);
            } else
                cpt_rdm_--;

            move(inter, direction_);
            if (cpt_ == 0) {
                cpt_ = limit_;
                draw(inter);
            } else if (cpt_ < 400) {
                draw_med(inter);
                cpt_ -= 1;
            } else {
                draw(inter);
                cpt_ -= 1;
            }
        }
    }

    /**
     * @param is_true_
     *            The is_true_ to set.
     */
    public void setIs_true_(boolean is_true_) {
        this.is_true_ = is_true_;
    }

    /**
     * @param otherleft_
     *            The otherleft_ to set.
     */
    public void setOtherleft(Giant_spider otherleft_) {
        this.otherleft_ = otherleft_;
    }

    /**
     * @param otherright_
     *            The otherright_ to set.
     */
    public void setOtherright(Giant_spider otherright_) {
        this.otherright_ = otherright_;
    }

    public void take_damage(Game_screen inter, String weapon, Perso enemy) {
        if (is_true_ && cpt_invinc_ == 0) {
        	cpt_invinc_ = 25;
            if (weapon.equals("arrow"))
                health_--;
            limit_ -= 100;
            if (health_ == 0)
                die(inter, enemy);
        }
    }
}