package com.hfad.freeingourselves;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthCareProviderFragment extends Fragment {


    public HealthCareProviderFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_care, null);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.healthcareFragment);
        TextView textView = new TextView(getActivity());
        textView.setText("please work");
        frameLayout.addView(textView);
        Log.d("HealthCareProvider", "test 1");
        SQLiteOpenHelper freeingOurselvesDB = new FreeingOurselvesDatabaseHelper(view.getContext());
        Log.d("HealthCareProvider", "test 2");
        SQLiteDatabase db = freeingOurselvesDB.getReadableDatabase();
        Log.d("HealthCareProvider", "test 3");
        ArrayList<String> questionArray =  FreeingOurselvesDatabaseUtilities.getHealthCareQuestions(view.getContext());
        Log.d("HealthCareProvider", "test 4");
        CheckedTextView checkedTextView;
        int qALength = questionArray.size();
        Log.d("HealthCareProvider", "test 5");
        for(int i = 0; i < qALength; i++) {
            Log.d("HealthCareProvider", "test inside loop");
            checkedTextView = new CheckedTextView(view.getContext());
            checkedTextView.setText(questionArray.get(i));
            frameLayout.addView(checkedTextView);
        }

        return view;
    }

}
