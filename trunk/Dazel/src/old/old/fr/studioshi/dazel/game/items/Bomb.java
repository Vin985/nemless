/*
 * Created on 15 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.items;

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
public class Bomb extends Element {

    private int explode_;

    private Perso launcher_;

    private int timing_;

    /**
     * @param obstacle
     * @param coord
     * @param type
     */
    public Bomb(boolean obstacle, int x, int y, String type, Element hide,
            Perso launcher) {
        super(obstacle, x, y, type, hide, null);
        is_monster_ = false;
        launcher_ = launcher;
        timing_ = 70;
        explode_ = 15;
    }

    /**
     *  
     */
    public void destroy(Game_screen inter) {
        Monstre e = null;

        for (int i = coord_.get_x() - 1; i < coord_.get_x() + 2; i++) {
            for (int j = coord_.get_y() - 1; j < coord_.get_y() + 2; j++) {
                e = inter.getMap_().getMonsters_(new Coordinates(i, j));
                // if (e instanceof Perso) {
                //   Perso p = (Perso) e;
                if (e != null){
                    e.take_damage(inter, "bomb", launcher_);
                }
                //}
                /*
                 * if (e instanceof Wall) { Wall w = (Wall) e;
                 * w.set_destroyed(true); }
                 */
            }
        }
    }

    public void draw(Interface inter, String s) {
        inter.getG2_().drawImage(
                getDemoImage("/resources/sprites/" + s + getType() + ".png", inter),
                getCoord().get_x() * 16, getCoord().get_y() * 16, inter);
    }

    /**
     *  
     */
    public void print(Game_screen inter, String s) {
        if (timing_ > 0) {
            draw(inter, s);
            timing_--;
        } else {
            if (explode_ == 15)
                destroy(inter);
            if (explode_ > 0) {
                explode_--;
                inter.getG2_()
                        .drawImage(
                                getDemoImage("/resources/sprites/" + s + "explode.png",
                                        inter), getCoord().get_x() * 16,
                                getCoord().get_y() * 16, inter);
            }
            if (explode_ == 0){
                inter.getMap_().setElement(coord_);
            }
        }
    }
}