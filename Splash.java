package arj.fittrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by John on 2017-10-16.
 */

public class Splash extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);
        //Add a upbutton to allow the user to go back to main screen
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
