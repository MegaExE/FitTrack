package arj.fittrack;

/**
 * Created by Owner on 1/11/2018.
 */

public class getchallenges {
    String challengesID;
    String challenges;


    public getchallenges(){

    }

    public getchallenges(String challengesID, String challenges) {
        this.challengesID = challengesID;
        this.challenges = challenges;
    }

    public String getchallengesID() {
        return challengesID;
    }

    public String getchallenges() {
        return challenges;
    }

}
