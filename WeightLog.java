package arj.fittrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by John on 2017-10-03.
 */

public class WeightLog extends AppCompatActivity {
    EditText currentWeight, finalWeight;
    Button saveBtn;
    SharedPreferences sharedPreferences;
    //String current = currentWeight.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);
        //Add a upbutton to allow the user to go back to main screen
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentWeight = (EditText) findViewById(R.id.currentWeight);
        finalWeight = (EditText) findViewById(R.id.finalWeight);
        saveBtn = (Button) findViewById(R.id.save);

        //sharedPreferences = getSharedPreferences("mypref",MODE_PRIVATE);
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //sharedPreferences.getString("current", "");




        //sharedPreferences.getFloat("currentFloat",0f);
    }
    public void save(View view){
        //String current = currentWeight.getText().toString();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentWeight = (EditText) findViewById(R.id.currentWeight);
        //float currentFloat = Float.parseFloat(current);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putFloat(current, currentFloat);
        editor.putString("current",currentWeight.getText().toString()).commit();
        //editor.putString("final", finalWeight.getText().toString());
        editor.apply();
        //editor.commit();
        sharedPreferences.getString("current", "");
        Toast.makeText(WeightLog.this,"Saved",Toast.LENGTH_SHORT).show();
    }

    protected void onResume(){
        super.onResume();
       // sharedPreferences.getString("current", "");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.getString("current", "");
    }
}