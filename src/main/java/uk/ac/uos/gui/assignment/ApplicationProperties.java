package uk.ac.uos.gui.assignment;

import java.io.*;
import java.util.Properties;

/**
 * Singleton application property class containing persistent storage application settings.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class ApplicationProperties {
    /**
     * Static variables to store local application settings.
     */
    private static final String propertyFileName = "contactTracer.properties";
    static private ApplicationProperties _instance = null;
    static public String resultsFile = "";
    static public String contactsFile = "";
    static public String courseNameFile = "";
    static public String studentsFile = "";
    static public String studentsCourseFile = "";

    /**
     * Save a file copy of the application settings stored in static variables.
     */
    public static void saveProperties(){
        Properties props = new Properties();
        props.setProperty("RESULTS_FILE", resultsFile);
        props.setProperty("CONTACTS_FILE", contactsFile);
        props.setProperty("COURSE_NAME_FILE", courseNameFile);
        props.setProperty("STUDENTS_FILE", studentsFile);
        props.setProperty("STUDENTS_COURSE_FILE", studentsCourseFile);
        try {
            props.store(new FileWriter(propertyFileName), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Application constructor where if the property file exists load the data if not, create a
     * file.
     */
    protected ApplicationProperties(){
        // Check if the application property exists.
        File propFile = new File(propertyFileName);
        if(!propFile.exists()) {
            saveProperties();
            return;
        }
        try {
            // Open the property file.
            InputStream file = new FileInputStream(new File(propertyFileName));
            Properties props = new Properties();
            props.load(file);
            // Loads the properties into static instance.
            resultsFile = props.getProperty("RESULTS_FILE");
            contactsFile = props.getProperty("CONTACTS_FILE");
            courseNameFile = props.getProperty("COURSE_NAME_FILE");
            studentsFile = props.getProperty("STUDENTS_FILE");
            studentsCourseFile = props.getProperty("STUDENTS_COURSE_FILE");
        } catch (Exception e) {
            System.out.println("error" + e);
            saveProperties();
        }
    }

    /**
     * Implements the singleton pattern.
     * @return Return the single instance of the class.
     */
    static public ApplicationProperties instance(){
        if (_instance == null) {
            _instance = new ApplicationProperties();
        }
        return _instance;
    }
}
