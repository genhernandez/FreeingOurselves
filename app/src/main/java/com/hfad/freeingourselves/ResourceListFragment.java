package com.hfad.freeingourselves;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResourceListFragment extends ListFragment {


    public ResourceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] rsc = getResources().getStringArray(R.array.resources);
        String[] resources = new String[rsc.length];
        for (int i = 0; i < rsc.length; i++) {
            resources[i] = rsc[i];
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                rsc);
        setListAdapter(adapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
