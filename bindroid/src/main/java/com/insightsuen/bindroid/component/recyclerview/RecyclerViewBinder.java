package com.insightsuen.bindroid.component.recyclerview;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.util.Log;

import com.insightsuen.bindroid.utils.HandlerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by InSight Suen on 2017/6/10.
 * RecyclerView Binder
 */
public class RecyclerViewBinder<Item> {

    private static final String TAG = "RecyclerViewBinder";

    private static final boolean DEBUG = true;

    public interface UpdateDataCallbacks {

        void onUpdateStart();

        void onUpdateFinished();

    }

    private static final int MSG_CAL_RESULT = 1;
    private static final int MSG_NOTIFY_RESULT = 2;

    private BindAdapter<Item> mAdapter;
    private DiffCallBacks<Item> mDiffCallBacks;
    private List<Item> mCurrentData;
    private Handler mMainHandler;
    private Handler mWorkHandler;
    private HandlerThread mWorkThread;

    private boolean mEnableDetectMoves = false;
    private boolean mEnableDispatch = false;
    private UpdateDataCallbacks mUpdateDataCallbacks = null;

    public RecyclerViewBinder(BindAdapter<Item> adapter, DiffCallBacks<Item> callback) {
        mAdapter = adapter;
        mDiffCallBacks = callback;

        mMainHandler = new MainHandler(this);
        mWorkThread = new HandlerThread("BinderWorkThread", Process.THREAD_PRIORITY_BACKGROUND);
        mWorkThread.start();
        HandlerUtils.flushStackLocalLeaks(mWorkThread.getLooper());
        mWorkHandler = new DispatchHandler(mWorkThread.getLooper(), this);
    }

    public void shutdown() {
        mWorkThread.quit();
    }

    public void enableDispatch(UpdateDataCallbacks callbacks) {
        if (callbacks == null) {
            throw new NullPointerException("UpdateDataCallbacks == null");
        }
        if (mUpdateDataCallbacks != null) {
            throw new IllegalStateException("Already enable dispatch");
        }
        mUpdateDataCallbacks = callbacks;
        mEnableDispatch = true;
    }

    public void disableDispatch() {
        mEnableDispatch = false;
        mUpdateDataCallbacks = null;
    }

    public boolean isEnableDispatch() {
        return mEnableDispatch;
    }

    public void onUpdateData(List<Item> newData) {
        if (mEnableDispatch) {
            mUpdateDataCallbacks.onUpdateStart();
        }

        List<Item> copy = new ArrayList<>(newData);
        mDiffCallBacks.onNewData(mCurrentData, copy);
        mCurrentData = copy;

        if (mEnableDispatch) {
            mWorkHandler.obtainMessage(MSG_CAL_RESULT).sendToTarget();
        } else {
            calResult();
        }
    }

    private void calResult() {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(mDiffCallBacks, mEnableDetectMoves);
        if (mEnableDispatch) {
            mMainHandler.obtainMessage(MSG_NOTIFY_RESULT, result).sendToTarget();
        } else {
            dispatchResult(result);
        }
    }

    private void dispatchResult(DiffUtil.DiffResult result) {
        if (mEnableDispatch) {
            mUpdateDataCallbacks.onUpdateFinished();
        }
        mAdapter.setItems(mCurrentData);
        if (DEBUG) {
            result.dispatchUpdatesTo(new ListUpdateCallback() {
                @Override
                public void onInserted(int position, int count) {
                    Log.d(TAG, "onInsert position=" + position + " count=" + count);
                    mAdapter.notifyItemRangeInserted(position, count);
                }

                @Override
                public void onRemoved(int position, int count) {
                    Log.d(TAG, "onRemoved position=" + position + " count=" + count);
                    mAdapter.notifyItemRangeRemoved(position, count);
                }

                @Override
                public void onMoved(int fromPosition, int toPosition) {
                    Log.d(TAG, "onMoved fromPosition=" + fromPosition + " toPosition=" + toPosition);
                    mAdapter.notifyItemMoved(fromPosition, toPosition);
                }

                @Override
                public void onChanged(int position, int count, Object payload) {
                    Log.d(TAG, "onChanged position=" + position + " count=" + count + " payload=" + payload);
                    mAdapter.notifyItemRangeChanged(position, count, payload);
                }
            });
        } else {
            result.dispatchUpdatesTo(mAdapter);
        }
    }

    public void setDetectMoves(boolean enableDetectMoves) {
        mEnableDetectMoves = enableDetectMoves;
    }

    public boolean isDetectMoves() {
        return mEnableDetectMoves;
    }

    private static class MainHandler extends Handler {

        private RecyclerViewBinder mBinder;

        MainHandler(RecyclerViewBinder binder) {
            super(Looper.getMainLooper());
            mBinder = binder;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_NOTIFY_RESULT:
                    DiffUtil.DiffResult result = (DiffUtil.DiffResult) msg.obj;
                    mBinder.dispatchResult(result);
                    break;

                default:
                    break;
            }
        }
    }

    private static class DispatchHandler extends Handler {

        private RecyclerViewBinder mBinder;

        DispatchHandler(Looper looper, RecyclerViewBinder binder) {
            super(looper);
            mBinder = binder;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CAL_RESULT:
                    mBinder.calResult();
                    break;

                default:
                    break;
            }
        }
    }
}
