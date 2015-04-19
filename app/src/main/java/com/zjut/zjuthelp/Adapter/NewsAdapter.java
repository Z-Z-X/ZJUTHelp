package com.zjut.zjuthelp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zjut.zjuthelp.Bean.News;
import com.zjut.zjuthelp.NewsViewActivity;
import com.zjut.zjuthelp.R;

import java.net.URLEncoder;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    // News list
    private List<News> mList;
    // Context
    private Context mContext;

    public NewsAdapter(Context context, List<News> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        News news = mList.get(i);
        viewHolder.newsTitle.setText(news.getNewsTitle());
        viewHolder.newsOutline.setText(news.getNewsOutline());
        // Encode thr url of image
        String url = news.getImageURL();
        int start = url.lastIndexOf("/");
        int end = url.indexOf("&", start);
        String part1 = url.substring(0, start+1);
        String part2 = URLEncoder.encode(url.substring(start + 1, end));
        String part3 = url.substring(end);
        url = part1 + part2 + part3;
        // Load image
        Picasso.with(mContext).load(url).into(viewHolder.newsImage);
    }

    @Override
    public int getItemCount() {
        return mList==null? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView newsImage;
        private TextView newsTitle;
        private TextView newsOutline;

        public ViewHolder(View v) {
            super(v);
            newsImage = (ImageView) v.findViewById(R.id.news_img);
            newsTitle = (TextView) v.findViewById(R.id.news_title);
            newsOutline = (TextView) v.findViewById(R.id.news_outline);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), NewsViewActivity.class);
            intent.putExtra("url", mList.get(getPosition()).getNewsURL());
            v.getContext().startActivity(intent);
        }
    }
}
