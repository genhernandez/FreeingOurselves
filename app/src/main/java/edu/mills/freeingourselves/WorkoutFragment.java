package edu.mills.freeingourselves;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Displays a list of workouts.
 */
public class WorkoutFragment extends Fragment {

    private View view;
    private ListView listFavorites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout, container, false);

        listFavorites = (ListView) view.findViewById(R.id.workout_list);

        new GetWorkoutNamesTask().execute(view.getContext());

        // Navigate to WorkoutActivity if a workout is clicked.
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(v.getContext(), WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.FAVE_NUM, (int) id + 1);
                startActivity(intent);
            }
        });
        return view;
    }

    private class GetWorkoutNamesTask extends AsyncTask<Context, Void, ArrayList<String>> {

        protected ArrayList<String> doInBackground(Context... context) {
            return FreeingOurselvesDatabaseUtilities.getWorkoutNames(context[0]);
        }

        protected void onPostExecute(ArrayList<String> workoutList) {
            if (workoutList == null) {
                Toast toast = Toast.makeText(view.getContext(), "Could not get workouts.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                String[] workoutNames = new String[workoutList.size()];
                for (int i = 0; i < workoutList.size(); i++) {
                    workoutNames[i] = workoutList.get(i);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                        android.R.layout.simple_list_item_1, workoutNames);
                listFavorites.setAdapter(adapter);
            }
        }
    }
}