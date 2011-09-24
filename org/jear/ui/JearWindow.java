/*
 * JEAR - Java ear training
 * User: richieb
 * Date: Oct 25, 2002
 * Time: 5:48:34 PM
 */
package org.jear.ui;

import org.jear.lesson.Lesson;
import org.jear.lesson.Teacher;
import org.jear.lesson.Question;
import org.jear.ui.actions.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;
import java.util.Observable;

public class JearWindow extends JFrame implements Observer {


    private JLabel longDescription;
    private TitledBorder lessonBorder;

    private JButton play;
    private JButton next;
    private JButton previous;
    private JButton show;

    private JLabel questionLabel;
    private JLabel correctsLabel;
    private JLabel wrongsLabel;
    private JLabel averageLabel;
    private JLabel statusLabel;

    private JProgressBar lessonProgressBar;
    // Note: These two attributes can be removed and Teacher can be passed in where needed.
    // Teacher is the Observable that is passed to update method
    private Lesson currentLesson;
    private Teacher myTeacher;

    private Keyboard myKeyboard;

    public JearWindow (Keyboard keyboard, Teacher teacher) {
        super ("JEAR");
        myKeyboard = keyboard;
        myTeacher = teacher;
        getContentPane().setLayout (new BorderLayout());
        // create a menu - goes at the top
        getContentPane().add (makeMenu(), BorderLayout.NORTH);

        // main panel with descriptions and buttons goes in the center
        JPanel aPanel = new JPanel ();
        aPanel.setLayout(new BoxLayout (aPanel, BoxLayout.Y_AXIS));
        getContentPane().add (aPanel, BorderLayout.CENTER);

        JPanel lessonPanel = createLessonPanel();
        aPanel.add(lessonPanel);

        JPanel buttonsPanel = createButtonsPanel();
        aPanel.add (buttonsPanel);

        JPanel resultsPanel = createResultsPanel();
        aPanel.add(resultsPanel);

        JPanel statusPanel = createStatusPanel ();
        aPanel.add(statusPanel);

        // keyboard goes at the bottom
        getContentPane().add (myKeyboard, BorderLayout.SOUTH);

        // Add listener for close events
        addWindowListener (new WindowAdapter () {
               public void windowClosing (WindowEvent e) {System.exit (0);}});
        // Set default size of the frame
        setVisible (true);
    }

    private JPanel createResultsPanel() {
        TitledBorder border;
        JPanel resultsPanel = new JPanel ();
        GridLayout grid = new GridLayout ();
        grid.setHgap(10);
        grid.setVgap (10);
        resultsPanel.setLayout (grid);
        border = BorderFactory.createTitledBorder ("");
        border.setTitle ("Results");
        resultsPanel.setBorder (border);
        questionLabel = new JLabel ();
        resultsPanel.add (questionLabel);
        lessonProgressBar = new JProgressBar ();
        lessonProgressBar.setValue(0);
        lessonProgressBar.setForeground(Color.green);
        resultsPanel.add (lessonProgressBar);
        correctsLabel = new JLabel ();
        resultsPanel.add (correctsLabel);
        wrongsLabel = new JLabel ();
        resultsPanel.add (wrongsLabel);
        averageLabel = new JLabel ();
        resultsPanel.add (averageLabel);
        return resultsPanel;
    }

    private JPanel createStatusPanel () {
        TitledBorder border;
        JPanel result = new JPanel ();
        border = BorderFactory.createTitledBorder("");
        border.setTitle("Status");
        result.setBorder(border);
        statusLabel = new JLabel ();
        result.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        result.add(statusLabel);
        return result;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel ();
        TitledBorder border = BorderFactory.createTitledBorder("");
        border.setTitle ("Actions");
        buttonsPanel.setBorder (border);
        previous = new JButton ("<<Previous");
        previous.setToolTipText("Go back to previous question");
        previous.addActionListener (new PreviousListener (myTeacher));
        buttonsPanel.add (previous);
        play = new JButton ("Play Question");
        play.setToolTipText("Play current question.");
        play.addActionListener (new PlayListener (myTeacher));
        buttonsPanel.add(play);
        next = new JButton ("Next>>");
        next.setToolTipText ("Continue to the next question - answer marked as wrong");
        buttonsPanel.add (next);
        next.addActionListener (new NextListener (myTeacher));
        show = new JButton ("Show Answer");
        show.setToolTipText ("Show the answer to current question");
        show.addActionListener (new ShowListener (myTeacher));
        buttonsPanel.add (show);
        return buttonsPanel;
    }

