package arj.fittrack;


<<<<<<< HEAD
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
=======
import android.os.Bundle;
>>>>>>> master
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

<<<<<<< HEAD
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

=======
>>>>>>> master
import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Challenges extends Fragment {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    //Declare Arraylist
    ArrayList<String> selectedItems;
    static ArrayList<String> arrayList;
<<<<<<< HEAD
    static ArrayAdapter<String> adapter;


    ListView chl;
=======
>>>>>>> master

    //Declare Database
    myDbAdapter helper;

<<<<<<< HEAD
    //Firebase
    DatabaseReference databaseRefChallenges;



=======
>>>>>>> master
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.challenges, container, false);

        /*SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            getActivity().setTheme(R.style.AppTheme_Dark_NoActionBar);
        }*/

<<<<<<< HEAD
        final SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editor =sharedPreferences.edit();

=======
>>>>>>> master
        super.onCreate(savedInstanceState);
        //getActivity().setContentView(R.layout.challenges);
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //Add the menu bar into the ActionBar
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //Add a upbutton to allow the user to go back
        // ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Initialize the DatabaseHelper
<<<<<<< HEAD
        helper = new myDbAdapter(getActivity().getApplicationContext());

        databaseRefChallenges = FirebaseDatabase.getInstance().getReference("challenges");

        //This section will be storing the Challenges and Task in a String
        String[] items = new String[0];

        //Create an ArrayList object to store the challenges and tasks
        arrayList = new ArrayList<>(Arrays.asList(items));

        //Create an instance of ListView
        chl=(ListView) view.findViewById(R.id.checkable_list);
=======
        helper = new myDbAdapter((getActivity().getApplicationContext()));


        //create an ArrayList object to store selected items
        selectedItems = new ArrayList<String>();


        //Create an instance of ListView
        ListView chl=(ListView) view.findViewById(R.id.checkable_list);
>>>>>>> master
        //Set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


<<<<<<< HEAD
        //create an ArrayList object to store selected items
        selectedItems = new ArrayList<String>();













        //Gets the user's goals from the database
        //String data = helper.getData_Challenges();
       // String[] test = data.split("\n");
        //Displays challenges
       // for(String challenges : test) {
        //    arrayList.add(challenges);
       // }

        //supply data items to Checkbox ListView
        //ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.checkablelist,R.id.txt_title,items);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);

       /// chl.setAdapter(adapter);
        chl.setItemChecked(0,true);

       // Toast.makeText((getActivity().getApplicationContext()), Integer.toString(chl.getCount()), Toast.LENGTH_LONG).show();


        /*for(int i = 0; i < chl.getCount(); i++){
            chl.setItemChecked(i,sharedPreferences.getBoolean(Integer.toString(i),false));
            if(sharedPreferences.getBoolean(Integer.toString(i),false)){
                chl.setItemChecked(i,true);
            }
            else{
                chl.setItemChecked(i,false);
            }
        }*/


       // chl.setChe

=======


        //This section will be storing the Challenges and Task in a String
        String[] items={" "};

        //Create an ArrayList object to store the challenges and tasks
        arrayList = new ArrayList<>(Arrays.asList(items));

        //Gets the user's goals from the database
        String data = helper.getData_Challenges();
        String[] test = data.split("\n");
        //Displays challenges
        for(String challenges : test) {
            arrayList.add(challenges);
        }

        //supply data items to Checkbox ListView
        //ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.checkablelist,R.id.txt_title,items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>((getActivity().getApplicationContext()), android.R.layout.simple_list_item_multiple_choice, arrayList);

        chl.setAdapter(adapter);


>>>>>>> master
        //set OnItemClickListener
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                //TextView textview=((LinearLayout)view).findViewById(R.id.txt_title);

                String selectedItem = ((TextView) view).getText().toString();
<<<<<<< HEAD
                if (selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                //editor.putBoolean(Integer.toString(position), false);

                editor.commit();
            }
                else {
                    selectedItems.add(selectedItem); //add selected item to the list of selected items


                    chl.setItemChecked(position,true);
                   // editor.putBoolean(Integer.toString(position),true);

                    editor.commit();


                    //selectedItem.setChecked();


                }

=======
                if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items

>>>>>>> master
            }

        });


        return view;

    }


<<<<<<< HEAD
    @Override
    public void onStart() {
        super.onStart();
        databaseRefChallenges.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();
                //goalidlist.clear();
                //Retrieve the User's goal and goal id and display it on List of goals
                for (DataSnapshot goalsnapshot : dataSnapshot.getChildren()) {
                    challengeslist challenges = goalsnapshot.getValue(challengeslist.class);
                    arrayList.add(challenges.getchallenge());
                   // goalidlist.add(goal.getgoalID());
                }
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);
                chl.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

=======
>>>>>>> master
/*
    public void onStart(){
        super.onStart();

    }

    public void showSelectedItems(View view){
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText((getActivity().getApplicationContext()), selItems, Toast.LENGTH_LONG).show();
    }

    public void viewdata(View view)
    {
        String data = helper.getData_Challenges();
        Message.message((getActivity().getApplicationContext()),data);
    }
*/

<<<<<<< HEAD



=======
>>>>>>> master
}
