package FitnessCourse;

public class Course {
    String name, description, type;
    int id, trainerId;

    public Course() {
    }

    public Course(String name, String desc, String type, int id, int trainerId) {
        this.name = name;
        this.description = desc;
        this.type = type;
        this.id = id;
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
