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
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Save_screen extends Interface {

    private Game_screen game_;

    private Menu menu_;

    private int position_;

    private File[] save_files_;

    /**
     * @param frame
     */
    public Save_screen(JFrame frame, Menu menu, Game_screen game) {
        super(frame);
        position_ = 0;
        menu_ = menu;
        game_ = game;
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
            if (position_ < 5)
                save();
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

        String save = "SAVE";
        String slot1 = "SLOT 1";
        String slot2 = "SLOT 2";
        String slot3 = "SLOT 3";
        String slot4 = "SLOT 4";
        String slot5 = "SLOT 5";

        String exit = "BACK";
        String saved = " Game saved";
        this.setFont_(new Font("Arial", Font.BOLD, 15));
        g2_.setFont(this.getFont_());

        g2_.drawString(save, 110, 20);
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

    public void save() {
        if (game_ != null) {
            File save = new File("save");
            save.mkdir();
            try {
                save_files_[position_].createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                FileWriter save_file = new FileWriter(save_files_[position_]);

                if (game_.getHeros_() != null) {
                    // boolean big_key_;
                    if (game_.getHeros_().is_big_key())
                        save_file.write(43 + "\n");
                    else
                        save_file.write(42 + "\n");
                    // int bombs_;
                    save_file
                            .write((game_.getHeros_().get_bombs() + 42) + "\n");
                    // int health_max_;
                    save_file.write((game_.getHeros_().getHealth_max_() + 42)
                            + "\n");
                    // int keys_;
                    save_file.write((game_.getHeros_().get_keys() + 42) + "\n");
                    // int level_;
                    save_file
                            .write((game_.getHeros_().getLevel_() + 42) + "\n");
                    // int mana_;
                    save_file.write((game_.getHeros_().get_mana() + 42) + "\n");
                    // int mana_max_;
                    save_file.write((game_.getHeros_().getMana_max_() + 42)
                            + "\n");
                    // Hashtable objects_;
                    for (int i = 0; i < 6; i++) {
                        if (game_.getHeros_().get_object(new Integer(i)) == null) {
                            save_file.write(42 + "\n");
                        } else
                            save_file.write(43 + "\n");
                    }
                    // int rupee_;
                    save_file
                            .write((game_.getHeros_().get_rupee() + 42) + "\n");
                    // int shield_;
                    save_file.write((game_.getHeros_().get_shield() + 42)
                            + "\n");
                    // int spell_book_;
                    save_file.write((game_.getHeros_().getSpell_book_() + 42)
                            + "\n");
                    // String suit_;
                    if (game_.getHeros_().get_suit().equals("green"))
                        save_file.write(42 + "\n");
                    else if (game_.getHeros_().get_suit().equals("blue"))
                        save_file.write(43 + "\n");
                    else if (game_.getHeros_().get_suit().equals("red"))
                        save_file.write(44 + "\n");
                    // int sword_;
                    save_file
                            .write((game_.getHeros_().get_sword() + 42) + "\n");
                    // int triforce_;
                    save_file.write((game_.getHeros_().get_triforce() + 42)
                            + "\n");
                    // int xp_;
                    save_file.write((game_.getHeros_().get_xp() + 42) + "\n");
                    // int a_;
                    save_file.write((game_.getOption_().getA_() + 42) + "\n");
                    // int b_;
                    save_file.write((game_.getOption_().getB_() + 42) + "\n");
                    // int down_;
                    save_file
                            .write((game_.getOption_().getDown_() + 42) + "\n");
                    // int inventory_;
                    save_file.write((game_.getOption_().getInventory_() + 42)
                            + "\n");
                    // int left_;
                    save_file
                            .write((game_.getOption_().getLeft_() + 42) + "\n");
                    // int menu_;
                    save_file
                            .write((game_.getOption_().getMenu_() + 42) + "\n");
                    // int pause_;
                    save_file.write((game_.getOption_().getPause_() + 42)
                            + "\n");
                    // int right_;
                    save_file.write((game_.getOption_().getRight_() + 42)
                            + "\n");
                    // int up_;
                    save_file.write((game_.getOption_().getUp_() + 42) + "\n");
                    // health
                    save_file.write((game_.getHeros_().get_health() + 42)
                            + "\n");
                    // sound
                    if (game_.getOption_().isSound_on_())
                        save_file.write(43 + "\n");
                    else
                        save_file.write(42 + "\n");
                    // map
                    if (game_.get_Map_().getName().equals("donjon1")) {
                        save_file.write(43 + "\n");
                    } else if (game_.get_Map_().getName().equals("donjon2")) {
                        save_file.write(44 + "\n");
                    } else if (game_.get_Map_().getName().equals("donjon3")) {
                        save_file.write(45 + "\n");
                    } else
                        save_file.write(42 + "\n");
                    //minimap x
                    System.out.println(game_.get_Map_().getName());
                    if (game_.get_Map_().getName().equals("donjon3")
                            || game_.get_Map_().getName().equals("donjon2")
                            || game_.get_Map_().getName().equals("donjon1")
                            || game_.get_Map_().getName().equals("main"))
                        save_file.write((42 + game_.getCurrent_map_().get_x())
                                + "\n");
                    else
                        save_file.write((42 + game_.getHeros_().getLast_main_()
                                .get_x())
                                + "\n");
                    //minimap y
                    if (game_.get_Map_().getName().equals("donjon3")
                            || game_.get_Map_().getName().equals("donjon2")
                            || game_.get_Map_().getName().equals("donjon1")
                            || game_.get_Map_().getName().equals("main"))
                        save_file.write((42 + game_.getCurrent_map_().get_y())
                                + "\n");
                    else
                        save_file.write((42 + game_.getHeros_().getLast_main_()
                                .get_y())
                                + "\n");
                    // Coord x
                    if (game_.get_Map_().getName().equals("donjon3")
                            || game_.get_Map_().getName().equals("donjon2")
                            || game_.get_Map_().getName().equals("donjon1")
                            || game_.get_Map_().getName().equals("main"))
                        save_file.write((42 + game_.getHeros_().getCoord()
                                .get_x())
                                + "\n");
                    else
                        save_file.write((42 + game_.getHeros_().getExit_()
                                .get_x())
                                + "\n");
                    // Coord y
                    if (game_.get_Map_().getName().equals("donjon3")
                            || game_.get_Map_().getName().equals("donjon2")
                            || game_.get_Map_().getName().equals("donjon1")
                            || game_.get_Map_().getName().equals("main"))
                        save_file.write((42 + game_.getHeros_().getCoord()
                                .get_y())
                                + "\n");
                    else
                        save_file.write((42 + game_.getHeros_().getExit_()
                                .get_y())
                                + "\n");
                    //Exit x
                    if (game_.getHeros_().getExit_() != null)
                        save_file.write((42 + game_.getHeros_().getExit_()
                                .get_x())
                                + "\n");
                    else
                        save_file.write(42 + "\n");
                    // Exit y
                    if (game_.getHeros_().getExit_() != null)
                        save_file.write((42 + game_.getHeros_().getExit_()
                                .get_y())
                                + "\n");
                    else
                        save_file.write(42 + "\n");
                    // Last Main x
                    if (game_.getHeros_().getLast_main_() != null)
                        save_file.write((42 + game_.getHeros_().getLast_main_()
                                .get_x())
                                + "\n");
                    else
                        save_file.write(42 + "\n");
                    // Last Main y
                    if (game_.getHeros_().getLast_main_() != null)
                        save_file.write((42 + game_.getHeros_().getLast_main_()
                                .get_y())
                                + "\n");
                    else
                        save_file.write(42 + "\n");

                }
                save_file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @param pos
     */
    public void set_pos(int pos) {
        position_ = pos;
    }

}