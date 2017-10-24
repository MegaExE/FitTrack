package arj.fittrack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by John on 2017-10-03.
 */

public class WeightLog extends AppCompatActivity {
    EditText weight;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);
        //Add a upbutton to allow the user to go back to main screen
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        weight = (EditText) findViewById(R.id.currentWeight);
        //sharedPreferences = getSharedPreferences();
    }
}