package uk.ac.uos.gui.assignment.layouts;

import uk.ac.uos.gui.assignment.ApplicationProperties;
import uk.ac.uos.gui.assignment.DataProcessor;
import uk.ac.uos.gui.assignment.CsvHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The jpanel window to add the student, build a report and output to text files and on screen.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class AddStudentPanel extends JPanel {
    /**
     * Create the variables used throughout students panel.
     */
    GridBagConstraints gbConstraints = new GridBagConstraints();
    JLabel outputText = new JLabel();
    JTextField textInput = new JTextField();

    /**
     * Create the top level add students title.
     */
    private void createTitle(){
        // Create title object.
        JLabel title = new JLabel("Add Student");
        // Apply styling.
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setBackground(Color.decode("#F5F5F5"));
        title.setOpaque(true);
        // Position title.
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.ipady = 40;
        gbConstraints.weightx = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        add(title, gbConstraints);
    }

    /**
     * Create the text input function to add the student number.
     */
    private void AddTextInput(){
        // Add the student number sub title.
        JLabel contactsTitle = new JLabel("Student Number: ");
        contactsTitle.setFont(new Font("Serif", Font.BOLD, 16));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 2;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 1;
        add(contactsTitle, gbConstraints);

        // Set the styling and add the text input to view.
        textInput.setFont(new Font("Serif", Font.BOLD, 20));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
        add(textInput, gbConstraints);
    }

    /**
     * Create the text output box
     */
    private void AddOutputText(){
        // Set the styling of the text output box.
        outputText.setFont(new Font("Serif", Font.PLAIN, 16));
        // Create scrolled text area view adding the output text.
        JScrollPane areaScrollPane = new JScrollPane(outputText);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        // Position and add the text output box.
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.weightx = 1;
        gbConstraints.ipady = 0;
        gbConstraints.weighty = 1;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 4;
        add(areaScrollPane, gbConstraints);
    }

    /**
     * Add negative result submit button.
     */
    private void addNegativeButton(){
        // Create negative button.
        var button = new JButton("Negative");
        // Create a button listener passing the false parameter to the submit method.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmitSelected(e,false);
            }
        });
        // Set styling and add.
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.weightx = 1;
        gbConstraints.ipady = 0;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 3;
        add(button, gbConstraints);
    }

    /**
     *Add negative false submit button.
     */
    private void addPositiveButton(){
        // Create positive button.
        var button = new JButton("Positive");
        // Create a button listener passing the positive parameter to the submit method.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmitSelected(e,true);
            }
        });
        // Set styling and add.
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.weightx = 1;
        gbConstraints.ipady = 0;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 3;
        add(button, gbConstraints);
    }

    /**
     * Method to handle the submit button pressed updating the text output with the values read
     * from the text file.
     * @param e Event info (not used).
     * @param result positive or negative button selected.
     */
    public void onSubmitSelected(ActionEvent e, boolean result)
    {
        // Add the student with the supplied user input and result.
        DataProcessor.addStudent(textInput.getText(), result);
        // Get the result text output.
        var resultsMatches = CsvHelper.findStudent(textInput.getText(),
                ApplicationProperties.resultsFile, false);
        // Get the contacts text output.
        var contacts = CsvHelper.findStudent(textInput.getText(),
                ApplicationProperties.contactsFile, true);
        // Create a formatted output string.
        String outputString =
                "<html><h1>Results File</h1>" + resultsMatches +  "<h1>Contacts File</h1>" +
                        contacts + "</html>";
        // Update the output text window.
        outputText.setText(outputString);
    }

    /**
     * Add students panne
     */
    public AddStudentPanel(){
        // Create and apply layout.
        var layout = new GridBagLayout();
        setLayout(layout);
        // Add ui elements.
        createTitle();
        AddTextInput();
        addNegativeButton();
        addPositiveButton();
        AddOutputText();
    }
}
