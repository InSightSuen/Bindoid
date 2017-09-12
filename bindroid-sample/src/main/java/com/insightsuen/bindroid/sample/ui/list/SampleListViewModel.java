package com.insightsuen.bindroid.sample.ui.list;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.insightsuen.bindroid.component.recyclerview.RecyclerViewBinder;
import com.insightsuen.bindroid.component.recyclerview.BindAdapter;
import com.insightsuen.bindroid.component.recyclerview.DiffCallBacks;
import com.insightsuen.bindroid.sample.ui.list.item.SampleItem;
import com.insightsuen.bindroid.sample.ui.list.item.SampleItemViewModel;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;
import com.insightsuen.bindroid.component.recyclerview.RecyclerViewBindable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by InSight Suen on 2017/6/10.
 * Abs List ViewModel
 */
public class SampleListViewModel extends LifecycleViewModel implements RecyclerViewBindable {

    //////////////////////////////
    // Data binding block start //
    //////////////////////////////

    public final ObservableBoolean isLoading = new ObservableBoolean(false);

    @Override
    public void bind(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BindAdapter) {
            DiffCallBacks<SampleItemViewModel> callBacks = new ListDiffCallbacks();
            //noinspection unchecked
            mBinder = new RecyclerViewBinder<>((BindAdapter) adapter, callBacks);
            mBinder.setDetectMoves(true);
            mBinder.enableDispatch(new RecyclerViewBinder.UpdateDataCallbacks() {
                @Override
                public void onUpdateStart() {
                    isLoading.set(true);
                }

                @Override
                public void onUpdateFinished() {
                    isLoading.set(false);
                }
            });
            refreshItemViewModelList();
        }
    }

    public void onRefreshList(Context context) {
        refreshList();
    }

    public void onClickAdd() {
        addNewItem();
    }

    public void onClickRemove() {
        removeRandom();
    }

    public void onClickShuffle() {
        shuffleOrder();
    }

    ////////////////////////////
    // Data binding block end //
    ////////////////////////////

    private AtomicInteger mNextId = new AtomicInteger(0);
    private List<SampleItem> mData;
    private RecyclerViewBinder<SampleItemViewModel> mBinder;

    SampleListViewModel() {
        mData = new ArrayList<>();
    }

    @Override
    public void onStart(Context context) {
        super.onStart(context);
        refreshItemViewModelList();
    }

    @Override
    public void onStop() {
        if (mBinder != null) {
            mBinder.shutdown();
        }
        super.onStop();
    }

    private void refreshList() {
        refreshItemViewModelList();
    }

    private void refreshItemViewModelList() {
        List<SampleItemViewModel> viewModelList = new ArrayList<>();
        for (SampleItem item : mData) {
            viewModelList.add(new SampleItemViewModel(this, item));
        }
        if (mBinder != null) {
            mBinder.onUpdateData(viewModelList);
        }
    }

    private void addNewItem() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat simple = new SimpleDateFormat("MM-dd hh:mm:ss", Locale.getDefault());
        SimpleDateFormat detail = new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss.SSS zz", Locale.CHINA);
        SampleItem newItem = new SampleItem(mNextId.getAndIncrement(),
                simple.format(now.getTime()), detail.format(now.getTime()));
        mData.add(newItem);

        refreshItemViewModelList();
    }

    public void removeItem(SampleItemViewModel itemViewModel) {
        mData.remove(itemViewModel.getListItem());
        refreshItemViewModelList();
    }

    private void removeRandom() {
        if (mData.isEmpty()) {
            return;
        }
        int position = (int) (Math.random() * mData.size());
        mData.remove(position);
        refreshItemViewModelList();
    }

    private void shuffleOrder() {
        Collections.sort(mData, new ShuffleComparator());
        refreshItemViewModelList();
    }

    private static class ListDiffCallbacks extends DiffCallBacks<SampleItemViewModel> {

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            SampleItemViewModel oldItem = mOld.get(oldItemPosition);
            SampleItemViewModel newItem = mNew.get(newItemPosition);
            return oldItem != null && newItem != null && oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            SampleItemViewModel oldItem = mOld.get(oldItemPosition);
            SampleItemViewModel newItem = mNew.get(newItemPosition);
            return TextUtils.equals(oldItem.getName(), newItem.getName())
                    && TextUtils.equals(oldItem.getContent(), newItem.getContent());
        }
    }

    private static class ShuffleComparator implements Comparator<SampleItem> {

        @Override
        public int compare(SampleItem o1, SampleItem o2) {
            return (int) (Math.random() * 3) - 1;
        }
    }
}
