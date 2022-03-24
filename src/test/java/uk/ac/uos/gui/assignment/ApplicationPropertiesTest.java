package uk.ac.uos.gui.assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;

/**
 * Implementation of the application properties tests which are responsible for testing the
 * functionality of the Application Properties class to read and write local settings to
 * persistent storage.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class ApplicationPropertiesTest {
    /**
     * Private variable to store private application properties instance.
     */
    private ApplicationProperties applicationProperties;

    /**
     * Setup method to reset the private csv handler instance between every test.
     */
    @BeforeEach()
    void setup()
    {
        applicationProperties = ApplicationProperties.instance();
    }

    /**
     * Test reading and writing local application settings
     */
    @Test()
    @DisplayName("Read and write to local settings file")
    void testReadApplicationSettings()  {
        // Act - Set and save application settings.
        ApplicationProperties.studentsCourseFile = "studentsTest.csv";
        ApplicationProperties.contactsFile = "contactsFileTest.csv";
        ApplicationProperties.resultsFile = "resultsFileTest.csv";
        ApplicationProperties.studentsFile = "studentFileTest.csv";
        ApplicationProperties.courseNameFile = "courseNameFileTest.csv";
        ApplicationProperties.saveProperties();
        // Reload the application properties with the new information found in the save file.
        applicationProperties = new ApplicationProperties();
        // Assert - Check settings inputted are equal to settings read.
        Assertions.assertEquals("studentsTest.csv",
                ApplicationProperties.studentsCourseFile);
        Assertions.assertEquals("contactsFileTest.csv",
                ApplicationProperties.contactsFile);
        Assertions.assertEquals("resultsFileTest.csv",
                ApplicationProperties.resultsFile);
        Assertions.assertEquals("studentFileTest.csv",
                ApplicationProperties.studentsFile);
        Assertions.assertEquals("courseNameFileTest.csv",
                ApplicationProperties.courseNameFile);
        // Dispose - Delete the contact tracer properties file.
        var file = new File("contactTracer.properties");
        file.delete();
    }
}
