package com.weddingcar.driver.function.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.MaterialDialog;
import com.weddingcar.driver.function.user.activity.ForgetPwdActivity;
import com.weddingcar.driver.function.user.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tv_modify_password)
    TextView tv_modify_password;
    @BindView(R.id.btn_logout)
    Button btn_logout;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("设置");
    }

    @Override
    protected void initView() {
        super.initView();

        tv_modify_password.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_modify_password:
                goToVerifyPhoneActivity();
                break;
            case R.id.btn_logout:
                showLoginOutDialog();
                break;
        }
    }

    /**
     * 显示退出登录的提示框
     */
    private void showLoginOutDialog() {
        final MaterialDialog dialog = new MaterialDialog(mContext);
        dialog.setTitle("");
        dialog.setMessage("确定退出登录状态？");
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPController.getInstance().logout();
                goToLoginActivity();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void goToVerifyPhoneActivity() {
        Intent intent = new Intent(this, ForgetPwdActivity.class);
        intent.putExtra("FROM", "MINE");
        startActivity(intent);
    }

    /**
     * 跳转登录界面
     */
    private void goToLoginActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
