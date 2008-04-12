/*
 * Created on 28 juin 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package old.fr.studioshi.dazel.game.items;

import java.util.LinkedList;
import java.util.ListIterator;

import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Map {
    int l_, h_, startx_, starty_;

    LinkedList minis_;

    Minimap link_;

    protected String name_;

    /**
     * @param map
     */
    public Map(String name, int l, int h, int x, int y, Minimap link) {
        name_ = name;
        h_ = h;
        l_ = l;
        startx_ = x;
        starty_ = y;
        link_ = link;
        minis_ = new LinkedList();
        minis_.add(new Minimap(0, 0, true));
    }

    public Map() {
        minis_ = new LinkedList();
    }

    /**
     * @return
     */
    public int getH() {
        return h_;
    }

    /**
     * @return
     */
    public int getL() {
        return l_;
    }

    public void setLink(Minimap m) {
        link_ = m;
    }

    public Minimap getLink() {
        return link_;
    }

    /**
     * @param coord
     * @return
     */
    public Minimap getMinimap(Coordinates coord) {
        ListIterator it = minis_.listIterator();

        while (it.hasNext()) {
            Minimap tmp = (Minimap) it.next();
            if (coord.equal(tmp.coord_)) {
                return tmp;
            }
        }
        return null;
    }

    public LinkedList getMinis() {
        return minis_;
    }

    /**
     * @return
     */
    public String getName() {
        return name_;
    }

    public int getStartx() {
        return startx_;
    }

    public int getStarty() {
        return starty_;
    }

    /**
     *  
     */
    public void print() {
    }

    /**
     * @param h
     */
    public void setH(int h) {
        h_ = h;
    }

    /**
     * @param hide
     */
    public void setHide(Minimap hide) {

    }

    /**
     * @param l
     */
    public void setL(int l) {
        l_ = l;
    }

    public String[] getMinimapNames() {
        ListIterator it = minis_.listIterator();
        String s[] = new String[minis_.size()];
        int i = 0;

        while (it.hasNext()) {
            Minimap tmp = (Minimap) it.next();
            s[i] = "Mini " + tmp.getCoord().get_x() + ", "
                    + tmp.getCoord().get_y();
            i++;
        }
        return s;
    }

    /**
     * @param mini
     */
    public void setMinimap(Minimap mini) {
        ListIterator it = minis_.listIterator();

        while (it.hasNext()) {
            Minimap tmp = (Minimap) it.next();
            if (mini.coord_.equal(tmp.coord_))
                it.remove();
        }
        minis_.add(mini);

    }

    /**
     * @param name
     */
    public void setName(String name) {
        name_ = name;
    }

    public void setStartx(int x) {
        startx_ = x;
    }

    public void setStarty(int y) {
        starty_ = y;
    }
}