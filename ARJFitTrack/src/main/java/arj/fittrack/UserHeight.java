package arj.fittrack;

/**
 * Created by John on 2018-01-11.
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


