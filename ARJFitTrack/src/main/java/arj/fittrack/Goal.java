package arj.fittrack;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

    ListView listView;

    //Declare Database
    myDbAdapter helper;

    //Declared the Context for Dialog
    //final Goal context = this;

    //Firebase
    DatabaseReference databaseRefGoal;

    int num = 0;
    String[] goalid = new String[num];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.goalactivity, container, false);
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

        //Initialize the DatabaseHelper
        helper = new myDbAdapter(getActivity().getApplicationContext());

        databaseRefGoal = FirebaseDatabase.getInstance().getReference("goal");



        //Stores the user's goal
        String[] goals = {""};


        //Create an ArrayList object to store the user's goal
        arrayList = new ArrayList<>(Arrays.asList(goals));
        //adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,android.R.layout.simple_list_item_multiple_choice,arrayList);
        //Supply data items to Checkbox Listview


        //set multiple selection mode
        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //Testing
        //arrayList.add("Test");

        //Creates the ListView
        listView = (ListView) view.findViewById(R.id.listv);



       /* //Gets the user's goals from the database
        String data = helper.getData_Goals();
        String[] test = data.split("\n");
        //Displays the user's goals
        /*for(String savegoals : test) {
            arrayList.add(savegoals);
        }*/
        // DataSnapshot dataSnapshot;*/


        //User's input for adding the Goals
        input = (EditText) view.findViewById(R.id.editText);

        //Create an ArrayList object to store the goals that has been click on the list view
        selectedItems = new ArrayList<String>();
        goalidlist = new ArrayList<String>();



        //set OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final String selectedItem = ((TextView) view).getText().toString();
              /*  if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items*/

                //current position in that the user click on the listview
                //Message.message(getActivity().getApplicationContext(), String.valueOf(position));


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Delete");
                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure?")
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

                                /*long i = helper.delete_goals(selectedItem);
                                if (i <= 0) {
                                    Message.message(getActivity().getApplicationContext(), "Unable to delete goal");

                                } else {
                                    Message.message(getActivity().getApplicationContext(), "Goal deleted");

                                }*/

                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();

            }
        });


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

                    //Add the user's goal to the array and display the goal in the ListView
                    arrayList.add(input.getText().toString());

                    String goal = input.getText().toString();

                    String id = databaseRefGoal.push().getKey();
                    UserGoal userGoal = new UserGoal(id,goal);
                    databaseRefGoal.child(id).setValue(userGoal);
                    Message.message(getActivity().getApplicationContext(),"Goal Added");


                    adapter.notifyDataSetChanged();
                    //Add the user's goal to the database
                   /* long id = helper.insertData_Goal(goals);
                    if(id<0)
                    {
                        Message.message(getActivity().getApplicationContext(),"Insertion Unsuccessful");
                        input.setText("");

                    } else
                    {
                        Message.message(getActivity().getApplicationContext(),"Insertion Successful");
                        input.setText("");
                    }*/

                }
            }
        });


       /* //This button will delete the user's Goals
        final Button deletegoal = (Button) view.findViewById(R.id.deletegoal);
        deletegoal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int count = 0;

                String data = helper.getData_Goals();
                String[] test = data.split("\n");
                for (String savegoals : test) {
                    count++;
                }

                String[] array = new String[count];

                int k = 0;
                //String goals = null;
                for (String goals : selectedItems) {
                    array[k] = goals;
                    k++;
                }


                // helper.delete_goals(goals); //remove the user's goals from the database
                //Message.message(getActivity().getApplicationContext(),goals);

                Message.message(getActivity().getApplicationContext(), array[0]);
              //  Message.message(getActivity().getApplicationContext(), array[1]);

                //Displays the user's goals


                    long i = helper.delete_goals(array[0]);
                    if (i <=0) {
                        Message.message(getActivity().getApplicationContext(), "Unable to delete goal");

                    } else {
                        Message.message(getActivity().getApplicationContext(), "Goal deleted");

                    }
                    // arrayList.remove(goals); //remove the user's goals
                    /// adapter.notifyDataSetChanged();
                }

        });*/
        return view;
    }


    //Debug
  /*  public void viewdata(View view)
    {
        String data = helper.getData_Goals();
        Message.message(getActivity().getApplicationContext(),data);
    }*/

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
            adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);
            listView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    }
}