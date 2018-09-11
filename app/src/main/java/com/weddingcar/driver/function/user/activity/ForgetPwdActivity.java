package com.weddingcar.driver.function.user.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.ui.VerifyCodeView;
import com.weddingcar.driver.common.utils.CheckUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPwdActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_verify_code)
    EditText et_verify_code;
    @BindView(R.id.btn_next_step)
    Button btn_next_step;
    @BindView(R.id.vcv_code)
    VerifyCodeView vcv_code;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("忘记密码");
    }

    @Override
    protected void initView() {
        super.initView();
        btn_next_step.setOnClickListener(this);

        initTextChangeListener();
        initVerifyCodeView();
    }

    private void initTextChangeListener() {
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (0 < et_verify_code.getText().toString().length() && 0 < et_phone.getText().toString().length()) {
                    btn_next_step.setTextColor(getResources().getColor(R.color.text_white));
                    btn_next_step.setSelected(true);
                }

                if(0 < et_phone.getText().toString().trim().length()){
                    vcv_code.setBackgroundResource(R.drawable.selector_button_verify_code_red);
                    vcv_code.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s.toString())) {
                    vcv_code.setBackgroundResource(R.drawable.selector_button_verify_code_gray);
                    vcv_code.setClickable(false);
                    btn_next_step.setTextColor(getResources().getColor(R.color.text_main_gray));
                    btn_next_step.setSelected(false);
                }
            }
        });
        et_verify_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (0 < et_verify_code.getText().toString().length() && 0 < et_phone.getText().toString().length()) {
                    btn_next_step.setTextColor(getResources().getColor(R.color.text_white));
                    btn_next_step.setSelected(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s.toString())) {
                    btn_next_step.setTextColor(getResources().getColor(R.color.text_main_gray));
                    btn_next_step.setSelected(false);
                }
            }
        });
    }

    private void initVerifyCodeView() {
        vcv_code.setMaxTime(60);
        vcv_code.setClickable(false);
        vcv_code.setSendCodeListener(new VerifyCodeView.OnSendCodeListener() {
            @Override
            public void onStartSend() {
                hideKeyBoard();
                getSmsVerifyCode();
            }
        });
    }

    /**
     * 获取短信验证码
     */
    private void getSmsVerifyCode() {
        if (!checkInputDataValid(et_phone, null)) {
            return;
        }
        String phone = et_phone.getText().toString().trim();
        // 获取短信验证码请求
        vcv_code.startToCountDown();
//        UserNetManager.fetchVerifyCodeRequest(mGetVerifyCodeTask,UserNetManager.VERIFY_CODE_FIND_PASSWORD,phone);
    }

    /**
     * 跳转修改密码界面
     */
    private void goToResetPwdActivity(String phone){
        Intent intent = new Intent(this,ResetPwdActivity.class);
        intent.putExtra("phone",phone);
        startActivity(intent);
    }

    /**
     * 检查输入数据的有效性
     */
    private boolean checkInputDataValid(EditText etMobile, EditText etVerifyCode) {
        if (null != etMobile) {
            String mobile = etMobile.getText().toString().trim();
            if (StringUtils.isEmpty(mobile)) {
                UIUtils.showToastSafe("手机号码不能为空！");
                etMobile.requestFocus();
                return false;
            }
            if (!CheckUtils.checkMobile(mobile, false)) {
                UIUtils.showToastSafe("无效的手机号码！");
                etMobile.requestFocus();
                return false;
            }
        }
        if (null != etVerifyCode) {
            String verifyCode = etVerifyCode.getText().toString().trim();
            if (StringUtils.isEmpty(verifyCode)) {
                UIUtils.showToastSafe("验证码不能为空！");
                etVerifyCode.requestFocus();
                return false;
            }
        }
        return true;
    }

    /**
     * 验证手机按钮点击事件
     */
    private void nextStepClick() {
        if (!checkInputDataValid(et_phone, et_verify_code)) {
            return;
        }
        String phone = et_phone.getText().toString().trim();
        String verifyCode = et_verify_code.getText().toString().trim();
        // 验证手机请求
//        UserNetManager.verifyPhoneInFindPwdRequest(mVerifyPhoneTask,phone,verifyCode);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_step:
//                nextStepClick();
                goToResetPwdActivity("h");
                break;
        }
    }
}
