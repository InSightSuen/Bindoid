package com.insightsuen.bindroid.sample.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.insightsuen.bindroid.sample.R;
import com.insightsuen.bindroid.sample.base.BaseActivity;
import com.insightsuen.bindroid.sample.ui.MainBinding;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;
import com.insightsuen.bindroid.utils.ViewModelUtil;

/**
 * Created by InSight Suen on 2017/5/25.
 * Main Activity
 */
public class MainActivity extends BaseActivity<MainBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected LifecycleViewModel createOrFindViewModel(@Nullable Bundle savedInstanceState) {
        MainViewModel mainViewModel = ViewModelUtil.findFromFragmentManger(getSupportFragmentManager(), TAG_VIEW_MODEL);
        if (mainViewModel == null) {
            mainViewModel = new MainViewModel();
            ViewModelUtil.addToFragmentManager(getSupportFragmentManager(), mainViewModel, TAG_VIEW_MODEL);
        }
        return mainViewModel;
    }

}
