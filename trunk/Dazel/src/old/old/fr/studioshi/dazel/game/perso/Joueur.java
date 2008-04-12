/*
 * Created on 3 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import java.awt.Font;
import java.util.Hashtable;

import old.fr.studioshi.dazel.game.items.Block;
import old.fr.studioshi.dazel.game.items.Bomb;
import old.fr.studioshi.dazel.game.items.Door;
import old.fr.studioshi.dazel.game.items.Element;
import old.fr.studioshi.dazel.game.items.Projectile;
import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;

import fr.studioshi.common.game.model.Coordinates;
import fr.studioshi.dazel.game.sound.PlayThread;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Joueur extends Perso {

    private boolean big_key_;

    private int bombs_;

    private boolean dead_;

    private Coordinates exit_;

    private int heal_;

    private int health_max_;

    private int keys_;

    private Coordinates last_main_;

    private int level_;

    private int lvl_up_;

    private int mana_;

    private int mana_max_;

    private Hashtable objects_;

    private int protect_;

    private int rupee_;

    private int shield_;

    private int spell_book_;

    private String[] spells_;

    private String suit_;

    private int sword_;

    private int take_cpt_;

    private String taken_;

    private int triforce_;

    private String used_weapon_;

    private String weapon_a_;

    private String weapon_b_;

    private int xp_;

    /**
     * @param type
     * @param health
     * @param direction
     * @param shield
     * @param sword
     * @param keys
     * @param triforce
     * @param xp
     * @param suit
     * @param rupee
     * @param mana
     * @param big_key
     * @param weapon_a
     * @param weapon_b
     * @param coord
     */
    public Joueur(String type, int health, int direction, int shield,
            int sword, int keys, int triforce, int xp, String suit, int rupee,
            int mana, boolean big_key, String weapon_a, String weapon_b,
            Coordinates coord, boolean obs) {
        super(type, health, direction, coord, obs);
        String[] spells = { "Fire", "Ice", "Death", "Heal", "Shield" };

        spells_ = spells;
        shield_ = shield;
        sword_ = sword;
        keys_ = keys;
        triforce_ = triforce;
        xp_ = xp;
        suit_ = suit;
        rupee_ = rupee;
        mana_ = mana;
        big_key_ = big_key;
        weapon_a_ = weapon_a;
        weapon_b_ = weapon_b;
        objects_ = new Hashtable();
        spell_book_ = 0;
        level_ = 1;
        bombs_ = 0;
        health_max_ = 12;
        mana_max_ = 8;
        heal_ = 0;
        lvl_up_ = 0;
        protect_ = 0;
        dead_ = false;
    }

    /**
     * @param i
     */
    public void add_xp(int i) {
        xp_ += i;
        if (xp_ > level_ * 100) {
            xp_ -= level_ * 100;
            level_++;
            if (level_ < 6) {
                health_max_ += 3;
                mana_max_ += 3;
            } else {
                health_max_ += 4;
                mana_max_ += 8;
            }
            lvl_up_ = 25;
            health_ = health_max_;
            mana_ = mana_max_;
        }
    }

    /**
     * @param inter
     * @param weapon
     */
    public void attack(Game_screen inter) {
        Monstre b = null;
        int i = (getCoord().get_x() + 8) / 16;
        int j = (getCoord().get_y() + 8) / 16;
        if (used_weapon_ != null) {

            // Bow : shoot arrows
            if (used_weapon_.equals("bow")) {
                inter.getMap_().setWeapons_(
                        new Projectile(new Coordinates(i, j), direction_, coord_
                                .get_x(), coord_.get_y(), this, "arrow", null));
                return;
            }
            // Blue potion : mana max
            if (used_weapon_.equals("potion_blue")) {
                mana_ = mana_max_;
                objects_.remove(new Integer(4));
                weapon_b_ = null;
            }
            // Red potion : health max
            if (used_weapon_.equals("potion_red")) {
                health_ = health_max_;
                objects_.remove(new Integer(3));
                weapon_b_ = null;
            }
            // Staff : magic if with the SpellBook && close combat
            if (used_weapon_.equals("staff") && spell_book_ > 0)
                magic(inter);

            // Bomb : explodes and do damage all around. Can break walls.
            if (used_weapon_.equals("bomb") && bombs_ > 0) {
                inter.getMap_().setWeapons_(
                        new Bomb(false, i, j, "bomb", null, null));
                bombs_--;
                return;
            }

            // Hit the monsters
            switch (direction_) {
            case 1:
                if (j > 0
                        && (b = inter.getMap_().getMonsters_(
                                new Coordinates(i, j - 1))) != null) {
                    b.take_damage(inter, used_weapon_, this);
                }
                break;
            case 2:
                if (i < 16
                        && (b = inter.getMap_().getMonsters_(
                                new Coordinates(i + 1, j))) != null) {
                    b.take_damage(inter, used_weapon_, this);
                }
                break;
            case 3:
                if (j < 11
                        && (b = inter.getMap_().getMonsters_(
                                new Coordinates(i, j + 1))) != null) {
                    b.take_damage(inter, used_weapon_, this);
                }
                break;
            case 4:
                if (i > 0
                        && (b = inter.getMap_().getMonsters_(
                                new Coordinates(i - 1, j))) != null) {
                    b.take_damage(inter, used_weapon_, this);
                }
                break;

            }
        }
    }

    /**
     * @param inter
     *            Check what is on the case where link stands
     */
    public void check_case(Game_screen inter) {
        Element e = null;
        Monstre b = null;
        int i = (getCoord().get_x() + 8) / 16;
        int j = (getCoord().get_y() + 8) / 16;

        if ((e = inter.getMap_().getElement(new Coordinates(i, j))) != null) {

            // Rupee : +1 rupee
            if (e.getType().equals("rupee")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                if (rupee_ != 99)
                    rupee_++;
            }
            // Blue rupee : +5 rupees
            if (e.getType().equals("rupee_blue")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                if (rupee_ + 5 < 100)
                    rupee_ += 5;
                else
                    rupee_ = 99;
            }

            //Bomb
            if (e.getType().equals("bomb") && !(e instanceof Bomb)) {
                inter.getMap_().setElement(new Coordinates(i, j));
                set_object(new Integer(2), "bomb");
                if (bombs_ != 15)
                    bombs_++;

            }

            // Blue potion : costs 50 Rupees. Can be found by sellers
            if (e.getType().equals("potion_blue")) {
                if (rupee_ >= 50) {
                    inter.getMap_().setElement(new Coordinates(i, j));
                    set_object(new Integer(4), "potion_blue");
                    inc_rupee(-50);
                    take_cpt_ = 30;
                    taken_ = "potion_blue";
                }
            }

            // Red potion : costs 30 Rupees. Can be found by sellers
            if (e.getType().equals("potion_red")) {
                if (rupee_ >= 30) {
                    inter.getMap_().setElement(new Coordinates(i, j));
                    set_object(new Integer(3), "potion_red");
                    inc_rupee(-30);
                    take_cpt_ = 30;
                    taken_ = "potion_red";
                }
            }

            // Bow
            if (e.getType().equals("bow")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                set_object(new Integer(0), "bow");
                take_cpt_ = 30;
                taken_ = "bow";
            }

            // SpellBook : give the ability to cast spells
            if (e.getType().equals("spellbook")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                set_object(new Integer(5), "spellbook");
                spell_book_ = 1;
                take_cpt_ = 30;
                taken_ = "spellbook";
            }

            // Staff
            if (e.getType().equals("staff")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                set_object(new Integer(1), "staff");
                take_cpt_ = 30;
                taken_ = "staff";
            }

            // Give one heart of life
            if (e.getType().equals("little_heart_red")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                if (health_ + 4 < health_max_)
                    health_ += 4;
                else
                    health_ = health_max_;
            }

            // Give 4 mana points
            if (e.getType().equals("little_heart")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                if (mana_ + 4 < mana_max_)
                    mana_ += 4;
                else
                    mana_ = mana_max_;
            }

            // Blue Suit : Better defense than the green one
            if (e.getType().equals("suit_blue")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                suit_ = "blue";
                take_cpt_ = 30;
                taken_ = "suit_blue";
            }

            // Red Suit : the best defense suit
            if (e.getType().equals("suit_red")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                suit_ = "red";
                take_cpt_ = 30;
                taken_ = "suit_red";
            }

            // A key
            if (e.getType().equals("key")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                keys_++;
            }

            // The Big key
            if (e.getType().equals("big_key")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                big_key_ = true;
                take_cpt_ = 30;
                taken_ = "big_key";
            }
            // The steel Sword
            if (e.getType().equals("sword")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                sword_ = 2;
                take_cpt_ = 30;
                taken_ = "sword";
                weapon_a_ = "sword";
            }
            // The wooden Sword
            if (e.getType().equals("sword_wood")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                sword_ = 1;
                take_cpt_ = 30;
                taken_ = "sword_wood";
                weapon_a_ = "sword_wood";
            }
            // The large shield
            if (e.getType().equals("shield")) {
                inter.getMap_().setElement(new Coordinates(i, j));
                shield_ = 1;
                take_cpt_ = 30;
                taken_ = "shield";
            }
            // A triforce part
            if (e.getType().equals("triforce_to_take")) {
                inter.getMap_().setElement(
                        new Element(false, i, j, "no_triforce", null, null));
                triforce_++;
                take_cpt_ = 30;
                taken_ = "triforce";
            }
        }
        // A monster : The player takes damages
        if ((b = inter.getMap_().getMonsters_(new Coordinates(i, j))) != null) {

            take_damage(b);
            b.setHas_hit_(true);
            switch (direction_) {
            case 1:
                if (getCoord().get_y() + 32 < 176)
                    coord_.add_y(16);
                else
                    coord_.set_y(160);
                break;
            case 2:
                if (getCoord().get_x() - 16 > 0)
                    coord_.add_x(-16);
                else
                    coord_.set_x(0);
                break;
            case 3:
                if (getCoord().get_y() - 16 > 0)
                    coord_.add_y(-16);
                else
                    coord_.set_y(0);
                break;
            case 4:
                if (getCoord().get_x() + 32 < 256)
                    coord_.add_x(16);
                else
                    coord_.set_x(240);
                break;
            }
        }

    }

    /**
     * @param inter
     * @param dir
     * @return Check if it's possible to move.
     */
    public boolean check_case_move(Game_screen inter, int dir) {
        int i = 0, j = 0;
        Element e = null;

        if (dir == 1 || dir == -1) {
            i = getCoord().get_x() / 16 + (dir + 1) / 2;
            j = (getCoord().get_y() + 10) / 16;
        } else {
            i = (getCoord().get_x() + 8) / 16;
            j = getCoord().get_y() / 16 + (dir + 2) / 4;
        }
        e = inter.getMap_().getDecor(new Coordinates(i, j));

        // An Entrance
        if (e.getType().equals("entrance")
                || e.getType().equals("stairs_green")
                || e.getType().equals("stairs_white")) {
            inter.setMap_(inter.getQuest_().getMap(e.getLink()));
            if (inter.get_Map_().getName().equals("donjon1")
                    || inter.get_Map_().getName().equals("donjon2")
                    || inter.get_Map_().getName().equals("donjon3")) {
                if (inter.getAudio_() != null)
                    inter.getAudio_().stop();
                if (inter.getOption_().isSound_on_()) {
                    PlayThread t = new PlayThread(inter, 0);
                }
            }
            if (inter.get_Map_().getName().equals("main")) {
                if (inter.getAudio_() != null)
                    inter.getAudio_().stop();
                if (inter.getOption_().isSound_on_()) {
                    PlayThread t = new PlayThread(inter, 3);
                }
            }
            if (e.getLink().equals("main")) {
                inter.setCurrent_map_(last_main_);
                inter.setMap_(inter.getMap_(inter.getCurrent_map_()));
                coord_ = exit_;
            } else {
                exit_ = coord_;
                last_main_ = inter.getCurrent_map_();
                inter.setCurrent_map_(new Coordinates(0, 0));
                inter.setMap_(inter.getMap_(inter.getCurrent_map_()));
                coord_ = new Coordinates(inter.get_Map_().getStartx() * 16, inter
                        .get_Map_().getStarty() * 16);
            }
            return true;
        }
        // Check if there's an obstacle
        if (e.is_obstacle()) {

            // Check if it's a Block then check if it's moveable
            if (e instanceof Block) {
                Block b = (Block) e;

                switch (dir) {
                case 1:
                    if ((b.getMoveable_() / 100) % 10 == 1) {
                        Element hide = b.getHide();
                        System.out.println(hide.getType());
                        b
                                .setHide(inter.getMap_().getDecor(
                                        new Coordinates(i + 1, j)));
                        b.setCoord(i + 1, j);
                        b.setMoveable(0);
                        inter.getMap_().setDecor(b);
                        inter.getMap_().setDecor(hide);
                        b = null;
                        e = null;
                        return false;
                    }
                    break;
                case -1:
                    if (b.getMoveable_() % 10 == 1) {
                        Element hide = b.getHide();
                        System.out.println(hide.getType());
                        hide.setCoord(i, j);
                        b
                                .setHide(inter.getMap_().getDecor(
                                        new Coordinates(i - 1, j)));
                        b.setCoord(i - 1, j);
                        b.setMoveable(0);
                        inter.getMap_().setDecor(b);
                        inter.getMap_().setDecor(hide);
                        b = null;
                        e = null;
                        return false;
                    }
                    break;
                case 2:
                    if ((b.getMoveable_() / 10) % 10 == 1) {
                        Element hide = b.getHide();
                        b
                                .setHide(inter.getMap_().getDecor(
                                        new Coordinates(i, j + 1)));
                        b.setCoord(i, j + 1);
                        b.setMoveable(0);
                        hide.setCoord(i, j);
                        inter.getMap_().setDecor(b);
                        inter.getMap_().setDecor(hide);
                        b = null;
                        e = null;
                        return false;
                    }
                    break;
                case -2:
                    if ((b.getMoveable_() / 1000) % 10 == 1) {
                        Element hide = b.getHide();
                        b
                                .setHide(inter.getMap_().getDecor(
                                        new Coordinates(i, j - 1)));
                        b.setCoord(i, j - 1);
                        b.setMoveable(0);
                        hide.setCoord(i, j);
                        inter.getMap_().setDecor(b);
                        inter.getMap_().setDecor(hide);
                        b = null;
                        e = null;
                        return false;
                    }
                    break;
                }
                b = null;
                e = null;
                return false;
            }

            // Check if it's a door then see if we can open it
            if (e instanceof Door) {
                Door b = (Door) e;
                if (!b.is_open()) {
                    if (keys_ > 0 || big_key_) {
                        if (!big_key_) {
                            keys_--;
                        }
                        b.setOpen(true);
                        inter.getMap_().setDecor(b.getCoord());
                        inter.getMap_().setDecor(b.getHide());
                        return true;
                    }
                    return false;
                }
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * @param inter
     * @param g2
     * @param s
     * @param x
     * @param y
     */
    public void draw(Interface inter, String s, int x, int y) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/" + getType() + "/"
                        + getType() + "_" + s + inter.getDirs_(direction_ - 1)
                        + "_" + suit_ + ".png", inter), getCoord().get_x() + x,
                getCoord().get_y() + y, inter);
    }

    /**
     * @param inter
     * 
     * Draw the hearts to show how much life the player has.
     *  
     */
    public void draw_heart(Interface inter) {

        int j = 48, i = 0, end = health_ / 4, y = 0;

        for (i = 0; i < end; i++) {
            if (i == 8) {
                y++;
                i = -1;
                end -= 8;
                continue;
            }
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/little_heart_red.png", inter),
                    j + 8 * i, 11 * 16 + y * 8, inter);
        }
        j += 8 * i;
        switch (health_ % 4) {
        case 1:
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/little_heart_quarter_red.png",
                            inter), j, 11 * 16 + y * 8, inter);
            break;
        case 2:
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/little_heart_half_red.png",
                            inter), j, 11 * 16 + y * 8, inter);
            break;
        case 3:
            inter
                    .getG2_()
                    .drawImage(
                            getDemoImage(
                                    "/resources/sprites/items/little_heart_three_quarters_red.png",
                                    inter), j, 11 * 16 + y * 8, inter);
            break;
        }
    }

    /**
     * @param g2
     * @param inter
     */
    public void draw_state(Interface inter) {

        // Rupees
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/items/rupee.png", inter), 0, 11 * 16,
                inter);
        String rupee = "x " + rupee_;
        inter.setFont_(new Font("Arial", Font.BOLD, 16));
        inter.getG2_().setFont(inter.getFont_());
        inter.getG2_().drawString(rupee, 16, 12 * 16);

        // Bombs
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/items/bomb.png", inter), 0, 12 * 16,
                inter);
        String bomb = "x " + bombs_;
        inter.getG2_().drawString(bomb, 16, 13 * 16);

        // Keys

        if (!big_key_) {
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/key.png", inter), 0, 13 * 16,
                    inter);
            String key = "x " + keys_;
            inter.getG2_().drawString(key, 16, 14 * 16);
        } else {
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/big_key.png", inter), 0,
                    13 * 16, inter);
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/infinite.png", inter), 16,
                    13 * 16, inter);
        }

        // Health
        draw_heart(inter);

        // Mana
        String mana = "mana: " + mana_;
        inter.setFont_(new Font("Arial", Font.BOLD, 11));
        inter.getG2_().setFont(inter.getFont_());
        inter.getG2_().drawString(mana, 128, 11 * 16 + 11);

        // Level
        String lvl = "level: " + level_;
        int nxt = level_ * 100 - xp_;
        String next = "next level: " + nxt;
        inter.getG2_().drawString(lvl, 192, 11 * 16 + 11);
        inter.getG2_().drawString(next, 128, 12 * 16 + 6);

        // Weapon A
        String weap_A = "A: ";
        inter.setFont_(new Font("Arial", Font.BOLD, 15));
        inter.getG2_().setFont(inter.getFont_());
        inter.getG2_().drawString(weap_A, 58, 13 * 16 + 8);
        inter.getG2_().drawImage(getDemoImage("/resources/sprites/case.png", inter), 73,
                12 * 17 - 2, inter);
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/items/" + weapon_a_ + ".png", inter),
                75, 12 * 17 - 1, inter);

        // Weapon B
        String weap_B = "B: ";
        inter.setFont_(new Font("Arial", Font.BOLD, 15));
        inter.getG2_().setFont(inter.getFont_());
        inter.getG2_().drawString(weap_B, 100, 13 * 16 + 8);
        inter.getG2_().drawImage(getDemoImage("/resources/sprites/case.png", inter), 116,
                12 * 17 - 2, inter);
        if (weapon_b_ != null)
            inter.getG2_()
                    .drawImage(
                            getDemoImage(
                                    "/resources/sprites/items/" + weapon_b_ + ".png",
                                    inter), 118, 12 * 17 - 1, inter);

        // Spell
        String spell = "Spell: ";
        inter.setFont_(new Font("Arial", Font.BOLD, 11));
        inter.getG2_().setFont(inter.getFont_());
        inter.getG2_().drawString(spell, 145, 13 * 16 + 8);
        if (spell_book_ > 0)
            inter.getG2_().drawString(spells_[spell_book_ - 1], 180,
                    13 * 16 + 8);

        // Level up
        if (lvl_up_ > 0) {
            inter.getG2_()
                    .drawImage(getDemoImage("/resources/sprites/items/level.png", inter),
                            coord_.get_x() - 15,
                            coord_.get_y() - (15 - lvl_up_), inter);
            lvl_up_--;
        }

        // Healing
        if (heal_ > 0) {
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/heal.png", inter),
                    coord_.get_x() + 6, coord_.get_y() - 11, inter);
            heal_--;
        }

        // Protect
        if (protect_ > 0) {
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/items/protect.png", inter),
                    coord_.get_x() + 6, coord_.get_y() - 11, inter);
            protect_--;
        }
    }

    /**
     * @return
     */
    public int get_bombs() {
        return bombs_;
    }

    /**
     * @return
     */
    public int get_keys() {
        return keys_;
    }

    /**
     * @return
     */
    public int get_mana() {
        return mana_;
    }

    /**
     * @param object
     * @return
     */
    public Object get_object(Integer object) {
        return objects_.get(object);
    }

    /**
     * @return
     */
    public int get_rupee() {
        return rupee_;
    }

    /**
     * @return
     */
    public int get_shield() {
        return shield_;
    }

    /**
     * @return
     */
    public String get_suit() {
        return suit_;
    }

    /**
     * @return
     */
    public int get_sword() {
        return sword_;
    }

    /**
     * @return
     */
    public int get_triforce() {
        return triforce_;
    }

    /**
     * @return
     */
    public String get_weapon_a() {
        return weapon_a_;
    }

    /**
     * @return
     */
    public String get_weapon_b() {
        return weapon_b_;
    }

    /**
     * @return
     */
    public int get_xp() {
        return xp_;
    }

    /**
     * @return Returns the exit_.
     */
    public Coordinates getExit_() {
        return this.exit_;
    }

    /**
     * @return Returns the health_max_.
     */
    public int getHealth_max_() {
        return this.health_max_;
    }

    /**
     * @return Returns the last_main_.
     */
    public Coordinates getLast_main_() {
        return this.last_main_;
    }

    /**
     * @return Returns the level_.
     */
    public int getLevel_() {
        return this.level_;
    }

    /**
     * @return Returns the mana_max_.
     */
    public int getMana_max_() {
        return this.mana_max_;
    }

    /**
     * @return Returns the spell_book_.
     */
    public int getSpell_book_() {
        return this.spell_book_;
    }

    /**
     * @return Returns the spells_.
     */
    public String getSpells_(int i) {
        return this.spells_[i];
    }

    /**
     * @return Returns the used_weapon_.
     */
    public String getUsed_weapon_() {
        return this.used_weapon_;
    }

    public void inc_rupee(int n) {
        rupee_ += n;
    }

    public void inc_spell(int i) {
        spell_book_ += i;
    }

    /**
     * @return
     */
    public boolean is_big_key() {
        return big_key_;
    }

    /**
     * @return Returns the big_key_.
     */
    public boolean isBig_key_() {
        return this.big_key_;
    }

    /**
     * @return Returns the dead_.
     */
    public boolean isDead_() {
        return this.dead_;
    }

    /**
     *  
     */
    public void magic(Game_screen inter) {
        int i = (getCoord().get_x() + 8) / 16;
        int j = (getCoord().get_y() + 8) / 16;

        if (spell_book_ == 1 && mana_ >= 4) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j), direction_, coord_.get_x(),
                            coord_.get_y(), this, "fire", null));
            mana_ -= 4;
        }
        if (spell_book_ == 2 && mana_ >= 6) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j), direction_, coord_.get_x(),
                            coord_.get_y(), this, "ice", null));
            mana_ -= 6;
        }
        if (spell_book_ == 4 && mana_ >= 10) {
            if (health_ + 8 < health_max_)
                health_ += 8;
            else
                health_ = health_max_;
            mana_ -= 10;
            heal_ = 20;
        }
        if (spell_book_ == 3 && mana_ >= 15) {
            inter.getMap_().setWeapons_(
                    new Projectile(new Coordinates(i, j), direction_, coord_.get_x(),
                            coord_.get_y(), this, "death", null));
            mana_ -= 15;
        }
        if (spell_book_ == 5 && mana_ >= 8) {
            protect_ = 400;
            mana_ -= 8;
        }
    }

    /**
     * @param inter
     */
    public void move(Game_screen inter) {
        if (inter.isKey_right_pressed_() && getAttack_stat_() == 0)
            if (isMoving_()) {
                if (direction_ == 2)
                    if (getCoord().get_x() + 3 + 16 < 256) {
                        if (check_case_move(inter, 1)) {
                            getCoord().add_x(3);
                            incMov_cpt();
                        }
                    } else
                        inter.scroll(1);
            } else {
                direction_ = 2;
                moving_ = true;
            }
        if (inter.isKey_left_pressed_() && getAttack_stat_() == 0)
            if (isMoving_()) {
                if (direction_ == 4)
                    if (getCoord().get_x() - 3 > 0) {
                        if (check_case_move(inter, -1)) {
                            getCoord().add_x(-3);
                            incMov_cpt();
                        }
                    } else
                        inter.scroll(-1);
            } else {
                direction_ = 4;
                moving_ = true;
            }
        if (inter.isKey_up_pressed_() && getAttack_stat_() == 0)
            if (isMoving_()) {
                if (direction_ == 1)
                    if (getCoord().get_y() - 3 > 0) {
                        if (check_case_move(inter, -2)) {
                            getCoord().add_y(-3);
                            incMov_cpt();
                        }
                    } else
                        inter.scroll(-2);
            } else {
                direction_ = 1;
                moving_ = true;
            }
        if (inter.isKey_down_pressed_() && getAttack_stat_() == 0)
            if (isMoving_()) {
                if (direction_ == 3)
                    if (getCoord().get_y() + 3 + 16 < 176) {
                        if (check_case_move(inter, 2)) {
                            getCoord().add_y(3);
                            incMov_cpt();
                        }
                    } else
                        inter.scroll(2);
            } else {
                direction_ = 3;
                moving_ = true;
            }
        if (!inter.isKey_down_pressed_() && direction_ == 3)
            moving_ = false;
        if (!inter.isKey_up_pressed_() && direction_ == 1)
            moving_ = false;
        if (!inter.isKey_right_pressed_() && direction_ == 2)
            moving_ = false;
        if (!inter.isKey_left_pressed_() && direction_ == 4)
            moving_ = false;
        check_case(inter);

    }

    /**
     * @param g2
     * @param inter
     */
    public void print(Game_screen inter) {
        int i = getMov_cpt();

        draw_state(inter);
        if (take_cpt_ > 0) {
            print_take(inter);
            return;
        }
        taken_ = null;
        move(inter);
        if (getAttack_stat_() % 100 > 0) {
            print_attack(inter);
            decAttack_stat_();
            return;
        } else
            setAttack_stat_(0);
        switch (direction_) {
        case 1:
            if (i <= 0) {
                draw(inter, "move_2_", 0, 0);
                setMov_cpt(15);
                break;
            }
            if (i > 7 && i < 15)
                draw(inter, "move_", 0, 0);
            else
                draw(inter, "move_2_", 0, 0);
            break;
        case 2:
            if (i <= 0) {
                draw(inter, "move_2_", 0, 0);
                setMov_cpt(15);
                break;
            }
            if (i > 7 && i < 15)
                draw(inter, "move_", 0, 0);
            else
                draw(inter, "move_2_", 0, 0);
            break;
        case 3:
            if (i <= 0) {
                draw(inter, "move_2_", 0, 0);
                setMov_cpt(15);
                break;
            }
            if (i > 7 && i < 15)
                draw(inter, "move_", 0, 0);
            else
                draw(inter, "move_2_", 0, 0);
            break;
        case 4:
            if (i <= 0) {
                draw(inter, "move_", 0, 0);
                setMov_cpt(15);
                break;
            }
            if (i > 7 && i < 15)
                draw(inter, "move_2_", 0, 0);
            else
                draw(inter, "move_", 0, 0);
            break;
        }
    }

    /**
     * @param g2
     * @param inter
     */
    private void print_attack(Interface inter) {
        int i = getAttack_stat_();
        switch (i / 100) {
        case 1:
            if (i % 100 > 6)
                draw(inter, "attack_", 0, 0);
            else if (used_weapon_ != null
                    && (used_weapon_.equals("sword")
                            || used_weapon_.equals("staff") || used_weapon_
                            .equals("sword_wood"))) {
                if (used_weapon_.equals("sword_wood"))
                    draw(inter, "attack_" + "sword" + "_", 0, -11);
                else
                    draw(inter, "attack_" + used_weapon_ + "_", 0, -11);
            } else {
                draw(inter, "move_", 0, 0);
                attack_stat_ = 0;
            }
            break;
        case 2:
            if (i % 100 > 6)
                draw(inter, "attack_", 0, 0);
            else if (used_weapon_ != null
                    && (used_weapon_.equals("sword")
                            || used_weapon_.equals("staff") || used_weapon_
                            .equals("sword_wood"))) {
                if (used_weapon_.equals("sword_wood"))
                    draw(inter, "attack_" + "sword" + "_", 0, 0);
                else
                    draw(inter, "attack_" + used_weapon_ + "_", 0, 0);
            } else {
                draw(inter, "move_", 0, 0);
                attack_stat_ = 0;
            }
            break;
        case 3:
            if (i % 100 > 6)
                draw(inter, "attack_", 0, 0);
            else if (used_weapon_ != null
                    && (used_weapon_.equals("sword")
                            || used_weapon_.equals("staff") || used_weapon_
                            .equals("sword_wood"))) {
                if (used_weapon_.equals("sword_wood"))
                    draw(inter, "attack_" + "sword" + "_", 0, 0);
                else
                    draw(inter, "attack_" + used_weapon_ + "_", 0, 0);
            } else {
                draw(inter, "move_", 0, 0);
                attack_stat_ = 0;
            }
            break;
        case 4:
            if (i % 100 > 6)
                draw(inter, "attack_", 0, 0);
            else if (used_weapon_ != null
                    && (used_weapon_.equals("sword")
                            || used_weapon_.equals("staff") || used_weapon_
                            .equals("sword_wood"))) {
                if (used_weapon_.equals("sword_wood"))
                    draw(inter, "attack_" + "sword" + "_", -11, 0);
                else
                    draw(inter, "attack_" + used_weapon_ + "_", -11, 0);
            } else {
                draw(inter, "move_", 0, 0);
                attack_stat_ = 0;
            }
            break;
        }

    }

    /**
     * @param inter
     */
    private void print_take(Game_screen inter) {
        if (take_cpt_ > 25)
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/characters/" + getType() + "/"
                            + getType() + "_get_" + suit_ + ".png", inter),
                    coord_.get_x(), coord_.get_y(), inter);
        else
            inter.getG2_().drawImage(
                    getDemoImage("/resources/sprites/characters/" + getType() + "/"
                            + getType() + "_get_2_" + suit_ + ".png", inter),
                    coord_.get_x(), coord_.get_y(), inter);
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/items/" + taken_ + ".png", inter),
                coord_.get_x(), coord_.get_y() - 19, inter);
        take_cpt_--;
    }

    /**
     * @param big_key
     */
    public void set_big_key(boolean big_key) {
        big_key_ = big_key;
    }

    /**
     * @param big_key
     */
    public void set_bombs(int bombs) {
        bombs_ = bombs;
    }

    /**
     * @param keys
     */
    public void set_keys(int keys) {
        keys_ = keys;
    }

    /**
     * @param mana
     */
    public void set_mana(int mana) {
        mana_ = mana;
    }

    /**
     * @param object
     * @param set
     */
    public void set_object(Integer key, String object) {
        objects_.put(key, object);
    }

    /**
     * @param rupee
     */
    public void set_rupee(int rupee) {
        rupee_ = rupee;
    }

    /**
     * @param shield
     */
    public void set_shield_(int shield) {
        shield_ = shield;
    }

    /**
     * @param suit
     */
    public void set_suit(String suit) {
        suit_ = suit;
    }

    /**
     * @param sword
     */
    public void set_sword(int sword) {
        sword_ = sword;
    }

    /**
     * @param triforce
     */
    public void set_triforce(int triforce) {
        triforce_ = triforce;
    }

    /**
     * @param weapon_a
     */
    public void set_weapon_a(String weapon_a) {
        weapon_a_ = weapon_a;
    }

    /**
     * @param weapon_b
     */
    public void set_weapon_b(String weapon_b) {
        weapon_b_ = weapon_b;
    }

    /**
     * @param xp
     */
    public void set_xp(int xp) {
        xp_ = xp;
    }

    /**
     * @param big_key_
     *            The big_key_ to set.
     */
    public void setBig_key_(boolean big_key_) {
        this.big_key_ = big_key_;
    }

    /**
     * @param dead_
     *            The dead_ to set.
     */
    public void setDead_(boolean dead_) {
        this.dead_ = dead_;
    }

    /**
     * @param exit_
     *            The exit_ to set.
     */
    public void setExit_(Coordinates exit_) {
        this.exit_ = exit_;
    }

    /**
     * @param health_max_
     *            The health_max_ to set.
     */
    public void setHealth_max_(int health_max_) {
        this.health_max_ = health_max_;
    }

    /**
     * @param last_main_
     *            The last_main_ to set.
     */
    public void setLast_main_(Coordinates last_main_) {
        this.last_main_ = last_main_;
    }

    /**
     * @param level_
     *            The level_ to set.
     */
    public void setLevel_(int level_) {
        this.level_ = level_;
    }

    /**
     * @param mana_max_
     *            The mana_max_ to set.
     */
    public void setMana_max_(int mana_max_) {
        this.mana_max_ = mana_max_;
    }

    /**
     * @param spell_book_
     *            The spell_book_ to set.
     */
    public void setSpell_book_(int spell_book_) {
        this.spell_book_ = spell_book_;
    }

    /**
     * @param spells_
     *            The spells_ to set.
     */
    public void setSpells_(String[] spells_) {
        this.spells_ = spells_;
    }

    /**
     * @param used_weapon_
     *            The used_weapon_ to set.
     */
    public void setUsed_weapon_(String used_weapon_) {
        this.used_weapon_ = used_weapon_;
    }

    public void take_damage(Game_screen inter, String s, Perso launcher) {
        int damage = 0;

        if (s.equals("bomb")) {
            damage = 4;
        }
        if (s.equals("pike_red")) {
            damage = 4;
        }
        if (s.equals("magician_fireball_blue")) {
            damage = 8;
        }
        if (s.equals("magician_fireball_red")) {
            damage = 32;
        }

        //      Check the suit Link is wearing
        if (suit_.equals("blue") && protect_ == 0)
            damage = damage / 2;
        if (suit_.equals("red") || protect_ > 0)
            damage = damage / 4;
        health_ -= damage;

        if (health_ <= 0) {
            dead_ = true;
        }

    }

    /**
     * @param e
     */
    public void take_damage(Monstre e) {

        int damage = 0;

        // If the enemy is a knight
        if (e.getType().equals("knight")) {
            Knight k = (Knight) e;
            if ((direction_ + k.getLooking_to_()) % 2 != 0)
                damage = 8;
            else
                damage = 8 - shield_ - 1;
        }

        // If the enemy is a blob
        if (e.getType().equals("blob")) {
            damage = 2;
        }

        // ganon
        if (e.getType().equals("ganon")) {
            damage = 16;
        }

        //giant_spider
        if (e.getType().equals("giant_spider")) {
            damage = 8;
        }

        // If the enemy is a spider
        if (e.getType().equals("spider")) {
            damage = 4;
        }

        // If the enemy is a magician
        if (e.getType().equals("magician")) {
            damage = 4;
        }

        // Check the suit Link is wearing
        if (suit_.equals("blue") && protect_ == 0)
            damage = damage / 2;
        if (suit_.equals("red") || protect_ > 0)
            damage = damage / 4;
        health_ -= damage;

        if (health_ <= 0) {
            dead_ = true;
        }
    }
}

