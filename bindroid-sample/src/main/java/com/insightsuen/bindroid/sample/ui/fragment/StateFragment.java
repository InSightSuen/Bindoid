package com.insightsuen.bindroid.sample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.insightsuen.bindroid.sample.R;
import com.insightsuen.bindroid.sample.base.BaseActivity;
import com.insightsuen.bindroid.sample.base.BaseFragment;
import com.insightsuen.bindroid.utils.ViewModelUtil;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

/**
 * Created by InSight Suen on 2017/7/4.
 */
public class StateFragment extends BaseFragment<StateBinding> {

    private static final String TAG = "StateFragment";
    private static final String TAG_VIEW_MODEL = "State";

    private String mTag;

    public static StateFragment newInstance(String tag) {
        Bundle args = new Bundle();
        StateFragment fragment = new StateFragment();
        fragment.mTag = tag;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_state;
    }

    @Nullable
    @Override
    protected LifecycleViewModel createOrFindViewModel(@Nullable Bundle savedInstanceState) {
        LifecycleViewModel viewModel = ViewModelUtil.findFromFragmentManger(getFragmentManager(),
                TAG_VIEW_MODEL + mTag);
        if (viewModel == null) {
            if (DEBUG) {
                Log.d(TAG, "createOrFindViewModel: create view model");
            }
            FragmentTestViewModel parentViewModel = ViewModelUtil.findFromFragmentManger(getFragmentManager(), BaseActivity.TAG_VIEW_MODEL);
            viewModel = new StateFragmentViewModel(parentViewModel, savedInstanceState, mTag);
            ViewModelUtil.addToFragmentManager(getFragmentManager(), viewModel, TAG_VIEW_MODEL + mTag);
        }
        return viewModel;
    }
}
