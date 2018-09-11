package com.weddingcar.driver.function.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类名称：修改密码
 * 类功能：用户修改登录密码
 * 类作者：YinLJ
 * 类日期：2017-02-21
 **/
public class ResetPwdActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.et_new_pwd)
    EditText et_new_pwd;
    @BindView(R.id.et_again_pwd)
    EditText et_again_pwd;
    @BindView(R.id.btn_ensure)
    Button btn_ensure;

    private String phone = "";
    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("重设密码");
    }

    @Override
    protected void initView() {
        super.initView();

//        et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
//        et_again_pwd = (EditText) findViewById(R.id.et_again_pwd);
//        btn_ensure = (Button) findViewById(R.id.btn_ensure);

        btn_ensure.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void onResume() {
        super.onResume();
        et_again_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (0 < et_again_pwd.getText().toString().length() && 0 < et_again_pwd.getText().toString().length()) {
                    btn_ensure.setTextColor(getResources().getColor(R.color.text_white));
                    btn_ensure.setSelected(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s.toString())) {
                    btn_ensure.setTextColor(getResources().getColor(R.color.text_main_gray));
                    btn_ensure.setSelected(false);
                }
            }
        });
    }

    /**
     * 确认修改按钮点击事件
     */
    private void ensureResetPasswordClick(){
        if (!checkInputDataValid(et_new_pwd, et_again_pwd)) {
            return;
        }

        if(StringUtils.isEmpty(phone)){
            UIUtils.showToastSafe("获取手机号失败，请返回上一页重新验证！");
            return;
        }

        // 确认修改密码请求
//        UserNetManager.ensureResetPwdRequest(mEnsureResetTask,phone,et_new_pwd.getText().toString());
    }


    /**
     * 返回登录界面
     */
    private void goBackLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    /**
     * 检查输入数据的有效性
     */
    private boolean checkInputDataValid(EditText etPassword1, EditText etPassword2) {

        if (null != etPassword1) {
            String password1 = etPassword1.getText().toString();
            if (StringUtils.isEmpty(password1)) {
                UIUtils.showToastSafe("新密码不能为空！");
                etPassword1.requestFocus();
                return false;
            }

            if(etPassword1.length() < 8 || etPassword1.length() > 20){
                UIUtils.showToastSafe("请输入8-20位的新密码");
                etPassword1.requestFocus();
                return false;
            }
        }
        if (null != etPassword2) {
            String password2 = etPassword2.getText().toString();
            if (StringUtils.isEmpty(password2)) {
                UIUtils.showToastSafe("再次输入的密码不能为空");
                etPassword2.requestFocus();
                return false;
            }
        }

        if (null != etPassword1 && null != etPassword2) {
            String password1 = etPassword1.getText().toString().trim();
            String password2 = etPassword2.getText().toString().trim();
            if (!StringUtils.equals(password1, password2)) {
                UIUtils.showToastSafe("两次输入的密码不一致！");
                etPassword2.requestFocus();
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ensure:
                ensureResetPasswordClick();
                break;
        }
    }
}
