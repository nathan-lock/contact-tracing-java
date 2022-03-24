package uk.ac.uos.gui.assignment;

/**
 * Static helper class containing methods to help deserialise files.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class CsvHelper {
    /**
     * Method when given a student id and a file name returns the lines broken up by html tags of
     * the matching students.
     * @param studentId Id of the student to find.
     * @param fileName File name string to search.
     * @param readAll Flag when set to true will return all the students.
     * @return
     */
    public static String findStudent(String studentId, String fileName, Boolean readAll)
    {
        var file = new CsvHandler(fileName);
        file.loadFile();
        var fileText = file.readFile();
        String output = "";
        /* Iterate through the elements starting at index 1 to exclude the headings checking the
        second element to see if it matches the name provided. */
        for (int i = 1; i < fileText.size(); i++)
        {
            if(fileText.get(i).get(1).equalsIgnoreCase(studentId) || readAll)
            {
                output += (fileText.get(i).toString() + "<br/>");
            }
        }
        file.dispose();
        return output;
    }
}
