package com.insightsuen.bindroid.adapters;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by InSight Suen on 2017/6/30.
 * {@link View} binding adapters
 */
public class ViewBindingAdapters {

    @BindingAdapter("android:width")
    public static void setWidth(View view, int width) {
        view.getLayoutParams().width = width;
        view.requestLayout();
    }

    @BindingAdapter("android:height")
    public static void setHeight(View view, int height) {
        view.getLayoutParams().height = height;
        view.requestLayout();
    }

    @BindingAdapter("android:enabled")
    public static void setEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @BindingAdapter("android:layout_weight")
    public static void setWeight(View view, float weight) {
        if (view.getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.weight = weight;
            view.requestLayout();
        }
    }

}
