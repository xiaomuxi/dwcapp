package com.weddingcar.driver.function.main.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.UIUtils;

public class MainActivity extends BaseActivity {

    private Context context;
    private long exitTime = 0;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitle("主界面");

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
                return false;
            } else {
                this.finish();
                System.exit(0);
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
