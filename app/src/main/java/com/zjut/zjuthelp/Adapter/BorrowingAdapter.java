package com.zjut.zjuthelp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjut.zjuthelp.Bean.Book;
import com.zjut.zjuthelp.R;

import java.util.List;

public class BorrowingAdapter extends RecyclerView.Adapter<BorrowingAdapter.ViewHolder> {
    // Book List
    private List<Book> mList;
    // Context
    private Context mContext;

    public BorrowingAdapter(Context context, List<Book> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Book book = mList.get(i);
        viewHolder.bookName.setText(book.getBookName());
        viewHolder.bookBorrowTime.setText("借书时间: " + book.getBookBorrowtime());
        viewHolder.bookReturnTime.setText("应还日期: " + book.getBookReturntime());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_borrowing, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView bookName;
        private TextView bookBorrowTime;
        private TextView bookReturnTime;

        public ViewHolder(View v) {
            super(v);
            bookName = (TextView) v.findViewById(R.id.book_name);
            bookBorrowTime = (TextView) v.findViewById(R.id.book_borrow_time);
            bookReturnTime = (TextView) v.findViewById(R.id.book_return_time);
        }
    }
}
