package arj.fittrack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Goal extends AppCompatActivity {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    //Declare ArrayList, ArrayAdapter and EditText
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    EditText input;

    //Declare Database
    myDbAdapter helper;

    //Declared the Context for Dialog
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Add a upbutton to allow the user to go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize the DatabaseHelper
        helper = new myDbAdapter(this);

        //Creates the ListView
        ListView listView = (ListView) findViewById(R.id.listv);

        //Stores the user's goal
        String[] goals = {};



        //Create an ArrayList object to store the user's goal
        arrayList = new ArrayList<>(Arrays.asList(goals));
        //adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,android.R.layout.simple_list_item_multiple_choice,arrayList);
        //Supply data items to Checkbox Listview
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayList);

        //set multiple selection mode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //Testing
        //arrayList.add("Test");

        listView.setAdapter(adapter);

        //Gets the user's goals from the database
        String data = helper.getData_Goals();
        String[] test = data.split("\n");
        //Displays the user's goals
        for(String savegoals : test) {
            arrayList.add(savegoals);
        }

        //User's input for adding the Goals
        input = (EditText) findViewById(R.id.editText);

        //set OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        //This button will set the user's Goals
        final Button setgoals = (Button) findViewById(R.id.setgoal);
        setgoals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView tv1;
                EditText input;
                input = (EditText) findViewById(R.id.editText);

                String goals = input.getText().toString();

                //Check if the EditText is empty or not
                if(goals.isEmpty()){
                    Message.message(getApplicationContext(),"Enter a goal");
                }else {

                    tv1 = (TextView) findViewById(R.id.textView2);
                    tv1.setText(input.getText().toString());

                    //Add the user's goal to the array and display the goal in the ListView
                    arrayList.add(input.getText().toString());
                    adapter.notifyDataSetChanged();


                    //Add the user's goal to the database
                    long id = helper.insertData_Goal(goals);
                    if(id<0)
                    {
                        Message.message(getApplicationContext(),"Insertion Unsuccessful");
                        input.setText("");

                    } else
                    {
                        Message.message(getApplicationContext(),"Insertion Successful");
                        input.setText("");
                    }

                }
            }
        });


        //Floating Action Button will go to the Challenges Screen
        FloatingActionButton challenges = (FloatingActionButton) findViewById(R.id.challenges);
        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Goes to the Challenges Screen
                Intent intent = new Intent(Goal.this, Challenges.class);
                startActivity(intent);
            }

        });

    }


    //Debug
    public void viewdata(View view)
    {
        String data = helper.getData_Goals();
        Message.message(this,data);
    }


    //Action Bar
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
            //Display the Help Message/FAQ in a Dialog
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
            //Display the About in a Dialog
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
            //Display the Setting for the FitTrack
            case R.id.Setting:
                Intent intent = new Intent(Goal.this, Setting.class);
                startActivity(intent);
                break;
            //Links to a discussion board regarding to health issues
            case R.id.Discussion:
                Uri url = Uri.parse("https://patient.info/forums");
                Intent launch = new Intent(Intent.ACTION_VIEW, url);
                startActivity(launch);
                break;
            //Redirects to reddit
            case R.id.News:
                Uri url2 = Uri.parse("http://www.cbc.ca/news/health");
                Intent launch2 = new Intent(Intent.ACTION_VIEW, url2);
                startActivity(launch2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}