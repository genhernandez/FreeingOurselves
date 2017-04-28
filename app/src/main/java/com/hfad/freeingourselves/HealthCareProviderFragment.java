package com.hfad.freeingourselves;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
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
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.healthcareFragment);
        ListView listView = new ListView(getActivity());

        frameLayout.addView(listView);

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


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
        TextView label = (TextView) v.getTag(R.id.label);
        CheckBox checkbox = (CheckBox) v.getTag(R.id.check);
        // Toast.makeText(v.getContext(), label.getText().toString()+" "+isCheckedOrNot(checkbox), Toast.LENGTH_LONG).show();
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

    public void onHealthNotesClicked() {
        
    }

}
