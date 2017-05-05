package edu.mills.freeingourselves;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.healthNotesFragment);
        Cursor cursor = FreeingOurselvesDatabaseUtilities.getSavedHealthcare(view.getContext());

        // Won't need hashmap if textwatcher works.
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

                Log.d("HealthNotes", "notes from cursor = " + notes);

                TextView textView = new TextView(view.getContext());
                textView.setText(question);
                linearLayout.addView(textView);

                EditText editText = new EditText(view.getContext());
                editText.setId(questionId);
                editText.setText(notes);
                editText.addTextChangedListener(new MyTextWatcher(view));
                linearLayout.addView(editText);
            }

            Log.d("HealthNotes", "closing cursor" + savedNotes);
            cursor.close();
        }

        // TODO: Make this method do something or get rid of it. Maybe a toast?
        Button saveNotesButton = (Button) view.findViewById(R.id.health_notes_save_button);
        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d("HealthNotes", "save notes clicked");

                // save text in edit texts to hashmap
//                for (int i = 0; i <= linearLayout.getChildCount(); i++) {
//                    if (view instanceof EditText) {
//                        savedNotes.put(view.getId(), this.getText());
//                    }
//                }
//
//                // save notes to database
//                for (HashMap.Entry<Integer, String> questionId : savedNotes.entrySet()) {
//
//                    FreeingOurselvesDatabaseUtilities.updateNotes(view.getContext(),
//                            questionId.getKey(), savedNotes.get(questionId.getKey()));
//                    Log.d("HealthNotes", "note updated" + questionId.getKey() + " " + savedNotes.get(questionId.getKey()));
//                }
            }
        });

        return view;
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;
        private boolean wasEdited = false;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //do nothing
        }


        // TODO: asynctask
        @Override
        public void afterTextChanged(Editable s) {
//            if (wasEdited) {
//                wasEdited = false;
//                return;
//            }
            FreeingOurselvesDatabaseUtilities.updateNotes(view.getContext(), view.getId(),
                    s.toString());
            Log.d("HealthNotes", s.toString());

            // To prevent infinite loop. But then it only updates every other letter.
            // wasEdited = true;

        }
    }

}
