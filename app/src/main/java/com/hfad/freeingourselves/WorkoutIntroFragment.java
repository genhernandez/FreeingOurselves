package com.hfad.freeingourselves;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
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

//intro to workouts and a list of workouts
public class WorkoutIntroFragment extends Fragment {


    public WorkoutIntroFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_intro, container, false);

        //TODO: where did the intro paragraph go
        // Populate the list_favorites ListView from a cursor.
        ListView listFavorites = (ListView) view.findViewById(R.id.workout_list);

        Cursor cursor = FreeingOurselvesDatabaseUtilities.getWorkoutNames(view.getContext());
        CursorAdapter workoutAdapter = new SimpleCursorAdapter(view.getContext(),
                    android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);
        listFavorites.setAdapter(workoutAdapter);


        //TODO: this is broken
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
}

