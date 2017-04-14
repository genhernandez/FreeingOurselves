package com.hfad.freeingourselves;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FreeingOurselvesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "freeing_ourselves";
    private static final int DB_VERSION = 0;

    // Table names
    private static final String CHAPTERS = "CHAPTERS";
    private static final String WORKOUTS = "WORKOUTS";
    private static final String HEALTHCARE = "HEALTHCARE";
    private static final String CHALLENGES = "CHALLENGES";
    private static final String GOALS = "GOALS";

    FreeingOurselvesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDataBase(db, oldVersion, newVersion);
    }

    private void updateMyDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            // Create chapters table.
            db.execSQL("CREATE TABLE CHAPTERS ("
                    + "CHAPTER_NUM INTEGER, "
                    + "TITLE TEXT, "
                    + "CONTENT TEXT, "
                    + "FAVE INTEGER, "
                    + "LANG TEXT);");
            ContentValues chapterOneValues = new ContentValues();
            chapterOneValues.put("CHAPTER_NUM", 1);
            chapterOneValues.put("TITLE", "Chapter 1");
            chapterOneValues.put("CONTENT", "hey it's chapter 1");
            chapterOneValues.put("FAVE", 0);
            chapterOneValues.put("LANG", "en");
            db.insert(CHAPTERS, null, chapterOneValues);

            // Create workouts table.
            db.execSQL("CREATE TABLE WORKOUTS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "WORKOUT_NAME TEXT, "
                    + "DETAILS TEXT, "
                    + "PICTURE_FILE TEXT"
                    + "COUNT INTEGER);");

            // Create healthcare table.
            db.execSQL("CREATE TABLE HEALTHCARE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "STEP_INFO TEXT, "
                    + "NOTES TEXT, "
                    + "COMPLETED INTEGER);");

            // Create challenges table.
            db.execSQL("CREATE TABLE CHALLENGES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "THREAT TEXT, "
                    + "SIGNS TEXT, "
                    + "ASK_WHEN TEXT, "
                    + "STRATEGIES TEXT);");

            // Create goals table.
            db.execSQL("CREATE TABLE GOALS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "GOAL_NAME TEXT, "
                    + "INPUTS TEXT, "
                    + "ACTIVITIES TEXT, "
                    + "ASSUPTIONS TEXT, "
                    + "SHORT_TERM TEXT, "
                    + "LONG_TERM TEXT, "
                    + "GOAL TEXT, "
                    + "COMPLETED INTEGER);");
        }
    }

//    private static void insertDrink(SQLiteDatabase db, String name,
//                                    String description, int resourceId) {
//        ContentValues drinkValues = new ContentValues();
//        drinkValues.put("NAME", name);
//        drinkValues.put("DESCRIPTION", description);
//        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
//        db.insert("DRINK", null, drinkValues);
//    }
//
//    private static void insertDrink(SQLiteDatabase db, String name,
//                                    String description, int resourceId, int calories) {
//        ContentValues drinkValues = new ContentValues();
//        drinkValues.put("NAME", name);
//        drinkValues.put("DESCRIPTION", description);
//        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
//        drinkValues.put("CALORIES", calories);
//        db.insert("DRINK", null, drinkValues);
//    }
}
