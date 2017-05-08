package edu.mills.freeingourselves;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The home page for the Freeing Ourselves app. This fragment includes an introduction to the app
 * and lists the users favorite workouts, if there are any.
 */
public class TopFragment extends Fragment {


    private View view;
    private ListView workoutsView;
    private TextView noFaveWorkouts;
    private Cursor cursor;
    private WebView introWebView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top, container, false);

        workoutsView = (ListView) view.findViewById(R.id.favorite_workouts_list);
        noFaveWorkouts = (TextView) view.findViewById(R.id.no_fave_workouts_text);

        introWebView = (WebView)view.findViewById(R.id.introWebView);
        introWebView.loadUrl("file:///android_asset/introduction_en.html");
        introWebView.setVerticalScrollBarEnabled(true);
        introWebView.setHorizontalScrollBarEnabled(true);

//        // Show favorite topics
//        new GetFaveTopicsTask().execute(view.getContext());
//
//        //Navigate to favorite topic when clicked
//        topicsView.setOnItemClickListener(new ListView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
//                selectTopicItem(position);
//            }
//        });

        // Show favorite workouts
        new GetFaveWorkoutsTask().execute(view.getContext());

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

    @Override
    public void onResume() {
        super.onResume();
        new GetFaveWorkoutsTask().execute(view.getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }

    private class GetFaveWorkoutsTask extends AsyncTask<Context, Void, Boolean> {

        protected Boolean doInBackground(Context... context) {
            cursor = FreeingOurselvesDatabaseUtilities.getFaveWorkouts(context[0]);
            return cursor != null;
        }

        protected void onPostExecute(Boolean success) {
            if (success) {
                if (cursor.moveToFirst()) {
                    CursorAdapter workoutsAdapter = new SimpleCursorAdapter(view.getContext(),
                            android.R.layout.simple_list_item_1, cursor,
                            new String[]{FreeingOurselvesDatabaseHelper.NAME},
                            new int[]{android.R.id.text1}, 0);
                    workoutsView.setAdapter(workoutsAdapter);
                    workoutsView.setVisibility(View.VISIBLE);
                } else { // If no favorite workouts, display text saying so.
                    noFaveWorkouts.setVisibility(View.VISIBLE);
                    workoutsView.setVisibility(View.INVISIBLE);
                }
            } else {
                Toast toast = Toast.makeText(view.getContext(), "Could not get workouts", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
