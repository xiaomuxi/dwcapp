package com.weddingcar.driver.common.utils;

import android.os.Environment;

import java.io.File;

public class FileUtils {

    /**
     * Determine whether the SD card is mounted
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Create a folder
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        return !(!file.exists() || !file.isDirectory()) || file.mkdirs();
    }

    /**
     * Modify file permissions
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /**
     * Delete the specified file
     * @param path
     * @return result
     */
    public static boolean DeleteFile(String path) {
        File f = new File(path);
        boolean ret = true;

        if (!f.exists()) {
            ret = false;
        } else if (f.exists()) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (!DeleteFile(files[i].getPath())) {
                        ret = false;
                    }
                }
            } else if (!f.delete()) {
                ret = false;
            }
        }
        return ret;
    }
}
