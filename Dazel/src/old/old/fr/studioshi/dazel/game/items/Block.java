/*
 * Created on 6 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.items;

import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;
import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Block extends Element {
    boolean block_;

    /**
     * Comment for <code>moveable_</code> the moveable flag works as masks It
     * a 4 digit number only composed of 1 and 0 it follows this rule
     * concerniong the directions first digit : up second digit: right third
     * digit : down fourth digit: left
     * 
     * So 0101 means that the block can be moved to the right or the left
     */
    private int moveable_;

    /**
     * @param obstacle
     * @param coord
     * @param type
     * @param moveable
     * @param block
     */
    public Block(boolean obstacle, Coordinates coord, String type, int moveable,
            boolean block, Element hide) {
        super(obstacle, coord.get_x(), coord.get_y(), type, hide, null);
        moveable_ = moveable;
        block_ = block;
    }
    
    public Block()
    {
    	super(true, 0, 0, "", null, null);
        moveable_ = 0;
        block_ = false;
    }

    /**
     * @param inter
     * @param g2
     */
    public void draw(Interface inter) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/map/" + getType() + ".png", inter),
                getCoord().get_x() * 16, getCoord().get_y() * 16, inter);
    }

    /**
     * @return Returns the moveable_.
     */
    public int getMoveable_() {
        return moveable_;
    }

    /**
     * @return
     */
    public boolean is_block() {
        return block_;
    }

    /**
     *  
     */
    public void move() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see map.Element#print()
     */
    public void print(Game_screen inter) {
        draw(inter);
    }

    /**
     * @param block
     */
    public void set_block(boolean block) {
        block_ = block;
    }

    /**
     * @param moveable_
     *            The moveable_ to set.
     */
    public void setMoveable(int moveable) {
        this.moveable_ = moveable;
    }
}