/**
 * Keyboard - draws a keyboard and uses SimpleSynth to play the notes.
 *  when keys are pressed with a mouse sound plays.
 * 
 * This code was clone from Java sound examples from SUN
 *
 * date 10/22/02
 * @author Richie Bielak
 */

package org.jear.ui;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jear.synth.SimpleSynth;

public class Keyboard extends JPanel implements MouseListener {

	private SimpleSynth synth;

	private Vector whiteKeys;
	private Vector blackKeys;
	private Vector keys;

	private Key currentKey;

	final int kw = 20, kh = 100;

	/**
	 * Create a graphical Keyboard that will catch mouse events and 
	 * play the notes
	 */
	public Keyboard () {
		setLayout (new BorderLayout());
		whiteKeys = new Vector ();
		blackKeys = new Vector ();
		keys = new Vector ();
		currentKey = null;
		setPreferredSize(new Dimension(42*kw, kh+1));
		int transpose = 24;  
		int whiteIDs[] = { 0, 2, 4, 5, 7, 9, 11 }; 
		// Set up the keys - stolen from SUN examples
		for (int i = 0, x = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++, x += kw) {
				int keyNum = i * 12 + whiteIDs[j] + transpose;
				whiteKeys.add(new Key(x, 0, kw, kh, keyNum));
			}
		}
		for (int i = 0, x = 0; i < 6; i++, x += kw) {
			int keyNum = i * 12 + transpose;
			blackKeys.add(new Key((x += kw)-4, 0, kw/2, kh/2, keyNum+1));
			blackKeys.add(new Key((x += kw)-4, 0, kw/2, kh/2, keyNum+3));
			x += kw;
			blackKeys.add(new Key((x += kw)-4, 0, kw/2, kh/2, keyNum+6));
			blackKeys.add(new Key((x += kw)-4, 0, kw/2, kh/2, keyNum+8));
			blackKeys.add(new Key((x += kw)-4, 0, kw/2, kh/2, keyNum+10));
		}
		keys.addAll(blackKeys);
		keys.addAll(whiteKeys);

		addMouseListener (this);
	}

	/**
	 * Repaint the keyboard showing any pressed keys.
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Dimension d = getSize();
		
		g2.setBackground(getBackground());
		g2.clearRect(0, 0, d.width, d.height);
		
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 42*kw, kh);
		
		for (int i = 0; i < whiteKeys.size(); i++) {
			Key key = (Key) whiteKeys.get(i);
			g2.setColor(Color.black);
			if (key.getIsOn ()) {
				g2.setColor(Color.blue);
				g2.fill(key);
			}
			g2.draw(key);
		}
		for (int i = 0; i < blackKeys.size(); i++) {
			Key key = (Key) blackKeys.get(i);
			if (key.getIsOn()) 
				g2.setColor(Color.blue);
			else
				g2.setColor(Color.black);
			g2.fill(key);
		}
	}

	public void playNotes (int [] notes, int duration) {
		// play the notes and show the keys changing
		try {
			for (int i=0; i < notes.length; i++) {
				Key key = getKey (notes[i]);
				key.setIsOn (true);
				synth.noteOn (key.getKeyNumber());
				repaint ();
				Thread.sleep (duration);
				synth.noteOff (key.getKeyNumber());
				key.setIsOn (false);
				repaint ();			
			}
		}
		catch (Exception e) {
			System.out.println ("Exception in playNotes: " + e.getMessage ());
			e.printStackTrace ();
		}
	}

	public void setSynth (SimpleSynth synth) {
		this.synth = synth;
	}

    public SimpleSynth getSynth() {
        return synth;
    }

	public void mousePressed (MouseEvent e) {
		Key key = getKey (e.getPoint());
		key.setIsOn (true);
		synth.noteOn (key.getKeyNumber());
		repaint ();
		currentKey = key;
	}

	public void mouseReleased (MouseEvent e) {
		if (currentKey != null) {
			synth.noteOff (currentKey.getKeyNumber());
			currentKey.setIsOn (false);
			repaint ();
			currentKey = null;
		}

	}

	public void mouseEntered (MouseEvent e) {}

	public void mouseExited (MouseEvent e) {}

	public void mouseClicked (MouseEvent e) {}

	protected Key getKey(Point point) {
		for (int i = 0; i < keys.size(); i++) {
			if (((Key) keys.get(i)).contains(point)) {
				return (Key) keys.get(i);
			}
		}
		return null;
	}

	protected Key getKey (int note) {
		for (int i = 0; i < keys.size(); i++) {
			if (((Key) keys.get(i)).getKeyNumber() == note) {
				return (Key) keys.get(i);
			}
		}
		return null;
	}

}


class Key extends Rectangle {

	private int keyNumber;

	private boolean isOn;

	public Key (int x, int y, int width, int height, int num) {
		super (x, y, width, height);
		keyNumber = num;
		isOn = false;
	}

	public int getKeyNumber () {return keyNumber;}

	public boolean getIsOn () {return isOn;}

	public void setIsOn (boolean v) {isOn = v;}
	
}
