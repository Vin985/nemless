/*
 * Created on 28 juin 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package old.fr.studioshi.dazel.game.ui;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JFrame;

import fr.studioshi.dazel.game.sound.PlayThread;
import fr.studioshi.dazel.game.sound.SoundList;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class Interface extends JApplet implements ImageObserver,
        KeyListener {

    public class PrintThread extends Thread {

        private boolean done_ = false;

        /**
         * @return Returns the done_.
         */
        public boolean isDone_() {
            return this.done_;
        }

        public void run() {
            while (!done_) {
                try {
                    sleep(10);
                    repaint();
                } catch (java.lang.InterruptedException e) {
                }
            }
        }

        /**
         * @param done_
         *            The done_ to set.
         */
        public void setDone_(boolean done_) {
            this.done_ = done_;
        }
    }

    protected AudioClip audio_;

    protected BufferedImage bimg_;

    private String[] dirs_;

    protected Font font_;

    protected JFrame frame_;

    protected Graphics2D g2_;

    protected boolean key_down_pressed_;

    protected boolean key_left_pressed_;

    protected boolean key_right_pressed_;

    protected boolean key_up_pressed_;

    int large_;

    protected int long_;

    protected Options option_;

    protected PrintThread p;

    protected boolean pause_;

    protected Random random_;

    protected String[] songs_;

    protected SoundList soundList_;
    
    protected PlayThread play_;
    
    /**
     *  
     */
    public Interface(JFrame frame) {
        String[] dir = { "up", "right", "down", "left" };
        String[] song = { "Dungeon.mid", "Intro.mid", "ff7-2-10-fortcondor.mid",
                "OverworldTheme.mid",  "ff7-1-10-fighting.mid"};
        dirs_ = dir;
        songs_ = song;
        frame_ = frame;
        long_ = 800;
        large_ = 600;
        option_ = null;
        random_ = new Random();
        soundList_ = null;
        audio_ = null;
    }

    /**
     * @param x
     * @param y
     */
    public void change_size(int x, int y) {
        long_ = x;
        large_ = y;
    }

    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg_ == null || bimg_.getWidth() != w || bimg_.getHeight() != h) {
            bimg_ = (BufferedImage) createImage(w, h);
        }
        g2 = bimg_.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }

    /**
     * @return
     */
    public int get_large() {
        return large_;
    }

    /**
     * @return
     */
    public int get_long() {
        return long_;
    }

    /**
     * @return Returns the audio_.
     */
    public AudioClip getAudio_() {
        return this.audio_;
    }

    public Image getDemoImage(String name, Interface inter) {
        URL url = null;
        
        try {
            url = new URL("file:" + System.getProperty("user.dir")
                    + name);
            
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        Image img = inter.getToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(inter);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
            System.exit(1);
        }
        return img;
    }

    /**
     * @return Returns the dirs_.
     */
    public String getDirs_(int i) {
        return this.dirs_[i];
    }

    /**
     * @return Returns the font_.
     */
    public Font getFont_() {
        return this.font_;
    }

    /**
     * @return Returns the g2_.
     */
    public Graphics2D getG2_() {
        return this.g2_;
    }

    /**
     * @return Returns the option_.
     */
    public Options getOption_() {
        return this.option_;
    }

    /**
     * @return Returns the random_.
     */
    public Random getRandom_() {
        return this.random_;
    }

    /**
     * @return Returns the songs_.
     */
    public String getSongs_(int i) {
        return this.songs_[i];
    }

    /**
     * @return Returns the soundList_.
     */
    public SoundList getSoundList_() {
        return this.soundList_;
    }

    /**
     * @return Returns the key_down_pressed_.
     */
    public boolean isKey_down_pressed_() {
        return this.key_down_pressed_;
    }

    /**
     * @return Returns the key_left_pressed_.
     */
    public boolean isKey_left_pressed_() {
        return this.key_left_pressed_;
    }

    /**
     * @return Returns the key_right_pressed_.
     */
    public boolean isKey_right_pressed_() {
        return this.key_right_pressed_;
    }

    /**
     * @return Returns the key_up_pressed_.
     */
    public boolean isKey_up_pressed_() {
        return this.key_up_pressed_;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.Component#paint(java.awt.Graphics)
     */
    public void paint(Graphics g) {
        if (!pause_) {
            Dimension d = getSize();
            g2_ = createGraphics2D(d.width, d.height);
            print();
            g2_.dispose();
            g.drawImage(bimg_, 0, 0, this);
        }
    }

    /**
     *  
     */
    public abstract void print();

    /**
     * @param audio_
     *            The audio_ to set.
     */
    public void setAudio_(AudioClip audio_) {
        this.audio_ = audio_;
    }

    /**
     * @param font_
     *            The font_ to set.
     */
    public void setFont_(Font font_) {
        this.font_ = font_;
    }

    /**
     * @param g2_
     *            The g2_ to set.
     */
    public void setG2_(Graphics2D g2_) {
        this.g2_ = g2_;
    }

    /**
     * @param key_down_pressed_
     *            The key_down_pressed_ to set.
     */
    public void setKey_down_pressed_(boolean key_down_pressed_) {
        this.key_down_pressed_ = key_down_pressed_;
    }

    /**
     * @param key_left_pressed_
     *            The key_left_pressed_ to set.
     */
    public void setKey_left_pressed_(boolean key_left_pressed_) {
        this.key_left_pressed_ = key_left_pressed_;
    }

    /**
     * @param key_right_pressed_
     *            The key_right_pressed_ to set.
     */
    public void setKey_right_pressed_(boolean key_right_pressed_) {
        this.key_right_pressed_ = key_right_pressed_;
    }

    /**
     * @param key_up_pressed_
     *            The key_up_pressed_ to set.
     */
    public void setKey_up_pressed_(boolean key_up_pressed_) {
        this.key_up_pressed_ = key_up_pressed_;
    }

    /**
     * @param option_
     *            The option_ to set.
     */
    public void setOption_(Options option_) {
        this.option_ = option_;
    }

    /**
     * @param random_
     *            The random_ to set.
     */
    public void setRandom_(Random random_) {
        this.random_ = random_;
    }

    /**
     * @param songs_
     *            The songs_ to set.
     */
    public void setSongs_(String[] songs_) {
        this.songs_ = songs_;
    }

    /**
     * @param soundList_
     *            The soundList_ to set.
     */
    public void setSoundList_(SoundList soundList_) {
        this.soundList_ = soundList_;
    }

    public void startLoadingSounds() {
        //Start asynchronous sound loading.
        URL codeBase = null;
        try {
            codeBase = new URL("file:" + System.getProperty("user.dir")
                    + "/Music/");
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        soundList_ = new SoundList(codeBase);
        for (int i = 0; i < 5; i++)
            soundList_.startLoading(songs_[i]);
    }

}