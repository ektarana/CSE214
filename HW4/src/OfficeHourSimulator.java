/**
 * Ekta Rana
 * 111030624
 * HW#4
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

import java.util.*;
import java.io.*;

/**
 * This class represents the manager of the simulation.
 * The main function’s responsibility is to get the parameters for the simulation and pass them to the simulate method by reading from a file.
 */
public class OfficeHourSimulator {
    public static String curLine;
    public static int numCourses, minTime, maxTime, numCups, simTime, numTAs;

    /**
     * Start for application, asks users for input file, which contains the following variables:
     * numCourses (int), courseNumbers (int []), arrivalProbability (double []), minTime (int), maxTime (int), numCups (int), officeHrTime (int), numTAs (int).
     * This method should also create the array of Courses with the given courseNumbers.
     *
     * @param args
     */
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        Scanner fileReader = null;
        String fileName = null;
        StudentQueue studentQueue = new StudentQueue();
        boolean run = true;

        System.out.println("Welcome to the Office Hours Simulation.");

        while (run) {
            System.out.println("Please enter a file name: ");
            fileName = input.nextLine();
            File myText = new File(fileName);
            try {
                fileReader = new Scanner(myText);
                if (!validSpecs(myText)) {
                    System.out.println("This file has invalid specifications.");
                } run = false;

            } catch (FileNotFoundException e) {
                System.out.println("Error 404 File not found!");

            }
        }
        int[] courseNumbers = new int[numCourses];
        double[] arrProbility = new double[numCourses];
        Course[] courses = new Course[numCourses];
        BooleanSource[] bSources = new BooleanSource[numCourses];
        Helper[] helpers = new Helper[numTAs+1];
        helpers[0] = new Helper(true);

        System.out.println("File " + fileName + " loaded.");

        curLine = fileReader.nextLine();
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        //adds data to the Course object array with the names of the courses
        //adds data to the int array of course numbers
        String[] array = curLine.split(" ");
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(0,0);
            courseNumbers[i] = 0;
            courses[i].setCourseNumber(Integer.parseInt(array[i]));
            courseNumbers[i] = Integer.parseInt(array[i]);
        }

        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        //adds data to the Course object array with the arrival probability of the courses
        //adds data to the arrProbility array and the BooleanSources array
        array = curLine.split(" ");
        for (int i = 0; i < numCourses; i++) {
            courses[i].setArrivalProbability(Double.parseDouble(array[i]));
            arrProbility[i] = Double.parseDouble(array[i]);
            bSources[i] = new BooleanSource(Double.parseDouble(array[i]));
        }

        //take an array of all the courses and sort it and then add the difficulty to the course object
        Arrays.sort(courses);
        for (int i = 0; i < courses.length; i++) {
            courses[i].setCourseDifficulty(numCourses - (i + 1));
        }

        System.out.printf("%-10s%-13s", "Course", "Probability");
        System.out.println();
        System.out.println("-----------------------");
        for (int i = 0; i < numCourses; i++) {
            System.out.printf("%-10d%f", courses[i].getCourseNumber(), courses[i].getArrivalProbability());
            System.out.println();
        }
        System.out.println("Number of TAs: " + numTAs);
        System.out.println("Coffee cups: " + numCups);
        System.out.println("Base time interval " + minTime + "-" + maxTime + " minutes.");
        System.out.println("Time: " + simTime + " minutes.");
        System.out.println("Begin simulation: ");
        System.out.println("__________________________________________________");
        simulate(simTime, arrProbility, courses, minTime, maxTime, numCups, numTAs);
    }

    /**
     * This method does the actual simulation. This method actually implements the algorithm, using the BooleanSource and StudentQueue classes.
     * The officeHrTime is how long Students can ‘arrive’ at office hours.
     *
     * @param officeHrTime       int
     * @param arrivalProbability double array
     * @param courses            Course array
     * @param minTime            int used to calculate time required
     * @param maxTime            int used to calculate time required
     * @param numCups            int used to calculate time required
     * @param numTAs             int used to calculate total amount of helpers
     */
    public static void simulate(int officeHrTime, double[] arrivalProbability, Course[] courses, int minTime, int maxTime, int numCups, int numTAs) {
        //creates a BooleanSource array to manage the events that occur
        BooleanSource[] bSources = new BooleanSource[numCourses];
        int curTS = 1; // current time step
        //create an array to manage the TAs with the prof at the front
        Helper[] helpers = new Helper[numTAs + 1];
        helpers[0] = new Helper(true);
        StudentQueue studentQueue = new StudentQueue();

        while (curTS != simTime) {
            System.out.println("Time Step " + curTS + ":");
            for (int i = 0; i < numCourses; i++) {
                if (bSources[i].occurs()) {
                    if (studentQueue.isEmpty()) {
                        studentQueue.enqueue(0, new Student(curTS, courses[i], calcTimeReq()));
                        System.out.println("Student " + studentQueue.get(i).getStudentId() + " has arrived for " + studentQueue.get(i).getCourse().getCourseNumber() + ".");
                    } else if (studentQueue.get(i).getCourse().getCourseDifficulty() > studentQueue.get(i - 1).getCourse().getCourseDifficulty()) {
                        studentQueue.enqueue(i - 1, new Student(curTS, courses[i], calcTimeReq()));
                        System.out.println("Student " + studentQueue.get(i).getStudentId() + " has arrived for " + studentQueue.get(i).getCourse().getCourseNumber() + ".");
                    }
                } else {
                    System.out.println("No students have arrived for " + courses[i].getCourseNumber() + ".");
                }
            }
            System.out.println();
            for (int i = 0; i < numTAs + 1; i++) {
                if (i == 0) {
                    System.out.println("Professor is helping Student " + studentQueue.get(i).getStudentId() + ", " + studentQueue.get(i).getTimeRequired() + " minutes remaining.");
                    studentQueue.get(i).setTimeRequired(helpers[i].getTimeLeftTilFree());
                }
            }
            System.out.println();
            curTS++;
        }

    }

    public static boolean validSpecs(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);

        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        numCourses = Integer.parseInt(curLine);

        //accounts for the course Names
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        StringTokenizer st = new StringTokenizer(curLine);
        if (st.countTokens() != numCourses) {
            return false;
        }
        //accounts for the arrival probabilities
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        st = new StringTokenizer(curLine);
        if (st.countTokens() != numCourses) {
            return false;
        }

        //makes sure that min and max are valid, as in not negative and min is less than max
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        minTime = Integer.parseInt(curLine);
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        maxTime = Integer.parseInt(curLine);
        if (minTime < 1 || minTime > maxTime || maxTime < 1) {
            return false;
        }
        //makes sure that numCups isn't negative
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        numCups = Integer.parseInt(curLine);
        if (numCups < 0) {
            return false;
        }
        //makes sure simTime isn't negative or 0
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        simTime = Integer.parseInt(curLine);
        if (simTime < 1) {
            return false;
        }
        //makes sure numTAs isn't negative
        curLine = fileReader.nextLine();
        curLine = curLine.substring(curLine.indexOf(":") + 1);
        numTAs = Integer.parseInt(curLine);
        if (numTAs < 0) {
            return false;
        }
        return true;
    }
    public static int calcTimeReq(){
        return (int) ((Math.floor(Math.random())) * (maxTime - minTime + 1) + minTime);
    }
}
