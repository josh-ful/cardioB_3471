package FitnessCourse;
/*
 * this class represents a session object
 * containing information about sessions
 */
public class Session {
    //Should have a user controller object and know details about the user as well as user type
    //either explicitly or implicitly through overriden constructors

    private String name;
    private static int sessionID = 0;

    /**
     * Creates Session object
     *
     * @param name of session
     */
    public Session(String name) {
        this.name = name;
        sessionID++;
    }
    /**
     * gets name of Session
     *
     * @return name of session
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

    //has a controller
}
