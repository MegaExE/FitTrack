package arj.fittrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class WeightLog extends AppCompatActivity {
    EditText Weight , DeleteWeight, bWeight, bHeight;
    TextView Result;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);

        //Retrieve the input from user using EditText
        Weight = (EditText) findViewById(R.id.weight);
        DeleteWeight = (EditText) findViewById(R.id.editDelete);

        //Retrieve input to calculate BMI
        bWeight = (EditText) findViewById(R.id.bmiWeight);
        bHeight = (EditText) findViewById(R.id.bmiHeight);

        //Uses a TextView to display the result
        Result = (TextView) findViewById(R.id.result);

        helper = new myDbAdapter(this);
    }

    //Function that adds weight when the "Add Button" is pressed. However, the field needs to contain a number otherwise a toast message will display
    public void addWeight(View view) {
        String weight = Weight.getText().toString();
        if(weight.isEmpty())
        {
            Message.message(getApplicationContext(),"enter a weight");
        }
        else
        {
            long id = helper.insertData_Weight(weight);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Insert Unsuccessful");
                Weight.setText("");
            } else
            {
                Message.message(getApplicationContext(),"Insert Successful");
                Weight.setText("");
            }
        }
    }

    //Function is used to display weight from the input
    public void viewdata(View view) {
        String data = helper.getData_Weight();
        Message.message(this,data);
    }

    //Displays the result from calculate but first if element such as KG and CM is empty then it will say enter them
    public void calculate(View view){
        String KG = bWeight.getText().toString();
        String CM = bHeight.getText().toString();

        if(KG.isEmpty() || CM.isEmpty()){
            Message.message(getApplicationContext(),"enter a weight and height");
        }

        float weight = Float.parseFloat(KG);
        float height = Float.parseFloat(CM) / 100;
        float bmi = calcualte(weight,height);
        String bmiresult = bmiResult(bmi);
        Result.setText(String.valueOf(String.format("%.1f", bmi) + ". \t" + bmiresult));
    }

    //When calculate button is pressed it calculate the BMI using weight and height
    private float calcualte(float weight, float height){
        return (float) (weight/(height * height));
    }
    //depending on bmi levels it will output to the user to display their body status
    private String bmiResult(float bmi){
        if (bmi < 16){
            return "You are severely underweight!";
        }
        else if(bmi < 18.5){
            return "You are underweight.";
        }
        else if(bmi < 25){
            return "Your weight is normal.";
        }
        else if(bmi < 30){
            return "You are overweight!";
        }
        else{
            return "You are obese!";
        }
    }
/*
    public void update( View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Data");
        }
        else
        {
            int a= helper.updateName( u1, u2);
            if(a<=0)
            {
                Message.message(getApplicationContext(),"Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                Message.message(getApplicationContext(),"Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }
    }
    */

    //used to delete a weight if user entered wrong weight
    public void delete(View view)
    {
        String uweight = DeleteWeight.getText().toString();
        if(uweight.isEmpty())
        {
            Message.message(getApplicationContext(),"enter existing weight to delete");
        }
        else{
            int i= helper.delete_weight(uweight);
            if(i <= 0)
            {
                Message.message(getApplicationContext(),"cannot delete non-existing weight");
                DeleteWeight.setText("");
            }
            else
            {
                Message.message(this, "weight is deleted");
                DeleteWeight.setText("");
            }
        }
    }
}