/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Oct 24, 2002
 * Time: 6:25:05 PM
 */
package org.jear.lesson;

import java.util.Random;

/**
 * Generete lessons - means creating a list of questions, getting a description and
 * the name of the lesson from somewhere (eventually from an XML file)
 */
public class LessonGenerator {

    private Random random;

    public LessonGenerator () {
         random = new Random ();
    }

    /**
     * Create a lesson to identify intervals.
     *
     * @param questionCount - count of questions
     * @param intervals   - kind of intervals to generate (integers indicate semitones)
     * @param melodic - true if intervals are to be played melodically
     * @param name        - name of this lesson
     * @param description  - description
     * @return  Lesson
     */
    public Lesson createIntervalLesson (int questionCount, int [] intervals,
                                        boolean melodic,
                                        boolean up,
                                        String name, String description)
    {
        Lesson result = new Lesson ();
        result.setDescription(description);
        result.setName(name);
		result.setMelodic (melodic);
        // now create questions
        int C4 = 60; // Start from middle C
        for (int i=0; i < questionCount; i++) {
            Question q = new Question ();
            int note = intervals [random.nextInt(intervals.length)];
            if (up)
                note = C4 + note;
            else
                note = C4 - note;
            q.setNotes(new int[]{C4, note});
            result.addQuestion(q);
        }
        return result;
    }

}
