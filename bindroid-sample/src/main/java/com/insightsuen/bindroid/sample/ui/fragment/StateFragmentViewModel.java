package com.insightsuen.bindroid.sample.ui.fragment;

import android.content.Context;
import android.databinding.Bindable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.insightsuen.bindroid.sample.BR;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by InSight Suen on 2017/6/30.
 */
public class StateFragmentViewModel extends LifecycleViewModel {

    private static final String TAG = "StateFragmentViewModel";

    private static final boolean DEBUG = true;

    @Bindable
    public String getTagInfo() {
        return "Fragment " + mTag;
    }

    public void onClickShowCreateTime(Context context) {
        toastCreateTime(context);
    }

    @Bindable
    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    private FragmentTestViewModel mParent;

    private String mInfo;
    private String mTag;
    private String mCreateTime;
    private String mInstanceStateKey;

    public StateFragmentViewModel(FragmentTestViewModel parent, @Nullable Bundle savedInstanceState, String tag) {
        Calendar now = Calendar.getInstance();
        mCreateTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(now.getTime());
        mParent = parent;
        mTag = tag;
//        mInstanceStateKey = TAG + ":" + tag;
        mInstanceStateKey = TAG;
        onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (DEBUG) {
            Log.d(TAG, "onRestoreInstanceState: savedInstanceState=" + savedInstanceState);
            if (savedInstanceState != null) {
                Log.d(TAG, "onRestoreInstanceState: mInstanceStateKey=" + mInstanceStateKey);
                Log.d(TAG, "onRestoreInstanceState: savedInstanceState.containsKey=" + savedInstanceState.containsKey(mInstanceStateKey));
            }
        }
        if (savedInstanceState != null && savedInstanceState.containsKey(mInstanceStateKey)) {
            FragmentState state = savedInstanceState.getParcelable(mInstanceStateKey);
            if (state != null) {
                mInfo = state.mInfo;
                mTag = state.mTag;
                mCreateTime = state.mCreateTime;
                if (DEBUG) {
                    Log.d(TAG, "onRestoreInstanceState: info=" + mInfo);
                }
                notifyPropertyChanged(BR.info);
                notifyPropertyChanged(BR.tagInfo);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!mParent.enableSaveInstanceState.get()) {
            return;
        }
        if (outState.containsKey(mInstanceStateKey)) {
            throw new IllegalStateException("OutState already has the key:" + mInstanceStateKey);
        } else {
            if (DEBUG) {
                Log.d(TAG, "onSaveInstanceState: mCreateTime=" + mCreateTime);
            }
            outState.putParcelable(mInstanceStateKey, createInstanceState());
        }
    }

    private void toastCreateTime(Context context) {
        Toast.makeText(context, "Created at " + mCreateTime, Toast.LENGTH_SHORT).show();
    }

    private State createInstanceState() {
        return new FragmentState(this);
    }

    private static class FragmentState extends State {

        private String mInfo;
        private String mTag;
        private String mCreateTime;

        private FragmentState(StateFragmentViewModel viewModel) {
            mInfo = viewModel.mInfo;
            mTag = viewModel.mTag;
            mCreateTime = viewModel.mCreateTime;
        }

        private FragmentState(Parcel in) {
            mInfo = in.readString();
            mTag = in.readString();
            mCreateTime = in.readString();
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeString(mInfo);
            dest.writeString(mTag);
            dest.writeString(mCreateTime);
        }

        public static final Creator<FragmentState> CREATOR = new Parcelable.Creator<FragmentState>() {
            @Override
            public FragmentState createFromParcel(Parcel in) {
                return new FragmentState(in);
            }

            @Override
            public FragmentState[] newArray(int size) {
                return new FragmentState[size];
            }
        };
    }
}
