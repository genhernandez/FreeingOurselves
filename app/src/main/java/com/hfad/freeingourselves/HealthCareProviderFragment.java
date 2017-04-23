package com.hfad.freeingourselves;


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
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
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
        ListView listView = new ListView(getActivity());

        frameLayout.addView(listView);

        SQLiteOpenHelper freeingOurselvesDB = new FreeingOurselvesDatabaseHelper(view.getContext());
        SQLiteDatabase db = freeingOurselvesDB.getReadableDatabase();
        ArrayList<String> questionArray =  FreeingOurselvesDatabaseUtilities.getHealthCareQuestions(db);

        Log.d("HealthCareProvider", questionArray.toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_multiple_choice, questionArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = ((CheckedTextView) view);
                checkedTextView.setChecked(!checkedTextView.isChecked());

                //onSavedClicked(view);
            }
        });

        return view;
    }

    public void onSavedClicked(View view) {
        //FreeingOurselvesDatabaseUtilities.updateSaved(view.getContext(), view.getId(), true);
    }

}
