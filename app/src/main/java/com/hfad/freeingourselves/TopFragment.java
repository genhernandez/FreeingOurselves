package com.hfad.freeingourselves;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    final static String TOPIC_NUM = "topic_num";


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_top, container, false);
        // Populate favorites
        View topLayout = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_top, null);
        ListView favoritesList = (ListView) topLayout.findViewById(R.id.favorites_list);
        //TODO: deal with asynctasks and null

        Cursor cursor = FreeingOurselvesDatabaseUtilities.getFaveTopics(view.getContext());
        CursorAdapter favoriteAdapter = new SimpleCursorAdapter(view.getContext(),
                android.R.layout.simple_list_item_1, cursor, new String[]{"TITLE"},
                new int[]{android.R.id.text1}, 0);
        favoritesList.setAdapter(favoriteAdapter);

        //Navigate to favorites when clicked
        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(view.getContext(), TopFragment.class);
                intent.putExtra(TOPIC_NUM, (int) id);
                startActivity(intent);
            }
        });
        return view;
    }

}
