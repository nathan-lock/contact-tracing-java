package uk.ac.uos.gui.assignment;

import uk.ac.uos.gui.assignment.layouts.AddStudentPanel;
import uk.ac.uos.gui.assignment.layouts.DataInputPanel;
import uk.ac.uos.gui.assignment.layouts.StudentSearchPanel;
import uk.ac.uos.gui.assignment.layouts.WelcomePanel;
import javax.swing.*;
import java.awt.*;

/**
 * The main entry point of the gui contact tracing covid 19, application inherited from the JFrame
 * to create the swing ui application window.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
class Gui extends JFrame {
    /**
     * Static constants to store the initial height and width for the window.
     */
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    /**
     *  Constructor to setup the top level UI components for main application.
     *  */
    public Gui() {
        // Create application property singleton.
        ApplicationProperties applicationProperties = ApplicationProperties.instance();

        // Create panels.
        JPanel welcomePanel = new WelcomePanel();
        welcomePanel.setBackground(Color.white);
        JPanel dataInput = new DataInputPanel();
        dataInput.setBackground(Color.white);
        JPanel addStudent = new AddStudentPanel();
        addStudent.setBackground(Color.white);
        JPanel studentSearch = new StudentSearchPanel();
        studentSearch.setBackground(Color.white);

        // Create tabbed pane and add panels.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(50,50,200,200);
        tabbedPane.add("Welcome", welcomePanel);
        tabbedPane.add("Data Store", dataInput);
        tabbedPane.add("Add Student", addStudent);
        tabbedPane.add("Student Search", studentSearch);
        add(tabbedPane);

        // Setup base JFrame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Safely exit the application.
        setTitle("UOS Contact Tracing GUI");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }

    /**
     * Main entry point of the application creating the swing window in the event dispatching
     * thread.
     * @param args command line args are unused.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
            }
        });
    }
}
