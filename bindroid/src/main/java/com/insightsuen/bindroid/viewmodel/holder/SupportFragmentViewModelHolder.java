package com.insightsuen.bindroid.viewmodel.holder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.insightsuen.bindroid.viewmodel.BaseViewModel;

/**
 * Created by InSight Suen on 2017/5/25.
 * A holder fragment retaining ViewModel across Activity re-creation.
 */
public class SupportFragmentViewModelHolder<VM extends BaseViewModel> extends Fragment {

    public static <VM extends BaseViewModel> SupportFragmentViewModelHolder<VM> newInstance(VM viewModel) {
        SupportFragmentViewModelHolder<VM> container = new SupportFragmentViewModelHolder<>();
        container.setViewModel(viewModel);
        return container;
    }

    private VM mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // retains across Activity re-creation
    }

    public void setViewModel(@Nullable VM viewModel) {
        mViewModel = viewModel;
    }

    @Nullable
    public VM getViewModel() {
        return mViewModel;
    }
}
