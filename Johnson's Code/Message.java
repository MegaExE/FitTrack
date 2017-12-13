package arj.fittrack;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by John on 2017-11-11.
 */


public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}