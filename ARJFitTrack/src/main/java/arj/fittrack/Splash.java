package arj.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

public class Splash extends AppCompatActivity {
    //A delay time to show the icon for 5 seconds (loading screen)
    final int SPLASH_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivity);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(SPLASH_TIME);
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
