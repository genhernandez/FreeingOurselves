package edu.mills.freeingourselves;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Provides a simple interface to the database. Activities should call the methods in this class
 * rather than accessing the database directly.
 */
final class FreeingOurselvesDatabaseUtilities {

    private FreeingOurselvesDatabaseUtilities() {
    }

    // Resources methods

    /**
     * Gets a list of the resources. Each String contains the resource name followed by " - " and
     * the resource description. If a database error occurs, null is returned; the caller will
     * need to handle this case.
     *
     * @param context the context
     * @return list of resources or null if there is an error
     */
    static ArrayList<String> getResources(Context context) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            ArrayList<String> resources = new ArrayList<>();
            String[] columns = new String[]{FreeingOurselvesDatabaseHelper.NAME,
                    FreeingOurselvesDatabaseHelper.CONTENT};
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.RESOURCES, columns,
                    null, null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                //Add name plus the description to the array list
                resources.add(cursor.getString(0) + " - " + cursor.getString(1));
            }
            cursor.close();
            return resources;
        } catch (SQLiteException e) {
            Log.d("getResources", "ers table error");
            return null;
        }
    }

    /**
     * Gets the link of a resource based on its position. If a database error occurs, null is returned; the caller will
     * need to handle this case.
     *
     * @param context the context
     * @param id      the resource id
     * @return resource link or null if there is an error
     */
    static String getResourceLink(Context context, int id) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            String[] columns = new String[]{FreeingOurselvesDatabaseHelper.LINK};
            String[] where = new String[]{"" + (id + 1) + ""};
            String resourceLink = null;
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.RESOURCES, columns,
                    FreeingOurselvesDatabaseHelper.ID + " = ?", where, null, null, null);
            if (cursor.moveToFirst()) {
                resourceLink = cursor.getString(0);
                Log.v("MainActivity Resource", resourceLink);
            }
            cursor.close();
            db.close();
            return resourceLink;
        } catch (SQLiteException e) {
            Log.d("getResourceLink", "ers table error");
            return null;
        }
    }

    // Workout methods

    /**
     * Gets a list of the workout names. If a database error occurs, null is returned; the caller
     * will need to handle this case.
     *
     * @param context the context
     * @return list of workout names or null if there is an error
     */
    static ArrayList<String> getWorkoutNames(Context context) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            ArrayList<String> names = new ArrayList<>();
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.WORKOUTS,
                    new String[]{FreeingOurselvesDatabaseHelper.NAME}, null, null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                names.add(cursor.getString(0));
            }
            cursor.close();
            return names;
        } catch (SQLiteException e) {
            Log.d("getWorkoutNames", "workout table error");
            return null;
        }
    }

    /**
     * Gets all information for a specific workout. The columns of the returned cursor are:
     * <ol>
     * <li>name (String)</li>
     * <li>details (String)</li>
     * <li>picture_file (String)</li>
     * <li>count (int)</li>
     * <li>fave (int, 0 or 1)</li>
     * </ol>
     * The caller is responsible for closing the returned cursor. If a database error occurs, null
     * is returned; the caller will need to handle this case.
     *
     * @param context the context
     * @param id      the workout id
     * @return all information for a specific workout or null if there is an error
     */
    static Cursor getSpecificWorkout(Context context, int id) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            return db.query(FreeingOurselvesDatabaseHelper.WORKOUTS,
                    new String[]{FreeingOurselvesDatabaseHelper.NAME,
                            FreeingOurselvesDatabaseHelper.CONTENT,
                            FreeingOurselvesDatabaseHelper.PICTURE,
                            FreeingOurselvesDatabaseHelper.COUNT,
                            FreeingOurselvesDatabaseHelper.FAVE},
                    FreeingOurselvesDatabaseHelper.ID + " = ?", new String[]{Integer.toString(id)},
                    null, null, null);
        } catch (SQLiteException e) {
            Log.d("getWorkouts", "workout table error");
            return null;
        }
    }

    /**
     * Updates the favorite column for a specific workout. If a database error occurs, false is
     * returned; the caller will need to handle this case.
     *
     * @param context    the context
     * @param id         the workout ID
     * @param isFavorite whether the workout is favorited
     * @return true if successful, false otherwise
     */
    static boolean updateWorkoutFavorite(Context context, int id, boolean isFavorite) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            ContentValues workoutValues = new ContentValues();
            if (isFavorite) {
                workoutValues.put(FreeingOurselvesDatabaseHelper.FAVE, 1);
            } else {
                workoutValues.put(FreeingOurselvesDatabaseHelper.FAVE, 0);
            }
            db.update(FreeingOurselvesDatabaseHelper.WORKOUTS, workoutValues,
                    FreeingOurselvesDatabaseHelper.ID + " = ?",
                    new String[]{Integer.toString(id)});
            db.close();
            return true;
        } catch (SQLiteException e) {
            Log.d("updateWorkoutFavorite", "workout table error");
            return false;
        }
    }

    /**
     * Updates the count for a specific workout. If a database error occurs, false is returned; the
     * caller will need to handle this case.
     *
     * @param context the context
     * @param id      the workout ID
     * @return true if successful, false otherwise
     */
    static boolean updateWorkoutCount(Context context, int id) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            Cursor workoutCursor = db.query(FreeingOurselvesDatabaseHelper.WORKOUTS,
                    new String[]{FreeingOurselvesDatabaseHelper.COUNT},
                    FreeingOurselvesDatabaseHelper.ID + " = ?", new String[]{Integer.toString(id)},
                    null, null, null);
            if (workoutCursor.moveToFirst()) {
                int workoutCount = workoutCursor.getInt(0);
                ContentValues workoutValues = new ContentValues();
                workoutValues.put(FreeingOurselvesDatabaseHelper.COUNT, ++workoutCount);
                db.update(FreeingOurselvesDatabaseHelper.WORKOUTS, workoutValues, FreeingOurselvesDatabaseHelper.ID + " = ?",
                        new String[]{Integer.toString(id)});
                db.close();
                return true;
            } else {
                Log.d("updateWorkoutCount", "nothing in cursor");
                db.close();
                return false;
            }
        } catch (SQLiteException e) {
            Log.d("updateWorkoutCount", "workout table error");
            return false;
        }
    }

    /**
     * Gets all information for all favorite workouts. The columns of the returned cursor are:
     * <ol>
     * <li>id (int)</li>
     * <li>name (String)</li>
     * </ol>
     * The caller is responsible for closing the returned cursor. If a database error occurs, null
     * is returned; the caller will need to handle this case.
     *
     * @param context the context
     * @return all information for all favorite workouts or null if there is an error
     */
    static Cursor getFaveWorkouts(Context context) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            return db.query(FreeingOurselvesDatabaseHelper.WORKOUTS,
                    new String[]{FreeingOurselvesDatabaseHelper.ID, FreeingOurselvesDatabaseHelper.NAME},
                    FreeingOurselvesDatabaseHelper.FAVE + " = 1",
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getFaveWorkouts", "topics table error");
            return null;
        }
    }

    // Healthcare methods

    /**
     * Gets a list of the healthcare questions. If a database error occurs, null is returned; the
     * caller will need to handle this case.
     *
     * @param context the context
     * @return list of healthcare questions or null if there is an error
     */
    static ArrayList<String> getHealthCareQuestions(Context context) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            ArrayList<String> questions = new ArrayList<>();
            String[] columns = new String[]{FreeingOurselvesDatabaseHelper.NAME};
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE, columns,
                    null, null, null, null, null);
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
     * Gets all information for a specific healthcare question. The columns of the returned cursor are:
     * <ol>
     * <li>step_info (String)</li>
     * <li>notes (String)</li>
     * <li>saved (int, 0 or 1)</li>
     * </ol>
     * The caller is responsible for closing the returned cursor. If a database error occurs, null
     * is returned; the caller will need to handle this case.
     *
     * @param context the context
     * @param id      the question id
     * @return all information for a specific healthcare question or null if there is an error
     */
    static Cursor getSpecificHealthcareQuestion(Context context, int id) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            return db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE,
                    new String[]{FreeingOurselvesDatabaseHelper.NAME, FreeingOurselvesDatabaseHelper.NOTES,
                            FreeingOurselvesDatabaseHelper.FAVE},
                    FreeingOurselvesDatabaseHelper.ID + " = ?",
                    new String[]{Integer.toString(id)}, null, null, null);
        } catch (SQLiteException e) {
            Log.d("SpecificHealthcare", "health table error");
            return null;
        }
    }

    /**
     * Checks whether a healthcare question is marked as saved. If a database error occurs, a "health
     * table error" message is logged; the caller will need to handle this case.
     *
     * @param context the context
     * @param id      the healthcare question ID
     * @return true if saved, false if not
     */
    static boolean healthcareIsSaved(Context context, int id) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE,
                    new String[]{FreeingOurselvesDatabaseHelper.FAVE},
                    FreeingOurselvesDatabaseHelper.ID + " = ?",
                    new String[]{Integer.toString(id)}, null, null, null);
            cursor.moveToFirst();
            return cursor.getInt(0) == 1;
        } catch (SQLiteException e) {
            Log.d("SpecificHealthcare", "health table error");
            return false;
        }
    }

    /**
     * Updates the note column for a specific healthcare question. If a database error occurs,
     * false is returned; the caller will need to handle this case.
     *
     * @param context the context
     * @param id      the healthcare question ID
     * @param notes   the notes to add
     * @return true if successful, false otherwise
     */
    static boolean updateNotes(Context context, int id, String notes) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getWritableDatabase();
            ContentValues healthcareValues = new ContentValues();
            healthcareValues.put(FreeingOurselvesDatabaseHelper.NOTES, notes);
            db.update(FreeingOurselvesDatabaseHelper.HEALTHCARE, healthcareValues,
                    FreeingOurselvesDatabaseHelper.ID + " = ?", new String[]{Integer.toString(id)});

            // log tests
            Log.d("db updateNotes", "healthCareValues notes = " +
                    healthcareValues.get(FreeingOurselvesDatabaseHelper.NOTES));
            String[] columns = new String[]{FreeingOurselvesDatabaseHelper.NOTES};
            String[] where = new String[]{"" + (id + 1) + ""};
            Log.d("db updateNotes", "db notes = " + db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE, columns,
                    FreeingOurselvesDatabaseHelper.ID + " = ?", where, null, null, null).moveToFirst());

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
     * @param context the context
     * @param id      the healthcare question ID
     * @param isSaved whether the workout is favorited
     * @return true if successful, false otherwise
     */
    static boolean updateSaved(Context context, int id, boolean isSaved) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            ContentValues healthcareValues = new ContentValues();
            if (isSaved) {
                healthcareValues.put(FreeingOurselvesDatabaseHelper.FAVE, 1);
            } else {
                healthcareValues.put(FreeingOurselvesDatabaseHelper.FAVE, 0);
            }
            db.update(FreeingOurselvesDatabaseHelper.HEALTHCARE, healthcareValues,
                    FreeingOurselvesDatabaseHelper.ID + " = ?", new String[]{Integer.toString(id)});
            db.close();
            return true;
        } catch (SQLiteException e) {
            Log.d("updateSaved", "healthcare table error");
            return false;
        }
    }

    /**
     * Gets all information for all favorite healthcare questions. The columns of the returned cursor are:
     * <ol>
     * <li>id (int)</li>
     * <li>step_info (String)</li>
     * <li>notes (String)</li>
     * </ol>
     * The caller is responsible for closing the returned cursor. If a database error occurs, null
     * is returned; the caller will need to handle this case.
     *
     * @param context the context
     * @return all information for all favorite healthcare questions or null if there is an error
     */
    static Cursor getSavedHealthcare(Context context) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(context);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            return db.query(FreeingOurselvesDatabaseHelper.HEALTHCARE,
                    new String[]{FreeingOurselvesDatabaseHelper.ID,
                            FreeingOurselvesDatabaseHelper.NAME, FreeingOurselvesDatabaseHelper.NOTES},
                    FreeingOurselvesDatabaseHelper.FAVE + " = 1",
                    null, null, null, null);
        } catch (SQLiteException e) {
            Log.d("getSavedHealthcare", "healthcare table error");
            return null;
        }
    }
}