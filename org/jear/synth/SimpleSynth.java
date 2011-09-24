/**
 * SimpleSynth - simple interface to the Java sythesizer.
 *
 * @date 10/22/02
 * @author Richie Bielak
 */
package org.jear.synth;


import javax.sound.midi.*;

public class SimpleSynth {

	private int velocity;
	private Synthesizer synth;
	private MidiChannel channel_0;

    private IRecorder recorder;

	/**
	 * Create a SimpleSynth - uses  "Piano" as the default instrument.
	 */
	public SimpleSynth () throws Exception {
		velocity = 64;
		synth = MidiSystem.getSynthesizer ();
		synth.open ();
		// set piano as the default instrument
		Soundbank sb = synth.getDefaultSoundbank ();
		if (sb == null)
			throw new Exception ("No soundbanks defined in default synthesizer");
		Instrument piano = null;
		Instrument [] inst = sb.getInstruments ();
		for (int i=0; i < inst.length; i++) {
			if (inst[i].getName().equalsIgnoreCase ("piano")) {
				piano = inst[i];
				break;
			}
		}
		if (piano == null)
			throw new Exception ("Cannot find 'piano' as the default instrument");
		synth.loadInstrument (piano);
		channel_0 = synth.getChannels()[0];
	}

    public void setRecorder (IRecorder r) {
        recorder = r;
    }

	/**
	 * Play a sequence of notes - max allowed 8.
	 *
	 * @param notes - notes to play (60 == middle C +1 or -1 is a semitone)
	 * @param melodic - if true, play notes one after another
	 * @param duration - duration in milliseconds 
	 */
	public void playNotes (int [] notes, boolean melodic, int duration) throws Exception {
		if (notes.length > 8) 
			throw new Exception ("Only 8 notes or less can be played");
		for (int i = 0; i < notes.length; i++) {
			channel_0.noteOn (notes[i], velocity);
            if (recorder != null) recorder.recordNote(notes[i]);
			if (melodic) {
				Thread.sleep (duration);
				channel_0.noteOff (notes[i], velocity);
			}
		}
		if (!melodic) {
			// Harmonic - wait for a while, then turn all notes off
			Thread.sleep (duration);
			channel_0.allNotesOff ();
		}
	}

	/**
	 * Close the synth
	 */
	public void close () {
		channel_0.allNotesOff ();
		synth.close ();
	}

	/**
	 * Start playing a note.
	 */
	public void noteOn (int note) {
		channel_0.noteOn (note, velocity);
        if (recorder != null) recorder.recordNote(note);
	}

	/**
	 * Stop playing a note - some instruments fade after a while anyways
	 */
	public void noteOff (int note) {
		channel_0.noteOff (note);
	}

	

}
