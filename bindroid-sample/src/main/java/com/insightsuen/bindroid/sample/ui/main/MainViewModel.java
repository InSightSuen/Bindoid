package com.insightsuen.bindroid.sample.ui.main;

import android.content.Context;

import com.insightsuen.bindroid.sample.ui.fragment.FragmentTestActivity;
import com.insightsuen.bindroid.sample.ui.list.SampleListActivity;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

/**
 * Created by InSight Suen on 2017/5/25.
 * Main ViewModel
 */
public class MainViewModel extends LifecycleViewModel {

    public void onClickListActivity(Context context) {
        startListActivity(context);
    }

    public void onClickFragmentActivity(Context context) {
        startFragmentTestActivity(context);
    }

    private void startListActivity(Context context) {
        SampleListActivity.start(context);
    }

    private void startFragmentTestActivity(Context context) {
        FragmentTestActivity.start(context);
    }
}
