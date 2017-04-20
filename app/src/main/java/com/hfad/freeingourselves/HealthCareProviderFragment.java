package com.hfad.freeingourselves;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthCareProviderFragment extends Fragment {
    boolean isComplete = false;



    public HealthCareProviderFragment() {
        // Required empty public constructor
    }

//    public void getHealthCareQuestions(SQLiteDatabase db) {
//        try{
//            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(getActivity());
//            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
//            Cursor cursor = db.query("HEALTHCARE",
//                    new String[] {"STEP_INFO", "NOTES", "COMPLETED"},
//                    "_id=?",
//                    null, null, null, null);
//            if(cursor.moveToFirst()) {
//                String questionText = cursor.getString(1);
//                String questionNotes = cursor.getString(2);
//                if(cursor.getInt(3) == 1) {
//                    isComplete = true;
//                }
//
//                CheckedTextView text = (CheckedTextView) findViewById(R.id.healthcareQuestion);
//                text.setText(questionText);
//
//            }
//        } catch (SQLiteException e){
//            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_care, container, false);

        try{
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(getActivity());
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("HEALTHCARE",
                    new String[] {"STEP_INFO", "NOTES", "COMPLETED"},
                    "_id=?",
                    null, null, null, null);
            if(cursor.moveToFirst()) {
                String questionText = cursor.getString(1);
                String questionNotes = cursor.getString(2);
                if(cursor.getInt(3) == 1) {
                    isComplete = true;
                }
                CheckedTextView text = (CheckedTextView) getView().findViewById(R.id.healthcareQuestion);
                text.setText(questionText);
                text.setChecked(isComplete);

                EditText notes = (EditText) getView().findViewById(R.id.healthcareNotes);
                notes.setText(questionNotes);

            }
            cursor.close();
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        // Inflate the layout for this fragment
        return view;
    }

}
