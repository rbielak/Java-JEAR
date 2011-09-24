/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Oct 25, 2002
 * Time: 5:59:35 PM
 */
package org.jear.tests;

import junit.framework.TestCase;
import org.jear.ui.JearWindow;
import org.jear.ui.Keyboard;
import org.jear.lesson.Lesson;
import org.jear.lesson.LessonGenerator;
import org.jear.lesson.Teacher;

import java.awt.*;

public class TestMainWindow extends TestCase {

    public TestMainWindow () {
        super ("TestMainWindow");
    }

    protected void runTest () throws Throwable {
        LessonGenerator gen = new LessonGenerator ();
        Lesson lesson = gen.createIntervalLesson(5, new int[]{0, 1, 2},
                true, true, "Intervals (Melodic)",
                "Unison, Major/Minor Seconds, Melodic, Up");
        Keyboard kb = new Keyboard ();
        Teacher teacher = new Teacher (kb);
        JearWindow jw = new JearWindow (kb, teacher);
        jw.setLesson (lesson);
        jw.setSize (new Dimension (850, 300));
        jw.setVisible(true);
    }
}
