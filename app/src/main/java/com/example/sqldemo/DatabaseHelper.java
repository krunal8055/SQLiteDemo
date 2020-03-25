package com.example.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {

    // Database Information
    static final String DB_NAME = "Demo.DB";

    // Table Name
    public static final String USER_TABLE = "User";

    // Table columns
    public static final String user_id = "user_ID";
    public static final String user_FN = "user_FN";
    public static final String user_LN = "user_LN";
    public static final String user_EMAIL = "user_EMAIL";
    public static final String user_PHONE = "user_PHONE";
    public static final String user_PASS = "user_PASS";


    private String CREATE_TABLE_QUERY = "CREATE TABLE " + USER_TABLE +
            "("  +  user_id + " INTEGER PRIMARY KEY ," +
            user_FN + " VARCHAR(255) NOT NULL ," +
            user_LN + " VARCHAR(255) NOT NULL ," +
            user_EMAIL + " VARCHAR(255) NOT NULL ,"+
            user_PHONE + " VARCHAR(255) NOT NULL ,"+
            user_PASS + " VARCHAR(255) NOT NULL "  + ")";

    private String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + USER_TABLE ;

    // database version
    static final int DB_VERSION = 1;

  /*  // Creating table query
    private static final String CREATE_TABLE = "create table " + USER_TABLE_NAME + "(" + user_id
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + user_FN + " VARCHAR(255) NOT NULL, "+ user_LN + " VARCHAR(255) NOT NULL, "+ user_EMAIL + " VARCHAR(255) NOT NULL " + user_PHONE + " INTEGER NOT NULL " + user_PASS + " VARCHAR(255) NOT NULL);";
*/
    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    /** Add New USer To The Table **/
    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(user_FN,user.getFirstName());
        contentValues.put(user_LN,user.getLastName());
        contentValues.put(user_EMAIL,user.getEmailID());
        contentValues.put(user_PHONE,user.getPhoneNO());
        contentValues.put(user_PASS,user.getPasssword());

        //Insert Row In Table
        db.insert(USER_TABLE,null,contentValues);
        db.close();
    }

    /** Check User is available in Table or not **/
    public boolean checkuser(String Email)
    {
        String[] columns = { user_id };
        SQLiteDatabase db = this.getReadableDatabase();

        String Selection = user_EMAIL + " = ? ";

        String[] SelectionArgs = { Email };

        Cursor cursor = db.query(
                USER_TABLE,
                columns,
                Selection,
                SelectionArgs,
                null,
                null,
                null);

        int cursor_count = cursor.getCount();

        cursor.close();
        db.close();

        if(cursor_count > 0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

    /** Check User is available in Table or not **/
    public boolean CheckUser(String Email, String Password)
    {
        String[] columns = { user_id };
        SQLiteDatabase db = this.getReadableDatabase();

        String Selection = user_EMAIL + " = ? " + " AND " + user_PASS + " = ?";

        String[] SelectionArgs = { Email , Password };

        Cursor cursor = db.query(
                USER_TABLE,
                columns,
                Selection,
                SelectionArgs,
                null,
                null,
                null);

        int cursor_count = cursor.getCount();

        cursor.close();
        db.close();

        if(cursor_count > 0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
}
