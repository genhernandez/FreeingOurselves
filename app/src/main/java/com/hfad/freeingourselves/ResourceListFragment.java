package com.hfad.freeingourselves;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.ListFragment;
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

public class ResourceListFragment extends ListFragment {

    public ArrayList<String> resourceList = new ArrayList<String>();
    private ResourceListListener listener;

    static interface ResourceListListener{
        void itemClicked(long id);
    }

    //Get link from resource
    /*
    String getResourceLink(SQLiteDatabase db, String resource){
        String[]columns=new String[]{"LINK"};
        Cursor cursor = db.query("RESOURCE", columns, null, null, null, null, null);
        String resourceLink = cursor.getString(0);
        return resourceLink;
    }*/

    void getResources(SQLiteDatabase db){
        String[]columns=new String[]{"RESOURCE_NAME","RESOURCE_DESCRIPTION"};
        Cursor cursor = db.query("RESOURCE", columns, null, null, null, null, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            //Add name plus the description to the array list
            resourceList.add(cursor.getString(0) + " - " + cursor.getString(1));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(getActivity());
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            getResources(db);
        } catch(SQLiteException e){
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                resourceList.toArray(new String[0]));
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (ResourceListListener) activity;
    }

    //Tells listener that item is clicked
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }

}
