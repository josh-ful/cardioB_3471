package FitnessCourse;

import java.util.ArrayList;
import java.util.List;

/*
 * this class represents a session object
 * containing information about sessions
 */
public class Course {
    private int id;
    private String name;
    private String type;
    private String description;
    private String time;
    private int trainerId;
    private List<Exercise> exercises = new ArrayList<>();

    public Course(){}

    public Course(int id, String name, String type, int trainerId , String description, String time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.trainerId = trainerId;
        this.description = description;
        this.time = time;
    }

    public Course(int id, String name, String type, int trainerId , String description, String time, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.trainerId = trainerId;
        this.description = description;
        this.time = time;
        this.exercises = exercises;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    /**
     * sets name of Session
     *
     * @param name of session
     */
    public void setName(String name) {//should be used by a controller
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
    public int getTrainerId() {
        return trainerId;
    }

    /**
     *
     * @return description of course
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
    public void setExercises(List<Exercise> exs) {
        this.exercises = exs;
    }
}
