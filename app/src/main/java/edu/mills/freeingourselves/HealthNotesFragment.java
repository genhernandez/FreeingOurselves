package edu.mills.freeingourselves;


import android.app.Fragment;
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

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthNotesFragment extends Fragment {
    HashMap<Integer, String> savedNotes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_notes, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.healthNotesFragment);
        Cursor cursor = FreeingOurselvesDatabaseUtilities.getSavedHealthcare(view.getContext());

        int i = (int) FreeingOurselvesDatabaseUtilities.getNumRows(view.getContext());
        savedNotes = new HashMap(i);


        Log.d("HealthNotes", "created cursor");

        if (cursor == null) {
            Toast toast = Toast.makeText(view.getContext(), "Healthcare cursor is null.", Toast.LENGTH_LONG);
            toast.show();
        } else {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int questionId = cursor.getInt(0);              // Get question's id.
                    String question = cursor.getString(1);          // Get question text.
                    String notes = cursor.getString(2);             // Get notes (if any).
                savedNotes.put(questionId, notes);

                    TextView textView = new TextView(view.getContext());
                    textView.setText(question);
                    linearLayout.addView(textView);

                    EditText editText = new EditText(view.getContext());
                editText.setId(questionId);
                    editText.setText(notes);
                    linearLayout.addView(editText);
                // FreeingOurselvesDatabaseUtilities.updateNotes(view.getContext(), questionId, editText.getText().toString());
            }

            Log.d("HealthNotes", "closing cursor" + savedNotes);
            cursor.close();
        }

        Button saveNotesButton = (Button)  view.findViewById(R.id.health_notes_save_button);
        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HealthNotes", "save notes clicked");

                // save text in edit text to hashmap

                for (HashMap.Entry<Integer, String> q : savedNotes.entrySet()) {
                    FreeingOurselvesDatabaseUtilities.updateNotes(view.getContext(),
                            q.getKey(), savedNotes.get(q.getKey()));
                    Log.d("HealthNotes", "note updated" + q.getKey() + " " + savedNotes.get(q.getKey()));
                }
            }
        });

        return view;
    }


    // TODO: onDestroy() or onDestroyView()?
    @Override
    public void onDestroy() {
        //ProgressDialog saveProgress = ProgressDialog.show(getView().getContext(),
        //        "Saving Notes", "Saving notes.", true);

        // Save all input from EditTexts to database.


        //saveProgress.dismiss();
        super.onDestroy();
    }
    }
