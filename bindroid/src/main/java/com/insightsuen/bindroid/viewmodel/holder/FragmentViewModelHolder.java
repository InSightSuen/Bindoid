package com.insightsuen.bindroid.viewmodel.holder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.insightsuen.bindroid.viewmodel.BaseViewModel;

/**
 * Created by InSight Suen on 2017/5/25.
 * A holder fragment retaining ViewModel across Activity re-creation.
 */
public class FragmentViewModelHolder<VM extends BaseViewModel> extends Fragment
        implements ViewModelHolder<VM> {

    public static <VM extends BaseViewModel> FragmentViewModelHolder<VM> newInstance(VM viewModel) {
        FragmentViewModelHolder<VM> holder = new FragmentViewModelHolder<>();
        holder.setViewModel(viewModel);
        return holder;
    }

    private VM mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // retains across Activity re-creation
    }

    @Override
    public void setViewModel(VM viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public VM getViewModel() {
        return mViewModel;
    }

}
