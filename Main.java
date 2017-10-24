package arj.fittrack;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static arj.fittrack.R.id.count;
/*ARJ
Johnson Raphael Adrain
*/

public class Main extends AppCompatActivity implements SensorEventListener{

    //Declared variables the sensor uses
    SensorManager sensorManager;
    Sensor stepCounter;
    Sensor stepDetector;
    TextView steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final ImageButton weight = (ImageButton) findViewById(R.id.weight);
        weight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Main.this, WeightLog.class);
                startActivity(intent);
            }
        });


        final ImageButton notepad = (ImageButton) findViewById(R.id.notepad);
        notepad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Main.this, Notepad.class);
                startActivity(intent);
            }
        });

        //Implementing Step Counter
        steps = (TextView) findViewById(count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
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
        switch (item.getItemId())
        {
            case R.id.Help:


                break;
            case R.id.About:


                break;
            case R.id.Setting:
                break;

            case R.id.Discussion:
                Uri url = Uri.parse("https://patient.info/forums");
                Intent launch = new Intent(Intent.ACTION_VIEW, url);
                startActivity(launch);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //Implementing SensorEventListener retrieves data from Sensor
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //if(start){
          //  steps.setText(String.valueOf(sensorEvent.values[0]));
        //}
        Sensor sensor = sensorEvent.sensor;
        float[] values = sensorEvent.values;
        int value = -1;

        if(values.length > 0){
            value = (int) values[0];
        }
        if(sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            steps.setText(String.valueOf(sensorEvent.values[0]));
        }
        else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
            steps.setText(String.valueOf(sensorEvent.values[0]));
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
}
