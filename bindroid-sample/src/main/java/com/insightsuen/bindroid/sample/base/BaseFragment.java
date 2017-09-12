package com.insightsuen.bindroid.sample.base;

import android.databinding.ViewDataBinding;

import com.insightsuen.bindroid.component.BindFragment;
import com.insightsuen.bindroid.sample.contract.Const;

/**
 * Created by InSight Suen on 2017/5/27.
 * Base Fragment
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends BindFragment<T> {

    protected static final boolean DEBUG = Const.DEBUG;

}
