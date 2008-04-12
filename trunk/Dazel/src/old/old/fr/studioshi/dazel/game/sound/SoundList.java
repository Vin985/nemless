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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.applet.AudioClip;
import java.net.URL;

import javax.swing.JApplet;

/**
 * Loads and holds a bunch of audio files whose locations are specified relative
 * to a fixed base URL.
 */
public class SoundList extends java.util.Hashtable {
    JApplet applet;

    URL baseURL;

    public SoundList(URL baseURL) {
        super(5); //Initialize Hashtable with capacity of 5 entries.
        this.baseURL = baseURL;
    }

    public AudioClip getClip(String relativeURL) {
        return (AudioClip) get(relativeURL);
    }

    public void putClip(AudioClip clip, String relativeURL) {
        put(relativeURL, clip);
    }

    public void startLoading(String relativeURL) {
        new SoundLoader(this, baseURL, relativeURL);
    }
}