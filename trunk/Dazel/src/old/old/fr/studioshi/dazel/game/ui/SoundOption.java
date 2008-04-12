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

import fr.studioshi.dazel.game.sound.PlayThread;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SoundOption extends Interface {

    private Options_screen options_;

    private int position_;

    /**
     *  
     */
    public SoundOption(JFrame frame, Options option, Options_screen options) {
        super(frame);
        position_ = 0;
        options_ = options;
        option_ = option;
        setBackground(Color.black);
        setForeground(Color.white);
        p = new PrintThread();
        p.start();
    }

    /**
     *  
     */
    private void change_sound_state() {
        if (option_.isSound_on_()) {
            option_.setSound_on_(false);
            if (audio_ != null)
                audio_.stop();
        } else {
            option_.setSound_on_(true);
            PlayThread t = new PlayThread(this, 1);
        }
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
        // TODO Auto-generated method stub
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
            if (position_ == 0)
                change_sound_state();
            if (position_ == 1)
                quit();
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
            if (position_ < 1)
                position_++;
            else
                position_ = 0;
            key_down_pressed_ = false;
        }
        if (key_up_pressed_) {
            if (position_ > 0)
                position_--;
            else
                position_ = 1;
            key_up_pressed_ = false;
        }
    }

    /**
     *  
     */
    public void print() {
      
        if (option_.isSound_on_()
                && (soundList_.getClip(songs_[0]) == null
                        || soundList_.getClip(songs_[1]) == null
                        || soundList_.getClip(songs_[2]) == null || soundList_
                        .getClip(songs_[3]) == null)) {
            g2_.drawString("Loading...", 6 * 16, 8 * 16);
            return;
        }
       
        move_cursor();

        String audio = "AUDIO";
        String exit = "BACK";
        String sound = "SOUND: ";
        String on = "ON";
        String off = "OFF";
        this.setFont_(new Font("Arial", Font.BOLD, 15));
        g2_.setFont(this.getFont_());

        g2_.drawString(audio, 100, 16);
        g2_.drawString(sound, 30, 100);
        if (option_.isSound_on_())
            g2_.drawString(on, 100, 100);
        else
            g2_.drawString(off, 100, 100);
        g2_.drawString(exit, 110, 14 * 16);
        if (position_ < 1)
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 0,
                    90 + 30 * position_, this);
        else
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 80,
                    14 * 16 - 10, this);
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
        options_.audio_ = audio_;
        p.setDone_(true);
    }

    /**
     * @param pos
     */
    public void set_pos(int pos) {
        position_ = pos;
    }

}