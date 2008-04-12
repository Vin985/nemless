package old.fr.studioshi.dazel.game.perso;

/*
 * Created on 20 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

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
public class Ganon extends Monstre {

    protected int air_magic_;

    protected boolean already_printed_;

    private Ganon bot_left_;

    private Ganon bot_right_;

    private int cpt_ = 500;

    protected int earth_shield_;

    protected int mutation_;

    private Ganon up_right_;

    private boolean is_true_;

    private int init_health_;

    /**
     * @param type
     * @param health
     * @param direction
     * @param under_attack
     * @param enemy_face
     * @param coord
     */
    public Ganon(String type, int health, int direction, boolean under_attack,
            boolean enemy_face, Coordinates coord) {
        super(type, health, direction, under_attack, enemy_face, coord);
        already_printed_ = false;
        air_magic_ = 0;
        earth_shield_ = 0;
        mutation_ = 1;
        cpt_ = 100;
        init_health_ = health;
        up_right_ = null;
        bot_left_ = null;
        bot_right_ = null;
        is_true_ = false;
    }

    public Ganon() {
        super("ganon", 30, 1, false, false, new Coordinates(0, 0));
        already_printed_ = false;
        air_magic_ = 0;
        earth_shield_ = 0;
        mutation_ = 1;
        cpt_ = 100;
        init_health_ = 30;
        up_right_ = null;
        bot_left_ = null;
        bot_right_ = null;
        is_true_ = false;
    }

    public void air_magic() {
        //        System.out.println("air");
        air_magic_ = 400;
    }

    public void attack(Game_screen inter) {
        int i = inter.getRandom_().nextInt(4);
        //System.out.println("i:" + i );
        switch (i) {
        case 0:
            if (earth_shield_ == 0 && air_magic_ == 0)
                earth_shield();
            else
                fire_attack(inter);
            break;
        case 1:
            if (air_magic_ == 0 && earth_shield_ == 0)
                air_magic();
            else
                ice_attack(inter);
            break;
        case 2:
            fire_attack(inter);
            break;
        case 3:
            ice_attack(inter);
            break;
        }

    }

    public int check_case_move(Game_screen inter, int x, int y) {

        if (inter.getMap_().getElement(new Coordinates(x, y)) == null
                && inter.getMap_().getMonsters_(new Coordinates(x, y)) == null
                && (inter.getMap_().getElement(new Coordinates(x + 1, y)) == null)
                && inter.getMap_().getMonsters_(new Coordinates(x + 1, y)) == null
                && (inter.getMap_().getElement(new Coordinates(x, y + 1)) == null)
                && inter.getMap_().getMonsters_(new Coordinates(x, y + 1)) == null
                && (inter.getMap_().getElement(new Coordinates(x + 1, y + 1)) == null)
                && inter.getMap_().getMonsters_(new Coordinates(x + 1, y + 1)) == null
                && (inter.getHeros_().getCoord().get_x() != x || inter
                        .getHeros_().getCoord().get_y() != y))
            return 0;
        else
            return 1;
    }

    public void decrease_health(Game_screen inter, int damage) {
        int i, j;
        Monstre b = null;
        for (i = 0; i < 16; i++)
            for (j = 0; j < 11; j++) {
                b = inter.getMap_().getMonsters_(new Coordinates(i, j));
                if (b != null && b instanceof Ganon) {
                    b.health_ -= damage;
                }
            }
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#die()
     */

    public void die(Game_screen inter, Perso enemy) {
        if (enemy instanceof Joueur) {
            Joueur enemy_ = (Joueur) enemy;
            enemy_.add_xp(xp_);
        }
        if (inter.getAudio_() != null)
            inter.getAudio_().stop();
        if (inter.getOption_().isSound_on_()) {
            PlayThread t = new PlayThread(inter, 0);
        }
        int i, j;
        Monstre b = null;
        for (i = 0; i < 16; i++)
            for (j = 0; j < 11; j++) {
                b = inter.getMap_().getMonsters_(new Coordinates(i, j));
                if (b != null && b instanceof Ganon) {
                    inter.getMap_().setMonsters_(new Coordinates(i, j));
                }
            }
    }

    public void draw(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/ganon1.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/ganon2.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_med2(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/ganon3.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_med3(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/ganon4.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_med4(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/ganon5.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_red(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/red_ganon1.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_red_med(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/red_ganon2.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_red_med2(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/red_ganon3.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_red_med3(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/red_ganon4.png", inter), print_x_,
                print_y_, inter);
    }

    public void draw_red_med4(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/boss/red_ganon5.png", inter), print_x_,
                print_y_, inter);
    }

    public void earth_shield() {
        System.out.println("esrth");
        earth_shield_ = 8;
    }

    /**
     * @return Returns the is_true_.
     */
    public boolean isIs_true_() {
        return this.is_true_;
    }

    /**
     * @param is_true_
     *            The is_true_ to set.
     */
    public void setIs_true_(boolean is_true_) {
        this.is_true_ = is_true_;
    }

    public void fire_attack(Game_screen inter) {
        int i = coord_.get_x();
        int j = coord_.get_y();
        int k = inter.getHeros_().getCoord().get_y() - coord_.get_y() * 16;
        int l = inter.getHeros_().getCoord().get_x() - coord_.get_x() * 16;
        int dir;

        dir = k - l;
        if (dir >= 0 && k >= 0 && k < 45 || (dir >= 0 && k < 0 && l < 0)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i - 1, j), 4,
                            (coord_.get_x() - 1) * 16, coord_.get_y() * 16,
                            this, "magician_fireball_red", null));
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i - 1, j + 1), 4,
                            (coord_.get_x() - 1) * 16,
                            (coord_.get_y() + 1) * 16, this,
                            "magician_fireball_red", null));
            return;
        }
        if (dir >= 0 && k >= 0 && l >= 0 || (dir >= 0 && k >= 45 && l < 0)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j + 2), 3, coord_.get_x() * 16,
                            (coord_.get_y() + 2) * 16, this,
                            "magician_fireball_red", null));
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i + 1, j + 2), 3,
                            (coord_.get_x() + 1) * 16,
                            (coord_.get_y() + 2) * 16, this,
                            "magician_fireball_red", null));
            return;
        }

        if (dir < 0 && k > 0 && l >= 0 || (dir < 0 && k < 0 && l >= 45)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i + 2, j), 2,
                            (coord_.get_x() + 2) * 16, coord_.get_y() * 16,
                            this, "magician_fireball_red", null));
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i + 2, j + 1), 2, (2 + coord_
                            .get_x()) * 16, (coord_.get_y() + 1) * 16, this,
                            "magician_fireball_red", null));

            return;
        }
        if (dir < 0 && k <= 0 && l >= 0 && l < 45
                || (dir < 0 && k < 0 && l < 0)) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j - 1), 1, coord_.get_x() * 16,
                            (coord_.get_y() - 1) * 16, this,
                            "magician_fireball_red", null));
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i + 1, j - 1), 1,
                            (coord_.get_x() + 1) * 16,
                            (coord_.get_y() - 1) * 16, this,
                            "magician_fireball_red", null));

            return;
        }
    }

    /**
     * @return
     */
    public int get_mutation() {
        return mutation_;
    }

    /**
     * @return Returns the bot_left_.
     */
    public Ganon getBot_left_() {
        return this.bot_left_;
    }

    /**
     * @return Returns the bot_right_.
     */
    public Ganon getBot_right_() {
        return this.bot_right_;
    }

    /**
     * @return Returns the up_right_.
     */
    public Ganon getUp_right_() {
        return this.up_right_;
    }

    public void ice_attack(Game_screen inter) {
        int i = coord_.get_x();
        int j = coord_.get_y();

        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i, j - 1), 1, coord_.get_x() * 16,
                        (coord_.get_y() - 1) * 16, this,
                        "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 1, j - 1), 1,
                        (coord_.get_x() + 1) * 16, (coord_.get_y() - 1) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i - 1, j - 2), 1,
                        (coord_.get_x() - 1) * 16, (coord_.get_y() - 2) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 2, j - 2), 1,
                        (coord_.get_x() + 2) * 16, (coord_.get_y() - 2) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 2, j), 2,
                        (coord_.get_x() + 2) * 16, coord_.get_y() * 16, this,
                        "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 3, j + 2), 2,
                        (coord_.get_x() + 3) * 16, (coord_.get_y() + 2) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 2, j + 1), 2,
                        (coord_.get_x() + 2) * 16, (coord_.get_y() + 1) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 3, j - 1), 2,
                        (coord_.get_x() + 3) * 16, (coord_.get_y() - 1) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i, j + 2), 3, coord_.get_x() * 16,
                        (coord_.get_y() + 2) * 16, this,
                        "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 1, j + 2), 3,
                        (coord_.get_x() + 1) * 16, (coord_.get_y() + 2) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i + 2, j + 3), 3,
                        (coord_.get_x() + 2) * 16, (coord_.get_y() + 3) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i - 1, j + 3), 3,
                        (coord_.get_x() - 1) * 16, (coord_.get_y() + 3) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i - 1, j), 4,
                        (coord_.get_x() - 1) * 16, coord_.get_y() * 16, this,
                        "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i - 2, j + 2), 4,
                        (coord_.get_x() - 2) * 16, (coord_.get_y() + 2) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i - 1, j + 1), 4,
                        (coord_.get_x() - 1) * 16, (coord_.get_y() + 1) * 16,
                        this, "magician_fireball_blue", null));
        inter.getMap_().setWeapons_(
                new Projectile(new Coordinates(i - 2, j - 1), 4,
                        (coord_.get_x() - 2) * 16, (coord_.get_y() - 1) * 16,
                        this, "magician_fireball_blue", null));
    }

    public void invoke(Game_screen inter) {
        int i = inter.getRandom_().nextInt(15);
        int j = inter.getRandom_().nextInt(10);
        int r = inter.getRandom_().nextInt(5);

        while (check_case_move(inter, i, j) != 0) {
            i = inter.getRandom_().nextInt(7);
            j = inter.getRandom_().nextInt(9);
        }
        switch (r) {
        case 0:
            inter.getMap_().setMonsters_(
                    new Spider("spider", 4, 1, false, false, false, new Coordinates(
                            i, j)));
            break;
        case 1:
            inter.getMap_().setMonsters_(
                    new Blob("blob", 4, 1, false, false, new Coordinates(i, j)));
            break;
        case 2:
            inter.getMap_().setMonsters_(
                    new Knight("knight", 4, 1, false, false, new Coordinates(i, j)));
            break;
        case 3:
            inter.getMap_().setMonsters_(
                    new Magician("magician", 4, 1, false, false,
                            new Coordinates(i, j), true));
            break;
        case 4:
            inter.getMap_().setMonsters_(
                    new Moblin("moblin", 4, 1, false, false, new Coordinates(i, j)));
        }

    }

    /**
     *  
     */
    public void move(Game_screen inter) {
        int i, j;

        i = inter.getRandom_().nextInt(12);
        j = inter.getRandom_().nextInt(8);
        while (check_case_move(inter, i, j) != 0 || i <= 1 || j <= 1) {
            i = inter.getRandom_().nextInt(12);
            j = inter.getRandom_().nextInt(8);
        }
        coord_.set_x(i);
        coord_.set_y(j);
        print_x_ = coord_.get_x() * 16;
        print_y_ = coord_.get_y() * 16;
        bot_left_.coord_.set_x(i);
        bot_left_.coord_.set_y(j + 1);
        bot_right_.coord_.set_x(i + 1);
        bot_right_.coord_.set_y(j + 1);
        up_right_.coord_.set_x(i + 1);
        up_right_.coord_.set_y(j);

    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#print()
     */
    public void print(Game_screen inter, String s) {

        if (health_ <= init_health_ / 2 && mutation_ == 1)
            set_mutation(2);
        if (is_true_) {
            if (earth_shield_ != 0) {
                inter.getG2_().drawImage(
                        getDemoImage("/Sprites/boss/ganon_protect.png", inter),
                        (coord_.get_x()) * 16 + 13, (coord_.get_y() - 1) * 16,
                        inter);
                inter.getG2_()
                        .drawImage(
                                getDemoImage("/Sprites/boss/ganon_shield.png",
                                        inter), (coord_.get_x()) * 16 - 7,
                                (coord_.get_y()) * 16, inter);
            }
            if (air_magic_ != 0) {
                inter.getG2_().drawImage(
                        getDemoImage("/Sprites/boss/ganon_eclair.png", inter),
                        (coord_.get_x()) * 16 + 13, (coord_.get_y() - 1) * 16,
                        inter);
                inter.getG2_()
                        .drawImage(
                                getDemoImage("/Sprites/boss/ganon_wind.png",
                                        inter), (coord_.get_x()) * 16 - 7,
                                (coord_.get_y()) * 16, inter);
            }
            if (mutation_ == 1) {
                if (air_magic_ == 0) {
                    if (cpt_ == 0) {
                        cpt_ = 100;
                        move(inter);
                    }
                    if (cpt_ >= 80)
                        draw(inter);
                    if (cpt_ < 80 && cpt_ >= 60)
                        draw_med(inter);
                    if (cpt_ < 60 && cpt_ >= 40) {
                        if (cpt_ == 50)
                            attack(inter);
                        draw_med2(inter);
                    }
                    if (cpt_ < 40 && cpt_ >= 20)
                        draw_med3(inter);
                    if (cpt_ < 20 && cpt_ > 0)
                        draw_med4(inter);
                    cpt_--;
                } else {
                    air_magic_--;
                    if (cpt_ == 0) {
                        cpt_ = 50;
                        move(inter);
                    }
                    if (cpt_ >= 40)
                        draw(inter);
                    if (cpt_ < 40 && cpt_ >= 30)
                        draw_med(inter);
                    if (cpt_ < 30 && cpt_ >= 20) {
                        if (cpt_ == 25)
                            attack(inter);
                        draw_med2(inter);
                    }
                    if (cpt_ < 20 && cpt_ >= 10)
                        draw_med3(inter);
                    if (cpt_ < 10 && cpt_ > 0)
                        draw_med4(inter);
                    cpt_--;
                }
            }
            if (mutation_ == 2) {
                if (earth_shield_ != 0)
                    earth_shield_ = 0;
                if (cpt_ == 0) {
                    cpt_ = 50;
                    move(inter);
                }
                if (cpt_ >= 40)
                    draw_red(inter);
                if (cpt_ < 40 && cpt_ >= 30)
                    draw_red_med(inter);
                if (cpt_ < 30 && cpt_ >= 20) {
                    if (cpt_ == 25)
                        super_attack(inter);
                    draw_red_med2(inter);
                }
                if (cpt_ < 20 && cpt_ >= 10)
                    draw_red_med3(inter);
                if (cpt_ < 10 && cpt_ > 0)
                    draw_red_med4(inter);
                cpt_--;

            }
        }
    }

    /**
     * @param mutation
     */
    public void set_mutation(int mutation) {
        mutation_ = mutation;
    }

    /**
     * @param bot_left_
     *            The bot_left_ to set.
     */
    public void setBot_left(Ganon bot_left_) {
        this.bot_left_ = bot_left_;
    }

    /**
     * @param bot_right_
     *            The bot_right_ to set.
     */
    public void setBot_right(Ganon bot_right_) {
        this.bot_right_ = bot_right_;
    }

    /**
     * @param up_right_
     *            The up_right_ to set.
     */
    public void setUp_right(Ganon up_right_) {
        this.up_right_ = up_right_;
    }

    public void super_attack(Game_screen inter) {
        int i = inter.getRandom_().nextInt(8);

        if (i <= 2) {
            invoke(inter);
        } else if (i <= 4)
            fire_attack(inter);
        else
            ice_attack(inter);
    }

    public void take_damage(Game_screen inter, String weapon, Perso enemy) {

        if (mutation_ == 1) {
            if (earth_shield_ != 0) {
                earth_shield_--;
                if (weapon.equals("sword"))
                    decrease_health(inter, 1);
            } else if (earth_shield_ == 0 && weapon.equals("sword"))
                decrease_health(inter, 2);
        } else {

            if (weapon.equals("death"))
                decrease_health(inter, 2);
        }
        if (health_ <= init_health_ / 2 && mutation_ == 1) {
            set_mutation(2);
            air_magic();
        }
        if (health_ <= 0)
            die(inter, enemy);
    }

}

