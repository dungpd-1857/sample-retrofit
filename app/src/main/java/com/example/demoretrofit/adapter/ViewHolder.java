package com.example.demoretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.demoretrofit.data.model.Item;
import com.example.demoretrofit.databinding.ItemBinding;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ItemBinding mItemBinding;

    private AnswersAdapter.PostItemListener mItemListener;

    public ViewHolder(ItemBinding item) {
        super(item.getRoot());
        this.mItemBinding = item;
    }
    static ViewHolder create(LayoutInflater inflater, ViewGroup view ){
        ItemBinding itemBinding = ItemBinding.inflate(inflater, view, false);
        return new ViewHolder(itemBinding);
    }
    public void setListener(AnswersAdapter.PostItemListener listener) {
        this.mItemListener = listener;
        mItemBinding.setListener(mItemListener);
    }

    public void bind(Item t) {
        mItemBinding.setData(t);
    }
}
