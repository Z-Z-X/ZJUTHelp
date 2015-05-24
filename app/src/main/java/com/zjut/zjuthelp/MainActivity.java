package com.zjut.zjuthelp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.zjut.zjuthelp.Fragments.BorrowingFragment;
import com.zjut.zjuthelp.Fragments.GradeQueryFragment;
import com.zjut.zjuthelp.Fragments.BorrowHistoryFragment;
import com.zjut.zjuthelp.Fragments.LibraryFragment;
import com.zjut.zjuthelp.Fragments.NewsFragment;
import com.zjut.zjuthelp.Fragments.NewsListFragment;
import com.zjut.zjuthelp.Fragments.RoomFreeQueryFragment;
import com.zjut.zjuthelp.Fragments.SettingsFragment;
import com.zjut.zjuthelp.Fragments.TeachingAffairsFragment;
import com.zjut.zjuthelp.Web.WebTools;
import com.zjut.zjuthelp.Web.ZJUTLibrary;
import com.zjut.zjuthelp.Web.ZJUTTeachingAffairs;

public class MainActivity extends BaseActivity
        implements View.OnClickListener,
        NewsFragment.OnFragmentInteractionListener,
        LibraryFragment.OnFragmentInteractionListener,
        TeachingAffairsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
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
    private LinearLayout itemUserState;
    private TextView userStatus;
    private LinearLayout itemNews;
    private LinearLayout itemLibrary;
    private LinearLayout itemTeachingAffairs;
    private LinearLayout itemSettings;

    // Fragments
    private NewsFragment newsFragment;
    private LibraryFragment libraryFragment;
    private TeachingAffairsFragment teachingAffairsFragment;
    private SettingsFragment settingsFragment;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Umeng
        MobclickAgent.updateOnlineConfig(this);
        AnalyticsConfig.enableEncrypt(true);

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
        itemUserState = (LinearLayout)findViewById(R.id.item_user_state);
        userStatus = (TextView)findViewById(R.id.user_status);
        itemNews = (LinearLayout)findViewById(R.id.item_news);
        itemLibrary = (LinearLayout)findViewById(R.id.item_library);
        itemTeachingAffairs = (LinearLayout)findViewById(R.id.item_teaching_affairs);
        itemSettings = (LinearLayout)findViewById(R.id.item_settings);
        // Set OnClickListener
        itemUserState.setOnClickListener(this);
        itemNews.setOnClickListener(this);
        itemLibrary.setOnClickListener(this);
        itemTeachingAffairs.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        // Set action bar drawer toggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setFitsSystemWindows(true);

        // Get fragment manager
        fragmentManager = getFragmentManager();

        // Get Shared Preference
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Check user status
        new CheckUserStatus().execute();

        // Load News Fragment
        onClick(itemNews);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen");
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
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
            case R.id.item_user_state:
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
        itemLibrary.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemTeachingAffairs.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemSettings.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    // Hide all fragments
    private void hideFragment(FragmentTransaction transaction) {
        if (newsFragment != null) {
            transaction.hide(newsFragment);
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

    // Check user status
    class CheckUserStatus extends AsyncTask<Void, Void, Void> {
        private String status;
        @Override
        protected Void doInBackground(Void... params) {
            if (!WebTools.isNetworkAvailable(MainActivity.this)) {
                status = "无网络连接";
                return null;
            }
            String userid = preferences.getString("student_id_preference", null);
            String libPasswrd = preferences.getString("library_password_preference", null);
            String tasPassword = preferences.getString("teaching_affairs_password_preference", null);
            ZJUTLibrary zjutLibrary = new ZJUTLibrary(userid, libPasswrd);
            ZJUTTeachingAffairs zjutTeachingAffairs = new ZJUTTeachingAffairs(userid, tasPassword);
            boolean libStatus = zjutLibrary.login().equals("OK");
            boolean tasStatus = zjutTeachingAffairs.login().equals("OK");
            if (libStatus && tasStatus) {
                status = userid;
            } else if (!libStatus && !tasStatus) {
                status = "没有账号被绑定";
            } else if (!libStatus) {
                status = "图书馆账号未绑定";
            } else {
                status = "教务系统账号未绑定";
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            userStatus.setText(status);
        }
    }

}
