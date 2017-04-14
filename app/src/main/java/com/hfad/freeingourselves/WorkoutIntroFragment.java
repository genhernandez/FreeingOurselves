package com.hfad.freeingourselves;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//intro to workouts and a list of workouts
public class WorkoutIntroFragment extends Fragment {


    public WorkoutIntroFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_intro, container, false);
    }
}

