/*
 * Created on 3 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import old.fr.studioshi.dazel.game.items.Map;
import old.fr.studioshi.dazel.game.items.Minimap;
import old.fr.studioshi.dazel.game.items.Quest;
import old.fr.studioshi.dazel.game.perso.Ganon;
import old.fr.studioshi.dazel.game.perso.Giant_spider;
import old.fr.studioshi.dazel.game.perso.Joueur;

import fr.studioshi.common.game.model.Coordinates;
import fr.studioshi.dazel.game.sound.PlayThread;
import fr.studioshi.dazel.game.sound.SoundList;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Game_screen extends Interface {

    protected Coordinates current_map_;

    private int dead_;

    private Joueur heros_;

    private Minimap map_;

    private Map Map_;

    private Quest quest_;

    public Game_screen(JFrame fr, Map map, Options option, SoundList sl) {
        super(fr);

        p = new PrintThread();
        Map_ = map;
        option_ = option;
        soundList_ = sl;
        current_map_ = new Coordinates(0, 0);
        setBackground(Color.black);
        setForeground(Color.white);
        //type, health, direction, shield, sword, keys, triforce, xp, suit,
        // rupee,mana, big_key, weapon_a, weapon_b,coord, obs
        heros_ = new Joueur("link", /* 12 */100, 2, 0, 0, 0, 2, 90, "red", 22,
                5, false, null, null, new Coordinates(8 * 16, 8 * 16), false);
        map_ = Map_.getMinimap(current_map_);
        key_down_pressed_ = false;
        key_up_pressed_ = false;
        key_left_pressed_ = false;
        key_right_pressed_ = false;
        pause_ = false;
        heros_.setMana_max_(100);
        dead_ = 0;
        p.start();

    }

    /**
     * @param x
     * @param y
     */
    public void change_map(int x, int y) {
        current_map_.set_x(x);
        current_map_.set_y(y);
    }

    /**
     *  
     */
    public void close() {
    }

    /**
     * @return
     */
    public Coordinates get_map() {
        return current_map_;
    }

    /**
     * @return Returns the current_map_.
     */
    public Coordinates getCurrent_map_() {
        return this.current_map_;
    }

    /**
     * @return Returns the heros_.
     */
    public Joueur getHeros_() {
        return this.heros_;
    }

    /**
     * @return Returns the map_.
     */
    public Minimap getMap_() {
        return this.map_;
    }

    /**
     * @return Returns the map_.
     */
    public Minimap getMap_(Coordinates coord) {
        return Map_.getMinimap(coord);
    }

    /**
     * @return Returns the map_.
     */
    public Map get_Map_() {
        return Map_;
    }

    /**
     * @return Returns the quest_.
     */
    public Quest getQuest_() {
        return this.quest_;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == option_.getLeft_()) {
            key_left_pressed_ = true;
        }
        if (e.getKeyCode() == option_.getRight_()) {
            key_right_pressed_ = true;
        }
        if (e.getKeyCode() == option_.getUp_()) {
            key_up_pressed_ = true;
        }
        if (e.getKeyCode() == option_.getDown_()) {
            key_down_pressed_ = true;
        }
        if (e.getKeyCode() == option_.getA_()) {
            heros_.setAttack_stat_(10 + 100 * heros_.get_dir());
        }
        if (e.getKeyCode() == option_.getB_()) {
            heros_.setAttack_stat_(10 + 100 * heros_.get_dir());
        }

    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {

        if (heros_.isDead_()) {
            if (e.getKeyCode() == option_.getMenu_()) {
                Menu menu = new Menu(frame_, null, soundList_, option_);
                frame_.setContentPane(menu);
                frame_.removeKeyListener(this);
                frame_.addKeyListener(menu);
                if (audio_ != null)
                    audio_.stop();
                frame_.validate();
            }
        } else {
            if (e.getKeyCode() == option_.getRight_()) {
                key_right_pressed_ = false;
                heros_.setMov_cpt(15);
            }
            if (e.getKeyCode() == option_.getLeft_()) {
                key_left_pressed_ = false;
                heros_.setMov_cpt(15);
            }
            if (e.getKeyCode() == option_.getUp_()) {
                key_up_pressed_ = false;
                heros_.setMov_cpt(15);
            }
            if (e.getKeyCode() == option_.getDown_()) {
                key_down_pressed_ = false;
                heros_.setMov_cpt(15);
            }
            if (e.getKeyCode() == option_.getA_()) {
                heros_.setUsed_weapon_(heros_.get_weapon_a());
                heros_.attack(this);
            }
            if (e.getKeyCode() == option_.getB_()) {
                heros_.setUsed_weapon_(heros_.get_weapon_b());
                heros_.attack(this);
            }
            if (e.getKeyCode() == option_.getMenu_()) {
                if (audio_ != null)
                    audio_.stop();
                Menu menu = new Menu(frame_, this, soundList_, option_);
                menu.audio_ = audio_;
                frame_.setContentPane(menu);
                frame_.removeKeyListener(this);
                frame_.addKeyListener(menu);
                frame_.validate();
            }
            if (e.getKeyCode() == option_.getInventory_()) {
                Item_screen item = new Item_screen(frame_, this);
                item.setOption_(option_);
                frame_.setContentPane(item);
                frame_.removeKeyListener(this);
                frame_.addKeyListener(item);
                frame_.validate();
            }
            if (e.getKeyCode() == option_.getPause_()) {
                if (pause_)
                    pause_ = false;
                else
                    pause_ = true;
            }
        }
    }

    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
    }

    /**
     *  
     */
    public void pause() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see screen.Interface#print()
     */
    public synchronized void print() {
        try {
            Thread.sleep(10);
            if (!heros_.isDead_()) {
                map_.print_decor(this);
                map_.print(this);
                map_.print_monsters(this);
                map_.print_weapons(this);
                heros_.print(this);
            } else {

                String dead = "You just died!!";
                String exit = "Press Menu ("
                        + KeyEvent.getKeyText(option_.getMenu_()) + ")";
                String back = " to go back to the menu";
                setFont_(new Font("Arial", Font.BOLD, 14));
                getG2_().setFont(getFont_());
                g2_.drawString(dead, 16 * 5, 11 * 9);
                g2_.drawString(exit, 16 * 4 + 8, 11 * 11);
                g2_.drawString(back, 16 * 3 + 8, 11 * 13);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *  
     */
    public void save() {
    }

    /**
     *  
     */
    public void scroll(int dir) {

        if (dir == 1 || dir == -1)
            current_map_ = new Coordinates(current_map_.get_x() + dir, current_map_
                    .get_y());
        else
            current_map_ = new Coordinates(current_map_.get_x(), current_map_.get_y()
                    - dir / 2);
        map_ = Map_.getMinimap(current_map_);
        switch (dir) {
        case 1:
            if (current_map_.equal(new Coordinates(0, 5))
                    && Map_.getName().equals("main")) {
                if (audio_ != null)
                    audio_.stop();
                if (option_.isSound_on_()) {
                    PlayThread p = new PlayThread(this, 3);
                }
            }
            heros_.setCoord(0, heros_.getCoord().get_y());
            break;
        case -1:
            if (current_map_.equal(new Coordinates(-1, 5))
                    && Map_.getName().equals("main")) {
                if (audio_ != null)
                    audio_.stop();
                if (option_.isSound_on_()) {
                    PlayThread p = new PlayThread(this, 2);
                }
            }
            if (current_map_.equal(new Coordinates(-1, 2))
                    && Map_.getName().equals("donjon1")) {
                if (audio_ != null)
                    audio_.stop();
                if (option_.isSound_on_()) {
                    PlayThread p = new PlayThread(this, 4);
                }
                Giant_spider spider = new Giant_spider("giant_spider", 8, 1,
                        false, false, new Coordinates(5, 5));
                spider.setOtherleft(new Giant_spider("giant_spider", 8, 1,
                        false, false, new Coordinates(spider.getCoord().get_x() - 1,
                                spider.getCoord().get_y())));
                spider.setOtherright(new Giant_spider("giant_spider", 8, 1,
                        false, false, new Coordinates(spider.getCoord().get_x() + 1,
                                spider.getCoord().get_y())));

                spider.getOtherleft_().setIs_true_(false);
                spider.getOtherright_().setIs_true_(false);
                map_.setMonsters_(spider);
                map_.setMonsters_(spider.getOtherleft_());
                map_.setMonsters_(spider.getOtherright_());

            }
            heros_.setCoord(16 * 15, heros_.getCoord().get_y());
            break;
        case 2:
            heros_.setCoord(heros_.getCoord().get_x(), 0);
            break;
        case -2:
            if (current_map_.equal(new Coordinates(0, 3))
                    && Map_.getName().equals("donjon3")) {
                if (audio_ != null)
                    audio_.stop();
                if (option_.isSound_on_()) {
                    PlayThread p = new PlayThread(this, 4);
                }
                Ganon ganon = new Ganon("ganon", 8, 1, false, false, new Coordinates(5, 5));
                ganon.setIs_true_(true);
              ganon.setUp_right(new Ganon("ganon", 8, 1, false, false, new Coordinates(
                        ganon.getCoord().get_x() + 1, ganon.getCoord().get_y())));
                ganon.setBot_right(new Ganon("ganon", 8, 1, false, false, new Coordinates(
                        ganon.getCoord().get_x() + 1, ganon.getCoord().get_y() + 1)));
                ganon.setBot_left(new Ganon("ganon", 8, 1, false, false, new Coordinates(
                        ganon.getCoord().get_x(), ganon.getCoord().get_y() + 1)));

                map_.setMonsters_(ganon);
                map_.setMonsters_(ganon.getUp_right_());
                map_.setMonsters_(ganon.getBot_right_());
                map_.setMonsters_(ganon.getBot_left_());
            }
            heros_.setCoord(heros_.getCoord().get_x(), 10 * 16);
            break;
        }

    }

    /**
     * @param current_map_
     *            The current_map_ to set.
     */
    public void setCurrent_map_(Coordinates current_map_) {
        this.current_map_ = current_map_;
    }

    /**
     * @param heros_
     *            The heros_ to set.
     */
    public void setHeros_(Joueur heros_) {
        this.heros_ = heros_;
    }

    /**
     * @param map_
     *            The map_ to set.
     */
    public void setMap_(Map map_) {
        this.Map_ = map_;
    }

    /**
     * @param map_
     *            The map_ to set.
     */
    public void setMap_(Minimap map_) {
        this.map_ = map_;
    }

    /**
     * @param quest_
     *            The quest_ to set.
     */
    public void setQuest_(Quest quest_) {
        this.quest_ = quest_;
    }

    public void skip(JFrame f) {
        f.getContentPane().add("Center", this);
    }
}