package arj.fittrack;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class challengeslist {
    String challengesid;
    String task;


    public challengeslist(){

    }

    public challengeslist(String challengesid, String task) {
        this.challengesid = challengesid;
        this.task = task;
    }

    public String getchallengesid() {
        return challengesid;
    }

    public String getchallenge() {
        return task;
    }

}
