package arj.fittrack;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    //Declare Database
    myDbAdapter helper;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.challenges, container, false);

        /*SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            getActivity().setTheme(R.style.AppTheme_Dark_NoActionBar);
        }*/

        super.onCreate(savedInstanceState);
        //getActivity().setContentView(R.layout.challenges);
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //Add the menu bar into the ActionBar
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //Add a upbutton to allow the user to go back
        // ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Initialize the DatabaseHelper
        helper = new myDbAdapter(getActivity().getApplicationContext());


        //create an ArrayList object to store selected items
        selectedItems = new ArrayList<String>();


        //Create an instance of ListView
        ListView chl=(ListView) view.findViewById(R.id.checkable_list);
        //Set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);




        //This section will be storing the Challenges and Task in a String
        String[] items = new String[0];

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);

        chl.setAdapter(adapter);


        //set OnItemClickListener
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                //TextView textview=((LinearLayout)view).findViewById(R.id.txt_title);

                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items

            }

        });


        return view;

    }


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

}
