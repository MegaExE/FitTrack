package arj.fittrack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
//import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;
import java.util.jar.Attributes;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class WeightLog extends AppCompatActivity {
    EditText Weight , DeleteWeight, bWeight, bHeight;
    TextView Result;
    myDbAdapter helper;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);

        //Retrieve the input from user using EditText
        //Weight = (EditText) findViewById(R.id.weight);
        DeleteWeight = (EditText) findViewById(R.id.editDelete);
        //Retrieve input to calculate BMI
        bWeight = (EditText) findViewById(R.id.bmiWeight);
        bHeight = (EditText) findViewById(R.id.bmiHeight);

        //Uses a TextView to display the result
        Result = (TextView) findViewById(R.id.result);
        helper = new myDbAdapter(this);

        final ImageButton addWeight = (ImageButton) findViewById(R.id.add);
        addWeight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(WeightLog.this);
                alertDialog.setTitle("WEIGHT");
                alertDialog.setMessage("Enter Weight");

                final EditText inputWeight = new EditText(WeightLog.this);
                inputWeight.setInputType(InputType.TYPE_CLASS_NUMBER);
                //Creates a new layout parameters with the specifed width and height
                LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                inputWeight.setLayoutParams(layoutparams);
                alertDialog.setView(inputWeight);

                //When pressed Add
                alertDialog.setPositiveButton("Add",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
                            String weight = inputWeight.getText().toString();
                            if(weight.isEmpty())
                            {
                                Message.message(getApplicationContext(),"enter a weight");
                            }
                            else {
                                long id = helper.insertData_Weight(weight);
                                if (id <= 0) {
                                    Message.message(getApplicationContext(), "Insert Unsuccessful");
                                    Weight.setText("");
                                } else {
                                    Message.message(getApplicationContext(), "Insert Successful");
                                    Weight.setText("");
                                }
                            }
                        }
                    });
                    alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
        });
    }

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
                        .setMessage("FAQ: \n" +
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
                                "Our health and fitness app is designed to help users achieve and maintain a healthy lifestyle. \n" +
                                " This app will help organize our users through the application. \n" +
                                " Our app will try and use a simple user interface so that all age groups will find it easy to use. \n" +
                                " As a result, we are trying to promote healthier life choices. ")
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

            //Redirects to the Notepad activity when notepad image is tapped
            case R.id.notepad:
                Intent intentNotepad = new Intent(WeightLog.this, Notepad.class);
                startActivity(intentNotepad);
                break;

            //Redirects to Goal activity when goal image is tapped
            case R.id.goal:
                Intent intentGoal = new Intent(WeightLog.this, MenuTab.class);
                startActivity(intentGoal);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //Function that adds weight when the "Add Button" is pressed. However, the field needs to contain a number otherwise a toast message will display
   /*
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
*/
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