/*
 * Created on 14 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.sound;

import old.fr.studioshi.dazel.game.ui.Interface;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PlayThread extends Thread {

    private Interface inter_;

    private int song_;

    private boolean play;

    public PlayThread(Interface inter,int i) {
        inter_ = inter;
        play = false;
        start();
        song_ = i;
    }

    public void run() {

        while (!play)
            try {
                sleep(30);
                if (inter_.getSoundList_().getClip(inter_.getSongs_(song_)) != null) {
                    inter_.setAudio_(inter_.getSoundList_().getClip(
                            inter_.getSongs_(song_)));
                    inter_.getAudio_().loop();
                    play = true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}