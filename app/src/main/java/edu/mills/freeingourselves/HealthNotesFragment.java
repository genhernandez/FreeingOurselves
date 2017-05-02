package edu.mills.freeingourselves;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthNotesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_notes, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.healthNotesFragment);
        Cursor cursor = FreeingOurselvesDatabaseUtilities.getSavedHealthcare(view.getContext());

        if (cursor == null) {
            Toast toast = Toast.makeText(view.getContext(), "Healthcare cursor is null.", Toast.LENGTH_LONG);
            toast.show();
        } else {

                for(cursor.moveToFirst() ; !cursor.isAfterLast(); cursor.moveToNext()){
                    int QuestionId = cursor.getInt(0);              // Get question's id.
                    String question = cursor.getString(1);          // Get question text.
                    String notes = cursor.getString(2);             // Get notes (if any).

                    TextView textView = new TextView(view.getContext());
                    textView.setText(question);
                    linearLayout.addView(textView);

                    EditText editText = new EditText(view.getContext());
                    editText.setText(notes);
                    linearLayout.addView(editText);
                    FreeingOurselvesDatabaseUtilities.updateNotes(view.getContext(), QuestionId, editText.getText().toString());
            }
            cursor.close();
        }

        Button saveNotesButton = (Button)  view.findViewById(R.id.health_notes_save_button);
        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }


    // TODO: onDestroy() or onDestroyView()?
    @Override
    public void onDestroy() {

        // Save all input from EditTexts to database.

        super.onDestroy();
    }
    }
