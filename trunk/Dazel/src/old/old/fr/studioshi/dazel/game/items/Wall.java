/*
 * Created on 15 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.items;

import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Wall extends Element {

    boolean destroyable_;

    boolean destroyed_;

    protected int direction_;

    /**
     * @param obstacle
     * @param coord
     * @param map
     * @param type
     * @param destroy
     * @param destroyable
     * @param direction
     */
    public Wall(boolean obstacle, Coordinates coord, String type, boolean destroyed,
            boolean destroyable, int direction, Element hide) {
        super(obstacle, coord.get_x(), coord.get_y(), type, hide, null);
        direction_ = direction;
        destroyed_ = destroyed;
        destroyable_ = destroyable;
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
    public boolean is_destroyed() {
        return destroyed_;
    }

    /**
     * @return
     */
    public boolean is_destroyable() {
        return destroyable_;
    }

    /*
     * (non-Javadoc)
     * 
     * @see map.Element#print()
     */
    public void print() {
    }

    /**
     * @param destroy
     */
    public void set_destroyed(boolean destroy) {
        destroyed_ = destroy;
    }

    /**
     * @param destroyable
     */
    public void set_destroyable(boolean destroyable) {
        destroyable_ = destroyable;
    }

    public void set_dir(int dir) {
        direction_ = dir;
    }
   
}