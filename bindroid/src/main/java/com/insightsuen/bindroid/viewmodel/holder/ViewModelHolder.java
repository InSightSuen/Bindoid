package com.insightsuen.bindroid.viewmodel.holder;

import com.insightsuen.bindroid.viewmodel.BaseViewModel;

/**
 * Created by InSight Suen on 2017/5/25.
 * A holder retaining ViewModel across Activity re-creation.
 */
public interface ViewModelHolder<VM extends BaseViewModel>  {

    void setViewModel(VM viewModel);

    VM getViewModel();
}

