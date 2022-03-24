package uk.ac.uos.gui.assignment;

import org.junit.jupiter.api.*;
import static uk.ac.uos.gui.assignment.CsvHelper.findStudent;

/**
 * Implementation of the Csv Helper tests which are responsible for testing the functionality of
 * finding a student from the csv file.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class CsvHelperTest {
    /**
     * File path the test resources.
     */
    String path = "resources/";

    /**
     * Checks the basic functionality of the csv handler helper find student method returning a
     * valid strings
     */
    @Test()
    @DisplayName("Finds a valid student file")
    void testFindStudent(){
        // Act - find a student.
        String studentString = findStudent("S199881", path + "results_file.csv",
                false);
        // Assert - Check the returned string is expected.
        Assertions.assertEquals("[John Smith, S199881, Positive, 2021/04/25, 16:51:35]" +
                        "<br/>[John Smith, S199881, Positive, 2021/04/25, 16:57:48]<br/>" +
                        "[John Smith, S199881, Positive, 2021/04/25, 18:43:16]<br/>",
                studentString);
    }

    /**
     * Checks the basic functionality of the csv handler helper find student method returning a
     * valid strings
     */
    @Test()
    @DisplayName("Finds all students in a file")
    void testFindStudentAllFlag(){
        // Act - Return all students in file.
        String studentString = findStudent("", path + "results_file.csv",
                true);
        // Assert - Check the returned string contains all students.
        Assertions.assertEquals("[John Smith, S199881, Positive, 2021/04/25, 16:51:35]" +
                        "<br/>[John Smith, S199881, Positive, 2021/04/25, 16:57:48]<br/>" +
                        "[John Smith, S199881, Positive, 2021/04/25, 18:43:16]<br/>" +
                        "[Tony Smith, S179881, Positive, 2021/04/25, 18:43:16]<br/>",
                studentString);
    }
}