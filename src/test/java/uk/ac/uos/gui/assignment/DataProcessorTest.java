package uk.ac.uos.gui.assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Arrays;

/**
 * Implementation of the application data processor tests which are responsible for testing the
 * functionality of the DataProcessor class to read and write local settings to process and add
 * students.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class DataProcessorTest {
    /**
     * File path the test resources.
     */
    String path = "resources/";

    /**
     * Test adding a valid positive student.
     */
    @Test()
    @DisplayName("Test adding valid student")
    void testAddValidPositiveStudent(){
        // Arrange - Setup the application properties.
        ApplicationProperties applicationProperties = ApplicationProperties.instance();
        ApplicationProperties.courseNameFile = path + "course_names.csv";
        ApplicationProperties.studentsFile = path + "students.csv";
        ApplicationProperties.studentsCourseFile = path + "student_course_map.csv";
        ApplicationProperties.resultsFile = path + "results_file_output.csv";
        ApplicationProperties.contactsFile = path + "contacts_output.csv";
        // Act - Run the application.
        DataProcessor.addStudent("S199881", true);
        var testFile = new CsvHandler(path + "results_file_output.csv");
        testFile.loadFile();
        var testFileOutput = testFile.readFile();
        var contactsFile = new CsvHandler(path + "contacts_output.csv");
        contactsFile.loadFile();
        var contactsFileOutput = contactsFile.readFile();
        // Assert - Check file output is as expected.
        Assertions.assertEquals(testFileOutput.get(1).subList(0,3),  Arrays.asList("John Smith",
                "S199881", "Positive"));
        Assertions.assertEquals(contactsFileOutput.get(1).subList(0,3),  Arrays.asList("Ben " +
                "Richardson", "S194521", "S194521@uos.ac.uk"));
        Assertions.assertEquals(contactsFileOutput.get(2).subList(0,3),  Arrays.asList("Peter " +
                "Smith", "S199999", "S199999@uos.ac.uk"));
        // Teardown
        testFile.dispose();
        contactsFile.dispose();
        File resultsFileDelete = new File(path + "results_file_output.csv");
        resultsFileDelete.delete();
        File contactsFileDelete = new File(path + "contacts_output.csv");
        contactsFileDelete.delete();
    }

    /**
     * Test adding a valid negative student.
     */
    @Test()
    @DisplayName("Test adding valid negative student")
    void testAddValidNegativeStudent(){
        // Arrange - Setup the application properties.
        ApplicationProperties applicationProperties = ApplicationProperties.instance();
        ApplicationProperties.courseNameFile = path + "course_names.csv";
        ApplicationProperties.studentsFile = path + "students.csv";
        ApplicationProperties.studentsCourseFile = path + "student_course_map.csv";
        ApplicationProperties.resultsFile = path + "results_file_output.csv";
        ApplicationProperties.contactsFile = path + "contacts_output.csv";
        // Act - Run the application.
        DataProcessor.addStudent("S199881", false);
        var testFile = new CsvHandler(path + "results_file_output.csv");
        testFile.loadFile();
        var testFileOutput = testFile.readFile();
        // Assert - Check file output is as expected.
        Assertions.assertEquals(testFileOutput.get(1).subList(0,3),  Arrays.asList("John Smith",
                "S199881", "Negative"));
        // Teardown
        testFile.dispose();
        File resultsFileDelete = new File(path + "results_file_output.csv");
        resultsFileDelete.delete();
        File contactsFileDelete = new File(path + "contacts_output.csv");
        contactsFileDelete.delete();
    }
}
