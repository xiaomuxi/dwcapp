package com.weddingcar.driver.function.user.activity;

import android.view.KeyEvent;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.UIUtils;

/**
 * Created by inrokei on 2018/9/4.
 */

public class LoginActivity extends BaseActivity {

    private long exitTime = 0;
    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitle("登陆");
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                UIUtils.showToastSafe("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
                System.exit(0);
            }
        }
        return false;
    }

}
