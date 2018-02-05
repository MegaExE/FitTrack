package arj.fittrack;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Goal extends Fragment {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    //Declare ArrayList, ArrayAdapter and EditText
    static ArrayList<String> arrayList;
    ArrayList<String> goalidlist;
    static ArrayAdapter<String> adapter;
    EditText input;

    //create an ArrayList object to store selected items
    ArrayList<String> selectedItems;

    //Declare ListView
    ListView listView;

    //Declare Database
    //myDbAdapter helper;

    //Declared the Context for Dialog
    //final Goal context = this;

    // Declare Firebase
    DatabaseReference databaseRefGoal;


    //int num = 0;
    //String[] goalid = new String[num];

    //Declare view
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.goalactivity, container, false);
        final Context context = getContext();
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

        //Initialize the FirebaseDatabase
        databaseRefGoal = FirebaseDatabase.getInstance().getReference("goal");

        //Stores the user's goal
        String[] goals = {""};


        //Create an ArrayList object to store the user's goal
        arrayList = new ArrayList<>(Arrays.asList(goals));
        //adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,android.R.layout.simple_list_item_multiple_choice,arrayList);
        //Supply data items to Checkbox Listview

        //Testing
        //arrayList.add("Test");

        //Creates the ListView
        listView = (ListView) view.findViewById(R.id.listv);


        //Create an ArrayList object to store the goals that has been click on the list view
        selectedItems = new ArrayList<String>();
        goalidlist = new ArrayList<String>();



        //set OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

            }
        });

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

                //When you press the add icon set goal, it will add the Goal to the firebase database and listview
                alertDialog.setPositiveButton("Add",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
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
            adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    }
}