package arj.fittrack;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by John on 2018-01-14.
 */
public class MainTest {

    private Main main;

    public void setUp() throws Exception{
        main = new Main();
    }
    @Test(expected=NullPointerException.class)
    public void testCalories() throws Exception{

        assertEquals(5f, main.caloriesBurnt(100f), 0);
    }
    @Test(expected=NullPointerException.class)
    public void testDistance() throws Exception{
        assertEquals(370f, main.distanceTraval(500f), 0);
    }
}