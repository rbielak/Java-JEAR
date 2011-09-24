/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Nov 5, 2002
 * Time: 5:15:58 PM
 */
package org.jear.ui.actions;

import org.jear.lesson.Teacher;
import org.jear.ui.actions.JearAction;

import java.awt.event.ActionEvent;

public class PlayListener extends JearAction {

    public PlayListener (Teacher v) {teach = v;}

    public void actionPerformed (ActionEvent e) {
        try {
            System.out.println ("asking question");
            teach.askQuestion();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
