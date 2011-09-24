/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Nov 5, 2002
 * Time: 9:04:16 AM
 */
package org.jear.synth;

public interface IRecorder {

    // Called to memorize a note
    public void recordNote (int note);

    // Count of notes recorded so far
    public int getCount ();

    // return notes recorded so far
    public int [] getNotes ();

    // forget recorded notes
    public void reset ();

}
