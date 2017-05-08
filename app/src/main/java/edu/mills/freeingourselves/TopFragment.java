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

    protected View view;
//    ListView topicsView;
    protected ListView workoutsView;
    protected TextView noFaveWorkouts;
//    TextView noFaveTopics;

    protected WebView introWebView;

    protected Cursor cursor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top, container, false);

//        topicsView = (ListView) view.findViewById(R.id.favorite_topics_list);
        workoutsView = (ListView) view.findViewById(R.id.favorite_workouts_list);
//        noFaveTopics = (TextView) view.findViewById(R.id.no_fave_topics_text);
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

//    private void selectTopicItem(int position) {
//        Fragment fragment;
//        switch (position) {
//            case 1:
//                fragment = new TopFragment();
//                break;
//            case 2:
//                fragment = new TestosteroneFragment();
//                break;
//            case 3:
//                fragment = new HealthCareProviderFragment();
//                break;
//            case 4:
//                fragment = new ResourceListFragment();
//                break;
//            case 5:
//                fragment = new WorkoutIntroFragment();
//                break;
//            case 6:
//                fragment = new ResourceListFragment();
//                break;
//            case 7:
//                fragment = new AboutFragment();
//                break;
//            default:
//                fragment = new TopFragment();
//        }
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.content_frame, fragment);
//        ft.addToBackStack(null);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.commit();
//    }

//    private class GetFaveTopicsTask extends AsyncTask<Context, Void, Cursor> {
//
//        protected Cursor doInBackground(Context... context) {
//            return FreeingOurselvesDatabaseUtilities.getFaveTopics(context[0]);
//        }
//
//        protected void onPostExecute(Cursor topicsCursor) {
//            if (topicsCursor == null) {
//                Toast toast = Toast.makeText(view.getContext(), "Could not get topics", Toast.LENGTH_SHORT);
//                toast.show();
//            } else {
//                if (topicsCursor.moveToFirst()) {
//                    CursorAdapter topicsAdapter = new SimpleCursorAdapter(view.getContext(),
//                            android.R.layout.simple_list_item_1, topicsCursor, new String[]{"TITLE"},
//                            new int[]{android.R.id.text1}, 0);
//                    topicsView.setAdapter(topicsAdapter);
//                } else { // If no favorite topics, display text saying so.
//                    noFaveTopics.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//    }

    private class GetFaveWorkoutsTask extends AsyncTask<Context, Void, Boolean> {

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }
}
