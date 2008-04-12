/*
 * Created on 12 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.sound;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.net.MalformedURLException;

class SoundLoader extends Thread {
    URL completeURL;

    String relativeURL;

    SoundList soundList;

    public SoundLoader(SoundList soundList, URL baseURL, String relativeURL) {
        this.soundList = soundList;
        try {
            completeURL = new URL(baseURL, relativeURL);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        this.relativeURL = relativeURL;
        setPriority(MIN_PRIORITY);
        this.start();
    }

    public void run() {
        AudioClip audioClip = Applet.newAudioClip(completeURL);
        soundList.putClip(audioClip, relativeURL);
    }
}