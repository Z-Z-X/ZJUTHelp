package com.zjut.zjuthelp;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.readystatesoftware.systembartint.SystemBarTintManager;


public class LicenseActivity extends ActionBarActivity {

    private WebView webView;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        // Change the color of systembar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setTintResource(R.color.colorPrimaryDark);
        }

        // Init progressbar
        progressBar = findViewById(R.id.progressBar);

        // Init toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.news_view_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.open_source_licence);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load html
        webView = (WebView) findViewById(R.id.web);
        webView.loadUrl("file:///android_asset/open_source_license.html");
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

}
