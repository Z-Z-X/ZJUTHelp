package com.zjut.zjuthelp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;
import com.zjut.zjuthelp.Fragments.BorrowRecordsFragment;
import com.zjut.zjuthelp.Fragments.CircleFragment;
import com.zjut.zjuthelp.Fragments.HistorykFragment;
import com.zjut.zjuthelp.Fragments.LibraryFragment;
import com.zjut.zjuthelp.Fragments.NewsFragment;
import com.zjut.zjuthelp.Fragments.NewsListFragment;
import com.zjut.zjuthelp.Fragments.SettingsFragment;
import com.zjut.zjuthelp.Fragments.TeachingAffairsFragment;

public class MainActivity extends ActionBarActivity
        implements View.OnClickListener,
        NewsFragment.OnFragmentInteractionListener,
        LibraryFragment.OnFragmentInteractionListener,
        TeachingAffairsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        CircleFragment.OnFragmentInteractionListener,
        NewsListFragment.OnFragmentInteractionListener,
        BorrowRecordsFragment.OnFragmentInteractionListener,
        HistorykFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private FragmentManager fragmentManager;

    //主菜单控件
    private LinearLayout itemNews;
    private LinearLayout itemCircle;
    private LinearLayout itemLibrary;
    private LinearLayout itemTeachingAffairs;
    private LinearLayout itemSettings;

    //页面
    private NewsFragment newsFragment;
    private CircleFragment circleFragment;
    private LibraryFragment libraryFragment;
    private TeachingAffairsFragment teachingAffairsFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化工具栏
        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        //初始化侧滑菜单
        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawer);
        //初始化菜单中的项目
        itemNews = (LinearLayout)findViewById(R.id.item_news);
        itemCircle = (LinearLayout)findViewById(R.id.item_circle);
        itemLibrary = (LinearLayout)findViewById(R.id.item_library);
        itemTeachingAffairs = (LinearLayout)findViewById(R.id.item_teaching_affairs);
        itemSettings = (LinearLayout)findViewById(R.id.item_settings);
        //设置菜单项点击事件
        itemNews.setOnClickListener(this);
        itemCircle.setOnClickListener(this);
        itemLibrary.setOnClickListener(this);
        itemTeachingAffairs.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        fragmentManager = getFragmentManager();

        //配置工具栏
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //开启关闭菜单
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        //Load News Fragment
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

    //侧滑菜单Fragment切换
    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        resetMenu();
        switch (v.getId()) {
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
        drawerLayout.closeDrawers();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    };

    //重置菜单颜色
    private void resetMenu() {
        itemNews.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemCircle.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemLibrary.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemTeachingAffairs.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemSettings.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    //隐藏Fragment
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
