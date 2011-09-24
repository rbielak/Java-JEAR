package org.jear.ui.actions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuExit implements ActionListener {

	public MenuExit () {
	}

	public void actionPerformed (ActionEvent e) {
		System.out.println ("Exiting....");
		System.exit (0);
	}

}
