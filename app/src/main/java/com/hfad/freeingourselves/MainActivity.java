package com.hfad.freeingourselves;

//import android.app.ActionBar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;


//import android.content.Intent;
//import android.widget.ShareActionProvider;

public class MainActivity extends Activity implements ResourceListFragment.ResourceListListener {


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

        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(this);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            ArrayList<String> tempList = FreeingOurselvesDatabaseUtilities.getTopics(db); //TODO: this needs an asynctask
            // TODO: deal with null
            titles = new String[tempList.size()];
            for (int i = 0; i < tempList.size(); i++) {
                titles[i] = tempList.get(i);
            }
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                titles);
        drawerList.setAdapter(adapter);



        //Populate the ListView.

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

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
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
                fragment = new MapFragment();
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
        getActionBar().setTitle(title);
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

    /*
    Gets clicked resource's link from database
     */
    String getResourceLink(SQLiteDatabase db, int position) {
        String[] columns = new String[]{"LINK"};
        String[] where = new String[]{"" + (position+1) + ""};
        String resourceLink = null;
        Cursor cursor = db.query("RESOURCES", columns, "_id = ?", where, null, null, null);
        if (cursor.moveToFirst()) {
            resourceLink = cursor.getString(0);
            Log.v("MainActivity Resource", resourceLink);
        }
        cursor.close();
        db.close();
        return resourceLink;
    }

    /*
    Gets resource clicked and launches web intent
     */
    @Override
    public void resourceListItemClicked(int position) {
        String url = null;
        try {
            SQLiteOpenHelper freeingOurselvesDatabaseHelper = new FreeingOurselvesDatabaseHelper(this);
            SQLiteDatabase db = freeingOurselvesDatabaseHelper.getReadableDatabase();
            url = getResourceLink(db, position);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}