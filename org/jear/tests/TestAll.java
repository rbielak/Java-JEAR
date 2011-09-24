package org.jear.tests;

import junit.framework.*;

public class TestAll {

	public TestAll () {
	}

	public static TestSuite allTests () {
		TestSuite result = new TestSuite ();
        result.addTest (new TestMainWindow ());
//         result.addTest (new TestLessonGen());
		// result.addTest (new TestKeyboard());
		//	result.addTest (new TestSimpleSynth());
		return result;
	}

	public static void main (String [] argv) throws Exception {
		System.out.println ("Running all the tests");
		junit.textui.TestRunner.run (allTests());
		//		System.exit (0);
	}

}
