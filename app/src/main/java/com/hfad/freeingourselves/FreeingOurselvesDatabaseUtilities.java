package com.hfad.freeingourselves;

import android.database.sqlite.SQLiteDatabase;

public class FreeingOurselvesDatabaseUtilities {

    boolean isFavorite(SQLiteDatabase db, int chapterNum) {
        return false;
    }

    int getWorkoutCount(SQLiteDatabase db, String workout) {
        return 0;
    }

    boolean isCompleted(SQLiteDatabase db, int stepId) {
        return false;
    }

    String getNotes(SQLiteDatabase db, int stepId) {
        return "";
    }

    String getGoalData(SQLiteDatabase db, String goalname) {
        return "";
    }

    boolean goalCompleted(SQLiteDatabase db, String goalname) {
        return false;
    }

    void setGoalData(SQLiteDatabase db, String goalname, String inputs, String activities, String assumptions, String short_term, String long_term, String goal, boolean completed) {}
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
