/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Nov 1, 2002
 * Time: 6:28:35 PM
 */
package org.jear.ui;

/**
 *  Keyboard player runs as a thread that can play the keyboard asynchronously. It's needed
 * by the action classes that handle GUI events.
 */
public class KeyboardPlayer extends Thread {

    private Keyboard keyboard;
    private int [] notesToPlay;

    public KeyboardPlayer (Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Plays the notes on the keyboard and shows the keys pressed
     * @param notes - notes to play
     */
    synchronized public void playNotes (int [] notes) {
        notesToPlay = notes;
        notify();
    }

    /**
     * The main loop of the thread that asynchronously play the keyboard
     */
    public void run () {
        // First thing we do is wait
        try {
            while (true) {
                synchronized (this) {
                    wait();
                }
                //waitForNotes ();
                keyboard.playNotes(notesToPlay, 1000);
            }
        }
        catch (Exception e) {
            System.out.println ("*** Exception in player thread:" + e);
            e.printStackTrace();
        }
    }

}
