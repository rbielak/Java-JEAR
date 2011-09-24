/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Nov 5, 2002
 * Time: 9:21:35 AM
 */
package org.jear.synth;

import java.util.Observable;

/**
 * AsynchRecorder - records notes played and notifed observers when a note is added
 */
public class AsynchRecorder extends Observable implements IRecorder {

    private int [] recordedNotes;
    private int count;

    public AsynchRecorder () {
        count = 0;
        recordedNotes = new int[10];
    }

    // Called to memorize a note
    public void recordNote(int note) {
        if (count == recordedNotes.length)
            reallocateNotes();
        synchronized (recordedNotes) {
            recordedNotes [count] = note;
            count++;
        }
        this.setChanged();
        notifyObservers ("Test");
    }

    public int getCount () {return count;}

    // return notes recorded so far
    public int[] getNotes() {
        int [] result = new int [count];
        synchronized (recordedNotes) {
            for (int i=0; i < count; i++)
                result [i] = recordedNotes[i];
        }
        return result;
    }

    // forget recorded notes
    public void reset() {
        count = 0;
    }

    synchronized protected void reallocateNotes () {
        int [] newNotes = new int [recordedNotes.length + 10];
        for (int i = 0; i < recordedNotes.length; i++)
            newNotes [i] = recordedNotes[i];
        recordedNotes = newNotes;
    }
}
