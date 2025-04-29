package FitnessCourse;
/*
 * this class represents a session object
 * containing information about sessions
 */
public class Course {
    //Should have a user controller object and know details about the user as well as user type
    //either explicitly or implicitly through overriden constructors

    private String name;
    private String description;
    /**
     * Creates Session object
     *
     * @param name of session
     */
    public Course(String name) {
        this.name = name;
    }
    /**
     * Creates Session object with description
     *
     * @param name of session
     * @param description of session
     */
    public Course(String name, String description) {
        this(name);
        this.description = description;
    }
    /**
     * gets name of Session
     *
     * @return name of course
     */
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

    //has a controller
}
