package uk.ac.uos.gui.assignment.layouts;

import uk.ac.uos.gui.assignment.ApplicationProperties;
import uk.ac.uos.gui.assignment.CsvHelper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The student search panel view which contains the container objects to search for a student in
 * the results and contacts output csv files.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class StudentSearchPanel extends JPanel {
    /**
     * Private variables for variables used to build multiple ui objects.
     */
    private GridBagConstraints gbConstraints = new GridBagConstraints();
    private JLabel outputText = new JLabel("");
    private JTextField textInput = new JTextField();

    /**
     * Create the title.
     */
    private void createTitle(){
        JLabel title = new JLabel("Student Search");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setBackground(Color.decode("#F5F5F5"));
        title.setOpaque(true);
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
     * Add the text input including the button to submit.
     */
    private void addTextInput(){
        // Create student and position number text.
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

        // Crate and position text input.
        textInput.setFont(new Font("Serif", Font.BOLD, 20));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
        add(textInput, gbConstraints);

        // Create and position button and event listener.
        var button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmitSelected(e);
            }
        });
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.weightx = 0.2;
        gbConstraints.ipady = 0;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 3;
        add(button, gbConstraints);
    }

    /**
     * Add a scrolling text output.
     */
    private void addOutputText(){
        outputText.setFont(new Font("Serif", Font.PLAIN, 16));
        JScrollPane areaScrollPane = new JScrollPane(outputText);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.weightx = 1;
        gbConstraints.ipady = 0;
        gbConstraints.weighty = 1;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 4;
        add(areaScrollPane, gbConstraints);
    }

    /**
     * Update the output text with the information from the search.
     * @param e Event information to be taken from the event handler.
     */
    public void onSubmitSelected(ActionEvent e)
    {
        // Get the search results and contact csv formatted strings.
        var resultsMatches = CsvHelper.findStudent(textInput.getText(),
                ApplicationProperties.resultsFile, false);
        var contacts = CsvHelper.findStudent(textInput.getText(),
                ApplicationProperties.contactsFile, false);
        // Apply basic html styling.
        String outputString =
                "<html><h1>Results File</h1>" + resultsMatches +  "<h1>Contacts File</h1>" +
                        contacts + "</html>";
        outputText.setText(outputString);
    }

    /**
     * Constructor for the student search panel to initialise the layout.
     */
    public StudentSearchPanel(){
        // Set layout type.
        var layout = new GridBagLayout();
        setLayout(layout);
        // Add ui elements.
        createTitle();
        addTextInput();
        addOutputText();
    }
}
