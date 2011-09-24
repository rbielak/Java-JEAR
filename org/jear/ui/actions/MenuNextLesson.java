package org.jear.ui.actions;

import java.awt.event.*;
import org.jear.lesson.LessonFolder;
import org.jear.lesson.Lesson;
import org.jear.lesson.Teacher;

import javax.swing.*;
/**
 * <p>Title: </p>
 * <p>Description: Jear - Java Ear Training</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: RB Software</p>
 * @author Richie Bielak
 * @version 0.4
 */


public class MenuNextLesson extends JearAction {

    private JMenuItem next;
    private JMenuItem previous;

    public MenuNextLesson(Teacher t, JMenuItem next, JMenuItem previous) {
        teach = t;
        this.next = next;
        this.previous = previous;
    }

    public void actionPerformed(ActionEvent e) {
        LessonFolder folder = LessonFolder.getInstance();
        Lesson l = null;
        if (folder.hasNext()) {
            l = (Lesson)folder.next();
            teach.setLesson(l);
            previous.setEnabled (true);
        }
        next.setEnabled (folder.hasNext ());
    }

}