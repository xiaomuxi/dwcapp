package com.weddingcar.driver.common.app;


import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.network.library.utils.Logger;
import com.network.library.utils.RetrofitUtil;
import com.weddingcar.driver.common.base.BaseApplication;
import com.weddingcar.driver.common.config.GlobalConfig;
import com.weddingcar.driver.common.manager.SPController;

import static com.weddingcar.driver.common.manager.SPController.ALI_PUSH_DEVICE_ID;

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
