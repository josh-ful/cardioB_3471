package FitnessCourse;
/*
 * this class represents a CourseExercise object
 * containing information about CourseExercises
 */
public class CourseExercise {
    private final int linkId;        // the PK of course_exercises
    private final Exercise exercise; // the actual exercise
    private final int order;         // its position in the course
    /**
     * constructs a CourseExercise object
     *
     * @param linkId int id that links exercise to course
     * @param exercise Exercise being linked to course
     * @param order int order index
     */
    public CourseExercise(int linkId, Exercise exercise, int order) {
        this.linkId   = linkId;
        this.exercise = exercise;
        this.order    = order;
    }
    /**
     * gets linkId of exercise
     *
     * @return int id that links exercise to course
     */
    public int getLinkId()   { return linkId; }
    /**
     * gets exercise of CourseExercise object
     *
     * @return Exercise exercise of CourseExercise object
     */
    public Exercise getExercise() { return exercise; }
    /**
     * gets order index of CourseExercise object
     *
     * @return int order index
     */
    public int getOrder()    { return order; }
    /**
     * overrides toString to call the exercise toString
     *
     * @return String output
     */
    @Override
    public String toString() {
        return exercise.toString();
    }
}
