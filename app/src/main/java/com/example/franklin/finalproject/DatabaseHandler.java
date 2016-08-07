package com.example.franklin.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franklin on 12/13/2015.
 */

/**
 * DatabaseHandler class which controls the functions of the database
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "liftsManager";
    //Table name
    private static final String TABLE_LIFTS = "lifts";
    //Table columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_REPS = "reps";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_DATE = "date";

    /**
     * Constructor
     * @param context
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the database when the class is created
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LIFTS_TABLE = "CREATE TABLE " + TABLE_LIFTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_REPS + " TEXT," + KEY_WEIGHT + " TEXT," + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_LIFTS_TABLE);
    }

    /**
     * creates the database on upgrade
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIFTS);

        onCreate(db);
    }

    /**
     * adds a Lift object to the database
     * @param lift
     */
    public void addLift(Lift lift) {
        SQLiteDatabase db = this.getWritableDatabase();

        //puts the attributes of the object into content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, lift.getName());
        values.put(KEY_REPS, lift.getReps());
        values.put(KEY_WEIGHT, lift.getWeight());
        values.put(KEY_DATE, lift.getDate());

        //puts content values into database
        db.insertWithOnConflict(TABLE_LIFTS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    /**
     * retrieves the Lift object from the database based on the ID
     * @param id
     * @return
     */
    public Lift getLift(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //cursor
        Cursor cursor = db.query(TABLE_LIFTS, new String[]{KEY_ID, KEY_NAME, KEY_REPS, KEY_WEIGHT, KEY_DATE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Lift lift = new Lift(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), cursor.getString(4));
        return lift;
    }

    /**
     * returns an ArrayList of all the List objects in the database
     * @return
     */
    public ArrayList<Lift> getAllLifts() {
        ArrayList<Lift> liftList = new ArrayList<Lift>();

        String selectQuery = "SELECT  * FROM " + TABLE_LIFTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //creates Lift object to put in array list
        if (cursor.moveToFirst()) {
            do {
                Lift lift = new Lift();
                lift.setID(Integer.parseInt(cursor.getString(0)));
                lift.setName(cursor.getString(1));
                lift.setReps(Integer.parseInt(cursor.getString(2)));
                lift.setWeight(Integer.parseInt(cursor.getString(3)));
                lift.setDate(cursor.getString(4));
                liftList.add(lift);
            } while (cursor.moveToNext());
        }

        return liftList;
    }

    /**
     * deletes the Lift object from the database based on ID
     * @param lift
     */
    public void deleteLift(Lift lift) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIFTS, KEY_ID + " = ?",
                new String[]{String.valueOf(lift.getID())});
        db.close();
    }
}
