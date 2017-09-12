package com.insightsuen.bindroid.sample.base;

import android.databinding.ViewDataBinding;

import com.insightsuen.bindroid.component.BindActivity;
import com.insightsuen.bindroid.sample.contract.Const;

/**
 * Created by InSight Suen on 2017/5/25.
 * Base Activity
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends BindActivity<T> {

    protected static final boolean DEBUG = Const.DEBUG;
    public static final String TAG_VIEW_MODEL = "ActivityViewModel";

}
