package arj.fittrack;

/**
 *  Team Name: ARJ
 *  Adrian Caprini N01115682, Raphael Najera N01104031, Johnson Liang N01129137
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    /*public long insertData(String name, String pass)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.KEY_goal, name);
        //contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDbHelper.TABLE_Goals, null , contentValues);
        return id;
    }*/

    //Insert value to the database
    public long insertData_Goal(String goals)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.KEY_goal, goals);
        //contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDbHelper.TABLE_Goals, null , contentValues);
        return id;
    }


    public long insertData_Challengnes(String goals)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.KEY_goal, goals);
        //contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDbHelper.TABLE_Goals, null , contentValues);
        return id;
    }

    public long insertData_Weight(String weight)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.KEY_Weight, weight);
        long id = dbb.insert(myDbHelper.TABLE_Weight, null , contentValues);
        return id;
    }

    //Get values from the database
    public String getData_Goals()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.KEY_UID, myDbHelper.KEY_goal};
        Cursor cursor =db.query(myDbHelper.TABLE_Goals,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.KEY_UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.KEY_goal));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            //buffer.append(cid+ "   " + name + "   " + password +" \n");
            buffer.append( name +" \n");

        }
        return buffer.toString();
    }

    public String getData_Challenges()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.KEY_UID, myDbHelper.KEY_challenges};
        Cursor cursor =db.query(myDbHelper.TABLE_Challenges,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.KEY_UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.KEY_challenges));
            //String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            //buffer.append(cid+ "   " + name + "   " + password +" \n");
            buffer.append(cid+ "   " + name +" \n");

        }
        return buffer.toString();
    }

    public String getData_Weight()
    {
        SQLiteDatabase database = myhelper.getWritableDatabase();
        //String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD};
        String[] columns = {myDbAdapter.myDbHelper.KEY_UID, myDbAdapter.myDbHelper.KEY_Date, myDbAdapter.myDbHelper.KEY_Weight};
        Cursor cursor =database.query(myDbAdapter.myDbHelper.TABLE_Weight,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbAdapter.myDbHelper.KEY_UID));
            String weight =cursor.getString(cursor.getColumnIndex(myDbAdapter.myDbHelper.KEY_Weight));
            String  date =cursor.getString(cursor.getColumnIndex(myDbAdapter.myDbHelper.KEY_Date));
            //buffer.append(cid+ "   " + name + "   " + password +" \n");
            buffer.append(date + "   " + weight + " LB" + " \n");
        }
        return buffer.toString();
    }

    //Delete values from the database
    public  int delete_weight(String uweight)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uweight};

        int count =db.delete(myDbHelper.TABLE_Weight , myDbHelper.KEY_Weight+" = ?",whereArgs);
        return  count;
    }

    //Delete goals from the database
    public  int delete_goals(String goals)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={goals};

        int count =db.delete(myDbHelper.TABLE_Goals , myDbHelper.KEY_goal+" = ?",whereArgs);
        return  count;
    }

  /*  public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.KEY_goal,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.KEY_UID,contentValues, myDbHelper.KEY_goal+" = ?",whereArgs );
        return count;
    }*/

    static class myDbHelper extends SQLiteOpenHelper
    {
        // Database Version
        private static final int DATABASE_Version = 1;

        //Name of the Database
        private static final String DATABASE_NAME = "FitTrackDataBase";    // Database Name

        //Table Names
        private static final String TABLE_Goals = "Goals"; //Database Table for Goals
        private static final String TABLE_Challenges = "Challenges"; //Database Table for Challenges
        private static final String TABLE_Weight = "Weight"; //Database Table for Weight
        //private static final String TABLE_Steps = "Steps"; //Database Table for Steps

        //Common column names
        private static final String KEY_UID="id";     // Column I (Primary Key)

        //Goals Table - Column Names
        private static final String KEY_goal = "Goals";

        //Challenges Table - Column Names
       private static final String KEY_challenges= "Challenges";

        //Weight Table - Column Names
        private static final String KEY_Date = "Date";
        private static final String KEY_Weight = "Weight";

        //Table Create Statements
        //Goals Table create Statements
        private static final String CREATE_TABLE_GOALS = "CREATE TABLE "+TABLE_Goals+
                " ("+KEY_UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_goal+" VARCHAR(255));";

        //Challenges Table create Statements
        private static final String CREATE_TABLE_CHALLENGES = "CREATE TABLE "+TABLE_Challenges+
                " ("+KEY_UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_challenges+" VARCHAR(255))";

        //Weight Table create Statements
        private static final String CREATE_TABLE_Weight = "CREATE TABLE "+TABLE_Weight+
                " ("+KEY_UID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_Date+" DEFAULT CURRENT_TIMESTAMP ,"+KEY_Weight+" FLOAT(7));";

        //drop older tables
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_Goals;
        //private static final String DROP_TABLE_Weight ="DROP TABLE IF EXISTS "+TABLE_Weight;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }


        //Create the requited Tables
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE_GOALS);
                db.execSQL(CREATE_TABLE_CHALLENGES);
                db.execSQL(CREATE_TABLE_Weight);

                //dbb.insert(myDbHelper.TABLE_Goals, null , contentValues);
                //Insert value to the database
                //List of Task/Challenges that the user can do
                db.execSQL("INSERT INTO " + TABLE_Challenges+ "("+KEY_challenges+") VALUES ('Workout on a treadmill for 30 minutes everyday')");
                db.execSQL("INSERT INTO " + TABLE_Challenges+ "("+KEY_challenges+") VALUES ('Daily Goa1: 3000 steps')");
                db.execSQL("INSERT INTO " + TABLE_Challenges+ "("+KEY_challenges+") VALUES ('40 Push-ups in ')");
                db.execSQL("INSERT INTO " + TABLE_Challenges+ "("+KEY_challenges+") VALUES ('Daily Goal: 500 jumps')");
                db.execSQL("INSERT INTO " + TABLE_Challenges+ "("+KEY_challenges+") VALUES ('Daily Goal: drink 2000 ml of water')");

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
