package com.insightsuen.bindroid.sample.ui.main;

import android.content.Context;

import com.insightsuen.bindroid.sample.ui.list.AbsListActivity;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

/**
 * Created by InSight Suen on 2017/5/25.
 * Main ViewModel
 */
public class MainViewModel extends LifecycleViewModel {

    public void onClickListActivity(Context context) {
        startListActivity(context);
    }

    private void startListActivity(Context context) {
        AbsListActivity.start(context);
    }
}
