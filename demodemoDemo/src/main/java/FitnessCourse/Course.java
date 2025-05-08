package FitnessCourse;

import java.util.ArrayList;
import java.util.List;

/*
 * this class represents a Course object
 * containing information about courses
 */
public class Course {
    private int id;
    private String name;
    private String type;
    private String description;
    private String time;
    private int trainerId;
    private List<Exercise> exercises = new ArrayList<>();
    /**
     * constructs a course object
     *
     */
    public Course(){}
    /**
     * constructs a course object
     *
     * @param id int course id
     * @param name String name of course
     * @param type String type of course
     * @param trainerId int id of trainer of course
     * @param description String description of course
     * @param time String time of course
     */
    public Course(int id, String name, String type, int trainerId , String description, String time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.trainerId = trainerId;
        this.description = description;
        this.time = time;
    }
    /**
     * constructs a course object with a list of exercises
     *
     * @param id int course id
     * @param name String name of course
     * @param type String type of course
     * @param trainerId int id of trainer of course
     * @param description String description of course
     * @param time String time of course
     * @param exercises List<Exercise> list of exercises in course
     */
    public Course(int id, String name, String type, int trainerId , String description, String time, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.trainerId = trainerId;
        this.description = description;
        this.time = time;
        this.exercises = exercises;
    }

    /**
     * gets course id
     *
     * @return int id of course
     */
    public int getId() {
        return id;
    }
    /**
     * sets course id
     *
     * @param id int of course
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * gets course name
     *
     * @return String name of course
     */
    public String getName() {
        return name;
    }
    /**
     * sets name of course
     *
     * @param name String name of course
     */
    public void setName(String name) {//should be used by a controller
        this.name = name;
    }
    /**
     * gets course type
     *
     * @return String type of course
     */
    public String getType() {
        return type;
    }
    /**
     * sets course type
     *
     * @param type int type of course
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets description of course
     *
     * @return String description of course
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description of course
     *
     * @param description String desc of course
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * sets description of course
     *
     * @return String time of course
     */
    public String getTime() {
        return time;
    }
    /**
     * sets time of course
     *
     * @param time String time of course
     */
    public void setTime(String time) {
        this.time = time;
    }
    /**
     * gets list of exercises
     *
     * @return List<Exercise> list of exercises in course
     */
    public List<Exercise> getExercises() {
        return exercises;
    }
    /**
     * sets list of exercises
     *
     * @param exs List<Exercise> list of exercises in course
     */
    public void setExercises(List<Exercise> exs) {
        this.exercises = exs;
    }
}
