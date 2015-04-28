package com.zjut.zjuthelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjut.zjuthelp.Bean.Subject;
import com.zjut.zjuthelp.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    // Subjects list
    private List<Subject> mList;
    // Context
    private Context mContext;

    public SubjectAdapter(Context context, List<Subject> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Subject subject = mList.get(i);
        viewHolder.mGrade.setText(subject.getSubjectGrade());
        viewHolder.mSubject.setText(subject.getSubjectName());
        viewHolder.mInfo.setText("学分：" + subject.getSubjectScore());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subject, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mGrade;
        private TextView mSubject;
        private TextView mInfo;

        public ViewHolder(View v) {
            super(v);
            mGrade = (TextView) v.findViewById(R.id.text_grade);
            mSubject = (TextView) v.findViewById(R.id.text_subject);
            mInfo = (TextView) v.findViewById(R.id.text_info);
        }
    }
}
