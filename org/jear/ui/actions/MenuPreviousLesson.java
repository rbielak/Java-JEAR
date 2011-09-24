package org.jear.ui.actions;

import org.jear.lesson.Teacher;
import org.jear.lesson.Lesson;
import org.jear.lesson.LessonFolder;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: richieb
 * Date: Jan 28, 2003
 * Time: 6:24:57 PM
 * To change this template use Options | File Templates.
 */
public class MenuPreviousLesson extends JearAction {

    private JMenuItem next;
    private JMenuItem previous;

    public MenuPreviousLesson (Teacher t, JMenuItem next, JMenuItem previous) {
        teach = t;
        this.next = next;
        this.previous = previous;
    }

    public void actionPerformed (ActionEvent ev) {
        LessonFolder folder = LessonFolder.getInstance();
        if (folder.hasPrevious ()) {
           Lesson l = (Lesson)folder.previous();
           teach.setLesson(l);
           next.setEnabled (true);
        }
        previous.setEnabled (folder.hasPrevious ());
    }


}
