package edu.mills.freeingourselves;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * The top-level activity for Freeing Ourselves. This activity contains a navigation drawer,
 * which allows the user to launch {@link edu.mills.freeingourselves.TestosteroneFragment},
 * {@link edu.mills.freeingourselves.HealthCareProviderFragment},
 * {@link edu.mills.freeingourselves.ResourceListFragment},
 * {@link edu.mills.freeingourselves.WorkoutIntroFragment}, and
 * {@link edu.mills.freeingourselves.AboutFragment}.
 */
public class MainActivity extends AppCompatActivity implements ResourceListFragment.ResourceListListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    //private ShareActionProvider shareActionProvider;
    private String[] titles;
    private ListView drawerList;

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Code to run when the item gets clicked.
            selectItem(position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerList = (ListView) findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //Populate the ListView.
        titles = getResources().getStringArray(R.array.titles);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        if (savedInstanceState == null) {
            selectItem(0);
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    //Called whenever we call invalidateOptionsMenu().
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //if the drawer is open, hide action items related to the content view.
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    private void selectItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new TestosteroneFragment();
                break;
            case 2:
                fragment = new HealthCareProviderFragment();
                break;
            case 3:
                fragment = new ResourceListFragment();
                break;
            case 4:
                fragment = new WorkoutIntroFragment();
                break;
            case 5:
                fragment = new AboutFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        //Set the action bar title.
        setActionBarTitle(position);

        //Close the drawer.
        drawerLayout.closeDrawer(drawerList);
    }

    private void setActionBarTitle(int position) {
        String title;
        if (position == 0) {
            title = getResources().getString(R.string.app_name);

        } else {
            title = titles[position];
        }
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //MenuItem menuItem = menu.findItem(R.id.action_share);
        //shareActionProvider = (ShareActionProvider)menuItem.getActionProvider();
        //setIntent("This is example text");

        return super.onCreateOptionsMenu(menu);
    }

    //what is this for? Do we need this
    //
    //private void setIntent(String text){
    //Intent intent = new Intent(Intent.ACTION_SEND);
    //intent.setType("text/plain");
    //intent.putExtra(Intent.EXTRA_TEXT, text);
    //shareActionProvider.setShareIntent(intent);
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                //Code to run when the settings item is clicked.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Responds to a user clicking an item of the resource list with appropriate action.
     * If the last item in the list is clicked, launches MapActivity.
     * Otherwise, gets the URL of a resource that is clicked by accessing the database with the position,
     * and launching an intent with an AsyncTask.
     *
     * @param position the position of the item clicked in the list
     */
    @Override
    public void resourceListItemClicked(int position) {
        if ((position + 1) == ResourceListFragment.resourceList.size()) {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        } else {
            ContentValues myValues = new ContentValues();
            myValues.put("position", position); //TODO: this should be a constant?
            new GetResourceLinkTask().execute(myValues);
        }
    }

    private class GetResourceLinkTask extends AsyncTask<ContentValues, String, String> {

        protected String doInBackground(ContentValues... myValues) {
            int myPosition = (int) myValues[0].get("position");
            return FreeingOurselvesDatabaseUtilities.getResourceLink(MainActivity.this, myPosition);
        }

        protected void onPostExecute(String myUrl) {
            if (myUrl == null) {
                Toast toast = Toast.makeText(MainActivity.this, "Could not get link", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(myUrl));
                startActivity(i);
            }
        }
    }
}
