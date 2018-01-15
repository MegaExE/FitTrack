package arj.fittrack;

<<<<<<< HEAD
=======
import android.Manifest;
>>>>>>> master
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
<<<<<<< HEAD
import android.net.Uri;
import android.os.Bundle;
=======
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
>>>>>>> master
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import static arj.fittrack.R.id.count;
<<<<<<< HEAD
=======
import static arj.fittrack.R.id.disableHome;
import static arj.fittrack.R.id.travelled;
>>>>>>> master
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
<<<<<<< HEAD
/*
        SharedPreferences pref = getSharedPreferences("StoredData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String displaySteps = steps.getText().toString();
        editor.putString("steps", displaySteps);
        editor.commit();
*/
=======

        //SharedPreference was used to set the text after saving
>>>>>>> master
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

<<<<<<< HEAD
        //int nsteps = Integer.parseInt(String.valueOf(displaySteps));

=======
        //SharedPreference was used to save the strings of steps, distance, and calories
>>>>>>> master
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

<<<<<<< HEAD
    //handle Android Back Key on Main Screen, and confirm user wants to exit the app.
    @Override
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        alertDialogBuilder.setTitle("Exit");
        // set dialog message
        alertDialogBuilder
                .setMessage("Are you sure you want to exit FitTrack?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public  void onClick(DialogInterface dialog,int id){
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // exit the app
                        finish();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

=======
>>>>>>> master
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
<<<<<<< HEAD
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
=======
>>>>>>> master

        //Menu (ToolBar)
        switch (item.getItemId()) {
            case R.id.Help:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Help");
                // set dialog message
                alertDialogBuilder
                        .setMessage("FAQ: \n" +
<<<<<<< HEAD
                                "How to set goals? \n" +
                                "The user inputs the goals in the notepad. \n" +
                                "\n" +
                                "Where is the goal section? \n" +
                                "The goal section is located in the main screen in the bottom right.  Another way is for the user to click on the set goals button. \n" +
                                "\n" +
                                "How does the distance travel work? \n" +
                                "The distance travelled works by it using a formula based on steps. Where the length of a step is 0.74 cm is multiplied with number of steps.  \n" +
                                "\n" +
                                "Is there a limit on the number of challenges you can set and goals that I can write? \n" +
                                "There is no limit, you can set as many challenges and write as many goals as you want. \n" +
                                "\n" +
                                "How do you save multiple notes using the notepad function? \n" +
                                "The user would click on the save button and that would save it to a file. \n" +
                                "\n" +
                                "How do I track all of my challenges and goals? \n" +
                                "It saves the goals to a database and it retrieves the challenges and tasks from the database so that the users can see it. \n" +
                                "\n" +
                                "How do I access all the different features of this app? \n" +
                                "Clicking on the icons that correspond to the different features of this app. ")
=======
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
>>>>>>> master
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
<<<<<<< HEAD
                                "Our health and fitness app is designed to help users achieve and maintain a healthy lifestyle. \n" +
                                " This app will help organize our users through the application. \n" +
                                " Our app will try and use a simple user interface so that all age groups will find it easy to use. \n" +
                                " As a result, we are trying to promote healthier life choices. ")
=======
                                getString(R.string.S1) +"\n" +
                                getString(R.string.S2) +"\n" +
                                getString(R.string.S3) +"\n" +
                                getString(R.string.S4)+"\n\n" +
                                getString(R.string.S5))
>>>>>>> master
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
<<<<<<< HEAD
            case R.id.Setting:
                break;
=======
            /*
            case R.id.Setting:
                break;
            */
>>>>>>> master
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
<<<<<<< HEAD
/*
            //Context.MODE_PRIVATE | Context.MODE_APPEND
            SharedPreferences pref = getSharedPreferences("Test2", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            displaySteps = steps.getText().toString();
            String displayDistance = distance.getText().toString();
            String displayCalories = calories.getText().toString();
            editor.putString("steps", displaySteps);
            editor.putString("distance", displayDistance);
            editor.putString("calories", displayCalories);
            editor.commit();
    */
=======

>>>>>>> master
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

