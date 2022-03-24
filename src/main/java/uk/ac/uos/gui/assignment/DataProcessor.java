package uk.ac.uos.gui.assignment;

import uk.ac.uos.datamanager.assignment.DataManager;
import uk.ac.uos.gui.assignment.layouts.AddStudentPanel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main processor for handling adding students test results and update the contact tracing
 * file.
 *
 * @author Nathan Lock
 * @author S221137@uos.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class DataProcessor {
    /**
     * Static variables to store data used throughout CmdLineContactTracer.
     */
    private static final List<String> resultsCsvHeadings = Arrays.asList("student name", "student" +
                    " number", "test result", "entry date", "entry time");
    private static final  List<String> contactsCsvHeadings = Arrays.asList("contact name",
            "contact number", "contact " +  "email address", "entry date", "entry time");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu/MM" +
            "/dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static DataManager dataManager = new DataManager();

    /**
     * Writes the student details provided through command line args to the results csv file.
     * @param resultsFile The csv handler file object for the results.
     * @param studentNumber The student number of the result to add.
     * @return Boolean of the error state of adding the result to the csv file
     */
    private static Boolean addResult(CsvHandler resultsFile, String studentNumber, Boolean result) {
        try {
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            List<String> data = Arrays.asList(
                    dataManager.getStudentName(studentNumber),
                    studentNumber,
                    result ? "Positive" : "Negative",
                    dateFormatter.format(localDate),
                    timeFormatter.format(localTime)
            );
            System.out.println("Added student to results file: " + Arrays.toString(data.toArray()));
            resultsFile.write(Arrays.asList(data));
            return true;
        } catch (NullPointerException e) {
            System.out.println(e);
            System.out.println("Could not add result to file as student number does not match " +
                    "input data.");
            return false;
        }
    }

    /**
     * When supplied with csv and a list of contacted students this method writes the result data
     * to the csv in the correct format.
     * @param contactFile The csv handler file object to write the contact results to.
     * @param contactedStudents List of students ids which have been in contact.
     */
    private static void addContactResult(CsvHandler contactFile, List<String> contactedStudents) {
        /* Iterate through the students numbers which are in contact with the supplied id,
        retrieve the details and write to the contact file csv. */
        for (String studentId : contactedStudents)
        {
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            List<String> data = Arrays.asList(
                    dataManager.getStudentName(studentId),
                    studentId,
                    dataManager.getStudentEmail(studentId),
                    dateFormatter.format(localDate),
                    timeFormatter.format(localTime)
            );
            contactFile.write(Arrays.asList(data));
            System.out.println("Contacted student found: " + Arrays.toString(data.toArray()));
        }
    }

    /**
     * Loads the csv files and outputs results to csv files.
     * @param studentId The student number to add the results file.
     * @param result Boolean positive or negative for the result of the test.
     */
    public static void addStudent(String studentId, Boolean result) {
        // Initialise csv files.
        CsvHandler resultsFile = new CsvHandler(ApplicationProperties.resultsFile);
        resultsFile.loadFile(resultsCsvHeadings);
        CsvHandler contactsFile = new CsvHandler(ApplicationProperties.contactsFile);
        contactsFile.loadFile(contactsCsvHeadings);
        CsvHandler studentsFile = new CsvHandler(ApplicationProperties.studentsFile);
        studentsFile.loadFile();
        CsvHandler studentsCourseFile = new CsvHandler(ApplicationProperties.studentsCourseFile);
        studentsCourseFile.loadFile();
        CsvHandler courseNameFile = new CsvHandler(ApplicationProperties.courseNameFile);
        courseNameFile.loadFile();

        // Read the input csv files.
        List<List<String>> studentsData = studentsFile.readFile();
        List<List<String>> studentsCourseData = studentsCourseFile.readFile();
        List<List<String>> courseNameData = courseNameFile.readFile();

        // Ensure the input csv files contain more than one entry.
        if (studentsData.size() < 2 ||  studentsCourseData.size() < 2 || courseNameData.size() < 2)
        {
            System.out.println("Supplied input files does not contain the correct length of data");
            return;
        }

        // Add students csv format 'student,name,email'.
        for (List<String> student : studentsData.subList(1, studentsData.size())) {
            dataManager.addStudent(student.get(0), student.get(1), student.get(2));
        }
        /* Read and add the student course map to dataManager library csv format 'student_id,
        course_id'. */
        Map<String, String> courseMap = new HashMap<>();
        for (List<String> course : studentsCourseData.subList(1, studentsCourseData.size())) {
            courseMap.put(course.get(0), course.get(1));
        }
        dataManager.loadStudentCourseList(courseMap);
        // Load the course key and to data manager csv format 'course_id,course_name'
        for (List<String> course : courseNameData.subList(1, courseNameData.size())) {
            dataManager.addCourse(course.get(0), course.get(1));
        }

        // Add student details to the results file.
        if (!addResult(resultsFile, studentId, result) || !result)
        {
            contactsFile.dispose();
            resultsFile.dispose();
            return;
        }

        // Add details of contacted students to the output csv.
        List<String> contactedStudents = dataManager.findContacts(studentId);
        if (contactedStudents.size() > 0)
            addContactResult(contactsFile, contactedStudents);
        else
            System.out.println("Could not find any contacted students.");

        // Flush buffers and close the files.
        contactsFile.dispose();
        resultsFile.dispose();
        studentsFile.dispose();
        studentsCourseFile.dispose();
        courseNameFile.dispose();
    }
}
