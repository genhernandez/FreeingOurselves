package edu.mills.freeingourselves;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Displays favorited workouts.
 */
public class FavoriteWorkoutsFragment extends Fragment {

    protected View view;
    protected ListView listFavorites;
    protected TextView noFaveWorkouts;
    Cursor cursor;

    public FavoriteWorkoutsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite_workouts, container, false);

        // Populate the list_favorites ListView from a cursor.
        listFavorites = (ListView) view.findViewById(R.id.fave_workout_list);
        noFaveWorkouts = (TextView) view.findViewById(R.id.no_fave_workouts_text);

        new GetFavoriteWorkoutNamesTask().execute(view.getContext());


        // Navigate to WorkoutActivity if a drink is clicked.
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(v.getContext(), WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.FAVE_NUM, (int) id);
                startActivity(intent);
            }
        });

        return view;
    }

    private class GetFavoriteWorkoutNamesTask extends AsyncTask<Context, Void, Boolean> {

        protected Boolean doInBackground(Context... context) {
            cursor = FreeingOurselvesDatabaseUtilities.getFaveWorkouts(context[0]);
            if (cursor == null) {
                return false;
            } else {
                return true;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (success) {
                if (cursor.moveToFirst()) {
                    CursorAdapter workoutsAdapter = new SimpleCursorAdapter(view.getContext(),
                            android.R.layout.simple_list_item_1, cursor,
                            new String[]{FreeingOurselvesDatabaseHelper.NAME},
                            new int[]{android.R.id.text1}, 0);
                    listFavorites.setAdapter(workoutsAdapter);
                } else { // If no favorite workouts, display text saying so.
                    noFaveWorkouts.setVisibility(View.VISIBLE);
                }
            } else {
                Toast toast = Toast.makeText(view.getContext(), "Could not get workouts", Toast.LENGTH_SHORT);
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