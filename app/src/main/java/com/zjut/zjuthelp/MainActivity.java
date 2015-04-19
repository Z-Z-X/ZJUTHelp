package com.zjut.zjuthelp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import com.zjut.zjuthelp.Fragments.BorrowingFragment;
import com.zjut.zjuthelp.Fragments.CircleFragment;
import com.zjut.zjuthelp.Fragments.GradeQueryFragment;
import com.zjut.zjuthelp.Fragments.BorrowHistoryFragment;
import com.zjut.zjuthelp.Fragments.LibraryFragment;
import com.zjut.zjuthelp.Fragments.NewsFragment;
import com.zjut.zjuthelp.Fragments.NewsListFragment;
import com.zjut.zjuthelp.Fragments.RoomFreeQueryFragment;
import com.zjut.zjuthelp.Fragments.SettingsFragment;
import com.zjut.zjuthelp.Fragments.TeachingAffairsFragment;

public class MainActivity extends BaseActivity
        implements View.OnClickListener,
        NewsFragment.OnFragmentInteractionListener,
        LibraryFragment.OnFragmentInteractionListener,
        TeachingAffairsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        CircleFragment.OnFragmentInteractionListener,
        NewsListFragment.OnFragmentInteractionListener,
        BorrowingFragment.OnFragmentInteractionListener,
        BorrowHistoryFragment.OnFragmentInteractionListener,
        GradeQueryFragment.OnFragmentInteractionListener,
        RoomFreeQueryFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private FragmentManager fragmentManager;

    // Linear Layout in main menu
    private LinearLayout itemNews;
    private LinearLayout itemCircle;
    private LinearLayout itemLibrary;
    private LinearLayout itemTeachingAffairs;
    private LinearLayout itemSettings;

    // Fragments
    private NewsFragment newsFragment;
    private CircleFragment circleFragment;
    private LibraryFragment libraryFragment;
    private TeachingAffairsFragment teachingAffairsFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Chang the color of system bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setTintResource(R.color.colorPrimaryDark);
        }

        // Init the toolbar
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        // Config the toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Init drawer menu
        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawer);
        // Init items in main menu
        itemNews = (LinearLayout)findViewById(R.id.item_news);
        itemCircle = (LinearLayout)findViewById(R.id.item_circle);
        itemLibrary = (LinearLayout)findViewById(R.id.item_library);
        itemTeachingAffairs = (LinearLayout)findViewById(R.id.item_teaching_affairs);
        itemSettings = (LinearLayout)findViewById(R.id.item_settings);
        // Set OnClickListener
        itemNews.setOnClickListener(this);
        itemCircle.setOnClickListener(this);
        itemLibrary.setOnClickListener(this);
        itemTeachingAffairs.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        // Set action bar drawer toggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        // Get fragment manager
        fragmentManager = getFragmentManager();

        // Load News Fragment
        onClick(itemNews);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    // Transition in difference fragments
    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // Hide all fragments
        hideFragment(transaction);
        // Init the color of items
        resetMenuBackgroundColor();
        switch (v.getId()) {
            // Transit to the news fragment
            case R.id.item_news:
                setTitle(R.string.news);
                itemNews.setBackgroundColor(getResources().getColor(R.color.colorLight));
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.page, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                break;
            // Transit to the circle fragment
            case R.id.item_circle:
                setTitle(R.string.circle);
                itemCircle.setBackgroundColor(getResources().getColor(R.color.colorLight));
                if (circleFragment == null) {
                    circleFragment = new CircleFragment();
                    transaction.add(R.id.page, circleFragment);
                } else {
                    transaction.show(circleFragment);
                }
                break;
            // Transit to the library fragment
            case R.id.item_library:
                setTitle(R.string.library);
                itemLibrary.setBackgroundColor(getResources().getColor(R.color.colorLight));
                if (libraryFragment == null) {
                    libraryFragment = new LibraryFragment();
                    transaction.add(R.id.page, libraryFragment);
                } else {
                    transaction.show(libraryFragment);
                }
                break;
            // Transit to the teaching affairs fragment
            case R.id.item_teaching_affairs:
                setTitle(R.string.teaching_affairs);
                itemTeachingAffairs.setBackgroundColor(getResources().getColor(R.color.colorLight));
                if (teachingAffairsFragment == null) {
                    teachingAffairsFragment = new TeachingAffairsFragment();
                    transaction.add(R.id.page, teachingAffairsFragment);
                } else {
                    transaction.show(teachingAffairsFragment);
                }
                break;
            // Transit to the settings fragment
            case R.id.item_settings:
                setTitle(R.string.settings);
                itemSettings.setBackgroundColor(getResources().getColor(R.color.colorLight));
                if (settingsFragment == null) {
                    settingsFragment = new SettingsFragment();
                    transaction.add(R.id.page, settingsFragment);
                } else {
                    transaction.show(settingsFragment);
                }
                break;
        }
        transaction.commit();
        // Close drawers
        drawerLayout.closeDrawers();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    };

    // Init the color of items
    private void resetMenuBackgroundColor() {
        itemNews.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemCircle.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemLibrary.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemTeachingAffairs.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemSettings.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    // Hide all fragments
    private void hideFragment(FragmentTransaction transaction) {
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (circleFragment != null) {
            transaction.hide(circleFragment);
        }
        if (libraryFragment != null) {
            transaction.hide(libraryFragment);
        }
        if (teachingAffairsFragment != null) {
            transaction.hide(teachingAffairsFragment);
        }
        if (settingsFragment != null) {
            transaction.hide(settingsFragment);
        }
    }

}
