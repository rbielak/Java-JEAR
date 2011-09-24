package org.jear.ui.actions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class MenuAbout implements ActionListener {

	private JFrame parent;

	private static final String msg =
		" JEAR - Ear Training Teacher  v0.1\n" +
		"written in Java by Richie Bielak";

	public MenuAbout (JFrame parent) {
		this.parent = parent;
	}

	public void actionPerformed (ActionEvent e) {
		// Show a dialog box with some text
		JOptionPane.showMessageDialog (parent, msg);

	}

}
