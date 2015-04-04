package com.zjut.zjuthelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjut.zjuthelp.Bean.Event;
import com.zjut.zjuthelp.R;

import java.util.List;

public class EventsAdapter {

    /*private List<Event> mList;
    private Context mContext;

    public EventsAdapter(Context context, List<Event> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mList==null? 0 : mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i ) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_view, viewGroup, false);
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder
    {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder( View v )
        {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name);
            mImageView = (ImageView) v.findViewById(R.id.pic);
        }
    }*/
}
