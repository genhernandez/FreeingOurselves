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
            populateChapters(db);

            // Create workouts table.
            db.execSQL("CREATE TABLE WORKOUTS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "WORKOUT_NAME TEXT, "
                    + "DETAILS TEXT, "
                    + "PICTURE_FILE TEXT"
                    + "COUNT INTEGER);");
            populateWorkouts(db);

            // Create healthcare table.
            db.execSQL("CREATE TABLE HEALTHCARE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "STEP_INFO TEXT, "
                    + "NOTES TEXT, "
                    + "COMPLETED INTEGER);");
            populateHealthcare(db);

            // Create challenges table.
            db.execSQL("CREATE TABLE CHALLENGES ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "THREAT TEXT, "
                    + "SIGNS TEXT, "
                    + "ASK_WHEN TEXT, "
                    + "STRATEGIES TEXT);");
            populateChallenges(db);

            // Create goals table.
            db.execSQL("CREATE TABLE GOALS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "GOAL_NAME TEXT, "
                    + "INPUTS TEXT, "
                    + "ACTIVITIES TEXT, "
                    + "ASSUMPTIONS TEXT, "
                    + "SHORT_TERM TEXT, "
                    + "LONG_TERM TEXT, "
                    + "GOAL TEXT, "
                    + "COMPLETED INTEGER);");
            populateGoals(db);
        }
    }

    private void populateChapters(SQLiteDatabase db) {
        ContentValues chapterOneValues = new ContentValues();
        chapterOneValues.put("CHAPTER_NUM", 1);
        chapterOneValues.put("TITLE", "Chapter 1");
        chapterOneValues.put("CONTENT", "hey it's chapter 1");
        chapterOneValues.put("FAVE", 0);
        chapterOneValues.put("LANG", "en");
        db.insert(CHAPTERS, null, chapterOneValues);
    }

    private void populateWorkouts(SQLiteDatabase db) {
        ContentValues chapterOneValues = new ContentValues();
        chapterOneValues.put("CHAPTER_NUM", 1);
        chapterOneValues.put("TITLE", "Chapter 1");
        chapterOneValues.put("CONTENT", "hey it's chapter 1");
        chapterOneValues.put("FAVE", 0);
        chapterOneValues.put("LANG", "en");
        db.insert(WORKOUTS, null, chapterOneValues);
    }

    private void populateHealthcare(SQLiteDatabase db) {
        ContentValues chapterOneValues = new ContentValues();
        chapterOneValues.put("CHAPTER_NUM", 1);
        chapterOneValues.put("TITLE", "Chapter 1");
        chapterOneValues.put("CONTENT", "hey it's chapter 1");
        chapterOneValues.put("FAVE", 0);
        chapterOneValues.put("LANG", "en");
        db.insert(HEALTHCARE, null, chapterOneValues);
    }

    private void populateChallenges(SQLiteDatabase db) {
        ContentValues chapterOneValues = new ContentValues();
        chapterOneValues.put("CHAPTER_NUM", 1);
        chapterOneValues.put("TITLE", "Chapter 1");
        chapterOneValues.put("CONTENT", "hey it's chapter 1");
        chapterOneValues.put("FAVE", 0);
        chapterOneValues.put("LANG", "en");
        db.insert(CHAPTERS, null, chapterOneValues);
    }

    private void populateGoals(SQLiteDatabase db) {

    }
}
