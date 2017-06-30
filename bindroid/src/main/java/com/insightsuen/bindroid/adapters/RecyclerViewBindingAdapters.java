package com.insightsuen.bindroid.adapters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.insightsuen.bindroid.component.recyclerview.RecyclerViewBindable;

/**
 * Created by InSight Suen on 2017/6/28.
 * {@link RecyclerView}
 */

public final class RecyclerViewBindingAdapters {

    @BindingAdapter("bind")
    public static void bind(RecyclerView view, RecyclerViewBindable bindable) {
        bindable.bind(view);
    }
}
