/*
 * Created on 17 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import old.fr.studioshi.dazel.editor.Parse;
import old.fr.studioshi.dazel.game.items.Map;
import old.fr.studioshi.dazel.game.items.Quest;

import fr.studioshi.common.game.model.Coordinates;
import fr.studioshi.dazel.game.sound.PlayThread;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Load_screen extends Interface {

    private Game_screen game_;

    private int load_cpt_;

    private Menu menu_;

    private int position_;

    private File[] save_files_;

    /**
     * @param frame
     */
    public Load_screen(JFrame frame, Menu menu) {
        super(frame);
        position_ = 0;
        menu_ = menu;
        load_cpt_ = 0;
        File[] save = { new File("save/slot1.dsv"), new File("save/slot2.dsv"),
                new File("save/slot3.dsv"), new File("save/slot4.dsv"),
                new File("save/slot5.dsv") };
        save_files_ = save;
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
            if (position_ == 5)
                quit();
            if (position_ < 5 && save_files_[position_].exists())
                load();
            ;
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
    public void load() {
        menu_.setGame_(null);
        if (menu_.audio_ != null)
            menu_.audio_.stop();
        //final Map map = new Map("map", 3, 3, 1, 1, null);
        Quest q = null;
        Parse parse = new Parse("quest.xml");
        parse.parse();
        q = parse.getQuest();
        Map map = q.getMap("main");
        game_ = new Game_screen(frame_, map, option_, soundList_);
        game_.setQuest_(q);
        game_.getHeros_().setExit_(new Coordinates(0,0));
        game_.getHeros_().setLast_main_(new Coordinates(0,0));
        try {
            FileReader save_file = new FileReader(save_files_[position_]);
            int c;
            int number = 0;
            while ((c = save_file.read()) != -1) {
                if (c != '\n') {
                    number = c - 48 + number * 10;
                } else {
                    load_part(number - 42);
                    number = 0;
                    load_cpt_++;
                }
            }
            save_file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        game_.setMap_(game_.getMap_(game_.getCurrent_map_()));
        frame_.setContentPane(game_);
        frame_.removeKeyListener(this);
        frame_.addKeyListener(game_);
        frame_.validate();
        if (audio_ != null)
            audio_.stop();
        if (option_.isSound_on_()) {
            PlayThread t = new PlayThread(game_, 3);
        }
        p.setDone_(true);
    }

    /**
     * @param number
     */
    private void load_part(int number) {
        switch (load_cpt_) {
        // big_key_;
        case 0:
            if (number == 0)
                game_.getHeros_().setBig_key_(false);
            else
                game_.getHeros_().setBig_key_(true);
            break;
        // bombs_;
        case 1:
            game_.getHeros_().set_bombs(number);
            break;
        // health_max_;
        case 2:
            game_.getHeros_().setHealth_max_(number);
            break;
        // keys_;
        case 3:
            game_.getHeros_().set_keys(number);
            break;
        // level_;
        case 4:
            game_.getHeros_().setLevel_(number);
            break;
        // mana_;
        case 5:
            game_.getHeros_().set_mana(number);
            break;
        // mana_max_;
        case 6:
            game_.getHeros_().setMana_max_(number);
            break;
        // objects_0;
        case 7:
            if (number == 1)
                game_.getHeros_().set_object(new Integer(0), "bow");
            break;
        // objects_1;
        case 8:
            if (number == 1)
                game_.getHeros_().set_object(new Integer(1), "staff");
            break;
        // objects_2;
        case 9:
            if (number == 1)
                game_.getHeros_().set_object(new Integer(2), "bomb");
            break;
        // objects_3;
        case 10:
            if (number == 1)
                game_.getHeros_().set_object(new Integer(3), "potion_red");
            break;
        // objects_4;
        case 11:
            if (number == 1)
                game_.getHeros_().set_object(new Integer(4), "potion_blue");
            break;
        // objects_5;
        case 12:
            if (number == 1)
                game_.getHeros_().set_object(new Integer(5), "spellbook");
            break;
        // rupee_;
        case 13:
            game_.getHeros_().set_rupee(number);
            break;
        // shield_;
        case 14:
            game_.getHeros_().set_shield_(number);
            break;
        // spell_book_;
        case 15:
            game_.getHeros_().setSpell_book_(number);
            break;
        // suit_;
        case 16:
            if (number == 0)
                game_.getHeros_().set_suit("green");
            if (number == 1)
                game_.getHeros_().set_suit("blue");
            if (number == 2)
                game_.getHeros_().set_suit("red");
            break;
        // sword_;
        case 17:
            game_.getHeros_().set_sword(number);
            if (number == 1)
                game_.getHeros_().set_weapon_a("sword_wood");
            if (number == 2)
                game_.getHeros_().set_weapon_a("sword");
            break;
        // triforce_;
        case 18:
            game_.getHeros_().set_triforce(number);
            break;
        // xp_;
        case 19:
            game_.getHeros_().set_xp(number);
            break;
        // a_;
        case 20:
            game_.option_.setA_(number);
            break;
        // b_;
        case 21:
            game_.option_.setB_(number);
            break;
        // down_;
        case 22:
            game_.option_.setDown_(number);
            break;
        // inventory_;
        case 23:
            game_.option_.setInventory_(number);
            break;
        // left_;
        case 24:
            game_.option_.setLeft_(number);
            break;
        // menu_;
        case 25:
            game_.option_.setMenu_(number);
            break;
        // pause_;
        case 26:
            game_.option_.setPause_(number);
            break;
        // right_;
        case 27:
            game_.option_.setRight_(number);
            break;
        // up_;
        case 28:
            game_.option_.setUp_(number);
            break;
        // health
        case 29:
            game_.getHeros_().set_health(number);
            break;
        // Sound
        case 30:
            if (number == 1)
                game_.getOption_().setSound_on_(true);
            break;
        // Map
        case 31:
            if (number == 0)
                game_.setMap_(game_.getQuest_().getMap("main"));
            if (number == 1)
                game_.setMap_(game_.getQuest_().getMap("donjon1"));
            if (number == 2)
                game_.setMap_(game_.getQuest_().getMap("donjon2"));
            if (number == 3)
                game_.setMap_(game_.getQuest_().getMap("donjon3"));
            break;
        // Minimap x
        case 32:
            game_.getCurrent_map_().set_x(number);
            break;
        // Minimap y
        case 33:
            game_.getCurrent_map_().set_y(number);
            break;
        // Coord x
        case 34:
            game_.getHeros_().getCoord().set_x(number);
            break;
        // Coord y
        case 35:
            game_.getHeros_().getCoord().set_y(number);
            // Exit x
        case 36:
            game_.getHeros_().getExit_().set_x(number);
            break;
        // Exit y
        case 37:
            game_.getHeros_().getExit_().set_y(number);
            break;
        // Last Main x
        case 38:
            game_.getHeros_().getLast_main_().set_x(number);
            break;
        // Last Main y
        case 39:
            game_.getHeros_().getLast_main_().set_y(number);
        }
    }

    /**
     *  
     */
    public void move_cursor() {

        if (key_down_pressed_) {
            if (position_ < 5)
                position_++;
            else
                position_ = 0;
            key_down_pressed_ = false;
        }
        if (key_up_pressed_) {
            if (position_ > 0)
                position_--;
            else
                position_ = 5;
            key_up_pressed_ = false;
        }
    }

    /**
     *  
     */
    public void print() {
        move_cursor();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("d/MM/yy  hh:mm:ss");

        String load = "LOAD";
        String slot1 = "SLOT 1";
        String slot2 = "SLOT 2";
        String slot3 = "SLOT 3";
        String slot4 = "SLOT 4";
        String slot5 = "SLOT 5";

        String exit = "BACK";
        String saved = " Game saved";
        this.setFont_(new Font("Arial", Font.BOLD, 15));
        g2_.setFont(this.getFont_());

        g2_.drawString(load, 110, 20);
        if (position_ < 5)
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 0,
                    60 + 25 * position_, this);
        else
            g2_.drawImage(
                    getDemoImage("/Sprites/Items/triforce_menu.png", this), 80,
                    14 * 16 - 10, this);
        g2_.drawString(slot1, 32, 70);
        g2_.drawString(slot2, 32, 95);
        g2_.drawString(slot3, 32, 120);
        g2_.drawString(slot4, 32, 145);
        g2_.drawString(slot5, 32, 170);

        for (int i = 0; i < 5; i++) {
            if (save_files_[i].exists())
                g2_.drawString(formatter.format(new Date(save_files_[i]
                        .lastModified())), 120, 70 + 25 * i);
            else
                g2_.drawString("UNUSED", 120, 70 + 25 * i);
        }

        g2_.drawString(exit, 110, 14 * 16);
    }

    /**
     *  
     */
    public void quit() {
        frame_.setContentPane(menu_);
        frame_.removeKeyListener(this);
        frame_.addKeyListener(menu_);
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