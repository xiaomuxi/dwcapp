package com.network.library.utils;

import android.util.Log;

public class Logger {
    private static final boolean DEBUG = true;
    private static final String DEBUG_TAG = "dwcapp_lxx";

    public static final void V(String verbose) {
        if (DEBUG)
            Log.v(DEBUG_TAG, verbose);
    }

    public static final void D(String debug) {
        if (DEBUG)
            Log.d(DEBUG_TAG, debug);
    }

    public static final void I(String info) {
        if (DEBUG)
            Log.i(DEBUG_TAG, info);
    }

    public static final void W(String warn) {
        if (DEBUG)
            Log.w(DEBUG_TAG, warn);
    }

    public static final void E(String error) {
        if (DEBUG)
            Log.e(DEBUG_TAG, error);
    }
}
