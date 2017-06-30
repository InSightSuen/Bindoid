package com.insightsuen.bindroid.component.recyclerview;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by InSight Suen on 2017/6/10.
 */
public abstract class DiffCallBacks<Item> extends DiffUtil.Callback {

    protected List<Item> mOld;
    protected List<Item> mNew;

    public DiffCallBacks() { }

    public void onNewData(List<Item> oldData, List<Item> newData) {
        mOld = oldData;
        mNew = newData;
    }

    @Override
    public int getOldListSize() {
        return mOld == null ? 0 : mOld.size();
    }

    @Override
    public int getNewListSize() {
        return mNew == null ? 0 : mNew.size();
    }

}
