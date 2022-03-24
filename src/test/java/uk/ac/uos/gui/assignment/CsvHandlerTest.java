package uk.ac.uos.gui.assignment;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the Csv Handler tests which are responsible for testing the functionality of
 * public methods of the Csv Handler class.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class CsvHandlerTest {
    /**
     * Private variable to store private csv handler instance.
     */
    private CsvHandler csvHandler;
    String path = "resources/";

    /**
     * Setup method to reset the private csv handler instance between every test.
     */
    @BeforeEach()
    void setup()
    {
        csvHandler = new CsvHandler("");
    }

    /**
     * Checks the basic functionality of the csv handler opening and reading the contents from an
     * example resource file.
     */
    @Test()
    @DisplayName("Opens and reads a csv file")
    void testReadValidCsv(){
        // Arrange - loads the test.csv resource file
        csvHandler = new CsvHandler(path + "test.csv");
        csvHandler.loadFile();
        // Act - reads and stores the file contents
        List<List<String>> fileContents = csvHandler.readFile();
        // Assert - checks the contents of the reader against predefined expected value arrays
        List<String> line = Arrays.asList("heading1", "heading2");
        Assertions.assertEquals(fileContents.get(0), line);
        line = Arrays.asList("entry1", "entry2");
        Assertions.assertEquals(fileContents.get(1), line);
    }

    /**
     * Checking creating a csv file with headings only returns valid headings.
     */
    @Test()
    @DisplayName("Open and read valid csv headings")
    void testValidHeadingsCsv() throws InterruptedException {
        // Arrange - loads the test.csv resource file
        var csvWriter = new CsvHandler(path + "new_test.csv");
        List<String> headings = Arrays.asList("heading1", "heading2");
        csvWriter.loadFile(headings);
        csvWriter.dispose();
        // Act - reads and stores the file contents
        var csvReader = new CsvHandler(path + "new_test.csv");
        csvReader.loadFile();
        List<List<String>> fileContents = csvReader.readFile();
        // Assert - checks the contents of the reader against predefined expected value arrays
        List<String> line = Arrays.asList("heading1", "heading2");
        Assertions.assertEquals(fileContents.get(0), line);
        // Dispose - Delete files
        csvReader.dispose();
        File file = new File(path + "new_test.csv");
        file.delete();
    }

    /**
     * Creates a csv, write data and then check the data written is retrieved on a read.
     */
    @Test()
    @DisplayName("Create, write and read a valid csv file")
    void testCreateReadValidCsv(){
        // Arrange - Create a valid csv file
        csvHandler = new CsvHandler(path + "new_test.csv");
        List<String> headings = Arrays.asList("heading1", "heading2");
        csvHandler.loadFile(headings);
        // Act - Write data
        var dataLine1 = Arrays.asList("data1", "data2");
        var dataLine2 = Arrays.asList("data2", "data3");
        csvHandler.write(Arrays.asList(dataLine1, dataLine2));
        csvHandler.dispose();
        // Assert - Read data
        csvHandler.loadFile();
        var readData = csvHandler.readFile();
        var dataSet = Arrays.asList(headings, dataLine1, dataLine2);
        Assertions.assertEquals(dataSet, readData);
        // Dispose - Delete files
        csvHandler.dispose();
        File file = new File(path + "new_test.csv");
        file.delete();
    }

    /**
     * Check the headings and data are written in the valid csv format.
     */
    @Test()
    @DisplayName("Check data is written in valid csv format")
    void testValidCsvFormat() throws IOException {
        // Arrange - Create a valid csv file
        csvHandler = new CsvHandler(path + "new_test.csv");
        List<String> headings = Arrays.asList("heading1", "heading2");
        csvHandler.loadFile(headings);
        // Act - Write and read data
        csvHandler.write(Arrays.asList(Arrays.asList("data1", "data2")));
        csvHandler.dispose();
        var reader = new BufferedReader(new FileReader(path + "new_test.csv"));
        var line1 = reader.readLine();
        var line2 = reader.readLine();
        // Asset - Check read data is in valid csv format
        Assertions.assertEquals("heading1,heading2", line1);
        Assertions.assertEquals("data1,data2", line2);
        // Dispose - Delete files and close reader
        reader.close();
        File file = new File(path + "new_test.csv");
        file.delete();
    }
}