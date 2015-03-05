package com.zjut.zjuthelp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.zjut.zjuthelp.Fragments.LibraryFragment;
import com.zjut.zjuthelp.Fragments.NewsFragment;
import com.zjut.zjuthelp.Fragments.SettingsFragment;
import com.zjut.zjuthelp.Fragments.TeachingAffairsFragment;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private FragmentManager fragmentManager;

    //主菜单控件
    private LinearLayout itemNews;
    private LinearLayout itemLibrary;
    private LinearLayout itemTeachingAffairs;
    private LinearLayout itemSettings;

    //页面
    private NewsFragment newsFragment;
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
        itemLibrary = (LinearLayout)findViewById(R.id.item_library);
        itemTeachingAffairs = (LinearLayout)findViewById(R.id.item_teaching_affairs);
        itemSettings = (LinearLayout)findViewById(R.id.item_settings);
        //事件处理
        itemNews.setOnClickListener(this);
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
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            String appTitle;
            @Override
            public void onDrawerOpened(View drawerView) {
                appTitle = getTitle().toString();
                setTitle(getResources().getString(R.string.app_name));
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                setTitle(appTitle);
            }
        };
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        //Load News Fragment
        onClick(itemNews);
    }

    //事件处理
    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        resetMenu();
        switch (v.getId()) {
            case R.id.item_news:
                setTitle(R.string.news);
                itemNews.setBackgroundColor(getResources().getColor(R.color.colorLight));
                break;
            case R.id.item_library:
                setTitle(R.string.library);
                itemLibrary.setBackgroundColor(getResources().getColor(R.color.colorLight));
                break;
            case R.id.item_teaching_affairs:
                setTitle(R.string.teaching_affairs);
                itemTeachingAffairs.setBackgroundColor(getResources().getColor(R.color.colorLight));
                break;
            case R.id.item_settings:
                setTitle(R.string.settings);
                itemSettings.setBackgroundColor(getResources().getColor(R.color.colorLight));
                break;
        }
        drawerLayout.closeDrawers();
    }

    //Reset Menu
    private void resetMenu() {
        itemNews.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemLibrary.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemTeachingAffairs.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        itemSettings.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    //Hide Fragement
}
