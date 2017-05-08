package edu.mills.freeingourselves;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
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
 * Displays a list of saved health care questions with a space for notes.
 */
public class HealthNotesFragment extends Fragment {
    HashMap<Integer, String> savedNotes;
    View view;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_health_notes, null);
        linearLayout = (LinearLayout) view.findViewById(R.id.healthNotesFragment);

        new GetSavedNotes().execute(view.getContext());

        int i = (int) FreeingOurselvesDatabaseUtilities.getNumRows(view.getContext());
        savedNotes = new HashMap(i);

        Log.d("HealthNotes", "created cursor");


        Button saveNotesButton = (Button) view.findViewById(R.id.health_notes_save_button);
        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("button", "clicked");
                Fragment healthCareProviderFragment = new HealthCareProviderFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, healthCareProviderFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        return view;
    }

    private class GetSavedNotes extends AsyncTask<Context, Void, Cursor> {
        public Cursor doInBackground(Context... context) {
            return FreeingOurselvesDatabaseUtilities.getSavedHealthcare(context[0]);
        }

        public void onPostExecute(Cursor cursor) {
            if (cursor.getCount() == 0) {
                Toast toast = Toast.makeText(view.getContext(), "Could not access saved healthcare questions", Toast.LENGTH_SHORT);
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
                    editText.addTextChangedListener(new MyTextWatcher(view, questionId));
                    linearLayout.addView(editText);
                }

                Log.d("HealthNotes", "closing cursor" + savedNotes);
                cursor.close();
            }
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;
        private boolean wasEdited = false;
        private int id;


        private MyTextWatcher(View view, int id) {
            this.view = view;
            this.id = id;
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

            FreeingOurselvesDatabaseUtilities.updateNotes(view.getContext(), id,
                    s.toString());
//            new UpdatingNotes().execute(view.getContext());


            // To prevent infinite loop. But then it only updates every other letter.
            // wasEdited = true;

        }

//        private class UpdatingNotes extends AsyncTask<Context, Void, Boolean> {
//            protected Boolean doInBackground(Context... context) {
//                return FreeingOurselvesDatabaseUtilities.updateNotes(context[0], id, s.toString());
//            }
//        }
    }

}
