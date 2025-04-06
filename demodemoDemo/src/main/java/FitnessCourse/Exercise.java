package FitnessCourse;

public class Exercise {
    private String name;
    private String description;

    public Exercise(String name) {
        this.name = name;
    }
    /**
     *
     *
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     *
     *
     * @param
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
    /**
     *
     * @param
     * @return
     */
    public void setDescription(String description) {
        this.description = description;
    }
}