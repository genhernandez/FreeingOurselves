package com.hfad.freeingourselves;


import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends Activity {

    final static String FAVE_NUM = "favorite_num";
    int workoutNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //Get the workout from the intent
        workoutNum = (Integer) getIntent().getExtras().get(FAVE_NUM);

        // Create a cursor and fill everything in.
        Object[] specficWorkoutParams = {workoutNum};
        new GetSpecificWorkoutTask().execute(specficWorkoutParams);
    }

    public void onFavoriteClicked(View view) {
        workoutNum = (Integer) getIntent().getExtras().get(FAVE_NUM);
        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
        Object[] workoutFavoriteParams = {workoutNum, favorite.isChecked()};
        new UpdateWorkoutFaveTask().execute(workoutFavoriteParams);
    }

    public void onWorkoutCountClick(View view) {
        Object[] workoutCountParams = {workoutNum};
        new UpdateWorkoutCountTask().execute(workoutCountParams);
    }

    private class GetSpecificWorkoutTask extends AsyncTask<Object, Void, Cursor> {

        protected Cursor doInBackground(Object... params) {
            return FreeingOurselvesDatabaseUtilities.getSpecificWorkout(WorkoutActivity.this, (int) params[0]);
        }

        protected void onPostExecute(Cursor workoutCursor) {
            if (workoutCursor == null) {
                Toast toast = Toast.makeText(WorkoutActivity.this, "Could not get workout", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (workoutCursor.moveToFirst()) {
                    // Get the workout details from the cursor.
                    String nameText = workoutCursor.getString(0);
                    String descriptionText = workoutCursor.getString(1);
                    //String photoId = cursor.getString(2); TODO: change this to int probably
                    int count = workoutCursor.getInt(3);
                    boolean isFavorite = (workoutCursor.getInt(4) == 1);

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

                    workoutCursor.close();
                }
            }
        }
    }

    private class UpdateWorkoutFaveTask extends AsyncTask<Object, Void, Boolean> {

        protected Boolean doInBackground(Object... params) {
            return FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(WorkoutActivity.this, (int) params[0], (boolean) params[1]);
        }

        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(WorkoutActivity.this, "Could not update workout favorite", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private class UpdateWorkoutCountTask extends AsyncTask<Object, Void, Boolean> {

        protected Boolean doInBackground(Object... params) {
            return FreeingOurselvesDatabaseUtilities.updateWorkoutCount(WorkoutActivity.this, (int) params[0]);
        }

        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(WorkoutActivity.this, "Could not update workout count", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}