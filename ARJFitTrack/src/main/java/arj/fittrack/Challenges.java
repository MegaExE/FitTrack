package arj.fittrack;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Challenges extends Fragment {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    //Declare Arraylist
    ArrayList<String> selectedItems;
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;

    //Declare ListView
    ListView chl;

    //Firebase
    DatabaseReference databaseRefChallenges;

    //Declare SharedPreferences
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.challenges, container, false);

        /*SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            getActivity().setTheme(R.style.AppTheme_Dark_NoActionBar);
        }*/

        //SharedPreference to remember the challenges/task that the user checked
      sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
       editor =sharedPreferences.edit();

        super.onCreate(savedInstanceState);

        //Initialize the firebase database
        databaseRefChallenges = FirebaseDatabase.getInstance().getReference("challenges");

        //This section will be storing the Challenges and Task in a String
        String[] items = new String[0];

        //Create an ArrayList object to store the challenges and tasks
        arrayList = new ArrayList<>(Arrays.asList(items));

        //Create an instance of ListView
        chl=(ListView) view.findViewById(R.id.checkable_list);
        //Set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //create an ArrayList object to store selected items
        selectedItems = new ArrayList<String>();

        //set OnItemClickListener
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItems.contains(selectedItem)){
                    //save the position that have been unchecked to false to SharedPreferences
                editor.putBoolean(Integer.toString(position), false);
                editor.commit();
            }
                else {
                    //save the position that have been checked to true to SharedPreferences
                    editor.putBoolean(Integer.toString(position),true);
                    editor.commit();
                }
            }

        });
        return view;
    }

    //Updated the listview and Display the challenges/task from the firebase database
    @Override
    public void onStart() {
        super.onStart();
        databaseRefChallenges.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();
                //Retrieve the challenges and  display it on List of challenges
                for (DataSnapshot goalsnapshot : dataSnapshot.getChildren()) {
                    challengeslist challenges = goalsnapshot.getValue(challengeslist.class);
                    arrayList.add(challenges.getchallenge());
                   // goalidlist.add(goal.getgoalID());
                }
                //Adds checkbox to the listview
                adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);
                chl.setAdapter(adapter);
                chl.setItemChecked(0,true);

                //Load the sharedPreferences to remember the challenges/task that the user checked to show that the user's compleleted the challenges/tasks
                for(int i = 0; i < chl.getCount(); i++){
                    chl.setItemChecked(i,sharedPreferences.getBoolean(Integer.toString(i),false));
                    if(sharedPreferences.getBoolean(Integer.toString(i),false)){
                        chl.setItemChecked(i,true);
                    }
                    else{
                        chl.setItemChecked(i,false);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
