package com.weddingcar.driver.function.user.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.ui.VerifyCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_again_pwd)
    EditText et_again_pwd;
    @BindView(R.id.et_verify_code)
    EditText et_verify_code;
    @BindView(R.id.vcv_code)
    VerifyCodeView vcv_code;
    @BindView(R.id.btn_register)
    Button btn_register;


    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("注册");
    }

    @Override
    protected void initView() {
        super.initView();
        btn_register.setOnClickListener(this);
    }

    private void goToPersonalInfoActivity() {
        Intent intent = new Intent(this, PersonalInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                goToPersonalInfoActivity();
                break;
        }
    }

}
