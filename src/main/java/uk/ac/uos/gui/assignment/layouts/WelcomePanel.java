package uk.ac.uos.gui.assignment.layouts;

import javax.swing.*;
import java.awt.*;

/**
 * The Welcome Panel view to give the user instructions on how to use the application.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class WelcomePanel extends JPanel {
    /**
     * Constructor method to configure the Welcome Panel with the title and subtext.
     */
    public WelcomePanel() {
        // Sets the layout for the Welcome Panel.
        var layout = new GridBagLayout();
        setLayout(layout);
        // Setups up the constraints to apply layout characteristics.
        GridBagConstraints c = new GridBagConstraints();

        // Create and setup the title.
        JLabel title = new JLabel("Welcome to UOS Contact Tracing Application");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setBackground(Color.decode("#F5F5F5"));
        title.setOpaque(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 2;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        add(title, c);

        // Create the sub text and format.
        JLabel subText = new JLabel("""
                <html>
                    <h2>Data Input:</h2>
                    <p>
                    Update application settings csv file locations and click "save" to save to 
                    persistent storage.<br>
                    <b>Results File:</b> Output csv file location of the test results.<br>
                    <b>Contact File:</b> Contacted output students csv location.<br>
                    <b>Course Name File:</b> Input course file to match ids to course names.<br>
                    <b>Students File:</b> Id of the student to perform to update.<br>
                    <b>Students Course File:</b> Input student to course map.<br>
                    </p>
                    <h2>Add Student:</h2>
                    <p>Enter the student id and click "positive" or "negative" buttons to add the
                    students to the results file and update the contacts tracing files. The output 
                    window will show all the data from the results and contacts csv files.</p>
                    <h2>Student Search:</h2>
                    <p>Enter the student id and click "Submit" to search for the students details in
                    the contact file and results file, an output will display the results in the 
                    below.</p>
                </html>
                """);
        subText.setFont(new Font("Serif", Font.PLAIN, 16));
        subText.setVerticalAlignment(JLabel.TOP);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 2;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 1;
        add(subText, c);
    }
}
