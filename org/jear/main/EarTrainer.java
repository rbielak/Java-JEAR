/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Oct 30, 2002
 * Time: 4:22:25 PM
 */
package org.jear.main;

import org.jear.synth.SimpleSynth;
import org.jear.ui.Keyboard;
import org.jear.ui.JearWindow;
import org.jear.lesson.LessonGenerator;
import org.jear.lesson.Lesson;
import org.jear.lesson.Teacher;
import org.jear.lesson.LessonFolder;

import java.awt.*;

public class EarTrainer {

    private SimpleSynth synth;
    private Keyboard keyboard;
    private JearWindow mainWindow;
    private LessonGenerator gen;
    private Teacher teacher;
    private LessonFolder lessons;

    public EarTrainer () throws Exception {
        synth = new SimpleSynth ();
        keyboard = new Keyboard ();
        keyboard.setSynth(synth);
        teacher = new Teacher (keyboard);
        gen = new LessonGenerator ();

        lessons = LessonFolder.getInstance();
        Lesson lesson = gen.createIntervalLesson(10, new int[]{0, 1, 2, 3, 4},
                        true, true, "Intervals (Melodic)",
                        "(1) Unison, Major/Minor Seconds and Thirds, Melodic, Up");
        lessons.add(lesson);
        lesson = gen.createIntervalLesson(10, new int[]{0, 5, 7, 12},
                        true, true, "Intervals (Melodic)",
                        "(2) Unison, Perfect Fourth, Perfect Fifth and Octave, Melodic, Up");
        lessons.add(lesson);
        lesson = gen.createIntervalLesson(10, new int[]{0, 1, 2, 3, 4},
                      false, true, "Intervals (Harmonic)",
                      "(3) Unison, Major/Minor Seconds and Thirds, Harmonic, Up");
        lessons.add(lesson);

        teacher.setLesson (lessons.getNext());
        mainWindow = new JearWindow (keyboard, teacher);
        mainWindow.setLesson(teacher.getCurrentLesson());
        mainWindow.setSize (new Dimension (850, 370));
        mainWindow.setVisible(true);
        teacher.addObserver(mainWindow);
    }

    /**
     * Main line of the Java Ear Training program
     *
     * @param args
     */
    public static void main (String [] args) {
        try {
            EarTrainer et = new EarTrainer ();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println ("Crashing --- exiting");
        }
    }

}
