package com.weddingcar.driver.common.app;


import android.os.StrictMode;

import com.network.library.utils.RetrofitUtil;
import com.weddingcar.driver.common.base.BaseApplication;
import com.weddingcar.driver.common.config.GlobalConfig;
import com.weddingcar.driver.common.manager.SPController;

/**
 * driver wedding car Application
 */

public class DWCApp extends BaseApplication {

    @Override
    public void initSystems() {
        super.initSystems();
        GlobalConfig.init(this);
        SPController.getInstance().init(this);
        RetrofitUtil.getInstance(this);

        //7.0闪退
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
