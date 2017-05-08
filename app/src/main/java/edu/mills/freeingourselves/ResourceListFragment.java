package edu.mills.freeingourselves;

import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Displays resources.
 */

public class ResourceListFragment extends ListFragment {

    public static ArrayList<String> resourceList = new ArrayList<>();
    private ResourceListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        resourceList.clear();

        new getResourcesTask().execute();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // Called when fragment gets attached to the activity.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (ResourceListListener) activity;
    }

    // Tells listener that an item in the ListView is clicked.
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.resourceListItemClicked(position);
        }
    }

    interface ResourceListListener {
        void resourceListItemClicked(int position);
    }

    private class getResourcesTask extends AsyncTask<Void, Void, ArrayList<String>> {

        protected ArrayList<String> doInBackground(Void... voids) {
            return FreeingOurselvesDatabaseUtilities.getResources(getActivity());
        }

        protected void onPostExecute(ArrayList<String> tempList) {
            if (tempList != null) {
                for (int i = 0; i < tempList.size(); i++) {
                    resourceList.add(tempList.get(i));
                }
            }
            resourceList.add("See nearby resources on map");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_list_item_1,
                    resourceList);
            setListAdapter(adapter);
        }
    }
}




