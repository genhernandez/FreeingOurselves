package edu.mills.freeingourselves;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Displays a list of saved health care questions with a space for notes.
 */
public class HealthNotesFragment extends Fragment {
    private View view;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_health_notes, null);
        linearLayout = (LinearLayout) view.findViewById(R.id.healthNotesFragment);

        new GetSavedNotes().execute(view.getContext());

        Button saveNotesButton = (Button) view.findViewById(R.id.health_notes_button);
        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Toast toast = Toast.makeText(view.getContext(), "Could not access saved healthcare questions",
                        Toast.LENGTH_SHORT);
                toast.show();
            } else {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    int questionId = cursor.getInt(0);              // Get question's id.
                    String question = cursor.getString(1);          // Get question text.
                    String notes = cursor.getString(2);             // Get notes (if any).

                    TextView textView = new TextView(view.getContext());
                    textView.setText(question);
                    linearLayout.addView(textView);

                    EditText editText = new EditText(view.getContext());
                    editText.setId(questionId);
                    editText.setText(notes);
                    editText.addTextChangedListener(new MyTextWatcher(view, questionId));
                    linearLayout.addView(editText);
                }
                cursor.close();
            }
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;
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

            FreeingOurselvesDatabaseUtilities.updateNotes(view.getContext(), id,
                    s.toString());
        }

//        private class UpdatingNotes extends AsyncTask<Context, Void, Boolean> {
//            protected Boolean doInBackground(Context... context) {
//                return FreeingOurselvesDatabaseUtilities.updateNotes(context[0], id, s.toString());
//            }
//        }
    }

}
