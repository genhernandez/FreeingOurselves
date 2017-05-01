package edu.mills.freeingourselves;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

//intro to workouts and a list of workouts
public class WorkoutIntroFragment extends Fragment {

    protected View view;

    protected WebView wWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout_intro, container, false);

        wWebView = (WebView) view.findViewById(R.id.webView);
        wWebView.loadUrl("file:///android_asset/workout_en.html");

        Button startWorkoutButton = (Button) view.findViewById(R.id.startWorkoutButton);
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment workoutFragment = new WorkoutFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, workoutFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            }
        });

        return view;
    }

}





