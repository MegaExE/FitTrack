package arj.fittrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Message;
import android.icu.text.SimpleDateFormat;
import android.widget.Toast;


/**
 * Created by John on 2017-11-11.1
 */

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String weight)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.KEY_Weight, weight);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase database = myhelper.getWritableDatabase();
        //String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD};
        String[] columns = {myDbHelper.UID, myDbHelper.DATE, myDbHelper.KEY_Weight};
        Cursor cursor =database.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String weight =cursor.getString(cursor.getColumnIndex(myDbHelper.KEY_Weight));
            String  date =cursor.getString(cursor.getColumnIndex(myDbHelper.DATE));
                        //buffer.append(cid+ "   " + name + "   " + password +" \n");
            buffer.append(date + "   " + weight + " LB" + " \n");
        }
        return buffer.toString();
    }

    public int delete(String uweight)
    {
        SQLiteDatabase database = myhelper.getWritableDatabase();
        String[] whereArgs ={uweight};

        int count = database.delete(myDbHelper.TABLE_NAME ,myDbHelper.KEY_Weight+" = ?",whereArgs);
        return  count;
    }
    /*

    public int updateName(String oldWeight , String newWeight)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.KEY_Weight,newWeight);
        String[] whereArgs= {oldWeight};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.KEY_Weight+" = ?",whereArgs );
        return count;
    }
    */
    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column 1 (Primary Key)
        private static final String DATE = "Date";  //Column 2
        private static final String KEY_Weight = "Weight";  //Column 3
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +DATE+" DEFAULT CURRENT_TIMESTAMP ,"+KEY_Weight+" FLOAT(7));";
        //private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
         //       " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_Weight+" FLOAT(7));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}