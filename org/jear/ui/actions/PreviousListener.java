/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Nov 12, 2002
 * Time: 12:28:51 PM
 */
package org.jear.ui.actions;

import org.jear.lesson.Teacher;

import java.awt.event.ActionEvent;

public class PreviousListener extends JearAction {

    public PreviousListener (Teacher t) {
        teach = t;
    }

    public void actionPerformed (ActionEvent e) {
        System.out.println ("Previous action");
        teach.skipToPreviousQuestion();
    }

}
