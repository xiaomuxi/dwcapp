package com.weddingcar.driver.common.app;


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
    }
}
