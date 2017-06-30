package com.insightsuen.bindroid.component;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.insightsuen.bindroid.BR;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

/**
 * Created by InSight Suen on 2017/5/27.
 * Data binding pattern Activity
 */
public abstract class BindActivity<Binding extends ViewDataBinding> extends AppCompatActivity {

    protected Binding mBinding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Nullable
    protected abstract LifecycleViewModel createOrFindViewModel(@Nullable Bundle savedInstanceState);

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        LifecycleViewModel viewModel = createOrFindViewModel(savedInstanceState);
        if (viewModel != null) {
            mBinding.setVariable(BR.viewModel, viewModel);
            viewModel.onStart(this);
        }
    }
}
