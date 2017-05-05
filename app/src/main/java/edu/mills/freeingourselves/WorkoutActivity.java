package edu.mills.freeingourselves;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Displays information for a selected workout. Users can use this fragment to learn about a workout
 * and keep track of how many times they have completed that workout.
 */
public class WorkoutActivity extends MainActivity {

    final static String FAVE_NUM = "favorite_num";
    int workoutNum;
    int workoutCount;
    TextView countText;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_workout);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Get the workout from the intent
        workoutNum = (Integer) getIntent().getExtras().get(FAVE_NUM);

        countText = (TextView) findViewById(R.id.workoutCount);

        View contentView = inflater.inflate(R.layout.activity_workout, null, false);
        drawerLayout.addView(contentView, 0);

        displayWorkout();
    }

    private void displayWorkout() {
        Object[] specificWorkoutParams = {workoutNum};
        new GetSpecificWorkoutTask().execute(specificWorkoutParams);
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
        workoutCount++;
        //setTimesCompleted();
        Toast toast = Toast.makeText(WorkoutActivity.this, "Good job!", Toast.LENGTH_SHORT);
        toast.show();
    }

    //private void setTimesCompleted() {
        //countText.setText("Completed " + workoutCount + ((workoutCount == 1) ? " time!" : " times!")); //TODO: this should be a constant?
    //}

    private class GetSpecificWorkoutTask extends AsyncTask<Object, Void, Boolean> {

        protected Boolean doInBackground(Object... params) {
            cursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(WorkoutActivity.this, (int) params[0]);
            if (cursor == null) {
                return false;
            } else {
                return true;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (success) {
                if (cursor.moveToFirst()) {
                    // Get the workout details from the cursor.
                    String name = cursor.getString(0);
                    String description = cursor.getString(1);
                    //String photoId = cursor.getString(2); TODO: change this to int probably
                    workoutCount = cursor.getInt(3);
                    boolean isFavorite = (cursor.getInt(4) == 1);

                    // Populate the workout name.
                    TextView nameText = (TextView) findViewById(R.id.workoutTitle);
                    nameText.setText(name);

                    //setTimesCompleted();

                    // Populate the workout image. TODO: deal with pictures
//                ImageView photo = (ImageView) findViewById(R.id.photo);
//                photo.setImageResource(photoId);
//                photo.setContentDescription(nameText);

                    // Populate the workout description.
                    TextView descriptionText = (TextView) findViewById(R.id.workoutDescription);
                    descriptionText.setText(description);

                    // Populate the favorite checkbox.
                    CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
                    favorite.setChecked(isFavorite);
                }
            } else {
                Toast toast = Toast.makeText(WorkoutActivity.this, "Could not get workout", Toast.LENGTH_SHORT);
                toast.show();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }
}