package edu.mills.freeingourselves;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Displays a list of questions for Health Care providers and a way to save questions.
 */
public class HealthCareProviderFragment extends Fragment implements AdapterView.OnItemClickListener {
    View view;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_health_care, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.healthcareFragment);
        listView = new ListView(getActivity());

        linearLayout.addView(listView);

        new GetHealthCareQuestions().execute(view.getContext());
        listView.setOnItemClickListener(this);

        Button savedQuestionButton = (Button) view.findViewById(R.id.saved_question_button);
        savedQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment healthNotesFragment = new HealthNotesFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, healthNotesFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
        TextView label = (TextView) v.getTag(R.id.label);
        CheckBox checkbox = (CheckBox) v.getTag(R.id.check);
    }

    public class GetHealthCareQuestions extends AsyncTask<Context, Void, List<String>> {
        protected List<String> doInBackground(Context... context) {
            return FreeingOurselvesDatabaseUtilities.getHealthCareQuestions(context[0]);
        }

        protected void onPostExecute(List<String> questionArray) {
            if (questionArray == null) {
                Toast toast = Toast.makeText(view.getContext(), "Could not get healthcare questions", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                ArrayList<Model> modelArray = new ArrayList();

                for (int i = 0; i < questionArray.size(); i++) {
                    Model modelQ = new Model(questionArray.get(i));
                    modelArray.add(i, modelQ);
                }

                Log.d("HealthCareProvider", questionArray.toString());

                HealthQuestionAdapter adapter = new HealthQuestionAdapter(getActivity(), modelArray);
                listView.setAdapter(adapter);
            }
        }
    }

    class Model {


        private String question;
        private boolean selected;

        public Model(String name) {
            this.question = name;
        }

        public String getName() {
            return question;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }


}
