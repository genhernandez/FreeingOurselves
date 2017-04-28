package com.hfad.freeingourselves;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthNotesFragment extends Fragment {


    public HealthNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Load questions into TextEdits and answers into EditTexts from db.


        return inflater.inflate(R.layout.fragment_health_notes, container, false);
    }


    // TODO: onDestroy() or onDestroyView()?
    @Override
    public void onDestroy() {

        // Save all input from EditTexts to database.

        super.onDestroy();
    }
}
