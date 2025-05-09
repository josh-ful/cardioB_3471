package FitnessCourse;
/*
 * this class represents an exercise object
 * containing information about an exercise
 */
public class Exercise {
    private int id;
    private String name;
    private String description;
    private int duration;
    /**
     * Constructs Exercise object
     *
     * @param s
     * @param name of exercise
     */
    public Exercise(String s, String name) {
        this.name = name;
    }
    /**
     * Constructs Exercise object
     *
     * @param name of exercise
     * @param description of exercise
     */
    public Exercise(String name, String description, int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
    /**
     * Constructs Exercise object
     *
     * @param name String name of exercise
     * @param duration Integer duration of exercise
     */
    public Exercise(String name, Integer duration) {
        this.name = name;
        this.duration = duration;
    }
    /**
     * Constructs Exercise object
     *
     * @param id int id of exercise
     * @param name String name of exercise
     */
    public Exercise(int id, String name, Integer duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
    /**
     * Constructs Exercise object
     *
     * @param id of exercise
     * @param name of exercise
     * @param description of exercise
     */
    public Exercise(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    /**
     * Constructs Exercise object
     *
     * @param name of exercise
     * @param description of exercise
     * @param duration Integer duration of exercise
     */
    public Exercise(int id, String name, String description, Integer duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
    /**
     * provides string of an Exercise object
     *
     * @return string of name of exercise
     */
    @Override
    public String toString() {
        return name;  //could add description
    }

    /**
     * get id of exercise
     *
     * @return id int of exercise
     */
    public int getId() { return id; }

    /**
     * set id of exercise
     *
     * @return nothing
     */
    public void setId(int id) { this.id = id; }


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
     * @return description String description of exercise
     */
    public String getDescription() {
        return description;
    }
    /**
     * sets the description of the exercise to the parameter
     *
     * @param description String description of exercise
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * get duration of exercise
     *
     * @return int duration of exercise
     */
    public int getDuration() {
        return duration;
    }
    /**
     * sets duration of exercise
     *
     * @param duration int duration of exercise
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}