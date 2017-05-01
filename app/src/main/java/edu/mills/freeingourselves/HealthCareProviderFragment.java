package edu.mills.freeingourselves;


import android.app.Fragment;
import android.app.FragmentTransaction;
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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthCareProviderFragment extends Fragment implements AdapterView.OnItemClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_care, null);
       // FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.healthcareFragment);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.healthcareFragment);
        ListView listView = new ListView(getActivity());

       linearLayout.addView(listView);

        //TODO: asynctask and null
        List<String> questionArray = FreeingOurselvesDatabaseUtilities.getHealthCareQuestions(view.getContext());

        ArrayList<Model> modelArray = new ArrayList();

        for (int i = 0; i < questionArray.size(); i++) {
            Model modelQ = new Model(questionArray.get(i));
            modelArray.add(i, modelQ);
        }

        Log.d("HealthCareProvider", questionArray.toString());

        HealthQuestionAdapter adapter = new HealthQuestionAdapter(this.getActivity(), modelArray);
        listView.setAdapter(adapter);
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

   // public void onHealthNotesClicked() {
        
    //}

        //public void setSelected(boolean selected) {
            //this.selected = selected;
        //}
    //}


}
