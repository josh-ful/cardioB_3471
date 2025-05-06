package FitnessCourse;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private String type;
    private String description;
    private String time;
    private int trainerId;
    private List<Exercise> exercises = new ArrayList<>();

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


    public List<Exercise> getExercises() {
        return exercises;
    }
    public void setExercises(List<Exercise> exs) {
        this.exercises = exs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public int getTrainerId() {
        return trainerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
