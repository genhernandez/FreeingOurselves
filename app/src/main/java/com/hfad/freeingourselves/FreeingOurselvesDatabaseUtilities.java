package com.hfad.freeingourselves;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

// If you call one of these methods, use an AsyncTask
final public class FreeingOurselvesDatabaseUtilities {

    private FreeingOurselvesDatabaseUtilities() {
    }

    // Topics methods

    /**
     * Gets a list of the topic titles. If a database error occurs, null is returned; the caller
     * will need to handle this case.
     *
     * @param db the database to search
     * @return list of topic titles or null if there is an error
     */
    static ArrayList<String> getTopicTitles(SQLiteDatabase db) {
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

    /**
     * Gets a cursor with only the favorited topics. The cursor contains, in this order, values for
     * int id and String title. If a database error occurs, null is returned; the caller will need
     * to handle this case.
     *
     * @param db the database to search
     * @return a cursor or null if there is an error
     */
    static Cursor getFaveTopics(SQLiteDatabase db) {
        try {
            return db.query(FreeingOurselvesDatabaseHelper.TOPICS, new String[]{"_id", "TITLE"}, "FAVE = 1",
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getFaveTopics", "topics table error");
            return null;
        }
    }

    // Resources methods

    /**
     * Gets a list of the resources. Each String contains the resource name followed by " - " and
     * the resource description. If a database error occurs, null is returned; the caller will
     * need to handle this case.
     *
     * @param db the database to search
     * @return list of resources or null if there is an error
     */
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


    // Workout methods

    /**
     * Gets a list of the workout names. If a database error occurs, null is returned; the caller
     * will need to handle this case.
     *
     * @param db the database to search
     * @return list of workout names or null if there is an error
     */
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

    /**
     * Gets a cursor with all of the data from the workout database table. The cursor contains,
     * in this order, values for int id, String name, String details, String picture file name, and
     * int count. If a database error occurs, null is returned; the caller will need to handle this case.
     *
     * @param db the database to search
     * @return a cursor or null if there is an error
     */
    static Cursor getWorkouts(SQLiteDatabase db) {
        try {
            return db.query(FreeingOurselvesDatabaseHelper.WORKOUTS,
                    new String[]{"_id", "NAME", "DETAILS", "PICTURE_FILE", "COUNT"},
                    null,
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getWorkouts", "workout table error");
            return null;
        }
    }

    /**
     * Updates the favorite column for a specific workout. If a database error occurs, false is
     * returned; the caller will need to handle this case.
     *
     * @param db         the database to update
     * @param id         the workout ID
     * @param isFavorite whether the workout is favorited
     * @return true if successful, false otherwise
     */
    static boolean updateWorkoutFavorite(SQLiteDatabase db, int id, boolean isFavorite) {
        try {
            ContentValues workoutValues = new ContentValues();
            if (isFavorite) {
                workoutValues.put("FAVE", 1);
            } else {
                workoutValues.put("FAVE", 0);
            }
            db.update(FreeingOurselvesDatabaseHelper.WORKOUTS, workoutValues, "_id = ?",
                    new String[]{Integer.toString(id)});
            db.close();
            return true;
        } catch (SQLiteException e) {
            Log.d("updateWorkoutFavorite", "workout table error");
            return false;
        }
    }

    /**
     * Gets a cursor with only the favorited workouts. The cursor contains, in this order, values for
     * int id and String name. If a database error occurs, null is returned; the caller will need
     * to handle this case.
     *
     * @param db the database to search
     * @return a cursor or null if there is an error
     */
    static Cursor getFaveWorkouts(SQLiteDatabase db) {
        try {
            return db.query(FreeingOurselvesDatabaseHelper.WORKOUTS,
                    new String[]{"_id", "NAME"}, "FAVE = 1",
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getFaveWorkouts", "topics table error");
            return null;
        }
    }

    // Healthcare methods

    /**
     * Gets a cursor with all of the data from the healthcare database table. The cursor contains,
     * in this order, values for int id, String question, String user notes, and an int that is
     * either 0 or 1 to represent whether or not the question is marked as saved. If a database
     * error occurs, null is returned; the caller will need to handle this case.
     *
     * @param db the database to search
     * @return a cursor or null if there is an error
     */
    static Cursor getHealthcareInfo(SQLiteDatabase db) {
        try {
            return db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE,
                    new String[]{"_id", "STEP_INFO", "NOTES", "SAVED"},
                    null,
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getHealthcareInfo", "healthcare table error");
            return null;
        }
    }

    /**
     * Gets a list of the healthcare questions. If a database error occurs, null is returned; the
     * caller will need to handle this case.
     *
     * @param db the database to search
     * @return list of healthcare questions or null if there is an error
     */
    static ArrayList<String> getHealthCareQuestions(SQLiteDatabase db) {
        try {
            ArrayList<String> questions = new ArrayList<>();
            String[] columns = new String[]{"STEP_INFO"};
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE, columns, null, null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                questions.add(cursor.getString(0));
            }
            cursor.close();
            return questions;
        } catch (SQLiteException e) {
            Log.d("getHealthCareQuestions", "healthcare table error");
            return null;
        }
    }

    /**
     * Updates the note column for a specific healthcare question. If a database error occurs,
     * false is returned; the caller will need to handle this case.
     *
     * @param db    the database to update
     * @param id    the healthcare question ID
     * @param notes the notes to add
     * @return true if successful, false otherwise
     */
    static boolean updateNotes(SQLiteDatabase db, int id, String notes) {
        try {
            ContentValues healthcareValues = new ContentValues();
            healthcareValues.put("NOTES", notes);
            db.update(FreeingOurselvesDatabaseHelper.HEALTHCARE, healthcareValues, "_id = ?",
                    new String[]{Integer.toString(id)});
            db.close();
            return true;
        } catch (SQLiteException e) {
            Log.d("updateNotes", "healthcare table error");
            return false;
        }
    }

    /**
     * Updates the saved column for a specific healthcare question. If a database error occurs,
     * false is returned; the caller will need to handle this case.
     *
     * @param db      the database to update
     * @param id      the workout ID
     * @param isSaved whether the workout is favorited
     * @return true if successful, false otherwise
     */
    static boolean updateSaved(SQLiteDatabase db, int id, boolean isSaved) {
        try {
            ContentValues healthcareValues = new ContentValues();
            if (isSaved) {
                healthcareValues.put("SAVED", 1);
            } else {
                healthcareValues.put("SAVED", 0);
            }
            db.update(FreeingOurselvesDatabaseHelper.HEALTHCARE, healthcareValues, "_id = ?",
                    new String[]{Integer.toString(id)});
            db.close();
            return true;
        } catch (SQLiteException e) {
            Log.d("updateSaved", "healthcare table error");
            return false;
        }
    }

    /**
     * Gets a cursor with only the saved healthcare questions. The cursor contains, in this order,
     * values for int id, String question, and String user notes. If a database error occurs, null
     * is returned; the caller will need to handle this case.
     *
     * @param db the database to search
     * @return a cursor or null if there is an error
     */
    static Cursor getSavedHealthcare(SQLiteDatabase db) {
        try {
            return db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE,
                    new String[]{"_id", "STEP_INFO", "NOTES"}, "SAVED = 1",
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getSavedHealthcare", "healthcare table error");
            return null;
        }
    }
}