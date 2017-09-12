package com.insightsuen.bindroid.sample.ui.list;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.insightsuen.bindroid.component.recyclerview.BindAdapter;
import com.insightsuen.bindroid.sample.R;
import com.insightsuen.bindroid.sample.base.BaseActivity;
import com.insightsuen.bindroid.sample.ui.list.item.SampleItemViewModel;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;
import com.insightsuen.bindroid.utils.ViewModelUtil;

/**
 * Created by InSight Suen on 2017/6/10.
 * AbsList test Activity
 */
public class SampleListActivity extends BaseActivity<ListBinding> {

    private static final String TAG_VIEW_MODEL = "ViewModel";

    public static void start(Context context) {
        Intent starter = new Intent(context, SampleListActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidgets();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_list;
    }

    @Override
    protected LifecycleViewModel createOrFindViewModel(@Nullable Bundle savedInstanceState) {
        SampleListViewModel viewModel = ViewModelUtil.findFromFragmentManger(getSupportFragmentManager(), TAG_VIEW_MODEL);
        if (viewModel == null) {
            viewModel = new SampleListViewModel();
            ViewModelUtil.addToFragmentManager(getSupportFragmentManager(), viewModel, TAG_VIEW_MODEL);
        }
        return viewModel;
    }

    private void initWidgets() {
        BindAdapter<SampleItemViewModel> adapter = new ListAdapter();
        mBinding.rvList.setAdapter(adapter);
        mBinding.rvList.setLayoutManager(new LinearLayoutManager(this));
    }

    private static class ListAdapter extends BindAdapter<SampleItemViewModel> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ItemViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            SampleItemViewModel viewModel = mItems.get(position);
            if (holder instanceof ItemViewHolder) {
                ((ItemViewHolder) holder).onBind(viewModel);
            }
        }

        @Override
        public int getItemCount() {
            return mItems != null ? mItems.size() : 0;
        }

        private static class ItemViewHolder extends RecyclerView.ViewHolder {

            private ListItemBinding mBinding;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }

            private void onBind(SampleItemViewModel viewModel) {
                mBinding.setViewModel(viewModel);
                mBinding.executePendingBindings();
            }
        }
    }
}
