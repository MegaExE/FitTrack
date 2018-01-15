package arj.fittrack;

<<<<<<< HEAD
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
=======
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
>>>>>>> master
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
=======
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
>>>>>>> master

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Goal extends Fragment {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    //Declare ArrayList, ArrayAdapter and EditText
    static ArrayList<String> arrayList;
<<<<<<< HEAD
    ArrayList<String> goalidlist;
=======
>>>>>>> master
    static ArrayAdapter<String> adapter;
    EditText input;

    //create an ArrayList object to store selected items
    ArrayList<String> selectedItems;

<<<<<<< HEAD
    ListView listView;

    //Declare Database
    //myDbAdapter helper;

    //Declared the Context for Dialog
    //final Goal context = this;

    // Declare Firebase
    DatabaseReference databaseRefGoal;

    int num = 0;

    String[] goalid = new String[num];
=======

    //Declare Database
    myDbAdapter helper;

    //Declared the Context for Dialog
    final Goal context = this;
>>>>>>> master

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.goalactivity, container, false);
<<<<<<< HEAD
        final Context context = getContext();
=======

>>>>>>> master
        /*SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            getActivity().setTheme(R.style.AppTheme_Dark_NoActionBar);
        }*/

        super.onCreate(savedInstanceState);
        //getActivity().setContentView(R.layout.goal);
        //  Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //Add a upbutton to allow the user to go back
        // ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize the DatabaseHelper
<<<<<<< HEAD
        //helper = new myDbAdapter(getActivity().getApplicationContext());

        //Initialize the FirebaseDatabase
        databaseRefGoal = FirebaseDatabase.getInstance().getReference("goal");

        //Stores the user's goal
        String[] goals = {""};
=======
        helper = new myDbAdapter((getActivity().getApplicationContext()));

        //Creates the ListView
        ListView listView = (ListView) view.findViewById(R.id.listv);

        //Stores the user's goal
        String[] goals = {};

>>>>>>> master


        //Create an ArrayList object to store the user's goal
        arrayList = new ArrayList<>(Arrays.asList(goals));
        //adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,android.R.layout.simple_list_item_multiple_choice,arrayList);
        //Supply data items to Checkbox Listview
<<<<<<< HEAD
=======
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);

        //set multiple selection mode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
>>>>>>> master

        //Testing
        //arrayList.add("Test");

<<<<<<< HEAD
        //Creates the ListView
        listView = (ListView) view.findViewById(R.id.listv);
=======
        listView.setAdapter(adapter);

        //Gets the user's goals from the database
        String data = helper.getData_Goals();
        String[] test = data.split("\n");
        //Displays the user's goals
        for(String savegoals : test) {
            arrayList.add(savegoals);
        }
>>>>>>> master

        //User's input for adding the Goals
        input = (EditText) view.findViewById(R.id.editText);

<<<<<<< HEAD
        //Create an ArrayList object to store the goals that has been click on the list view
        selectedItems = new ArrayList<String>();
        goalidlist = new ArrayList<String>();
=======
        selectedItems = new ArrayList<String>();
>>>>>>> master



        //set OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
<<<<<<< HEAD
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final String selectedItem = ((TextView) view).getText().toString();

                //Display a dialog for the user to remove the goal that show that the user completed the goal
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle(getString(R.string.remove));
                // set dialog message
                alertDialogBuilder
                        .setMessage(getString(R.string.completed))
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public  void onClick(DialogInterface dialog,int id){
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked
                                // delete the goal

                                //Find the usergoal to be deleted
                                DatabaseReference drgoal = FirebaseDatabase.getInstance().getReference("goal").child(goalidlist.get(position));
                                //Delete the user's goal
                                drgoal.removeValue();
                                Message.message(getActivity().getApplicationContext(),"Goal Deleted");
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
=======
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items
>>>>>>> master

            }
        });

