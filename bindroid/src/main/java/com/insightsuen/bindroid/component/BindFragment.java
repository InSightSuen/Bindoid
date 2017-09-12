package com.insightsuen.bindroid.component;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.insightsuen.bindroid.BR;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

/**
 * Created by InSight Suen on 2017/6/30.
 * Data binding pattern Fragment
 */
public abstract class BindFragment<Binding extends ViewDataBinding> extends Fragment {

    protected Binding mBinding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Nullable
    protected abstract LifecycleViewModel createOrFindViewModel(@Nullable Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LifecycleViewModel viewModel = createOrFindViewModel(savedInstanceState);
        if (viewModel != null) {
            mBinding.setVariable(BR.viewModel, viewModel);
            viewModel.onStart(getActivity());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LifecycleViewModel viewModel = createOrFindViewModel(null);
        if (viewModel != null) {
            viewModel.onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

}
