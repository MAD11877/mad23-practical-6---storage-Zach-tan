package sg.edu.np.mad.madprac2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    String title = "MyDBhandler";

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "accountDB.db";
    public static String USER = "User";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "ID";
    public static String COLUMN_FOLLOWED = "Followed";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNT_TABLE = "Create TABLE " + USER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_FOLLOWED + " INTEGER)";
        Log.i(title, CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        onCreate(db);
    }

    public void addUser(User userData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, userData.getName());
        values.put(COLUMN_DESCRIPTION, userData.getDescription());
        values.put(COLUMN_FOLLOWED, userData.isFollowed() ? 1 : 0);

        SQLiteDatabase db = this. getWritableDatabase();
        db.insert(USER, null, values);
        Log.i(title, " Inserted/Created user" + values.toString());
        db.close();
    }

    public void updateUser(User userData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, userData.getName());
        values.put(COLUMN_DESCRIPTION, userData.getDescription());
        values.put(COLUMN_FOLLOWED, (!(userData.isFollowed()) ? 1 : 0));

        db.update(USER, values,COLUMN_ID + "=?", new String[]{String.valueOf(userData.getId())});
        Log.i(title, "Updated Task");
        db.close();
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER,null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                int followedInt = cursor.getInt(3);
                boolean followed = followedInt == 1;

                User user = new User(name, desc, id, followed);
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }
    public User findUser(int id){
        String query = "SELECT * FROM " + USER + " WHERE " + COLUMN_ID + "=\'" + id + "\'";
        Log.i(title, "Query :" + query);

        User queryResult = new User();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            queryResult.setId(cursor.getInt(0));
            queryResult.setName(cursor.getString(1));
            queryResult.setDescription(cursor.getString(2));
            queryResult.setFollowed(cursor.getInt(3) == 1);
            cursor.close();
        }
        else{
            queryResult = null;
        }
        db.close();
        return queryResult;
    }
}

