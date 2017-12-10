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
 * Created by John on 2017-10-03.
 */

public class WeightLog extends AppCompatActivity {
    EditText Weight , DeleteWeight, bWeight, bHeight;
    TextView Result;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);
        Weight = (EditText) findViewById(R.id.weight);
        DeleteWeight = (EditText) findViewById(R.id.editDelete);

        bWeight = (EditText) findViewById(R.id.bmiWeight);
        bHeight = (EditText) findViewById(R.id.bmiHeight);
        Result = (TextView) findViewById(R.id.result);


        helper = new myDbAdapter(this);
    }
    public void addWeight(View view) {
        String weight = Weight.getText().toString();
        if(weight.isEmpty())
        {
            Message.message(getApplicationContext(),"enter a weight");
        }
        else
        {
            long id = helper.insertData(weight);
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

    public void viewdata(View view) {
        String data = helper.getData();
        Message.message(this,data);
    }

    public void calculate(View view){
        String KG = bWeight.getText().toString();
        String CM = bHeight.getText().toString();

        if(KG.isEmpty() || CM.isEmpty()){
            Message.message(getApplicationContext(),"enter a weight and height");
        }

        float weight = Float.parseFloat(KG);
        float height = Float.parseFloat(CM) / 100;
        float bmi = calcualte(weight,height);

        Result.setText(String.valueOf(bmi));
    }

    private float calcualte(float weight, float height){
        return (float) (weight/(height * height));
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
    public void delete(View view)
    {
        String uweight = DeleteWeight.getText().toString();
        if(uweight.isEmpty())
        {
            Message.message(getApplicationContext(),"enter existing weight to delete");
        }
        else{
            int i= helper.delete(uweight);
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