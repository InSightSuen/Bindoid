package com.insightsuen.bindroid.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by InSight Suen on 2017/6/10.
 */
public class HandlerUtils {

    private static final int THREAD_LEAK_CLEANING_MS = 1000;

    /**
     * From com.squareup.picasso.Utils
     *
     * Prior to Android 5, HandlerThread always keeps a stack local reference to the last message
     * that was sent to it. This method makes sure that stack local reference never stays there
     * for too long by sending new messages to it every second.
     */
    public static void flushStackLocalLeaks(Looper looper) {
        Handler handler = new Handler(looper) {
            @Override public void handleMessage(Message msg) {
                sendMessageDelayed(obtainMessage(), THREAD_LEAK_CLEANING_MS);
            }
        };
        handler.sendMessageDelayed(handler.obtainMessage(), THREAD_LEAK_CLEANING_MS);
    }
}
