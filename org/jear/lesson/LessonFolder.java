package org.jear.lesson;

/**
 * <p>Title: </p>
 * <p>Description: Jear - Java Ear Training</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: RB Software</p>
 * @author Richie Bielak
 * @version 0.4
 */

import java.util.Vector;
import java.util.ListIterator;

public class LessonFolder implements ListIterator {

    private Vector lessons;
    private int currentLesson;

    private static LessonFolder theInstance = null;

    // Singleton holding the lessons we are playing
    public synchronized static LessonFolder getInstance () {
        if (theInstance == null) {
            theInstance = new LessonFolder();
        }
        return theInstance;
    }

    // Lesson forlde contains a bunch of lessons
    public LessonFolder() {
        currentLesson = 0;
        lessons = new Vector ();
    }


    // Return next lesson, null if no more.
    public Lesson getNext () {
        Lesson aLesson = null;
        if (currentLesson < lessons.size()) {
            aLesson = (Lesson)lessons.elementAt(currentLesson);
            currentLesson++;
        }
        return aLesson;
    }

    public Object next () {
        currentLesson++;
        return lessons.elementAt (currentLesson - 1);
    }

    public int nextIndex () {return currentLesson;}


    public boolean hasNext () {
        return currentLesson < lessons.size();
    }

    public Object previous () {
          currentLesson--;
          return lessons.elementAt(currentLesson);
    }

    public boolean hasPrevious () {
        return currentLesson > 0;
    }

    public int previousIndex () {return currentLesson - 1;}

    public void add (Object aLesson) {
        lessons.add(aLesson);
    }

    public void remove () throws UnsupportedOperationException {
		throw new UnsupportedOperationException ("Cannot remove lessons");
	}


	public void set (Object o) throws UnsupportedOperationException {
		throw new UnsupportedOperationException ("Cannot set lessons");
	}

}