/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Oct 25, 2002
 * Time: 10:37:12 AM
 */
package org.jear.tests;

import junit.framework.TestCase;
import org.jear.lesson.LessonGenerator;
import org.jear.lesson.Lesson;
import org.jear.lesson.Question;

public class TestLessonGen extends TestCase {

    public TestLessonGen  () {
        super ("TestLessonGen");
    }

    protected void runTest () throws Throwable {
        LessonGenerator gen = new LessonGenerator ();

        // Try and generate some lessons
        Lesson lesson = gen.createIntervalLesson(5, new int[]{0, 1, 2},
                true, true, "Unison, Major/Minor Seconds, Melodic, Up",
                "Short description");
        assertTrue ("right number of questions", lesson.getQuestionCount() == 5);
        assertTrue ("right kind", lesson.getMelodic() == true);

        // make sure the questions are like what we expect.
        for (int i = 0; i < lesson.getQuestionCount(); i++) {
            lesson.next ();
            Question q = (Question)lesson.next ();
            int [] notes = q.getNotes();
            assertTrue ("right number of notes", notes.length == 2);
            assertTrue ("notes properly related",
                    (notes[0] <= notes[1]) & (notes[1] - notes[0] <= 2));
        }

    }
}
