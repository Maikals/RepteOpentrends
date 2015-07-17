package com.example.mcastanys.repte.Activities;



import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.example.mcastanys.repte.NavItem;
import com.example.mcastanys.repte.R;
import com.example.mcastanys.repte.adapter.DrawerListAdapter;
import com.example.mcastanys.repte.adapter.PageAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ViewPager mPager;
    PageAdapter mPageAdapter;
    CirclePageIndicator mIndicator;
    private final static String TAG = MainActivity.class.getSimpleName();
    private RelativeLayout mDrawerPane;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private ActionBar actionBar;
    private final static String NUM_FRAGMENTS = "fragments";
    private int mNumFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            // Restore value of members from saved state
            mNumFragments = savedInstanceState.getInt(NUM_FRAGMENTS);
        } else {
            mNumFragments = 0;
        }


        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavItems.add(new NavItem("Home", "Meetup destination", R.drawable.ic_action_home));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);

        // Set the adapter for the list view
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };
        // Set the list's click listener

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        mPager = (ViewPager) findViewById(R.id.vp_parent);
        if(mPageAdapter == null) {
            Log.d("MainActivity", "adaptador null");
        }
        else {
            Log.d("MainActivity", "adaptador no null");
        }

        mPageAdapter = new PageAdapter(getSupportFragmentManager());

        mPager.setAdapter(mPageAdapter);
        mPager.setOffscreenPageLimit(mPageAdapter.getCount());
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(pageListener);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        for (int i = 0; i < mNumFragments; ++i) {
            mPageAdapter.addFragment();
        }
        mPageAdapter.notifyDataSetChanged();


    }

    ViewPager.SimpleOnPageChangeListener pageListener = new ViewPager.SimpleOnPageChangeListener() {};

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(NUM_FRAGMENTS, mNumFragments);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }



    private void selectItemFromDrawer(int position) {
        mPageAdapter.notifyDataSetChanged();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);
        Intent intent = new Intent(this, Preference.class);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
        startActivity(intent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.add_fragment) {
            mPageAdapter.addFragment();
            ++mNumFragments;
            mPageAdapter.notifyDataSetChanged();
            return true;
        }
        else if (id == R.id.remove_fragment) {
            if (mNumFragments > 0){
                mPageAdapter.removeFragment();
                --mNumFragments;
                mPageAdapter.notifyDataSetChanged();
                mPager.setOffscreenPageLimit(mNumFragments + 1);
                Log.d(TAG, "num Fragments " + mNumFragments);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

}
