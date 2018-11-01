package com.weddingcar.driver.common.app;


import android.os.StrictMode;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
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

        initHX();
    }

    private void initHX() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //初始化
        EMClient.getInstance().init(this, options);
        EaseUI.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
