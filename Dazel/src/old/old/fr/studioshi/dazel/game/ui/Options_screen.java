/*
 * Created on 15 juil. 2004
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
public class Options_screen extends Interface {

    private boolean capt_;

    private Menu menu_;

    private int position_;

    /**
     * @param frame
     */
    public Options_screen(JFrame frame, Options option, Menu menu) {
        super(frame);
        position_ = 0;
        menu_ = menu;
        option_ = option;
        audio_ = menu_.audio_;
        setBackground(Color.black);
        setForeground(Color.white);
        p = new PrintThread();
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

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            key_down_pressed_ = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            key_up_pressed_ = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (position_ == 2)
                quit();
            if (position_ == 0)
                key_capture();
            if (position_ == 1)
                audio();
        }

    }

    /**
     *  
     */
    private void audio() {
        SoundOption sound = new SoundOption(frame_, option_, this);
        sound.audio_ = audio_;
        sound.soundList_ = menu_.soundList_;
        frame_.setContentPane(sound);
        frame_.removeKeyListener(this);
        frame_.addKeyListener(sound);
        frame_.validate();
    }

    public void key_capture() {
        Key_capture key = new Key_capture(frame_, option_, this);
        frame_.setContentPane(key);
        frame_.removeKeyListener(this);
        frame_.addKeyListener(key);
        frame_.validate();
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
            if (position_ < 2)
                position_++;
            else
                position_ = 0;
            key_down_pressed_ = false;
        }
        if (key_up_pressed_) {
            if (position_ > 0)
                position_--;
            else
                position_ = 2;
            key_up_pressed_ = false;
        }
    }

    /**
     *  
     */
    public void print() {
        move_cursor();

        String key = "KEY CAPTURE";
        String audio = "AUDIO";
        String exit = "BACK";
        String options = "OPTIONS";
        this.setFont_(new Font("Arial", Font.BOLD, 15));
        g2_.setFont(this.getFont_());

        g2_.drawString(options, 100, 16);
        g2_.drawString(key, 70, 105);
        g2_.drawString(audio, 70, 135);
        g2_.drawString(exit, 110, 14 * 16);
        if (position_ < 2)
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 40,
                    95 + 30 * position_, this);
        else
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 80,
                    14 * 16 - 10, this);

    }

    /**
     *  
     */
    public void quit() {
        menu_.setOption_(option_);
        frame_.setContentPane(menu_);
        frame_.removeKeyListener(this);
        frame_.addKeyListener(menu_);
        menu_.audio_ = audio_;
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