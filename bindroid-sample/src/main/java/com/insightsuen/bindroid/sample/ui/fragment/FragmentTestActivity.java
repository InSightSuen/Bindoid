package com.insightsuen.bindroid.sample.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.insightsuen.bindroid.sample.R;
import com.insightsuen.bindroid.sample.base.BaseActivity;
import com.insightsuen.bindroid.sample.contract.Const;
import com.insightsuen.bindroid.utils.ViewModelUtil;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

/**
 * Created by InSight Suen on 2017/6/30.
 * Fragment test Activity
 */
public class FragmentTestActivity extends BaseActivity<FragmentTestBinding> {

    private static final String TAG = "FragmentTestActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (Const.DEBUG) {
            Log.d(TAG, "onCreate");
        }
        super.onCreate(savedInstanceState);
        initWidgets();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fragment_test;
    }

    @Nullable
    @Override
    protected LifecycleViewModel createOrFindViewModel(@Nullable Bundle savedInstanceState) {
        FragmentTestViewModel viewModel = ViewModelUtil.findFromFragmentManger(getSupportFragmentManager(), TAG_VIEW_MODEL);
        if (viewModel == null) {
            if (Const.DEBUG) {
                Log.d(TAG, "create view model");
            }
            viewModel = new FragmentTestViewModel();
            ViewModelUtil.addToFragmentManager(getSupportFragmentManager(), viewModel, TAG_VIEW_MODEL);
        }
        return viewModel;
    }

    private void initWidgets() {

    }

}
