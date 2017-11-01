package arj.fittrack;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static arj.fittrack.R.id.count;
import static arj.fittrack.R.id.travelled;
/*ARJ
Johnson Raphael Adrain
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageButton for Weight Activity (dumbbell)
        final ImageButton weight = (ImageButton) findViewById(R.id.weight);
        weight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Main.this, WeightLog.class);
                startActivity(intent);
            }
        });

        //ImageButton for Notepad Activity (notepad)
        final ImageButton notepad = (ImageButton) findViewById(R.id.notepad);
        notepad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Main.this, Notepad.class);
                startActivity(intent);
            }
        });

        //Set goals
        //Open the goals screen
        final Button setgoals = (Button) findViewById(R.id.setgoals);
        setgoals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Goal.class);


                startActivity(intent);
            }
        });

        //Implementing Step Counter
        steps = (TextView) findViewById(count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        //Implementing Distance
        distance = (TextView) findViewById(R.id.travelled);

        //Implementing Calories Calculator
        calories = (TextView) findViewById(R.id.cals);

/*
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("steps", steps.getText().toString());
        editor.putString("distance", distance.getText().toString());
        editor.putString("calories",calories.getText().toString());
        editor.commit();
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Menu (ToolBar)
        switch (item.getItemId()) {
            case R.id.Help:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Help");
                // set dialog message
                alertDialogBuilder
                        .setMessage("Help Message:")
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
                        .setMessage("About Message:")
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
            case R.id.Setting:
                break;
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
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            steps.setText(String.valueOf(Math.round(sensorEvent.values[0])));
            distance.setText(String.valueOf(String.format("%.2f",distanceTraval(sensorEvent.values[0]))));
            calories.setText(String.valueOf(caloriesBurnt(sensorEvent.values[0])));
            //distance.setText(String.valueOf(distanceTraval(sensorEvent.values[0])));
            //distance.setText(String.valueOf(distanceTraval(step)));
            //step++;
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            steps.setText(String.valueOf(Math.round(sensorEvent.values[0])));
            //distance.setText(String.valueOf(distanceTraval(values[0])));
            //distance.setText(String.valueOf(distanceTraval(step)));
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
    protected void onPause() {
        super.onPause();
        //distance.setText(String.valueOf(distanceTraval(step)));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
/*
    public float distanceTraval(float steps) {
        //78 cm is the average step length for men while 70cm is for woman. I used 0.74 because it is a median.
        final float CENTIMETER = 0.74f;
        final float CONVERSION = 1000f;
        float distance = (float) (CENTIMETER * steps) / CONVERSION;

        return distance;
        //return steps;
    }
}
*/

    public float distanceTraval(float steps){
        //78 cm is the average step length for men while 70cm is for woman. I used 0.74 because it is a median.
        final float CENTIMETER = 0.74f;
        final float CONVERSION = 1000f;
        TextView METER = (TextView) findViewById(R.id.meter);
        float distanceinM = 0;
        //float distanceinM = (float) (CENTIMETER * steps);
        //float distanceinKM = (float) (CENTIMETER * steps) / CONVERSION;
        if(distanceinM <= 1000)
        {
            METER.setText("meters");
            METER.setTextSize(15);
            distanceinM = (float) (CENTIMETER * steps);
            //return distanceinM;
        }
        if (distanceinM >= 1000)
        {
            METER.setText("kilometers");
            METER.setTextSize(15);
            distanceinM = (float) (CENTIMETER * steps) / CONVERSION;
            //return distanceinM;
            //return distanceinKM;
        }
        return distanceinM;
    }

    public float caloriesBurnt(float steps){
        //A person burns 0.05 calories per step; rough estimate
        final float CALORIES_PER_STEP = 0.05f;
        float caloriesBurnt = (float) (CALORIES_PER_STEP * steps);
        return caloriesBurnt;
    }
}

