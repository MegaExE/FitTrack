package arj.fittrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Team Name: ARJ
 */

public class Goals extends AppCompatActivity {

    static ArrayList<String> arrayList;
    static  ArrayAdapter<String> adapter;
     EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);

        //Creates the ArrayList for Goals
        ListView listView = (ListView)findViewById(R.id.listv);
        String[] items={};
        arrayList=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,arrayList);
        listView.setAdapter(adapter);
        input=(EditText)findViewById(R.id.editText);




            //Set the user's Goals
            final Button setgoals = (Button) findViewById(R.id.setgoal);
            setgoals.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    TextView tv1;
                    EditText input;
                    input = (EditText) findViewById(R.id.editText);

                   tv1 = (TextView ) findViewById(R.id.textView2);
                   tv1.setText(input.getText().toString());

                    //Display the users Goals in ListView
                    arrayList.add(input.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            });





        }
    }

