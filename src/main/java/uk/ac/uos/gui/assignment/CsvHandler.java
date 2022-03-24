package uk.ac.uos.gui.assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CSV handler which is act as a wrapper around file editing functionality including csv
 * specifics such as writing headings etc.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class CsvHandler {
    /**
     * Private variables to store commonly used objects for writing to the file.
     */
    private File file;
    private String filePath;
    private FileWriter csvWriter;
    private BufferedReader csvReader;

    /**
     * Constructor method for creating an instance of the csv handler.
     * @param filePath The path of the file to edit.
     */
    public CsvHandler(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Reads the contents of the csv file after it has been loaded.
     * @return The contents of the csv file including the headings at index 0.
     */
    public List<List<String>> readFile() {
        List<List<String>> output = new ArrayList<>();
        String line;
        /* Loops through the contents of the csv file line by line, splitting the line by
        comma and adding each lines split output to the output array. */
        try {
            while ((line = csvReader.readLine()) != null) {
                String[] values = line.split(",");
                output.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filePath);
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Sets up the object for reading and writing to the file.
     */
    public void loadFile() {
        // Creates the file writer and reader objects for file manipulation and reading.
        try {
            csvWriter = new FileWriter(filePath, true);
            csvReader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            System.out.println("Error loading " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * Method overloaded version of the load file method with added functionality to handle the
     * creation of csv files including headings.
     * @param csvHeadings The headings to generate at the first line of the csv.
     */
    public void loadFile(List<String> csvHeadings) {
        /* If the file doesn't exist create the file, load the files buffers and write the
        headings. */
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                loadFile();
                write(Arrays.asList(csvHeadings));
            } else {
                // If the file has already been created just load the buffers.
                loadFile();
            }
        } catch (IOException e) {
            System.out.println("Error loading " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * Appends the loaded csv file and writes the provided data to the csv file.
     * @param data The data to write to the csv file.
     */
    public void write(List<List<String>> data) {
        /* Iterates over the rows in the list adds the comma and new line character between each
        index. */
        for (List<String> rowData : data) {
            try {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            } catch (IOException e) {
                System.out.println("Error writing to " + filePath);
                e.printStackTrace();
            }
        }
    }

    /**
     * Disposes, flushes and releases the buffers ready for another application to use the files.
     */
    public void dispose() {
        try {
            if(csvWriter != null) {
                csvWriter.flush();
                csvWriter.close();
            }
            if(csvReader != null)
                csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
