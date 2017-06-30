package com.insightsuen.bindroid.sample.ui.list.item;

import android.text.TextUtils;

import com.insightsuen.bindroid.component.recyclerview.DiffCallBacks;

/**
 * Created by InSight Suen on 2017/6/10.
 */
public final class ListItem {

    private int mId;
    private String mName;
    private String mContent;

    public ListItem(int id) {
        mId = id;
    }

    public ListItem(int id, String name, String content) {
        mId = id;
        mName = name;
        mContent = content;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public boolean contentEquals(ListItem other) {
        return TextUtils.equals(this.mName, other.mName) && TextUtils.equals(this.mContent, other.mContent);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof ListItem) {
            return this.mId == ((ListItem) obj).mId;
        }
        return false;
    }

    public static class ListItemDiffCallbacks extends DiffCallBacks<ListItem> {

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOld.get(oldItemPosition).equals(mNew.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOld.get(oldItemPosition).contentEquals(mNew.get(newItemPosition));
        }

    }
}
