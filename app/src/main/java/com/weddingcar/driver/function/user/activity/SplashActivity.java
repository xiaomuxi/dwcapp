package com.weddingcar.driver.function.user.activity;

import android.content.Intent;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.activity.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFirstInstall();
    }

    private void initFirstInstall() {
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirstInstall = SPController.getInstance().getBoolean(SPController.IS_APP_FIRST_INSTALL, true);
                if (isFirstInstall) {
                    SPController.getInstance().putBoolean(SPController.IS_APP_FIRST_INSTALL, false);
                    //first install go to guide activity
                    goToWelcomeGuideActivity();
                    return;
                }
                checkAutoLogin();
            }
        }, 1500);
    }

    /**
     * 跳转到引导界面
     */
    private void goToWelcomeGuideActivity() {
        Intent intent = new Intent(this, WelcomeGuideActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转到登录界面
     */
    private void goToLoginActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转到首页
     */
    private void goToHomeActivity() {
        Intent intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkAutoLogin() {
        boolean isAutoLogin = SPController.getInstance().getBoolean(SPController.IS_USER_AUTO_LOGIN, false);
        if (isAutoLogin) {
            goToHomeActivity();
        }else {
            goToLoginActivity();
        }
    }
}
