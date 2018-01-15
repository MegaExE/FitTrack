package arj.fittrack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class WeightLog extends AppCompatActivity {

    final Context context = this;
    DatabaseReference databaseRefWeight, databaseRefHeight;
    ListView listView;
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    TextView showHeight, showWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightactivity);
        databaseRefWeight = FirebaseDatabase.getInstance().getReference("weight");
        databaseRefHeight = FirebaseDatabase.getInstance().getReference("height");

        //Declaring Variables
        String[] weights = {""};
        arrayList = new ArrayList<>(Arrays.asList(weights));
        listView = (ListView) findViewById(R.id.listWeight);
        showHeight = (TextView) findViewById(R.id.displayHeight);
        showWeight = (TextView) findViewById(R.id.displayWeight);

        final ImageButton addWeight = (ImageButton) findViewById(R.id.add);
        addWeight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(WeightLog.this);
                alertDialog.setTitle("WEIGHT");
                alertDialog.setMessage(getString(R.string.enterWeight));

                //Creates an EditText in the activity inside of the alert dialog
                final EditText inputWeight = new EditText(WeightLog.this);
                inputWeight.setId(R.id.inWeight);
                inputWeight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                //Creates a new layout parameters with the specifed width and height
                LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                inputWeight.setLayoutParams(layoutparams);
                alertDialog.setView(inputWeight);

                //When pressed Add
                alertDialog.setPositiveButton("Add",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                String weight = inputWeight.getText().toString();
                                if(!TextUtils.isEmpty(weight))
                                {
                                    //Inputting data into the database ID, Current Date, and Weight
                                    String id = databaseRefWeight.push().getKey();
                                    String currentDate = java.text.DateFormat.getDateTimeInstance().format(new Date());
                                    UserWeight userWeight = new UserWeight(id, weight, currentDate);
                                    databaseRefWeight.child(id).setValue(userWeight);
                                    Message.message(getApplicationContext(),"Weight Added!");
                                }
                                else
                                {
                                    Message.message(getApplicationContext(),"Please enter a weight in LB");
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

        final ImageButton addHeight = (ImageButton) findViewById(R.id.addHeight);
        addHeight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(WeightLog.this);
                alertDialog.setTitle("HEIGHT");
                alertDialog.setMessage(getString(R.string.enterHeight));

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
                                getString(R.string.S4) +"\n\n" +
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
    */
}