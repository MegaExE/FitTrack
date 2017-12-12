package arj.fittrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Challenges extends AppCompatActivity {


    ArrayList<String> selectedItems;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenges);
        //create an ArrayList object to store selected items
        selectedItems = new ArrayList<String>();


       // FloatingActionButton challenges= (FloatingActionButton)findViewById(R.id.challenges);
       // challenges.setOnClickListener(new View.OnClickListener(){
       //     public void onClick(View v){
       //         Toast.makeText(getApplicationContext(),"Testing",Toast.LENGTH_LONG).show();
      //      }

       // });



    }



    public void onStart(){
        super.onStart();
        //Create an instance of ListView
        ListView chl=(ListView) findViewById(R.id.checkable_list);
        //Set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //This section will be storing the Challenges and Task in a String
        String[] items={"Task 1","Task 2"};



        //supply data items to Checkbox ListView
        //ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.checkablelist,R.id.txt_title,items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);

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
    }

    public void showSelectedItems(View view){
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }


}
