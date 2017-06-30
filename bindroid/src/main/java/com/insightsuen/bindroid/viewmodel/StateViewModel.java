package com.insightsuen.bindroid.viewmodel;

import android.os.Parcelable;

/**
 * Created by InSight Suen on 2017/5/25.
 * State savable ViewModel
 */
public abstract class StateViewModel extends LifecycleViewModel {


    public static abstract class State implements Parcelable {

        public State(StateViewModel viewModel) { }

        @Override
        public int describeContents() {
            return 0;
        }

    }
}
