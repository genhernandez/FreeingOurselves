package com.hfad.freeingourselves;

import android.database.sqlite.SQLiteDatabase;


import java.lang.reflect.Array;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

// If you call one of these methods, use an AsyncTask
final public class FreeingOurselvesDatabaseUtilities {

    private FreeingOurselvesDatabaseUtilities() {
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static boolean isFavorite(SQLiteDatabase db, int chapterNum) {
        return false;
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
    static String getGoalData(SQLiteDatabase db, String goalname) {
        return "";
    }

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static boolean goalCompleted(SQLiteDatabase db, String goalname) {
        return false;
    }

    static void setGoalData(SQLiteDatabase db, String goalname, String inputs, String activities, String assumptions, String short_term, String long_term, String goal, boolean completed) {
    }

    static String getResources(SQLiteDatabase db, String resource) {
        return "";
    }

    static ArrayList<String> getTopics(SQLiteDatabase db) {
        ArrayList<String> titles = new ArrayList<>();
        String[] columns = new String[]{"TITLE"};
        Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.TOPICS, columns, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            titles.add(cursor.getString(0));

    // Returns null and logs if it fails, so make sure to deal with the null possibility when you call it
    static void setGoalData(SQLiteDatabase db, String goalname, String inputs, String activities, String assumptions, String short_term, String long_term, String goal, boolean completed) {
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

        ArrayList<String> workouts = new ArrayList<>();
        String[] columns = new String[]{"NAME"};
        Cursor cursor = db.query(FreeingOurselvesDatabaseHelper.WORKOUTS, columns, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            workouts.add(cursor.getString(0));

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
        ArrayList<String> healthQuestions = new ArrayList<>();
        Cursor cursor = db.query("HEALTHCARE",
                new String[]{"STEP_INFO", "NOTES", "COMPLETED"},
                "_id=?",
                null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            healthQuestions.add(cursor.getString(0) + " " + cursor.getString(2));
        }
        cursor.close();
        return healthQuestions;
    }
}


//public class DrinkActivity extends Activity {
//
//    public static final String EXTRA_DRINKNO = "drinkNo";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_drink);
//
//        //Get the drink from the intent
//        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
//
//        // Create a cursor.
//        try {
//            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
//            SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
//            Cursor cursor = db.query("DRINK",
//                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE", "CALORIES"},
//                    "_id = ?",
//                    new String[]{Integer.toString(drinkNo)},
//                    null, null, null);
//
//            // Move to the first record in the Cursor.
//            if (cursor.moveToFirst()) {
//                // Get the drink details from the cursor.
//                String nameText = cursor.getString(0);
//                String descriptionText = cursor.getString(1);
//                int photoId = cursor.getInt(2);
//                boolean isFavorite = (cursor.getInt(3) == 1);
//                int calories = cursor.getInt(4);
//
//                // Populate the drink name.
//                TextView name = (TextView) findViewById(R.id.name);
//                name.setText(nameText + " (" + calories + ")");
//
//                // Populate the drink image.
//                ImageView photo = (ImageView) findViewById(R.id.photo);
//                photo.setImageResource(photoId);
//                photo.setContentDescription(nameText);
//
//                // Populate the drink description.
//                TextView description = (TextView) findViewById(R.id.description);
//                description.setText(descriptionText);
//
//                // Populate the favorite checkbox.
//                CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
//                favorite.setChecked(isFavorite);
//            } else {
//                Toast toast = Toast.makeText(this, "Drink not found", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//            cursor.close();
//            db.close();
//        } catch (SQLiteException e) {
//            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
//
//    public void onFavoriteClicked(View view) {
//        int drinkNo = (Integer) getIntent().getExtras().get("drinkNo");
//        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
//        ContentValues drinkValues = new ContentValues();
//        drinkValues.put("FAVORITE", favorite.isChecked());
//        Object[] array = new Object[2];
//        array[0] = drinkNo;
//        array[1] = drinkValues;
//        new UpdateDrinkTask().execute(array);
//    }
//
//    private class UpdateDrinkTask extends AsyncTask<Object, Void, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(Object... drinks) {
//            int drinkNo = (int)drinks[0];
//            ContentValues drinkValues = (ContentValues)drinks[1];
//            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);
//            try {
//                SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
//                db.update("DRINK", drinkValues, "_id = ?", new String[] {Integer.toString(drinkNo)});
//                db.close();
//                return true;
//            } catch (SQLException e) {
//                return false;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Boolean success) {
//            if (!success) {
//                Toast toast = Toast.makeText(DrinkActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        }
//    }
//}
//

}

