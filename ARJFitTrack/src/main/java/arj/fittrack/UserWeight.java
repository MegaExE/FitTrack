package arj.fittrack;

import java.util.Date;
import java.util.Map;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class UserWeight {

    String weightID;
    String weight;
    String date;
    //public String height; not used yet

    public UserWeight(){

    }

    public UserWeight(String weightID, String weight, String date) {
        this.weightID = weightID;
        this.weight = weight;
        this.date = date;
    }

    public String getWeightID() {
        return weightID;
    }

    public String getWeight() {
        return weight;
    }

    public String getDate() {
        return date;
    }
}
