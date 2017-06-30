package com.insightsuen.bindroid.adapters;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Created by InSight Suen on 2017/6/30.
 * {@link ImageView} binding adapters
 */
public class ImageViewBindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView view, @DrawableRes int res) {
        view.setImageResource(res);
    }

    @BindingAdapter("android:src")
    public static void setImageBitmap(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

}
