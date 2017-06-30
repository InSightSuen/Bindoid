package com.insightsuen.bindroid.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.insightsuen.bindroid.viewmodel.BaseViewModel;
import com.insightsuen.bindroid.viewmodel.holder.FragmentViewModelHolder;
import com.insightsuen.bindroid.viewmodel.holder.SupportFragmentViewModelHolder;
import com.insightsuen.bindroid.viewmodel.holder.ViewModelHolder;

/**
 * Created by InSight Suen on 2017/5/25.
 * Util of ViewModel
 */
public final class ViewModelUtil {

    private ViewModelUtil() {
        // no instance
    }

    public static <VM extends BaseViewModel> void addToFragmentManager(FragmentManager fragmentManager, VM viewModel, String tag) {
        Fragment holder = FragmentViewModelHolder.newInstance(viewModel);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(holder, tag);
        transaction.commit();
    }

    public static <VM extends BaseViewModel> void addToFragmentManager(android.support.v4.app.FragmentManager fragmentManager, VM viewModel, String tag) {
        android.support.v4.app.Fragment holder = SupportFragmentViewModelHolder.newInstance(viewModel);
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(holder, tag);
        transaction.commit();
    }

    @SuppressWarnings("unchecked")
    public static <VM extends BaseViewModel> VM findFromFragmentManger(FragmentManager fragmentManager, String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment instanceof ViewModelHolder) {
            return (VM) ((ViewModelHolder) fragment).getViewModel();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <VM extends BaseViewModel> VM findFromFragmentManger(android.support.v4.app.FragmentManager fragmentManager, String tag) {
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment instanceof ViewModelHolder) {
            return (VM) ((ViewModelHolder) fragment).getViewModel();
        }
        return null;
    }

}
