package com.example.demoretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.demoretrofit.data.model.Item;
import java.util.List;

public class AnswersAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Item> mListItems;
    private PostItemListener mItemListener;
    private LayoutInflater inflater;

    public AnswersAdapter(Context context, List<Item> item) {
        this.inflater = LayoutInflater.from(context);
        this.mListItems = item;
    }

    public void setListener(PostItemListener mListener) {
        this.mItemListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(inflater, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item mItem = mListItems.get(position);
        holder.bind(mItem);
        if (mItemListener != null) {
            holder.setListener(mItemListener);
        }
    }

    @Override
    public int getItemCount() {
        return mListItems == null ? 0 : mListItems.size();
    }

    public interface PostItemListener {
        void onPostClick(Item id);
    }
}
