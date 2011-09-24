/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Nov 5, 2002
 * Time: 5:04:41 PM
 */
package org.jear.ui.actions;

import org.jear.lesson.Teacher;
import org.jear.ui.actions.JearAction;

import java.awt.event.ActionEvent;

public class ShowListener extends JearAction {

    public ShowListener (Teacher v) {teach = v;}

    public void actionPerformed (ActionEvent e) {
        teach.showAnswer ();
    }

}
