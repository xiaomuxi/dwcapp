package com.weddingcar.driver.common.utils;

import android.util.Log;

import com.weddingcar.driver.BuildConfig;


/**
 * Log handing util
 */
public class LogUtils {
    /**
     * Log level note
     */
    public static final int LEVEL_NONE = 0;
    /**
     * Log level verbose
     */
    private static final int LEVEL_VERBOSE = 1;
    /**
     * Log level debug
     */
    private static final int LEVEL_DEBUG = 2;
    /**
     * Log level info
     */
    private static final int LEVEL_INFO = 3;
    /**
     * Log level warn
     */
    private static final int LEVEL_WARN = 4;
    /**
     * Log level error
     */
    private static final int LEVEL_ERROR = 5;

    /**
     * Log tag
     */
    private static String mTag = "DWCApp";
    /**
     * is debug
     */
    private static boolean isDebug =  BuildConfig.DEBUG;

    /**
     * is test environment
     */
    private static boolean isStagingEnv = "staging".equals(BuildConfig.FLAVOR);

    /**
     * Timestamp
     */
    private static long mTimestamp = 0;
    /**
     * Lock object which be used to write file
     */
    private static final Object mLogLock = new Object();

    /**
     * log verbose level
     */
    public static void v(String msg) {
        if (isDebug || isStagingEnv) {
            Log.v(mTag, msg);
        }
    }

    /**
     * log debug level
     */
    public static void d(String msg) {
        if (isDebug || isStagingEnv) {
            Log.d(mTag, msg);
        }
    }

    /**
     * log debug level
     */
    public static void d(String tag, String msg) {
        if (isDebug || isStagingEnv) {
            Log.d(tag, msg);
        }
    }
    /**
     * log info level
     */
    private static void i(String msg) {
        if (isDebug || isStagingEnv) {
            Log.i(mTag, msg);
        }
    }
    /**
     * log info level
     */
    public static void i(String tag, String msg) {
        if ((isDebug || isStagingEnv) && null != msg) {
            Log.i(tag, msg);
        }
    }

    /**
     * log warn level
     */
    public static void w(String msg) {
        if (isDebug || isStagingEnv) {
            Log.w(mTag, msg);
        }
    }

    /**
     * log warn level to log throwable
     */
    public static void w(Throwable tr) {
        if (isDebug || isStagingEnv) {
            Log.w(mTag, "", tr);
        }
    }

    /**
     * log warn level to log msg and throwable
     */
    public static void w(String msg, Throwable tr) {
        if ((isDebug || isStagingEnv) && null != msg) {
            Log.w(mTag, msg, tr);
        }
    }

    /**
     * log warn level to log message
     */
    public static void w(String tag, String msg) {
        if ((isDebug || isStagingEnv) && null != msg) {
            Log.w(tag, msg);
        }
    }

    /**
     * log error level
     */
    public static void e(String msg) {
        if (isDebug || isStagingEnv) {
            Log.e(mTag, msg);
        }
    }

    /**
     * log error level to log throwable
     */
    public static void e(Throwable tr) {
        if (isDebug || isStagingEnv) {
            Log.e(mTag, "", tr);
        }
    }

    /**
     * log error level to log message and throwable
     */
    public static void e(String msg, Throwable tr) {
        if ((isDebug || isStagingEnv) && null != msg) {
            Log.e(mTag, msg, tr);
        }
    }

    /**
     * log error level to log message and throwable
     */
    public static void e(String tag, String msg, Throwable tr) {
        if ((isDebug || isStagingEnv) && null != msg) {
            Log.e(tag, msg, tr);
        }
    }
}
