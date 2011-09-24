/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Oct 24, 2002
 * Time: 5:57:14 PM
 */
package org.jear.lesson;


import java.util.Vector;
import java.util.ListIterator;

/**
 *  A Lesson is a collection of questions together with a score that is set as
 *  the questions are answered. A Teacher will ask the question and set the score
 *  in this lesson.
 */
public class Lesson implements ListIterator {

    private String description;
    private String name;
    private Vector questions;
    private boolean melodic;
    private int currentQuestionIndex;

    public Lesson () {
        description = null;
        name = null;
        questions = new Vector();
        melodic = true;
        currentQuestionIndex = 0;
    }

	public void add (Object o) {
		addQuestion ((Question)o);
	}

    public void addQuestion (Question q) {
        questions.addElement(q);
    }

	// Attributes of the lesson
    public String getDescription () {return description;}

    public void setDescription (String v) {description = v;}

    public String getName () {return name;}

    public void setName (String v) {name =v;}

    public boolean getMelodic () {return melodic;}

    public void setMelodic (boolean v) {melodic = v;}

	/* Implementation of ListIterator interface */
    public int getCurrentQuestionIndex () {
		// index points to the next question
        return currentQuestionIndex - 1;
    }

    public int getQuestionCount () {
        return questions.size();
    }

    public Object next () {
        Question result = (Question)questions.elementAt (currentQuestionIndex);
		// System.out.println ("Lesson.next returing: " + result + " index: " + currentQuestionIndex);
        currentQuestionIndex++;
        return result;
    }

    public int nextIndex () {return currentQuestionIndex;}

    public int previousIndex () {return currentQuestionIndex - 1;}

    public Object previous () {
        currentQuestionIndex--;
		Question result = (Question)questions.elementAt (currentQuestionIndex);
		return result;
    }

	public void remove () throws UnsupportedOperationException {
		throw new UnsupportedOperationException ("Cannot remove questions");
	}


	public void set (Object o) throws UnsupportedOperationException {
		throw new UnsupportedOperationException ("Cannot set questions");
	}


	public boolean hasNext () {
		return (currentQuestionIndex < questions.size());
	}

    public boolean hasPrevious () {
		return (currentQuestionIndex > 0);
	}


    /**
     * Position the cursor before the first question.
     */
    public void start () {
        currentQuestionIndex = 0;
    }

    /**
     * Count the number of questions answered wrong
     * @return  number of wrong answers
     */
    public int getWrongs () {
        int result = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = (Question)questions.elementAt (i);
            if (q.getAnswerState() == Question.ANSWERED_WRONG)
                result++;
        }
        return result;
    }

    /**
     * Count correct answers
     * @return  number of correct answers
     */
    public int getCorrects () {
        int result = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = (Question)questions.elementAt (i);
            if (q.getAnswerState() == Question.ANSWERED_CORRECT)
                result++;
        }
        return result;
    }

}
