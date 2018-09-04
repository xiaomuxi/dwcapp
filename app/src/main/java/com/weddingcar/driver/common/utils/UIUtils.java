package com.weddingcar.driver.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.weddingcar.driver.BuildConfig;
import com.weddingcar.driver.common.base.BaseApplication;

import java.util.List;

/**
 * UI handing util
 */
public class UIUtils {

    public static Context getContext() {
        return BaseApplication.getApplication();
    }

    public static Thread getMainThread() {
        return BaseApplication.getMainThread();
    }

    private static long getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    /**
     * dip to px
     */
    public static int dipTopx(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz to dip
     */
    public static int pxTodip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * Get main thread handler
     */
    private static Handler getHandler() {
        return BaseApplication.getMainThreadHandler();
    }

    /**
     * Delay executing runnable in main thread
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * Executing runnable in main thread
     */
    private static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * Remove the runnable in main thread
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    /**
     * Remove all runnable and messages in main thread
     */
    public static void removeCallbacksAndMessage() {
        getHandler().removeCallbacksAndMessages(null);
    }

    /**
     *
     * @param resId
     * @return
     */
    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    public static View inflate(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }
    /**
     * Get resources
     */
    private static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * Get string in resources
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * Get string array from resources
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * Get dimens from resources
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * Get drawable from resources
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * Get color from resources
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * get color state list
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    /**
     * Determine whether is in main thread currently
     */
    private static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * Execute the runnable in main thread
     */
    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * Show toast in main thread
     */
    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }

    /**
     * Show toast when build type is debuging
     * @param resId
     */
    public static void showToastDebug(final int resId) {
        if (BuildConfig.DEBUG) {
            showToastSafe(resId);
        }
    }

    /**
     * Show toast when build type is debuging
     */
    public static void showToastDebug(String str) {
        if (BuildConfig.DEBUG) {
            showToastSafe(str);
        }
    }

    /**
     * Show toast in main thread
     */
    public static void showToastSafe(final String str) {
        if (isRunInMainThread()) {
            showToast(str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str);
                }
            });
        }
    }

    /**
     * Show toast
     */
    private static void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * Get status bar height
     * @param context
     * @return result px
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    private static long lastClickTime;
    /**
     * Check double click
     */
    public static boolean isFastDoubleClick(int delayTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < delayTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    /**
     * Checking if application is running in foreground
     * @param context
     * @return result
     */
    public static boolean isApplicationRunningForeground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            if(processInfo.processName.equals(context.getPackageName())) {
                return (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                        || processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE);
            }
        }
        return false;
    }
}


