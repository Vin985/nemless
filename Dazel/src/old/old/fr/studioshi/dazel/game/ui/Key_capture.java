/*
 * Created on 11 juil. 2004
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

public class Key_capture extends Interface {

    private boolean capt_;

    private Game_screen game_;

    private Options_screen options_;

    private int position_;

    /**
     * @param frame
     */
    public Key_capture(JFrame frame, Options option, Options_screen options) {
        super(frame);
        position_ = 0;
        options_ = options;
        option_ = option;
        setBackground(Color.black);
        setForeground(Color.white);
        capt_ = false;
        p = new PrintThread();
        p.start();
    }

    /**
     * @return
     */
    public int get_pos() {
        return position_;
    }

    /**
     * @param e
     */
    private void key_capt(KeyEvent e) {
        if (position_ == 0)
            option_.setA_(e.getKeyCode());
        if (position_ == 1)
            option_.setB_(e.getKeyCode());
        if (position_ == 2)
            option_.setUp_(e.getKeyCode());
        if (position_ == 3)
            option_.setDown_(e.getKeyCode());
        if (position_ == 4)
            option_.setLeft_(e.getKeyCode());
        if (position_ == 5)
            option_.setRight_(e.getKeyCode());
        if (position_ == 6)
            option_.setPause_(e.getKeyCode());
        if (position_ == 7)
            option_.setInventory_(e.getKeyCode());
        if (position_ == 8)
            option_.setMenu_(e.getKeyCode());

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
        if (!capt_) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                key_down_pressed_ = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                key_up_pressed_ = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (position_ == 9)
                    quit();
                if (position_ < 9)
                    capt_ = true;
            }
        } else {
            key_capt(e);
            capt_ = false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    public void keyTyped(KeyEvent arg0) {
    }

    /**
     *  
     */
    public void move_cursor() {

        if (key_down_pressed_) {
            if (position_ < 9)
                position_++;
            else
                position_ = 0;
            key_down_pressed_ = false;
        }
        if (key_up_pressed_) {
            if (position_ > 0)
                position_--;
            else
                position_ = 9;
            key_up_pressed_ = false;
        }
    }

    /**
     *  
     */
    public void print() {
        move_cursor();

        String a = "BUTTON A";
        String b = "BUTTON B";
        String up = "UP";
        String down = "DOWN";
        String exit = "BACK";
        String left = "LEFT";
        String right = "RIGHT";
        String options = "KEY CAPTURE";
        String pause = "PAUSE";
        String ok = "OK/INVENTORY";
        String menu = "MENU";
        String new_key = "Press the new key";
        this.setFont_(new Font("Arial", Font.BOLD, 15));
        g2_.setFont(this.getFont_());

        g2_.drawString(options, 90, 16);
        g2_.drawString(a, 30, 40);
        g2_.drawString(KeyEvent.getKeyText(option_.getA_()), 150, 40);
        g2_.drawString(b, 30, 60);
        g2_.drawString(KeyEvent.getKeyText(option_.getB_()), 150, 60);
        g2_.drawString(up, 30, 80);
        g2_.drawString(KeyEvent.getKeyText(option_.getUp_()), 150, 80);
        g2_.drawString(down, 30, 100);
        g2_.drawString(KeyEvent.getKeyText(option_.getDown_()), 150, 100);
        g2_.drawString(left, 30, 120);
        g2_.drawString(KeyEvent.getKeyText(option_.getLeft_()), 150, 120);
        g2_.drawString(right, 30, 140);
        g2_.drawString(KeyEvent.getKeyText(option_.getRight_()), 150, 140);
        g2_.drawString(pause, 30, 160);
        g2_.drawString(KeyEvent.getKeyText(option_.getPause_()), 150, 160);
        g2_.drawString(ok, 30, 180);
        g2_.drawString(KeyEvent.getKeyText(option_.getInventory_()), 150, 180);
        g2_.drawString(menu, 30, 200);
        g2_.drawString(KeyEvent.getKeyText(option_.getMenu_()), 150, 200);
        g2_.drawString(exit, 110, 14 * 16);
        if (position_ < 9)
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 0,
                    30 + 20 * position_, this);
        else
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 80,
                    14 * 16 - 10, this);
        if (capt_) {
            g2_.drawImage(getDemoImage("/Sprites/Items/blackground.png", this),
                    50, 110, this);
            g2_.drawString(new_key, 52, 133);
        }

    }

    /**
     *  
     */
    public void quit() {
        options_.setOption_(option_);
        frame_.setContentPane(options_);
        frame_.removeKeyListener(this);
        frame_.addKeyListener(options_);
        frame_.validate();
        p.setDone_(true);
    }

    /**
     * @param pos
     */
    public void set_pos(int pos) {
        position_ = pos;
    }

}

