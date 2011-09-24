package org.jear.tests;

import org.jear.ui.*;
import org.jear.synth.*;
import junit.framework.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestKeyboard extends TestCase {

	public TestKeyboard () {
		super ("TestKeyboard");
	}

	protected void runTest () throws Throwable {
		JFrame fr = new JFrame ("Test Keyboard");
		fr.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {System.exit(0);}});
		Keyboard kb =  new Keyboard ();
		SimpleSynth synth = new SimpleSynth ();
		kb.setSynth (synth);
		fr.getContentPane ().add (kb);
        fr.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation(d.width/2 - kb.getWidth()/2, d.height/2 - kb.getHeight()/2);
        fr.setSize(new Dimension(kb.getWidth(), kb.getHeight()));
		fr.setVisible(true);
		Thread.sleep (2000);
		kb.playNotes (new int[]{60, 64, 67}, 1000);

	}

}
