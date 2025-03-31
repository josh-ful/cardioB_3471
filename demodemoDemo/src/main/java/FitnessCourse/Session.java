package FitnessCourse;

public class Session {
    //Should have a user controller object and know details about the user as well as user type
    //either explicitly or implicitly through overriden constructors

    private String name;
    private static int sessionID = 0;

    public Session(String name) {
        this.name = name;
        sessionID++;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {//should be used by a controller
        this.name = name;
    }

    //has a controller
}
