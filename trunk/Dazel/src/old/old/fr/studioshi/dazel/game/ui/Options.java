/*
 * Created on 3 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.ui;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Options {
    private int a_;

    private int b_;

    private int down_;

    private int inventory_;

    private int left_;

    private int menu_;

    private int pause_;

    private int right_;

    private boolean sound_on_;

    private int up_;

    /**
     *  
     */
    public Options() {
        a_ = 32;
        b_ = 67;
        down_ = 40;
        right_ = 39;
        up_ = 38;
        left_ = 37;
        inventory_ = 10;
        pause_ = 80;
        menu_ = 27;
        sound_on_ = false;
        }

    /**
     * @return Returns the a_.
     */
    public int getA_() {
        return this.a_;
    }

    /**
     * @return Returns the b_.
     */
    public int getB_() {
        return this.b_;
    }

    /**
     * @return Returns the down_.
     */
    public int getDown_() {
        return this.down_;
    }

    /**
     * @return Returns the inventory_.
     */
    public int getInventory_() {
        return this.inventory_;
    }

    /**
     * @return Returns the left_.
     */
    public int getLeft_() {
        return this.left_;
    }

    /**
     * @return Returns the menu_.
     */
    public int getMenu_() {
        return this.menu_;
    }

    /**
     * @return Returns the pause_.
     */
    public int getPause_() {
        return this.pause_;
    }

    /**
     * @return Returns the right_.
     */
    public int getRight_() {
        return this.right_;
    }

    /**
     * @return Returns the up_.
     */
    public int getUp_() {
        return this.up_;
    }

    /**
     * @return Returns the sound_on_.
     */
    public boolean isSound_on_() {
        return this.sound_on_;
    }

    /**
     * @param a_
     *            The a_ to set.
     */
    public void setA_(int a_) {
        this.a_ = a_;
    }

    /**
     * @param b_
     *            The b_ to set.
     */
    public void setB_(int b_) {
        this.b_ = b_;
    }

    /**
     * @param down_
     *            The down_ to set.
     */
    public void setDown_(int down_) {
        this.down_ = down_;
    }

    /**
     * @param inventory_
     *            The inventory_ to set.
     */
    public void setInventory_(int inventory_) {
        this.inventory_ = inventory_;
    }

    /**
     * @param left_
     *            The left_ to set.
     */
    public void setLeft_(int left_) {
        this.left_ = left_;
    }

    /**
     * @param menu_
     *            The menu_ to set.
     */
    public void setMenu_(int menu_) {
        this.menu_ = menu_;
    }

    /**
     * @param pause_
     *            The pause_ to set.
     */
    public void setPause_(int pause_) {
        this.pause_ = pause_;
    }

    /**
     * @param right_
     *            The right_ to set.
     */
    public void setRight_(int right_) {
        this.right_ = right_;
    }

    /**
     * @param sound_on_
     *            The sound_on_ to set.
     */
    public void setSound_on_(boolean sound_on_) {
        this.sound_on_ = sound_on_;
    }

    /**
     * @param up_
     *            The up_ to set.
     */
    public void setUp_(int up_) {
        this.up_ = up_;
    }
}