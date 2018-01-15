package arj.fittrack;

/**
 * Created by Owner on 1/11/2018.
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
