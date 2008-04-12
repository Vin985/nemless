/*
 * Created on 5 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.items;

import old.fr.studioshi.dazel.game.perso.Joueur;
import old.fr.studioshi.dazel.game.perso.Monstre;
import old.fr.studioshi.dazel.game.perso.Perso;
import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;
import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Projectile extends Element {

    /**
     * @param obstacle
     * @param coord
     * @param type
     * @param hide
     */
    private int direction_;

    private Perso launcher_;

    /**
     * Comment for <code>print_x_</code> this is the x variable for printing
     */
    private int print_x_;

    /**
     * Comment for <code>print_y_</code> this is the v variable for printing
     */
    private int print_y_;

    /**
     * @param coord
     * @param dir
     * @param x
     * @param y
     *  
     */
    public Projectile(Coordinates coord, int dir, int x, int y, Perso launcher,
            String type, Element hide) {
        super(false, coord.get_x(), coord.get_y(), type, hide, null);
        direction_ = dir;
        print_x_ = x;
        print_y_ = y;
        launcher_ = launcher;
    }

    /*
     * public Projectile(Coord coord, int dir, int x, int y, Monstre launcher,
     * String type) { super(false, coord, type, null); direction_ = dir;
     * print_x_ = x; print_y_ = y; launcher_ = launcher; }
     */
    /**
     * @param inter
     * @return This methode check if there is something on the case It checks if
     *         there is a non null element, if it's a monster and then cast the
     *         element into the Monster class to inflict some damages.
     *  
     */
    public int check_case(Game_screen inter) {
        Element e = null;
        Monstre b = null;
        int i = coord_.get_x();
        int j = coord_.get_y();

        if (i == (inter.getHeros_().coord_.get_x() + 8) / 16
                && j == (inter.getHeros_().coord_.get_y() + 8) / 16
                && !type_.equals("arrow") && !type_.equals("fire")
                && !type_.equals("ice") && !type_.equals("death")) {
            inter.getHeros_().take_damage(inter, type_, launcher_);
            return 1;
        }

        if ((b = inter.getMap_().getMonsters_(new Coordinates(i, j))) != null) {
            b.take_damage(inter, type_, launcher_);
            return 1;
        }

        //        if ((e = inter.getMap_().getWeapons_(new Coord(i, j))) instanceof
        // Projectile) {
        //          return 1;
        //       }

        if ((e = inter.getMap_().getElement(new Coordinates(i, j))) != null) {

            if (e.getType().equals("rupee")) {
                if (type_ == "arrow" && launcher_ instanceof Joueur) {
                    Joueur launcher = (Joueur) launcher_;
                    inter.getMap_().setElement(coord_);
                    launcher.inc_rupee(1);
                    return 0;
                }
            }
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see map.Element#draw(screen.Interface, java.awt.Graphics2D)
     * 
     * Really prints the images. It go and take the image at the specified
     * location which is of type
     * ../Spritee/items/'item_name'direction_of_the_item'.png Reminder for the
     * direction : 1 = up 2 = right 3 = down 4 = left
     */
    public void draw(Interface inter, String s) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/" + s + getType() + "_"
                        + inter.getDirs_(direction_ - 1) + ".png", inter),
                print_x_, print_y_, inter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see map.Element#print(java.awt.Graphics2D, screen.Game_screen)
     * 
     * Specifies what to do to print the image. First it checks the bounds. then
     * when the projectile has gone far enough, it tries to move further the
     * next case in the map, if possible : It checks if there is a further case
     * and then what is on the case. If there is something, then the projectile
     * disappear Else it goes on the next case and clear the previous one.
     *  
     */

    public synchronized void print(Game_screen inter, String s) {

        switch (direction_) {
        case 1:
            if (print_y_ - 5 > 0)
                print_y_ -= 5;
            else
                inter.getMap_().setWeapons_(coord_);
            if (print_y_ / 16 < coord_.get_y() && coord_.get_y() - 1 > 0)
                coord_.add_y(-1);
            if (check_case(inter) == 1)
                inter.getMap_().setWeapons_(coord_);
            break;

        case 2:
            if (print_x_ + 5 + 16 < 256)
                print_x_ += 5;
            else
                inter.getMap_().setWeapons_(coord_);

            if (print_x_ / 16 > coord_.get_x() && coord_.get_x() + 1 < 15)
                coord_.add_x(1);
            if (check_case(inter) == 1)
                inter.getMap_().setWeapons_(coord_);
            break;

        case 3:
            if (print_y_ + 5 + 16 < 11 * 16)
                print_y_ += 5;
            else
                inter.getMap_().setWeapons_(coord_);

            if (print_y_ / 16 > coord_.get_y() && coord_.get_y() + 1 < 10)
                coord_.add_y(1);
            if (check_case(inter) == 1)
                inter.getMap_().setWeapons_(coord_);

            break;
        case 4:
            if (print_x_ - 5 > 0)
                print_x_ -= 5;
            else
                inter.getMap_().setWeapons_(coord_);

            if (print_x_ / 16 < coord_.get_x() && coord_.get_x() - 1 > 0)
                coord_.add_x(-1);
            if (check_case(inter) == 1)
                inter.getMap_().setWeapons_(coord_);

            break;
        }
        draw(inter, s);
    }
}