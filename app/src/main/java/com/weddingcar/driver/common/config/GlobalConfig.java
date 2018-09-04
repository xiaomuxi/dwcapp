package com.weddingcar.driver.common.config;

import android.content.Context;

import com.weddingcar.driver.common.utils.FileUtils;
import com.weddingcar.driver.common.utils.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * Global config
 */
public class GlobalConfig {

    private static final String ROOT_DIR = "DWCApp";
    private static final String DOWNLOAD_DIR = "download";
    private static final String IMAGE_DIR = ".image";
    private static final String LOG_DIR = ".log";
    private static final String CACHE_DIR = ".cache";

    // device info
    // sd card path
    private String sdPath;
    // sd root path
    private String sdRootPath;
    // download path
    private String downloadPath;
    // image path
    private String imagePath;
    // sd cache path
    public String sdCachePath;
    // sd log path
    private String sdLogPath;
    // db path
    private String databasePath;

    // globalConfig instance
    private static GlobalConfig globalInstance;

    public static GlobalConfig getInstance() {
        if (globalInstance == null) {
            globalInstance = new GlobalConfig();
        }

        return globalInstance;
    }

    public static void init(Context context) {
        getInstance().initThis(context);
    }

    /**
     * Init the config
     */
    private void initThis(Context context) {
        // init file path
        String fileSeparator = System.getProperty("file.separator");
        // sd dir path
        sdPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        // app project dir path in sd
        String projectName = ROOT_DIR;
        sdRootPath = sdPath + fileSeparator + projectName + fileSeparator;
        // db path
        databasePath = context.getFilesDir() + "db" + fileSeparator;
        //download path
        downloadPath = sdRootPath + DOWNLOAD_DIR + fileSeparator;
        // image path
        imagePath = sdRootPath + IMAGE_DIR + fileSeparator;
        // cache path
        sdCachePath = sdRootPath + CACHE_DIR + fileSeparator;
        // log path
        sdLogPath = sdRootPath + LOG_DIR + fileSeparator;

        // init db, download, image, cache and log path
        checkAndCreatePrivateDirectory();

        // init cache path
        checkAndCreateSdDirectory();
    }

    /**
     * Check and create private dir
     */
    private void checkAndCreatePrivateDirectory() {
        String[] path = {sdRootPath, databasePath, downloadPath, imagePath, sdCachePath, sdLogPath};
        // firstly create sd/xxx，then create /sd/xxx/image
        for (String x : path) {
            File file = new File(x);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    /**
     * Delete the cache and cache image path
     */
    private void checkAndCreateSdDirectory() {
        if ((!StringUtils.isEmpty(sdPath) && new File(sdPath).canRead())) {

            String[] path = {sdCachePath, imagePath};
            for (String x : path) {
                File file = new File(x);

                if (!file.exists()) {
                    file.mkdir();
                } else {
                    FileUtils.DeleteFile(x);
                    file.mkdir();
                }
            }

            // forbid Media search image path
            String nomediapath = imagePath + ".nomedia";
            File nomedia = new File(nomediapath);

            try {
                if (!nomedia.exists()) {
                    nomedia.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
