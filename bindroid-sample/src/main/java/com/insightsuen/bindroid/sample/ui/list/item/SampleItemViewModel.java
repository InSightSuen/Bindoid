package com.insightsuen.bindroid.sample.ui.list.item;

import android.databinding.Bindable;

import com.insightsuen.bindroid.sample.ui.list.SampleListViewModel;
import com.insightsuen.bindroid.viewmodel.ListItemViewModel;

/**
 * Created by InSight Suen on 2017/6/28.
 * List item ViewModel
 */
public class SampleItemViewModel extends ListItemViewModel<SampleItem> {

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

    private SampleListViewModel mParent;
    private SampleItem mItem;

    public SampleItemViewModel(SampleListViewModel parent, SampleItem item) {
        mParent = parent;
        mItem = item;
    }

    public SampleItem getListItem() {
        return mItem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != SampleItemViewModel.class) {
            return false;
        }
        SampleItemViewModel other = (SampleItemViewModel) obj;
        return this.mItem.equals(other.mItem);
    }
}
