package com.weddingcar.driver.common.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

/**
 * Base application
 */
public class BaseApplication extends Application {
    //Global context
    private static BaseApplication mInstance;
    //Main thread id
    private static int mMainThreadId = -1;
    //Main thread
    private static Thread mMainThread;
    //Main thread handler
    private static Handler mMainThreadHandler;
    //Main thread looper
    private static Looper mMainLooper;

    @Override
    public void onCreate() {
        super.onCreate();
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
        String processName = getProcessName(this, android.os.Process.myPid());
        if (null == processName || processName.equals(getApplication().getPackageName())) {
            initSystems();
        }
    }

    /**
     * Init system method
     */
    public void initSystems() {}

    /**
     * Get application instance
     */
    public static BaseApplication getApplication() {
        return mInstance;
    }

    /**
     * Get the main thread id
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * Get the main thread
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * Get the main thread handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * Get the main thread looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    /**
     * Get process name by pid
     * @param cxt   context
     * @param pid   app process id
     * @return processName
     */
    public String getProcessName(Context cxt, int pid) {
        try {
            ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
            if (null != runningApps) {
                for (ActivityManager.RunningAppProcessInfo processInfo : runningApps) {
                    if (processInfo.pid == pid) {
                        return processInfo.processName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
