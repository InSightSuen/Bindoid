package com.insightsuen.bindroid.sample.ui.list.item;

import android.databinding.Bindable;

import com.insightsuen.bindroid.sample.ui.list.AbsListViewModel;
import com.insightsuen.bindroid.viewmodel.ItemViewModel;

/**
 * Created by InSight Suen on 2017/6/28.
 * List item ViewModel
 */
public class ListItemViewModel extends ItemViewModel<ListItem> {

    @Bindable
    public String getName() {
        return mItem.getName();
    }

    @Bindable
    public String getContent() {
        return mItem.getContent();
    }

    public void onClickDelete() {
        mParent.removeItem(this);
    }

    private AbsListViewModel mParent;
    private ListItem mItem;

    public ListItemViewModel(AbsListViewModel parent, ListItem item) {
        mParent = parent;
        mItem = item;
    }

    public ListItem getListItem() {
        return mItem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != ListItemViewModel.class) {
            return false;
        }
        ListItemViewModel other = (ListItemViewModel) obj;
        return this.mItem.equals(other.mItem);
    }
}
