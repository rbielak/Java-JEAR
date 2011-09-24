/*
 * Date: Oct 24, 2002
 *
 * Describes a single question. A question is a sequence of notes that will be played by the
 * teacher that the student has to identify.
 *
 */
package org.jear.lesson;

public class Question {

    public static final int NOT_ANSWERED = 1;
    public static final int ANSWERED_WRONG = 2;
    public static final int ANSWERED_CORRECT = 3;

    public Question () {
        notes = null;
        answerState = NOT_ANSWERED;
    }

    private int [] notes;
    private int answerState;

    /**
     * Notes that are played for this question
     * @return  array of notes
     */
    public int [] getNotes () {
        return notes;
    }

    /**
     * Set the sequence of notes that will be played for this question.
     * @param notes an array of notes (60 == middle C)
     */
    public void setNotes (int [] notes) {
        this.notes = notes;
    }

    public int getAnswerState () {
            return answerState;
    }

    public void markWrong () {
        answerState = ANSWERED_WRONG;
    }

    /**
     * Check if a sequence of notes is the same as the notes in this question.
     * Sets "answerState" according to the correctness of the answer
     * @param answer
     */
    public void checkAnswer (int [] answer) {
        boolean correct = false;
        if (answer.length == notes.length) {
            correct = true;
            for (int i =0; i < answer.length; i++)
                correct = correct && (answer[i] == notes[i]);
        }
        if (correct)
            answerState = ANSWERED_CORRECT;
        else
            answerState = ANSWERED_WRONG;
		System.out.println ("QUestion answered correct: " + correct + " ST:" + answerState);
    }
}
