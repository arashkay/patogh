package com.tectual.patogh.patogh;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.util.AQUtility;
import com.astuetz.PagerSlidingTabStrip;
import com.tectual.patogh.patogh.models.PagerAdapter;
import com.tectual.patogh.patogh.util.AppRater;


public class MainActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    PagerAdapter mSectionsPagerAdapter;
    private PagerSlidingTabStrip tabs;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBar actionBar;
    private boolean doubleBackToExitPressedOnce;
    public int loyaltyCardId;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        Bundle bundle = getIntent().getExtras();
        if ( bundle!=null ) {
            loyaltyCardId = bundle.getInt("loyaltyCardId");
        }

        tabs();
        menu();
        AppRater.app_launched(this);
    }

    public void menu(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
                @Override
                public void onDrawerClosed(View drawerView) {
                    invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }
        };
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void tabs(){
        mSectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setTypeface(Typeface.createFromAsset(this.getAssets(), "Arshia.ttf"), Typeface.BOLD);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round(22 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        tabs.setTextSize(px);
        tabs.setShouldExpand(true);
        tabs.setViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {
            /*mDrawerLayout.openDrawer(Gravity.LEFT);
            if(mDrawerLayout.isDrawerOpen(mDrawerList)){
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }else{
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
            return true;*/
        }else if(keyCode == KeyEvent.KEYCODE_BACK){
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return true;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast toast = Toast.makeText(getApplicationContext(), R.string.exiting,
                    Toast.LENGTH_SHORT);
            ((TextView)((LinearLayout) toast.getView()).getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL);
            toast.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() { doubleBackToExitPressedOnce=false; }
            }, 2000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onDestroy(){

        super.onDestroy();

        if(isTaskRoot()){
            AQUtility.cleanCacheAsync(this);
        }
    }
}
