package com.insightsuen.bindroid.sample.ui.fragment;

import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.insightsuen.bindroid.sample.base.BaseActivity;
import com.insightsuen.bindroid.viewmodel.LifecycleViewModel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by InSight Suen on 2017/6/30.
 * Fragment test ViewModel
 */
public class FragmentTestViewModel extends LifecycleViewModel {

    private static final AtomicInteger COUNT = new AtomicInteger(0);

    public final ObservableBoolean enableSaveInstanceState = new ObservableBoolean(true);

    public void onClickAddNew(View container) {
        if (isRunning()) {
            FragmentTransaction transaction = getFragmentManger().beginTransaction();
            StateFragment fragment = StateFragment.newInstance(COUNT.getAndIncrement() + "");
            transaction.add(container.getId(), fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void onClickPrevious() {
        if (isRunning()) {
            getFragmentManger().popBackStack();
        }
    }

    private FragmentManager getFragmentManger() {
        if (isRunning()) {
            BaseActivity activity = (BaseActivity) getContext();
            return activity.getSupportFragmentManager();
        }
        return null;
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private static class ViewModelState extends State {

        private boolean mEnableSaveInstanceState;

        public ViewModelState(boolean enableSaveInstanceState) {
            mEnableSaveInstanceState = enableSaveInstanceState;
        }

        private ViewModelState(Parcel in) {
            mEnableSaveInstanceState = in.readInt() > 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeInt(mEnableSaveInstanceState ? 1 : 0);
        }

        public static final Creator<ViewModelState> CREATOR = new Creator<ViewModelState>() {
            @Override
            public ViewModelState createFromParcel(Parcel in) {
                return new ViewModelState(in);
            }

            @Override
            public ViewModelState[] newArray(int size) {
                return new ViewModelState[size];
            }
        };
    }
}
