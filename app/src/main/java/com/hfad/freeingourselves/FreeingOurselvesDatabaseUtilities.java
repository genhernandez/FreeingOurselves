package com.hfad.freeingourselves;

import android.database.sqlite.SQLiteDatabase;


import java.lang.reflect.Array;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

// If you call one of these methods, use an AsyncTask
final public class FreeingOurselvesDatabaseUtilities {

    private FreeingOurselvesDatabaseUtilities() {
    }


    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static Cursor getFaveTopics(SQLiteDatabase db) {
        try {
            ArrayList<String> faveTopics = new ArrayList<>();
            return db.query(FreeingOurselvesDatabaseHelper.TOPICS, new String[]{"_id", "TITLE"}, "FAVE = 1",
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getFaveTopics", "topics table error");
            return null;
        }
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static int getWorkoutCount(SQLiteDatabase db, String workout) {
        return 0;
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static boolean isCompleted(SQLiteDatabase db, int stepId) {
        return false;
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static String getNotes(SQLiteDatabase db, int stepId) {
        return "";
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static ArrayList<String> getResources(SQLiteDatabase db) {
        try {
            ArrayList<String> resources = new ArrayList<>();
            String[] columns = new String[]{"RESOURCE_NAME", "RESOURCE_DESCRIPTION"};
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.RESOURCES, columns, null, null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                //Add name plus the description to the array list
                resources.add(cursor.getString(0) + " - " + cursor.getString(1));
            }
            cursor.close();
            return resources;
        } catch (SQLiteException e) {
            Log.d("getResources", "resources table error");
            return null;
        }
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static ArrayList<String> getTopics(SQLiteDatabase db) {
        try {
            ArrayList<String> titles = new ArrayList<>();
            String[] columns = new String[]{"TITLE"};
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.TOPICS, columns, null, null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                titles.add(cursor.getString(0));
            }
            cursor.close();
            return titles;
        } catch (SQLiteException e) {
            Log.d("getWorkoutsNames", "workout table error");
            return null;
        }
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static ArrayList<String> getWorkoutNames(SQLiteDatabase db) {
        try {
            ArrayList<String> workouts = new ArrayList<>();
            String[] columns = new String[]{"NAME"};
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.WORKOUTS, columns, null, null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                workouts.add(cursor.getString(0));
            }
            cursor.close();
            return workouts;
        } catch (SQLiteException e) {
            Log.d("getWorkoutsNames", "workout table error");
            return null;
        }
    }

    static ArrayList<String> getHealthCareQuestions(SQLiteDatabase db) {
        try {
            ArrayList<String> healthQuestions = new ArrayList<>();
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE,
                    new String[]{"STEP_INFO", "NOTES", "SAVED"},
                    "_id=?",
                    null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                healthQuestions.add(cursor.getString(0) + " " + cursor.getString(2));
            }
            cursor.close();
            return healthQuestions;
        } catch (SQLiteException e) {
            Log.d("getHealthCareQuestions", "healthcare table error");
            return null;
        }
    }
}