    private JPanel createLessonPanel() {
        JPanel lessonPanel = new JPanel ();
        lessonPanel.setLayout(new BorderLayout());
        lessonBorder = BorderFactory.createTitledBorder("");
        lessonBorder.setTitle("");
        lessonPanel.setBorder(lessonBorder);
        longDescription = new JLabel();
        longDescription.setText ("");
        // desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        lessonPanel.add (longDescription, BorderLayout.WEST);
        return lessonPanel;
    }

    public void setLesson (Lesson aLesson) {
        currentLesson = aLesson;
        lessonBorder.setTitle (aLesson.getName());
        String desc = "<html><b>" + aLesson.getDescription() + "</b></html>";
        longDescription.setText (desc);
        lessonProgressBar.setMinimum(0);
        lessonProgressBar.setMaximum (currentLesson.getQuestionCount() - 1);
        update (null, null);
    }

    // Update display, when the objects we are watching change
    public void update (Observable o, Object arg) {
        if (currentLesson != myTeacher.getCurrentLesson())
            setLesson (myTeacher.getCurrentLesson());
		String text = "Question #: " + (myTeacher.getCurrentLesson().getCurrentQuestionIndex()+1);
		int answerState = myTeacher.getCurrentQuestion().getAnswerState ();
		if (answerState == Question.ANSWERED_WRONG)
			text += " (Wrong)";
		else if (answerState == Question.ANSWERED_CORRECT)
			text += " (OK)";
		questionLabel.setText (text);
		System.out.println (" label contents: " + text + " AS: " + answerState);
        correctsLabel.setText ("Correct: " + currentLesson.getCorrects());
        wrongsLabel.setText ("Wrong: " + currentLesson.getWrongs());

        lessonProgressBar.setValue(currentLesson.getCurrentQuestionIndex());
        if (myTeacher.getPercentWrong () > 50.0)
            lessonProgressBar.setForeground (Color.red);
        else if (myTeacher.getPercentWrong () > 25.0)
            lessonProgressBar.setForeground (Color.orange);
        else
            lessonProgressBar.setForeground (Color.green);
        averageLabel.setText ("Average: " + myTeacher.getCurrentAverage());
        statusLabel.setForeground(Color.blue);
        statusLabel.setText ("Listening for an answer...");
        if (myTeacher.getAnswerAttempted()) {
            if (answerState == Question.ANSWERED_CORRECT) {
                //statusLabel.setForeground(Color.green);
                statusLabel.setText ("Correct Answer!");
            }
            else if (answerState == Question.ANSWERED_WRONG) {
                statusLabel.setForeground(Color.red);
                statusLabel.setText("Incorrect. You can try again!!");
            }
        }
        super.repaint();
    }

    public void setTeacher (Teacher v) {myTeacher = v;}

    protected JMenuBar makeMenu () {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = menuBar.add (new JMenu("File"));
        JMenuItem item = fileMenu.add (new JMenuItem ("About"));
		item.addActionListener (new MenuAbout(this));
        item = fileMenu.add (new JMenuItem ("Exit"));
		item.addActionListener (new MenuExit());

		JMenu lessonMenu = menuBar.add (new JMenu ("Lessons"));
		JMenuItem itemLoad = lessonMenu.add (new JMenuItem ("Load Lessons"));
        itemLoad.setEnabled(false);

		JMenuItem itemNext = lessonMenu.add (new JMenuItem ("Next Lesson"));
        JMenuItem itemPrev = lessonMenu.add (new JMenuItem ("Previous Lesson"));
        itemNext.addActionListener(new MenuNextLesson(myTeacher, itemNext, itemPrev));

        itemPrev.addActionListener (new MenuPreviousLesson (myTeacher, itemNext, itemPrev));
        itemPrev.setEnabled(false);

        lessonMenu.insertSeparator(1);
        return menuBar;
    }

}





