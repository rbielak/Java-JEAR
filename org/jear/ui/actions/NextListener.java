/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Nov 5, 2002
 * Time: 5:13:08 PM
 */
package org.jear.ui.actions;

import org.jear.lesson.Teacher;

import java.awt.event.ActionEvent;

public class NextListener extends JearAction  {

    public NextListener (Teacher v) {teach = v;}

    public void actionPerformed (ActionEvent e) {
        System.out.println ("Skipping to next question");
        if (teach.getCurrentLesson().hasNext ())
            teach.skipToNextQuestion();
    }

}
