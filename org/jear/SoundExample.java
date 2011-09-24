package org.jear;

/**
 * Title:        Sound Example
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      My Desk
 * @author Richie Bielak
 * @version 1.0
 */

import javax.sound.midi.*;

public class SoundExample {

    public SoundExample() throws Exception {
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open ();
		//		Sequencer sequencer = MidiSystem.getSequencer();
		System.out.println("Got Synthsizer");
		Soundbank sb = synth.getDefaultSoundbank ();
		if (sb == null) {
			System.out.println ("No sound banks");
		}
		else {
			
			Instrument [] inst = sb.getInstruments();
			System.out.println ("Instruments loaded: " + inst.length);
			Instrument piano = null;
			for (int i=0; i < inst.length; i++) {
				if (inst[i].getName().equals ("Tubular Bell")) {
					piano = inst[i];
				}
				//				System.out.println ("I:" + inst[i].getName());
			}
			if (piano != null) {
				System.out.println ("Found:" + piano.getName ());
				synth.loadInstrument (piano);
				System.out.println ("Count of loaded inst: " + 
									synth.getLoadedInstruments().length);
				MidiChannel mc = synth.getChannels()[0];
				mc.noteOn (60, 74);
				System.out.println ("Sound should be playing");
				//				Thread.sleep (1000);
				mc.noteOn (63, 64);	
				//				Thread.sleep (1000);			
				mc.noteOn (67, 64);	
				mc.noteOn (69, 64);	
				for (int i=0; i<7; i++) {
					System.out.println ("tick..");
					Thread.sleep (1000);
				}
					
				mc.noteOff (63, 64);				
				mc.noteOff (67, 64);				
				mc.noteOff (60, 64);
				mc.noteOff (69, 64);	
					
			}
		}
		System.out.println ("Done with tests...");
		synth.close ();
    }

	public static void main (String [] argv) throws Exception {
		SoundExample ex = new SoundExample ();
		System.out.println("Exiting");
		System.exit (0);
	}
}
