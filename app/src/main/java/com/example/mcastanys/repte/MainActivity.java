package com.example.mcastanys.repte;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mcastanys.repte.adapter.PageAdapter;
import com.viewpagerindicator.CirclePageIndicator;


public class MainActivity extends ActionBarActivity {
    ViewPager mPager;
    PageAdapter mPageAdapter;
    CirclePageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
    }

    ViewPager.SimpleOnPageChangeListener pageListener = new ViewPager.SimpleOnPageChangeListener() {};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
