/*
 * Created on 11 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.items;

import old.fr.studioshi.dazel.game.ui.Game_screen;
import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class Door extends Element {

    int direction_;

    private boolean open_;

    /**
     * @param obstacle
     * @param coord
     * @param type
     * @param open
     * @param key
     * @param big_key
     * @param direction
     */
    public Door(boolean obstacle, Coordinates coord, String type, boolean open,
            int direction, Element hide) {
        super(obstacle, coord.get_x(), coord.get_y(), type, hide, null);
        open_ = open;
        direction_ = direction;
    }
    
    public Door() {
        super(true, 0, 0, "door", null, null);
        open_ = false;
        direction_ = 0;
    }

    /**
     * @return
     */
    public int get_dir() {
        return direction_;
    }

    /**
     * @return
     */
    public boolean is_open() {
        return open_;
    }

    /*
     * (non-Javadoc)
     * 
     * @see map.Element#print()
     */
    public void print(Game_screen inter, String s) {
      /*  if (open_)
            inter.getG2_().drawImage(
                    getDemoImage("/Sprites/dungeon/blue/blue-dooropen-"
                            + inter.getDirs_(direction_) + ".png", inter),
                    getCoord().get_x() * 16, getCoord().get_y() * 16, inter);
        */
       /* else
            inter.getG2_().drawImage(
                    getDemoImage("/Sprites/dungeon/green/green-doorclosed-"
                            + inter.getDirs_(direction_) + ".png", inter),
                    getCoord().get_x() * 16, getCoord().get_y() * 16, inter);*/
    }

    /**
     * @param dir
     */
    public void set_dir(int dir) {
        direction_ = dir;
    }

    /**
     * @param open
     */
    public void setOpen(boolean open) {
        open_ = open;
    }
    
    public boolean getOpen()
    {
    	return open_;
    }
}

