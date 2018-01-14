package arj.fittrack;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import static arj.fittrack.R.id.count;
import static arj.fittrack.R.id.disableHome;
import static arj.fittrack.R.id.travelled;
/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Main extends AppCompatActivity implements SensorEventListener {

    //Declared variables the sensor uses
    SensorManager sensorManager;
    Sensor stepCounter;
    Sensor stepDetector;
    TextView steps;

    //
    final Context context = this;

    //TextView for distance travelled
    TextView distance;

    //TextView for calories burnt
    TextView calories;

    String displaySteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implementing Step Counter
        steps = (TextView) findViewById(count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        //Implementing Distance
        distance = (TextView) findViewById(R.id.travelled);

        //Implementing Calories Calculator
        calories = (TextView) findViewById(R.id.cals);

        //SharedPreference was used to set the text after saving
        SharedPreferences preferences = getSharedPreferences("Test3", Context.MODE_PRIVATE);
        String getSteps = preferences.getString("steps", steps.getText().toString());
        String getDistance = preferences.getString("distance", distance.getText().toString());
        String getCalories = preferences.getString("calories", calories.getText().toString());
        steps.setText(getSteps);
        distance.setText(getDistance);
        calories.setText(getCalories);
    }

    protected void onPause(){
        super.onPause();

        //SharedPreference was used to save the strings of steps, distance, and calories
        SharedPreferences pref = getSharedPreferences("Test3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        String displaySteps = steps.getText().toString();
        String displayDistance = distance.getText().toString();
        String displayCalories = calories.getText().toString();
        editor.putString("steps", displaySteps).commit();
        editor.putString("distance", displayDistance).commit();
        editor.putString("calories", displayCalories).commit();
        editor.apply();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Menu (ToolBar)
        switch (item.getItemId()) {
            case R.id.Help:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Help");
                // set dialog message
                alertDialogBuilder
                        .setMessage("FAQ: \n" +
                                getString(R.string.Q1) + "\n" +
                                getString(R.string.A1) + "\n" +
                                "\n" +
                                getString(R.string.Q2) +"\n" +
                                getString(R.string.A2) +"\n" +
                                "\n" +
                                getString(R.string.Q3) +"\n" +
                                getString(R.string.A3) + "\n" +
                                "\n" +
                                getString(R.string.Q4) +"\n" +
                                getString(R.string.A4) +"\n" +
                                "\n" +
                                getString(R.string.Q5) +"\n" +
                                getString(R.string.A5) +"\n" +
                                "\n" +
                                getString(R.string.Q6) +"\n" +
                                getString(R.string.A6))
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                                //display in short period of time
                                Toast.makeText(getApplicationContext(), "Ok is clicked",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                break;
            case R.id.About:
                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder2.setTitle("About");
                // set dialog message
                alertDialogBuilder2
                        .setMessage("About Message: \n" +
                                getString(R.string.S1) +"\n" +
                                getString(R.string.S2) +"\n" +
                                getString(R.string.S3) +"\n" +
                                getString(R.string.S4)+"\n\n" +
                                getString(R.string.S5))
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog2 = alertDialogBuilder2.create();
                // show it
                alertDialog2.show();
                break;
            /*
            case R.id.Setting:
                break;
            */
            //Links to a discussion board regarding to health issues
            case R.id.Discussion:
                Uri url = Uri.parse("https://patient.info/forums");
                Intent launch = new Intent(Intent.ACTION_VIEW, url);
                startActivity(launch);
                break;
            //Redirects to CBC News
            case R.id.News:
                Uri url2 = Uri.parse("http://www.cbc.ca/news/health");
                Intent launch2 = new Intent(Intent.ACTION_VIEW, url2);
                startActivity(launch2);
                break;

            //Redirects to the Notepad activity when notepad image is tapped
            case R.id.notepad:
                Intent intentNotepad = new Intent(Main.this, Notepad.class);
                startActivity(intentNotepad);
                break;

            //Redirects to Goal activity when goal image is tapped
            case R.id.goal:
                Intent intentGoal = new Intent(Main.this, MenuTab.class);
                startActivity(intentGoal);
                break;

            //Redirects to Weight activity when weight image is tapped
            case R.id.weight:
                Intent intentWeight = new Intent(Main.this, WeightLog.class);
                startActivity(intentWeight);
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    //Implementing SensorEventListener retrieves data from Sensor
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        float[] values = sensorEvent.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        //Used to display the step counter but also the distance and calories.
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            steps.setText(String.valueOf(Math.round(sensorEvent.values[0])));
            distance.setText(String.valueOf(String.format("%.2f",distanceTraval(sensorEvent.values[0]))));
            calories.setText(String.valueOf(String.format("%.1f",caloriesBurnt(sensorEvent.values[0]))));

            //distance.setText(String.valueOf(distanceTraval(sensorEvent.values[0])));
            //distance.setText(String.valueOf(distanceTraval(step)));
            //step++;
        }

        else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            //steps.setText(String.valueOf(Math.round(sensorEvent.values[0])));

        }

    }

    //Registers the Sensor and get sensor data as fast as possible
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, stepDetector, SensorManager.SENSOR_DELAY_FASTEST);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //Formula used to calculate the distance
    public float distanceTraval(float steps){
        //78 cm is the average step length for men while 70cm is for woman. I used 0.74 because it is a median.
        final float CENTIMETER = 0.74f;
        final float CONVERSION = 1000f;
        final int MTOKM = 1000;
        TextView METER = (TextView) findViewById(R.id.meter);
        float distanceinM = 0;

        if(distanceinM <= MTOKM)
        {
            METER.setText("meters");
            METER.setTextSize(20);
            distanceinM = (float) (CENTIMETER * steps);
        }
        if (distanceinM >= MTOKM)
        {
            METER.setText("kilometers");
            METER.setTextSize(15);
            distanceinM = (float) (CENTIMETER * steps) / CONVERSION;
        }
        return distanceinM;
    }

    //Formula used to calculate calories burnt
    public float caloriesBurnt(float steps){
        //A person burns 0.05 calories per step; rough estimate
        final float CALORIES_PER_STEP = 0.05f;
        float caloriesBurnt = (float) (CALORIES_PER_STEP * steps);
        return caloriesBurnt;
    }
}

