package com.insightsuen.bindroid.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by InSight Suen on 2017/6/28.
 * Android lifecycle
 */
public abstract class LifecycleViewModel extends BaseViewModel {

    private static final String EXTRA_VIEW_MODEL_STATE = "ViewModelState";

    private WeakReference<Context> mContext;
    private boolean mStopped = true;
    private boolean mRunning = false;

    @CallSuper
    public void onStart(Context context) {
        mContext = new WeakReference<Context>(context);
        mStopped = false;
        mRunning = false;
    }

    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) { }

    @CallSuper
    public void onResume() {
        mRunning = true;
    }

    @CallSuper
    public void onPause() {
        mRunning = false;
    }

    public void onSaveInstanceState(Bundle outState) { }

    @CallSuper
    public void onStop() {
        mRunning = false;
        mStopped = true;
        mContext.clear();
    }

    @Nullable
    public Context getContext() {
        if (!mStopped) {
            return mContext.get();
        }
        return null;
    }

    public boolean isStopped() {
        return mStopped;
    }

    public boolean isRunning() {
        return mRunning;
    }

    public static abstract class State implements Parcelable {

        @Override
        public int describeContents() {
            return 0;
        }

    }
}
