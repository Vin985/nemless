/*
 * Created on 3 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.perso;

import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Monstre extends Perso {

    /**
     * @author Vincent
     * 
     * To change the template for this generated type comment go to
     * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
     */

    boolean enemy_face_;

    protected boolean has_hit_;

    protected boolean is_called_;

    protected int print_x_;

    protected int print_y_;

    protected boolean under_attack_;

    protected int xp_;

    /**
     * @param type
     * @param health
     * @param direction
     * @param under_attack
     * @param enemy_face
     * @param coord
     */
    public Monstre(String type, int health, int direction,
            boolean under_attack, boolean enemy_face, Coordinates coord) {
        super(type, health, direction, coord, true);
        under_attack_ = under_attack;
        enemy_face_ = enemy_face;
        is_monster_ = true;
        print_x_ = coord.get_x() * 16;
        print_y_ = coord.get_y() * 16;
    }

    /**
     *  
     */
    public void attack() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#die()
     */
    public void die() {
    }

    /**
     * @return Returns the has_hit_.
     */
    public boolean isHas_hit_() {
        return this.has_hit_;
    }

    /**
     *  
     */
    public void move() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see perso.Perso#print()
     */
    public void print() {
    }

    /**
     * @param has_hit_
     *            The has_hit_ to set.
     */
    public void setHas_hit_(boolean has_hit_) {
        this.has_hit_ = has_hit_;
    }

    public void setX(int x) {
        coord_.set_x(x);
        print_x_ = x * 16;
    }

    public void setY(int y) {
        coord_.set_y(y);
        print_y_ = y * 16;
    }

    /**
     *  
     */
    public void watch() {
    }

}

