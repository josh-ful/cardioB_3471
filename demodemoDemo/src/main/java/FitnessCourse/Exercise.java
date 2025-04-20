package FitnessCourse;
/*
 * this class represents an exercise object
 * containing information about an exercise
 */
public class Exercise {
    private String name;
    private String description;
    /**
     * Creates Exercise object
     *
     * @param name of exercise
     */
    public Exercise(String name) {
        this.name = name;
    }
    /**
     * Creates Exercise object
     *
     * @param name of exercise
     * @param description of exercise
     */
    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }
    /**
     * get name of exercise
     *
     * @return name string of exercise
     */
    public String getName() {
        return name;
    }
    /**
     * sets the name of the exercise to the parameter
     *
     * @param name string of exercise
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * get description of exercise
     *
     * @return description string of exercise
     */
    public String getDescription() {
        return description;
    }
    /**
     * sets the description of the exercise to the parameter
     *
     * @param description string of exercise
     */
    public void setDescription(String description) {
        this.description = description;
    }
}