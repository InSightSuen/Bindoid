package com.insightsuen.bindroid.component.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by InSight Suen on 2017/6/10.
 */
public abstract class BindAdapter<Item> extends RecyclerView.Adapter {

    protected List<Item> mItems;

    public void setItems(List<Item> items) {
        mItems = items;
    }

}