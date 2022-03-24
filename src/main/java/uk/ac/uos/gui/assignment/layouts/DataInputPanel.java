package uk.ac.uos.gui.assignment.layouts;

import uk.ac.uos.gui.assignment.ApplicationProperties;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The data input panel which allows users to enter the csv files for input and output and save
 * to the persistent application settings.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class DataInputPanel extends JPanel {
    /**
     * Shared file path text jlabel objects set using lambda functions.
     */
    /* If the properties value is empty then use the instructions text otherwise use the value in
    the properties file. */
    JLabel resultFilePathText = new JLabel(ApplicationProperties.resultsFile.length() == 0 ?
            "Click browse to select a file" : ApplicationProperties.resultsFile);
    JLabel contactsFilePathText = new JLabel(ApplicationProperties.contactsFile.length() == 0 ?
            "Click browse to select a file" : ApplicationProperties.contactsFile);
    JLabel courseFilePathText = new JLabel(ApplicationProperties.courseNameFile.length() == 0 ?
            "Click browse to select a file" : ApplicationProperties.courseNameFile);
    JLabel studentFilePathText = new JLabel(ApplicationProperties.studentsFile.length() == 0 ?
            "Click browse to select a file" : ApplicationProperties.studentsFile);
    JLabel studentsCourseFilePathText =
            new JLabel(ApplicationProperties.studentsCourseFile.length() == 0 ?  "Click browse to " +
                    "select a file" : ApplicationProperties.studentsCourseFile);
    GridBagConstraints gbConstraints = new GridBagConstraints();

    /**
     * Create the title.
     */
    private void createTitle(){
        JLabel title = new JLabel("Data Input");
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
     * Method to create the contacts file input elements and add them to the jpanel.
     */
    private void createContactsFileInput(){
        // Create contacts file sub heading text.
        JLabel contactsTitle = new JLabel("Contacts: File ");
        contactsTitle.setFont(new Font("Serif", Font.BOLD, 16));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 2;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 4;
        add(contactsTitle, gbConstraints);

        // Create contacts file sub heading text.
        contactsFilePathText.setFont(new Font("Serif", Font.PLAIN, 14));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 0.8;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 5;
        add(contactsFilePathText, gbConstraints);

        // Create browse file explorer button.
        var contactBrowseButton = new JButton();
        contactBrowseButton.setText("Browse");
        contactBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a browse explorer and show.
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Csv Files",
                        "csv"));
                int returnValue = fileChooser.showOpenDialog(null);
                // If a file is selected, get the file path and update the properties.
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    ApplicationProperties.contactsFile = selectedFile.getPath();
                    contactsFilePathText.setText(ApplicationProperties.contactsFile);
                }
            }
        });
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 0.3;
        gbConstraints.gridwidth = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 5;
        add(contactBrowseButton, gbConstraints);
    }

    /**
     * Method to create the results file input elements and add them to the jpanel.
     */
    private void createResultsFileInput(){
        // Create results file sub heading text.
        JLabel resultsTitle = new JLabel("Results File: ");
        resultsTitle.setFont(new Font("Serif", Font.BOLD, 16));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 2;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 1;
        add(resultsTitle, gbConstraints);

        // Create results files path text.
        resultFilePathText.setFont(new Font("Serif", Font.PLAIN, 14));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 0.8;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
        add(resultFilePathText, gbConstraints);

        // Create browse file explorer button.
        var resultsButton = new JButton();
        resultsButton.setText("Browse");
        resultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a browse explorer and show.
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                // Only allow csv files to be selected.
                fileChooser.setFileFilter(new FileNameExtensionFilter("Csv Files",
                        "csv"));
                // If a file is selected, get the file path and update the properties.
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    ApplicationProperties.resultsFile = selectedFile.getPath();
                    resultFilePathText.setText(ApplicationProperties.resultsFile);
                }
            }
        });
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 0.3;
        gbConstraints.gridwidth = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 2;
        add(resultsButton, gbConstraints);
    }

    /**
     * Method to create the course file input elements and add them to the jpanel.
     */
    private void createCourseFileInput(){
        // Create course file sub heading text.
        JLabel courseTitle = new JLabel("Course Names File: ");
        courseTitle.setFont(new Font("Serif", Font.BOLD, 16));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 2;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 7;
        add(courseTitle, gbConstraints);

        // Create course file path text.
        courseFilePathText.setFont(new Font("Serif", Font.PLAIN, 14));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 0.8;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 8;
        add(courseFilePathText, gbConstraints);

        // Create browse file explorer button.
        var courseBrowseButton = new JButton();
        courseBrowseButton.setText("Browse");
        courseBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                // Only allow csv files to be selected.
                fileChooser.setFileFilter(new FileNameExtensionFilter("Csv Files",
                        "csv"));
                int returnValue = fileChooser.showOpenDialog(null);
                // If a file is selected, get the file path and update the properties.
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    ApplicationProperties.courseNameFile = selectedFile.getPath();
                    courseFilePathText.setText(ApplicationProperties.courseNameFile);
                }
            }
        });
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 0.3;
        gbConstraints.gridwidth = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 8;
        add(courseBrowseButton, gbConstraints);
    }

    /**
     * Method to create the student file input elements and add them to the jpanel.
     */
    private void createStudentsFileInput(){
        // Create students file sub heading text.
        JLabel studentsTitle = new JLabel("Students File: ");
        studentsTitle.setFont(new Font("Serif", Font.BOLD, 16));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 2;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 10;
        add(studentsTitle, gbConstraints);

        // Create students file path text.
        studentFilePathText.setFont(new Font("Serif", Font.PLAIN, 14));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 0.8;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 11;
        add(studentFilePathText, gbConstraints);

        // Create browse file explorer button.
        var studentBrowseButton = new JButton();
        studentBrowseButton.setText("Browse");
        studentBrowseButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              // Create a browse explorer and show.
              JFileChooser fileChooser = new JFileChooser();
              int returnValue = fileChooser.showOpenDialog(null);
              // Only allow csv files to be selected.
              fileChooser.setFileFilter(new FileNameExtensionFilter("Csv Files",
                      "csv"));
              // If a file is selected, get the file path and update the properties.
              if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    ApplicationProperties.studentsFile = selectedFile.getPath();
                    studentFilePathText.setText(ApplicationProperties.studentsFile);
                }
            }
        });
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 2;
        gbConstraints.gridwidth = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 11;
        add(studentBrowseButton, gbConstraints);
    }

    /**
     * Method to create the student course file input elements and add them to the jpanel.
     */
    private void createStudentsCourseFileInput(){
        // Create students course file sub heading text.
        JLabel studentsCourseTitle = new JLabel("Students Course File: ");
        studentsCourseTitle.setFont(new Font("Serif", Font.BOLD, 16));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 2;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 13;
        add(studentsCourseTitle, gbConstraints);

        // Create students course file path text.
        studentsCourseFilePathText.setFont(new Font("Serif", Font.PLAIN, 14));
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        gbConstraints.anchor = GridBagConstraints.NORTHWEST;
        gbConstraints.ipady = 10;
        gbConstraints.weightx = 0.8;
        gbConstraints.weighty = 0;
        gbConstraints.gridwidth = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 14;
        add(studentsCourseFilePathText, gbConstraints);

        // Create browse file explorer button.
        var studentCourseBrowseButton = new JButton();
        studentCourseBrowseButton.setText("Browse");
        studentCourseBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a browse explorer and show.
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                // Only allow csv files to be selected.
                fileChooser.setFileFilter(new FileNameExtensionFilter("Csv Files",
                        "csv"));
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    ApplicationProperties.studentsCourseFile = selectedFile.getPath();
                    // Update the students file path text with the csv file.
                    studentsCourseFilePathText.setText(ApplicationProperties.studentsCourseFile);
                }
            }
        });
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 2;
        gbConstraints.gridwidth = 1;
        gbConstraints.weighty = 0;
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 14;
        add(studentCourseBrowseButton, gbConstraints);
    }

    /**
     * Constructor for the student search panel to initialise the layout.
     */
    void createSaveButton(){
        // Create the save button.
        var saveButton = new JButton();
        saveButton.setText("Save");
        // Add the action listener to save the properties added.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the csv file paths to persistent storage.
                ApplicationProperties.saveProperties();
            }
        });
        // Add positioning constraints to the save button.
        gbConstraints.ipady = 0;
        gbConstraints.weightx = 1;
        gbConstraints.weighty = 1;
        gbConstraints.gridwidth = 2;
        gbConstraints.gridx = 0;
        gbConstraints.insets = new Insets(15,3,3,3);
        gbConstraints.gridy = 15;
        add(saveButton, gbConstraints);
    }

    /**
     * Constructor for the data input panel to initialise the layout.
     */
    public DataInputPanel() {
        // Set layout type.
        var layout = new GridBagLayout();
        setLayout(layout);
        // Add ui elements.
        createTitle();
        createResultsFileInput();
        createContactsFileInput();
        createCourseFileInput();
        createStudentsFileInput();
        createStudentsCourseFileInput();
        createSaveButton();
    }
}