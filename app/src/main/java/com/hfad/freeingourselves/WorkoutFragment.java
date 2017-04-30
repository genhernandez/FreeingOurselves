package com.hfad.freeingourselves;


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
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.hfad.freeingourselves.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment {

    protected View view;
    protected ListView listFavorites;
    protected String[] workoutNames;

    public WorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_workout, container, false);

        // Populate the list_favorites ListView from a cursor.
        listFavorites = (ListView) view.findViewById(R.id.workout_list);

        new GetWorkoutNamesTask().execute(view.getContext());


        // Navigate to WorkoutActivity if a drink is clicked.
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(v.getContext(), WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.FAVE_NUM, (int) id+1);
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
                Toast toast = Toast.makeText(view.getContext(), "Could not get workouts", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                workoutNames = new String[workoutList.size()];
                for (int i = 0; i < workoutList.size(); i++) {
                    workoutNames[i] = workoutList.get(i);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                        android.R.layout.simple_list_item_1, workoutNames);
                listFavorites.setAdapter(adapter);
            }
        }
    }
}