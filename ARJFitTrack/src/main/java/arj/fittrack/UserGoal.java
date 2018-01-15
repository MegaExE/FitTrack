package arj.fittrack;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
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
