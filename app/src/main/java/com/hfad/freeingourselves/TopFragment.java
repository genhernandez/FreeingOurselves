package com.hfad.freeingourselves;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    View view;
    ListView topicsView;
    ListView workoutsView;
    TextView noFaveWorkouts;
    TextView noFaveTopics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top, container, false);

        topicsView = (ListView) view.findViewById(R.id.favorite_topics_list);
        workoutsView = (ListView) view.findViewById(R.id.favorite_workouts_list);
        noFaveTopics = (TextView) view.findViewById(R.id.no_fave_topics_text);
        noFaveWorkouts = (TextView) view.findViewById(R.id.no_fave_workouts_text);

        //TODO: delete these later
        FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(view.getContext(), 2, true);
        FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(view.getContext(), 4, false);
        FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(view.getContext(), 1, false);
        FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(view.getContext(), 3, true);

        // Show favorite topics
        new GetFaveTopicsTask().execute(view.getContext());

        //Navigate to favorite topic when clicked
        //TODO: broken
        topicsView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                selectTopicItem(position);
            }
        });

        // Show favorite workouts
        new GetFaveWorkoutsTask().execute(view.getContext());

        // Navigate to WorkoutActivity if a workout is clicked.
        //TODO: finish this
        workoutsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(view.getContext(), WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.FAVE_NUM, (int) id);
                startActivity(intent);
            }
        });

        return view;
    }

    private void selectWorkoutItem(int position) {

    }

    private void selectTopicItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new TopFragment();
                break;
            case 2:
                fragment = new TestosteroneFragment();
                break;
            case 3:
                fragment = new HealthCareProviderFragment();
                break;
            case 4:
                fragment = new ResourceListFragment();
                break;
            case 5:
                fragment = new WorkoutIntroFragment();
                break;
            case 6:
                fragment = new ResourceListFragment();
                break;
            case 7:
                fragment = new AboutFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private class GetFaveTopicsTask extends AsyncTask<Context, Void, Cursor> {

        protected Cursor doInBackground(Context... context) {
            return FreeingOurselvesDatabaseUtilities.getFaveTopics(context[0]);
        }

        protected void onPostExecute(Cursor topicsCursor) {
            if (topicsCursor == null) {
                Toast toast = Toast.makeText(view.getContext(), "Could not get topics", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (topicsCursor.moveToFirst()) {
                    CursorAdapter topicsAdapter = new SimpleCursorAdapter(view.getContext(),
                            android.R.layout.simple_list_item_1, topicsCursor, new String[]{"TITLE"},
                            new int[]{android.R.id.text1}, 0);
                    topicsView.setAdapter(topicsAdapter);
                } else { // If no favorite topics, display text saying so.
                    noFaveTopics.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private class GetFaveWorkoutsTask extends AsyncTask<Context, Void, Cursor> {

        protected Cursor doInBackground(Context... context) {
            return FreeingOurselvesDatabaseUtilities.getFaveWorkouts(context[0]);
        }

        protected void onPostExecute(Cursor workoutsCursor) {
            if (workoutsCursor == null) {
                Toast toast = Toast.makeText(view.getContext(), "Could not get workouts", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (workoutsCursor.moveToFirst()) {
                    CursorAdapter workoutsAdapter = new SimpleCursorAdapter(view.getContext(),
                            android.R.layout.simple_list_item_1, workoutsCursor, new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
                    workoutsView.setAdapter(workoutsAdapter);
                } else { // If no favorite workouts, display text saying so.
                    noFaveWorkouts.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
