/*
 * Created on 3 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import java.awt.Font;

import old.fr.studioshi.dazel.game.ui.Game_screen;

import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Pnj extends Perso {

    private String dialog_;

    /**
     * @param type
     * @param health
     * @param direction
     * @param coord
     */
    public Pnj(String type, int health, int direction, Coordinates coord,
            String dialog) {
        super(type, health, direction, coord, true);
        dialog_ = dialog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#print()
     */
    public void print(Game_screen inter, String s) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/characters/Pnj/" + getType() + ".png",
                        inter), getCoord().get_x() * 16,
                getCoord().get_y() * 16, inter);
        talk(inter);
    }

    /**
     *  
     */
    public void talk(Game_screen inter) {
        inter.setFont_(new Font("Arial", Font.BOLD, 12));
        inter.getG2_().setFont(inter.getFont_());
        inter.getG2_().drawString(dialog_, 40, (coord_.get_y() - 1) * 16);

    }
}