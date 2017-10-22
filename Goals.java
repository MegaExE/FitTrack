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
 * Created by Owner on 10/2/2017.
 */

public class Goals extends AppCompatActivity {

    static ArrayList<String> arrayList;
    static  ArrayAdapter<String> adapter;
     EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);


        ListView listView = (ListView)findViewById(R.id.listv);
        String[] items={"test","test"};
        arrayList=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,arrayList);
        listView.setAdapter(adapter);
        input=(EditText)findViewById(R.id.editText);





            final Button setgoals2 = (Button) findViewById(R.id.setgoal);
            setgoals2.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    TextView tv1;
                    EditText input;
                    input = (EditText) findViewById(R.id.editText);

                   tv1 = (TextView ) findViewById(R.id.textView2);
                   tv1.setText(input.getText().toString());

                    arrayList.add(input.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            });





        }
    }

