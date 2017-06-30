package com.insightsuen.bindroid.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by InSight Suen on 2017/6/11.
 * {@link SwipeRefreshLayout} data binding adapters
 */

@BindingMethods({
        @BindingMethod(type = SwipeRefreshLayout.class, attribute = "onRefresh", method = "setOnRefreshListener")
})
public final class SwipeRefreshLayoutBindingAdapter {

    @BindingAdapter(value = {"onRefresh", "refreshingAttrChanged"}, requireAll = false)
    public static void setOnRefreshListener(SwipeRefreshLayout view,
            final SwipeRefreshLayout.OnRefreshListener listener,
            final InverseBindingListener attrChange) {
        SwipeRefreshLayout.OnRefreshListener newValue = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (attrChange != null) {
                    attrChange.onChange();
                }
                listener.onRefresh();
            }
        };
        view.setOnRefreshListener(newValue);
    }

    @InverseBindingAdapter(attribute = "refreshing", event = "refreshingAttrChanged")
    public static boolean isRefreshing(SwipeRefreshLayout view) {
        return view.isRefreshing();
    }
}
