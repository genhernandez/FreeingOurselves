package com.hfad.freeingourselves;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.ListView;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import android.util.Log;

public class ResourceListFragment extends ListFragment {

    public static ArrayList<String> resourceList = new ArrayList<>();
    private ResourceListListener listener;

    public interface ResourceListListener {
        void resourceListItemClicked(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        resourceList.clear();
        final View view = inflater.inflate(R.layout.fragment_top, container, false);

        ArrayList<String> tempList = FreeingOurselvesDatabaseUtilities.getResources(view.getContext()); //TODO: asynctask, do something if it returns null
        // TODO: deal with null
        for (int i = 0; i < tempList.size(); i++) {
            resourceList.add(tempList.get(i));
        }
        resourceList.add("See nearby resources on map");

        //super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                resourceList);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    /*
        Called when fragment gets attached to the activity
         */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (ResourceListListener) activity;
    }

    /*
   Tells listener that an item in the ListView is clicked
    */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.resourceListItemClicked(position);
        }
    }

}
