package arj.fittrack;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class UserHeight {

    String heightID;
    String height;

    public UserHeight(){

    }

    public UserHeight(String heightID, String height){
        this.heightID = heightID;
        this.height = height;
    }

    public String getHeightID(){
        return heightID;
    }
    public String getHeight(){
        return height;
    }
}


