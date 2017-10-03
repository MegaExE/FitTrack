package arj.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
*Raphael Najera
*ARJ
*/

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final ImageButton weight = (ImageButton) findViewById(R.id.weight);
        weight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Main.this, Weight.class);
                startActivity(intent);
            }
        });


        final ImageButton notepad = (ImageButton) findViewById(R.id.notepad);
        notepad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Main.this, Notepad.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Menu (ToolBar)
        switch (item.getItemId())
        {
            case R.id.Help:


                break;
            case R.id.About:


                break;
            case R.id.Setting:


                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
