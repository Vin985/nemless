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

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class Item_screen extends Interface {

    private Game_screen game_;

    protected int position_;

    private int spell_pos_;

    /**
     *  
     */
    public Item_screen(JFrame frame, Game_screen game) {
        super(frame);
        game_ = game;
        position_ = 0;
        spell_pos_ = game_.getHeros_().getSpell_book_() - 1;
        setBackground(Color.black);
        setForeground(Color.white);
        p = new PrintThread();
        for (int i = 0; i < 6; i++) {
            if (game_.getHeros_().get_object(new Integer(i)) != null) {
                game_.getHeros_().set_weapon_b(
                        (String) game_.getHeros_().get_object(new Integer(i)));
                position_ = i;
                break;
            }
        }
        p.start();
    }

    /**
     * @return
     */
    public int get_pos() {
        return position_;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == option_.getLeft_()) {
            key_left_pressed_ = true;
        }

        if (e.getKeyCode() == option_.getUp_()) {
            key_up_pressed_ = true;
        }
        if (e.getKeyCode() == option_.getDown_()) {
            key_down_pressed_ = true;
        }

        if (e.getKeyCode() == option_.getRight_()) {
            key_right_pressed_ = true;
        }
        if (e.getKeyCode() == option_.getInventory_()) {
            if (position_ == 6)
                game_.getHeros_().set_weapon_b(
                        (String) game_.getHeros_().get_object(new Integer(1)));
            frame_.setContentPane(game_);
            frame_.removeKeyListener(this);
            frame_.addKeyListener(game_);
            frame_.validate();
            p.setDone_(true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     *  
     */
    public void move_cursor() {
        int i;
        if (key_left_pressed_) {
            if (position_ > 0) {
                for (i = position_ - 1; i > -1; i--) {
                    if (game_.getHeros_().get_object(new Integer(i)) != null) {
                        position_ = i;
                        key_left_pressed_ = false;
                        game_.getHeros_().set_weapon_b(
                                (String) game_.getHeros_().get_object(
                                        new Integer(i)));
                        return;
                    }
                }
            }
            key_left_pressed_ = false;
        }
        if (key_right_pressed_) {
            if (position_ < 6) {
                for (i = position_ + 1; i < 6; i++) {
                    if (game_.getHeros_().get_object(new Integer(i)) != null) {
                        position_ = i;
                        key_right_pressed_ = false;
                        game_.getHeros_().set_weapon_b(
                                (String) game_.getHeros_().get_object(
                                        new Integer(i)));
                        return;
                    }
                }
            }
            if (position_ == 5)
                position_++;
            key_right_pressed_ = false;
        }
        if (key_down_pressed_) {
            if (position_ == 6)
                if (spell_pos_ < 4) {
                    spell_pos_++;
                    game_.getHeros_().inc_spell(1);
                }
            key_down_pressed_ = false;
        }
        if (key_up_pressed_) {
            if (position_ == 6)
                if (spell_pos_ > 0) {
                    spell_pos_--;
                    game_.getHeros_().inc_spell(-1);
                }

            key_up_pressed_ = false;
        }
    }

    /**
     *  
     */
    public void print() {

        move_cursor();
        String title = "INVENTORY";
        String items = "Items:";
        String suit = "Suit: ";
        String sword = "Sword: ";
        String shield = "Shield: ";
        String triforce = "Triforce: ";
        this.setFont_(new Font("Arial", Font.BOLD, 15));
        g2_.setFont(this.getFont_());
        setForeground(Color.lightGray);
        g2_.drawString(title, 88, 16);
        g2_.drawString(items, 4, 48);
        game_.getHeros_().draw_state(this);
        g2_.drawImage(getDemoImage("/resources/sprites/items/items.png", this), 0, 56,
                this);
        if (position_ < 6)
            g2_.drawImage(getDemoImage("/resources/sprites/selected2.png", this),
                    1 + 19 * position_, 57, this);
        if (position_ == 6)
            g2_.drawImage(getDemoImage("/resources/sprites/selected3.png", this), 151,
                    56 + spell_pos_ * 17, this);
        g2_.drawString(suit, 3, 93);
        g2_.drawImage(getDemoImage("/resources/sprites/items/suit_"
                + game_.getHeros_().get_suit() + ".png", this), 34, 80, this);
        g2_.drawString(sword, 3, 113);
        if (game_.getHeros_().get_sword() == 2)
            g2_.drawImage(getDemoImage("/resources/sprites/items/sword.png", this), 43,
                    100, this);
        if (game_.getHeros_().get_sword() == 1)
            g2_.drawImage(getDemoImage("/resources/sprites/items/sword_wood.png", this),
                    43, 100, this);
        g2_.drawString(shield, 3, 133);
        if (game_.getHeros_().get_shield() == 1)
            g2_.drawImage(getDemoImage("/resources/sprites/items/shield.png", this), 43,
                    120, this);
        else
            g2_.drawImage(
                    getDemoImage("/resources/sprites/items/shield_little.png", this), 43,
                    120, this);
        g2_.drawString(triforce, 3, 153);
        if (game_.getHeros_().get_triforce() >= 1) {
            g2_.drawImage(getDemoImage("/resources/sprites/items/triforce_inventory.png",
                    this), 75, 142, this);
            if (game_.getHeros_().get_triforce() >= 2) {
                g2_.drawImage(getDemoImage(
                        "/resources/sprites/items/triforce_inventory.png", this), 67,
                        150, this);
                if (game_.getHeros_().get_triforce() == 3)
                    g2_.drawImage(getDemoImage(
                            "/resources/sprites/items/triforce_inventory.png", this), 83,
                            150, this);
            }
        }
        print_items();
        print_spells();
    }

    /**
     *  
     */
    public void print_items() {
        int i;
        Object s;
        for (i = 0; i < 6; i++) {
            if ((s = game_.getHeros_().get_object(new Integer(i))) != null) {
                g2_.drawImage(getDemoImage("/resources/sprites/items/" + (String) s
                        + ".png", this), 2 + i * 19, 58, this);
            }
        }
    }

    /**
     *  
     */
    public void print_spells() {
        if (game_.getHeros_().get_object(new Integer(5)) != null) {

            g2_.drawImage(getDemoImage("/resources/sprites/items/Spells.png", this), 150,
                    36, this);

            int i;
            Object s;
            String fire = "Fire           4";
            String ice = "Ice            6";
            String death = "Death       15";
            String heal = "Heal         10";
            String protect = "Shield       8";
            String spell = "Spells";
            String mana = "Mana";
            this.setFont_(new Font("Arial", Font.BOLD, 13));
            g2_.setFont(this.getFont_());
            g2_.drawString(fire, 153, 70);
            g2_.drawString(ice, 153, 87);
            g2_.drawString(death, 153, 104);
            g2_.drawString(heal, 153, 121);
            g2_.drawString(protect, 153, 138);
            g2_.drawString(spell, 153, 50);
            g2_.drawString(mana, 208, 50);

            if (position_ >= 5)
                g2_.drawImage(getDemoImage("/resources/sprites/items/bound.png", this),
                        116, 42, this);
        }
    }

    /**
     * @param pos
     */
    public void set_pos(int pos) {
        position_ = pos;
    }
}