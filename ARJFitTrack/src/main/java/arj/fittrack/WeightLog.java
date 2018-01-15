package arj.fittrack;

<<<<<<< HEAD
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
=======
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
>>>>>>> master
import android.net.Uri;
import android.os.Bundle;
//import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
<<<<<<< HEAD
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;


import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

=======
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

>>>>>>> master
/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class WeightLog extends AppCompatActivity {
<<<<<<< HEAD
    /*
    EditText Weight , DeleteWeight, bWeight, bHeight;
    TextView Result;
    myDbAdapter helper;
    */
    //
    final Context context = this;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseRefWeight;
    EditText inputWeight;
=======

    final Context context = this;
    DatabaseReference databaseRefWeight, databaseRefHeight;
    ListView listView;
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    TextView showHeight, showWeight;

>>>>>>> master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);
<<<<<<< HEAD
        //firebaseAuth = FirebaseAuth.getInstance(); initializing firebase authenication object
        /*
        //Retrieve the input from user using EditText
        //Weight = (EditText) findViewById(R.id.weight);
        DeleteWeight = (EditText) findViewById(R.id.editDelete);
        //Retrieve input to calculate BMI
        bWeight = (EditText) findViewById(R.id.bmiWeight);
        bHeight = (EditText) findViewById(R.id.bmiHeight);

        //Uses a TextView to display the result
        Result = (TextView) findViewById(R.id.result);
        helper = new myDbAdapter(this);
        */
        databaseRefWeight = FirebaseDatabase.getInstance().getReference("weight");
=======
        databaseRefWeight = FirebaseDatabase.getInstance().getReference("weight");
        databaseRefHeight = FirebaseDatabase.getInstance().getReference("height");

        //Declaring Variables
        String[] weights = {""};
        arrayList = new ArrayList<>(Arrays.asList(weights));
        listView = (ListView) findViewById(R.id.listWeight);
        showHeight = (TextView) findViewById(R.id.displayHeight);
        showWeight = (TextView) findViewById(R.id.displayWeight);
>>>>>>> master

        final ImageButton addWeight = (ImageButton) findViewById(R.id.add);
        addWeight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(WeightLog.this);
                alertDialog.setTitle("WEIGHT");
                alertDialog.setMessage("Enter Weight in pounds (LB)");

<<<<<<< HEAD
                final EditText inputWeight = new EditText(WeightLog.this);
                inputWeight.setId(R.id.inWeight);
                inputWeight.setInputType(InputType.TYPE_CLASS_NUMBER);
=======
                //Creates an EditText in the activity inside of the alert dialog
                final EditText inputWeight = new EditText(WeightLog.this);
                inputWeight.setId(R.id.inWeight);
                inputWeight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
>>>>>>> master
                //Creates a new layout parameters with the specifed width and height
                LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                inputWeight.setLayoutParams(layoutparams);
                alertDialog.setView(inputWeight);

                //When pressed Add
                alertDialog.setPositiveButton("Add",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
<<<<<<< HEAD
                            //String weight = inputWeight.getText().toString();
                            //UserWeight userWeight = new UserWeight(weight);
                            String weight = inputWeight.getText().toString();
                            if(!TextUtils.isEmpty(weight))
                            {
=======
                            String weight = inputWeight.getText().toString();
                            if(!TextUtils.isEmpty(weight))
                            {
                                //Inputting data into the database ID, Current Date, and Weight
>>>>>>> master
                                String id = databaseRefWeight.push().getKey();
                                String currentDate = java.text.DateFormat.getDateTimeInstance().format(new Date());
                                UserWeight userWeight = new UserWeight(id, weight, currentDate);
                                databaseRefWeight.child(id).setValue(userWeight);
                                Message.message(getApplicationContext(),"Weight Added!");
                            }
                            else
                            {
                                Message.message(getApplicationContext(),"Please enter a weight in LB");
<<<<<<< HEAD
                                //stop the execution
                                return;
=======
>>>>>>> master
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
<<<<<<< HEAD
    }
/*
    public void registerWeight(){
        String weight = inputWeight.getText().toString();
        if(!TextUtils.isEmpty(weight))
        {
            String id = databaseRefWeight.push().getKey();
            UserWeight userWeight = new UserWeight(id, weight);
            databaseRefWeight.child(id).setValue(userWeight);
            Message.message(getApplicationContext(),"Weight Added!");
        }
        else
        {
            Message.message(getApplicationContext(),"Please enter a weight");
            //stop the execution
            //return;
        }
*/
/*
        if(weight.isEmpty())
        {
            Message.message(getApplicationContext(),"Please enter a weight");
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
*/
=======

        final ImageButton addHeight = (ImageButton) findViewById(R.id.addHeight);
        addHeight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(WeightLog.this);
                alertDialog.setTitle("HEIGHT");
                alertDialog.setMessage("Enter Height in centimeter (CM)");

                final EditText inputHeight = new EditText(WeightLog.this);
                inputHeight.setId(R.id.inHeight);
                inputHeight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                //Creates a new layout parameters with the specifed width and height
                LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                inputHeight.setLayoutParams(layoutparams);
                alertDialog.setView(inputHeight);

                //When pressed Add
                alertDialog.setPositiveButton("Add",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                String height = inputHeight.getText().toString();
                                if(!TextUtils.isEmpty(height))
                                {
                                    //Inputs an ID and height enter from user into the database
                                    String id = databaseRefHeight.push().getKey();
                                    UserHeight userHeight = new UserHeight(id, height);
                                    databaseRefHeight.child(id).setValue(userHeight);
                                    Message.message(getApplicationContext(),"Height Added!");
                                }
                                else
                                {
                                    Message.message(getApplicationContext(),"Please enter a height in CM");
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

>>>>>>> master
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
                                getString(R.string.S4) +"\n\n" +
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

<<<<<<< HEAD
    //Function that adds weight when the "Add Button" is pressed. However, the field needs to contain a number otherwise a toast message will display
=======
    public void onStart(){
        super.onStart();

        databaseRefWeight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                //Creates an array list and displays into a ListView of the date and weight
                for(DataSnapshot dss : dataSnapshot.getChildren()){
                    UserWeight userWeight = dss.getValue(UserWeight.class);
                    arrayList.add(userWeight.getDate() + getString(R.string.tab) + "Your weight: "+ userWeight.getWeight() +" LB");
                }
                //Uses a TextView to set the last weight inputted
                for(DataSnapshot dss : dataSnapshot.getChildren()) {
                    UserWeight userWeight2 = dss.getValue(UserWeight.class);

                    String weight = userWeight2.getWeight();
                    showWeight.setText(weight + " LB");
                }
                //Displays the data of into a ListView
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseRefHeight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dss : dataSnapshot.getChildren()){
                    UserHeight userHeight = dss.getValue(UserHeight.class);

                    String height = userHeight.getHeight();
                    showHeight.setText(height + " CM");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


   // These were all used for a local database for debugging and testing
    //The current app now uses Firebase
>>>>>>> master
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

    //Function is used to display weight from the input
    public void viewdata(View view) {
        String data = helper.getData_Weight();
        Message.message(this,data);
    }
<<<<<<< HEAD

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
    */
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
    /*
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
=======
>>>>>>> master
    */
}