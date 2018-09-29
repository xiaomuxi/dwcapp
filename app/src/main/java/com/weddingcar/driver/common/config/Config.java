package com.weddingcar.driver.common.config;


import com.weddingcar.driver.BuildConfig;
import com.weddingcar.driver.common.utils.StringUtils;

/**
 * Global config
 */

public class Config {
    private static boolean isDebug = BuildConfig.DEBUG;

    private static final String APP_BASE_URL_DEV = "http://139.196.254.89:8080/";
    private static final String APP_BASE_URL_TEST = "http://139.196.254.89:8080/";
    private static final String APP_BASE_URL_PRO = "http://139.196.254.89:8080/";
    private static final String APP_BASE_URL_USER_AVATOR = "http://139.196.254.89:8080/LJTP/CATP/";
    private static final String APP_BASE_URL_CAR_BRANDS = "http://139.196.254.89:8080/LJTP/Logo/";

    /**
     * Determine whether is test environment currently
     */
    public boolean isTestEnvironment() {
        return isDebug || StringUtils.equals(BuildConfig.FLAVOR, "staging");
    }

    /**
     * Get app html url by flavor
     * @return result   app html url
     */
    public static String getAppHtmlUrl() {
        if (isDebug) {
            return APP_BASE_URL_DEV;
        }

        return APP_BASE_URL_PRO;
    }

    /**
     * Get the app base url of development environment
     */
    public static String getAppBaseUrlDev() {
        return APP_BASE_URL_DEV;
    }

    /**
     * Get the app base url of staging(test) environment
     */
    public static String getAppBaseUrlTest() {
        return APP_BASE_URL_TEST;
    }

    /**
     * Get the app base url of production environment
     */
    public static String getAppBaseUrlPro() {
        return APP_BASE_URL_PRO;
    }

    public static String getCarBrandsBaseUrl() {
        return APP_BASE_URL_CAR_BRANDS;
    }

    public static String getUserAvatorBaseUrl() {
        return APP_BASE_URL_USER_AVATOR;
    }
}
