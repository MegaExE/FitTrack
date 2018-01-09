package arj.fittrack;

/**
 * Created by John on 2017-12-29.
 */

public class UserGoal {

    String goalID;
    String goal;


    public UserGoal(){

    }

    public UserGoal(String goalID, String goal) {
        this.goalID = goalID;
        this.goal = goal;
    }

    public String getgoalID() {
        return goalID;
    }

    public String getgoal() {
        return goal;
    }

}
