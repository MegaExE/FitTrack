package arj.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Goal extends AppCompatActivity {

    //Declare ArrayList, ArrayAdapter and EditText
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);

        //Creates the ListView
        ListView listView = (ListView) findViewById(R.id.listv);

        //Stores the user's goals
        String[] goals = {};
        //Create an ArrayList object to store the user's goals
        arrayList = new ArrayList<>(Arrays.asList(goals));
        //adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,android.R.layout.simple_list_item_multiple_choice,arrayList);
        //Supply data items to Checkbox Listview
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayList);

        //set multiple selection mode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //Testing
        //arrayList.add("Test");
        listView.setAdapter(adapter);
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

                tv1 = (TextView) findViewById(R.id.textView2);
                tv1.setText(input.getText().toString());

                //Add the user's goals to the array and display the goal in the ListView
                arrayList.add(input.getText().toString());
                adapter.notifyDataSetChanged();
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

 /*   @Override
    protected void onRestoreInstanceState(Bundle state){
        super.onRestoreInstanceState(state);
        name_array.addAll(state.getStringArrayList("key"));
        setListAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("key",name_array);
    }   */


    }
}