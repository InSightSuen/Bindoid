package com.insightsuen.bindroid.viewmodel;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by InSight Suen on 2017/6/28.
 * Android lifecycle
 */
public class LifecycleViewModel extends BaseViewModel {

    private WeakReference<Context> mContext;
    private boolean mStopped = true;
    private boolean mRunning = false;

    @CallSuper
    public void onStart(Context context) {
        mContext = new WeakReference<Context>(context);
        mStopped = false;
        mRunning = false;
    }

    @CallSuper
    public void onResume() {
        mRunning = true;
    }

    @CallSuper
    public void onPause() {
        mRunning = false;
    }

    @CallSuper
    public void onStop() {
        mRunning = false;
        mStopped = true;
        mContext.clear();
    }

    @Nullable
    public Context getContext() {
        if (mStopped) {
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

}
