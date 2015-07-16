package com.example.mcastanys.repte;



import android.support.v7.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.example.mcastanys.repte.adapter.DrawerListAdapter;
import com.example.mcastanys.repte.adapter.PageAdapter;
import com.viewpagerindicator.CirclePageIndicator;
import com.example.mcastanys.repte.fragment.PreferenceFragment;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        mPageAdapter = new PageAdapter(getSupportFragmentManager());
        if(mPager == null) {
            Log.d("MainActivity", "adaptador null");
        }
        else {
            Log.d("MainActivity", "adaptador no null");
        }
        mPager.setAdapter(mPageAdapter);
        mPager.setOffscreenPageLimit(mPageAdapter.getCount());
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(pageListener);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);




    }

    ViewPager.SimpleOnPageChangeListener pageListener = new ViewPager.SimpleOnPageChangeListener() {};






    private void selectItemFromDrawer(int position) {
        mPageAdapter.addFragment();
        mPageAdapter.notifyDataSetChanged();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
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
            mPageAdapter.notifyDataSetChanged();
            return true;
        }
        else if (id == R.id.remove_fragment) {
            mPageAdapter.removeFragment();
            mPageAdapter.notifyDataSetChanged();
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
