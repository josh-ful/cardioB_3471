package FitnessCourse;

public class CourseExercise {
    private final int linkId;        // the PK of course_exercises
    private final Exercise exercise; // the actual exercise
    private final int order;         // its position in the course

    public CourseExercise(int linkId, Exercise exercise, int order) {
        this.linkId   = linkId;
        this.exercise = exercise;
        this.order    = order;
    }

    public int getLinkId()   { return linkId; }
    public Exercise getExercise() { return exercise; }
    public int getOrder()    { return order; }


}
