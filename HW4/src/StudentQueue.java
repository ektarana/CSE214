import java.util.ArrayList;

/**
 * Ekta Rana
 * 111030624
 * HW#4
 * Recitation section: 08
 * Recitation TA: Michael Rizzo
 * Grading TA: Tim Zhang
 */

/**
 * The line of students outside the professor’s office in our simulator will be modelled as a Priority Queue of Students.
 * The order in which Students will be serviced will depend on their priority, which will be the course number they require the Helper’s help with.
 * Students are sorted in decreasing order based on their course number and the order they arrived at the office hours.
 */
public class StudentQueue extends ArrayList<Student> {
    /**
     * Default constructor that creates an empty StudentQueue.
     * This priority queue should be maintained in decreasing order based on the Student’s respective course numbers.
     */
    public StudentQueue() {

    }

    /**
     * Enqueues the passed in Student into the specified StudentQueue.
     * Preconditions: The StudentQueue object should be instantiated.
     * Postconditions: Given that the Student specified was not null, the Student should be added to the proper position of the StudentQueue.
     *
     * @param s Student object
     */
    public void enqueue(int i, Student s) {
        if (s != null) {
            super.add(i, s);
        }
    }

    /**
     * Dequeues the first Student in the StudentQueue.
     * Returns the Student dequeued.
     * Preconditions: The first Student in the StudentQueue should have the highest course number as well as have arrived first with respect to the rest of the Student’s in the same course.
     * Postconditions: The first Student is removed from the queue.
     *
     * @return Student object
     */
    public Student dequeue() {
        Student removed = get(0);
        super.remove(0);
        return removed;
    }

    /**
     * Returns the current number of Students waiting in the StudentQueue.
     *
     * @return int
     */
    public int size() {
        return super.size();
    }

    /**
     * Checks if the specified StudentQueue is empty.
     * Returns true if the StudentQueue is empty, else false.
     * Preconditions: The StudentQueue object should be instantiated.
     * Postconditions: returns trtrue if the StudentQueue is empty, else false.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return super.isEmpty();
    }
}