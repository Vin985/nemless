/*
 * Created on 28 juin 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package old.fr.studioshi.dazel.game.perso;

import old.fr.studioshi.dazel.game.items.Element;
import old.fr.studioshi.dazel.game.ui.Game_screen;
import old.fr.studioshi.dazel.game.ui.Interface;
import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Perso extends Element {

    protected int attack_stat_;

    protected int direction_;

    protected int health_;

    protected int mov_cpt_;

    protected boolean moving_;

    /**
     * @param type
     * @param health
     * @param direction
     * @param coord
     */
    public Perso(String type, int health, int direction, Coordinates coord,
            boolean obs) {
        super(obs, coord.get_x(), coord.get_y(), type, null, null);
        direction_ = direction;
        health_ = health;
        coord_ = coord;
        mov_cpt_ = 15;
        attack_stat_ = 0;
    }

    /**
     *  
     */
    public void attack(Game_screen inter) {

    }

    /**
     *  
     */
    public void decAttack_stat_() {
        this.attack_stat_--;
    }

    /**
     *  
     */
    public void die() {
    }

    /**
     * @param inter
     * @param i
     * @param g2
     * @param s
     * @param x
     * @param y
     */
    public void draw(Interface inter, int i,String s, int x,
            int y) {
        inter.getG2_().drawImage(getDemoImage("../resources/sprites/characters/" + getType() + "/"
                + s + i, inter), getCoord().get_x() + x,
                getCoord().get_y() + y, inter);
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
    public int get_health() {
        return health_;
    }

    /**
     * @return Returns the attack_stat_.
     */
    public int getAttack_stat_() {
        return this.attack_stat_;
    }

    /**
     * @return Returns the mov_cpt.
     */
    public int getMov_cpt() {
        return this.mov_cpt_;
    }

    /**
     *  
     */
    public void incMov_cpt() {
        this.mov_cpt_--;
    }

    /**
     * @return Returns the moving_.
     */
    public boolean isMoving_() {
        return this.moving_;
    }

    /**
     *  
     */
    public void move() {
    }

    /**
     *  
     */
    public void print() {

    }

    /**
     * @param dir
     */
    public void set_dir(int dir) {
        direction_ = dir;
    }

    /**
     * @param health
     */
    public void set_health(int health) {
        health_ = health;
    }

    /**
     * @param attack_stat_
     *            The attack_stat_ to set.
     */
    public void setAttack_stat_(int attack_stat_) {
        this.attack_stat_ = attack_stat_;
    }

    /**
     * @param mov_cpt
     *            The mov_cpt to set.
     */
    public void setMov_cpt(int mov_cpt) {
        this.mov_cpt_ = mov_cpt;
    }

    /**
     * @param moving_
     *            The moving_ to set.
     */
    public void setMoving_(boolean moving_) {
        this.moving_ = moving_;
    }

    /**
     * @param inter
     * @param weapon
     */

    public void take_damage(Game_screen inter, String weapon, Perso enemy) {
    }
}