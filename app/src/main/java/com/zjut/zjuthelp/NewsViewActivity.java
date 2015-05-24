package com.zjut.zjuthelp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsViewActivity extends ActionBarActivity {

    private String url;
    private WebView webView;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        // Get target url
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView = (WebView) findViewById(R.id.web);

        new LoadNews().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    class LoadNews extends AsyncTask<Void, Integer, Integer> {
        private String title;
        private String content;
        // Do before execute
        @Override
        protected void onPreExecute() {

        }
        // Do in background
        @Override
        protected Integer doInBackground(Void... params) {
           try {
               Document doc = Jsoup.connect(url).get();
               // Get title of news
               Element h1 = doc.select("div.single > h1").first();
               title = h1.text();
               // Get content of news
               Element div = doc.select("div.content").first();
               // Fit web page to screen
               Elements imgs = div.select("img");
               for (Element img: imgs) {
                   img.removeAttr("width")
                           .removeAttr("height")
                           .attr("style", "width:100%;height:auto;");
               }
               content = div.html();
           } catch (Exception e) {
               e.printStackTrace();
           }
            return 0;
        }

        // Do after execute
        @Override
        protected void onPostExecute(Integer integer) {
            progressBar.setVisibility(View.GONE);
            setTitle(title);
            webView.loadDataWithBaseURL("", content, "text/html", "UTF-8", "");
        }
    }
}
