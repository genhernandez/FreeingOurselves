package com.hfad.freeingourselves;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class WorkoutActivity extends Activity {

    final static String FAVE_NUM = "favorite_num";
    int workoutNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_workout_activity);

        //Get the workout from the intent
        workoutNum = (Integer) getIntent().getExtras().get(FAVE_NUM);

        // Create a cursor.
        Cursor cursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(this, workoutNum);

            // Move to the first record in the Cursor.
            if (cursor.moveToFirst()) {
                // Get the workout details from the cursor.
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                //String photoId = cursor.getString(2); TODO: change this to int probably
                int count = cursor.getInt(3);
                boolean isFavorite = (cursor.getInt(4) == 1);

                // Populate the workout name.
                TextView name = (TextView) findViewById(R.id.workoutTitle);
                name.setText(nameText + ": " + count);

                // Populate the workout image. TODO: deal with pictures
//                ImageView photo = (ImageView) findViewById(R.id.photo);
//                photo.setImageResource(photoId);
//                photo.setContentDescription(nameText);

                // Populate the workout description.
                TextView description = (TextView) findViewById(R.id.workoutDescription);
                description.setText(descriptionText);

                // Populate the favorite checkbox.
                CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);

                cursor.close();
            }
    }

        public void onFavoriteClicked(View view) {
            workoutNum = (Integer) getIntent().getExtras().get("drinkNo");
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
            FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(this, workoutNum, favorite.isChecked());
        }
    }