<<<<<<< HEAD
        //Adds the user's goals
        final ImageButton addGoal = (ImageButton) view.findViewById(R.id.setgoal);
        addGoal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle(getString(R.string.Goals));
                alertDialog.setMessage(getString(R.string.NewGoal));

                //Display the EditText in the dialog box for the user to enter the goal
                final EditText inputGoal = new EditText(getActivity());
                inputGoal.setId(R.id.idgoal);

                LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                inputGoal.setLayoutParams(layoutparams);

                alertDialog.setView(inputGoal);

                //When pressed Add, it will add the Goal to the firebase database and listview
                alertDialog.setPositiveButton("Add",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                //String weight = inputWeight.getText().toString();
                                //UserWeight userWeight = new UserWeight(weight);

                                String goal = inputGoal.getText().toString();
                                if(!TextUtils.isEmpty(goal))
                                {
                                    String id = databaseRefGoal.push().getKey();
                                    UserGoal userGoal = new UserGoal(id,goal);
                                    databaseRefGoal.child(id).setValue(userGoal);
                                    Message.message(getActivity().getApplicationContext(),getString(R.string.Addedgoalmessage));
                                }
                                else
                                {
                                    Message.message(getActivity().getApplicationContext(),getString(R.string.Entergoal));
                                    //stop the execution
                                    return;
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
        return view;
    }

    //Updated the listview and Display the users list of goals from the firebase database
    @Override
    public void onStart() {
        super.onStart();
    databaseRefGoal.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            arrayList.clear();
            goalidlist.clear();
            //Retrieve the User's goal and goal id and display it on List of goals
            for (DataSnapshot goalsnapshot : dataSnapshot.getChildren()) {
                UserGoal goal = goalsnapshot.getValue(UserGoal.class);
                arrayList.add(goal.getgoal());
                goalidlist.add(goal.getgoalID());
            }
            adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    }
=======

        //This button will set the user's Goals
        final Button setgoals = (Button) view.findViewById(R.id.setgoal);
        setgoals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView tv1;
                EditText input;
                input = (EditText) view.findViewById(R.id.editText);

                String goals = input.getText().toString();

                //Check if the EditText is empty or not
                if(goals.isEmpty()){
                    Message.message(getActivity().getApplicationContext(),"Enter a goal");
                }else {

                    // tv1 = (TextView) getActivity().findViewById(R.id.textView2);
                    //tv1.setText(input.getText().toString());

                    //Add the user's goal to the array and display the goal in the ListView
                    arrayList.add(input.getText().toString());
                    adapter.notifyDataSetChanged();


                    //Add the user's goal to the database
                    long id = helper.insertData_Goal(goals);
                    if(id<0)
                    {
                        Message.message(getActivity().getApplicationContext(),"Insertion Unsuccessful");
                        input.setText("");

                    } else
                    {
                        Message.message(getActivity().getApplicationContext(),"Insertion Successful");
                        input.setText("");
                    }

                }
            }
        });


        //This button will delete the user's Goals
        final Button deletegoal = (Button) view.findViewById(R.id.deletegoal);
        deletegoal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                for(String goals:selectedItems) {

                    // helper.delete_goals(goals); //remove the user's goals from the database
                    //Message.message(getActivity().getApplicationContext(),goals);
                    int i= helper.delete_goals(goals); //remove the goal that the user's checkmark
                    if(i <= 0)
                    {

                        Message.message(getActivity().getApplicationContext(),"Unable to delete goal");

                    }
                    else
                    {
                        Message.message(getActivity().getApplicationContext(), "Goal deleted");

                    }
                    // arrayList.remove(goals); //remove the user's goals
                    adapter.notifyDataSetChanged();
                }

            }
        });


        return view;
    }


    //Debug
  /*  public void viewdata(View view)
    {
        String data = helper.getData_Goals();
        Message.message(getActivity().getApplicationContext(),data);
    }*/



>>>>>>> master
}