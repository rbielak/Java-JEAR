package org.jear.tests;

import junit.framework.*;
import org.jear.synth.SimpleSynth;

public class TestSimpleSynth extends TestCase {
	
	public TestSimpleSynth () {
		super ("TestSimpleSynth");
	}

	private SimpleSynth synth;

	protected void setUp () {
		try {
			System.out.println ("Creating simple synth");
			synth = new SimpleSynth ();		
		}
		catch (Exception e) {
			System.out.println ("Error creating synth");
			e.printStackTrace();
		}
	}

	protected void tearDown () {
		synth.close ();
	};

	protected void runTest () throws Throwable {
		testOneNote ();
		testIntervalMelodic ();
		testChordsMelodic ();
		testIntervalHarmonic ();
		testChordsHarmonic ();
		testScale ();
	}
	
	public void testOneNote () throws Throwable {
		System.out.println ("Playing C:");
		int [] notes = {60};
		synth.playNotes (notes, true, 2000);
	}

	public void testIntervalMelodic () throws Throwable {
		System.out.println ("Playing C and G");
		int [] notes = {60, 67};
		synth.playNotes (notes, true, 2000/2);
	}

	public void testChordsMelodic () throws Throwable {
		System.out.println ("Playing Cmajor");
		int [] notes = {60, 64, 67};
		synth.playNotes (notes, true, 2000/3);
	}

	public void testIntervalHarmonic () throws Throwable {
		System.out.println ("Playing C and G");
		int [] notes = {60, 67};
		synth.playNotes (notes, false, 2000);
	}

	public void testChordsHarmonic () throws Throwable {
		System.out.println ("Playing Cminor");
		int [] notes = {60, 63, 67};
		synth.playNotes (notes, false, 2000);
	}

	public void testScale () throws Throwable {
		System.out.println ("Playing C major scale");
		int [] notes = {60, 62, 64, 65, 67, 69, 71, 72};
		synth.playNotes (notes, true, 1000);
		notes = new int[]{60, 62, 63, 65, 67, 69, 70, 72};
		System.out.println ("Playing C dorian mode");
		synth.playNotes (notes, true, 1000);
	}


}
