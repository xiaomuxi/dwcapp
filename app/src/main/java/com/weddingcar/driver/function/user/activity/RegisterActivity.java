package com.weddingcar.driver.function.user.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.network.library.bean.user.response.RegisterEntity;
import com.network.library.bean.user.response.VerifyCodeEntity;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.config.IntentConstant;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.ui.VerifyCodeView;
import com.weddingcar.driver.common.utils.CheckUtils;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

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

    NetworkController<NormalView> mControllerVerifyCode;
    NetworkController<NormalView> mControllerRegister;

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
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_phone.getText().toString().length() > 0) {
                    vcv_code.setBackgroundResource(R.drawable.selector_button_verify_code_red);
                    vcv_code.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ("".equals(editable.toString())) {
                    vcv_code.setBackgroundResource(R.drawable.selector_button_verify_code_gray);
                    vcv_code.setClickable(false);
                }
            }
        });

        initVerifyCodeView();

        mControllerVerifyCode = new NetworkController<>();
        mControllerRegister = new NetworkController<>();
        mControllerVerifyCode.attachView(verifyCodeView);
        mControllerRegister.attachView(registerView);
    }

    private void initVerifyCodeView() {
        vcv_code.setMaxTime(60);
        vcv_code.setClickable(false);
        vcv_code.setSendCodeListener(new VerifyCodeView.OnSendCodeListener() {
            @Override
            public void onStartSend() {
                hideKeyBoard();
                String phone = et_phone.getText().toString().trim();
                if (checkInsert(phone, null, null, null)) {
                    mControllerVerifyCode.sendVerifyCode(phone, "1");
                }
            }
        });
    }

    private NormalView<VerifyCodeEntity> verifyCodeView = new NormalView<VerifyCodeEntity>() {
        @Override
        public void onSuccess(VerifyCodeEntity entity) {
            LogUtils.i(TAG, "-------"+entity.toString());
            VerifyCodeEntity.Data data = entity.getData().get(0);
            if (data == null) {
                UIUtils.showToastSafe(ToastConstant.TOAST_SERVER_IS_BUSY);
                return;
            }
            if (!StringUtils.equals(entity.getStatus(), "1") || !StringUtils.equals(entity.getCount(), "1")) {
                UIUtils.showToastSafe(entity.getMsg());
                return;
            }

            UIUtils.showToastSafe("验证码已发送");
            vcv_code.startToCountDown();
        }

        @Override
        public void showLoading() {
            showProcess("正在请求验证码...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            LogUtils.e(errorMsg);
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    private NormalView<RegisterEntity> registerView = new NormalView<RegisterEntity>() {
        @Override
        public void onSuccess(RegisterEntity entity) {
            RegisterEntity.Data data = entity.getData().get(0);
            if (data == null) {
                UIUtils.showToastSafe(ToastConstant.TOAST_SERVER_IS_BUSY);
                return;
            }
            if (!StringUtils.equals(entity.getStatus(), "1") || !StringUtils.equals(entity.getCount(), "1")) {
                UIUtils.showToastSafe(entity.getMsg());
                return;
            }

            UIUtils.showToastSafe("注册成功");
            goToPersonalInfoActivity();
        }

        @Override
        public void showLoading() {
            showProcess("正在注册账号...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            LogUtils.e(errorMsg);
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    private boolean checkInsert(String phone, String pwd, String pwdAgain, String verifyCode) {
        if (phone != null) {
            if ("".equals(phone)) {
                UIUtils.showToastSafe("手机号码不能为空！");
                return false;
            }

            if (!CheckUtils.checkMobile(phone, false)) {
                UIUtils.showToastSafe("无效的手机号码！");
                return false;
            }
        }

        if (pwd != null || pwdAgain != null) {
            if (StringUtils.isEmpty(pwd)) {
                UIUtils.showToastSafe("请输入密码！");
                return false;
            }
            if (pwd.length() < 8 || pwd.length() > 20) {
                UIUtils.showToastSafe("请输入8-20位密码！");
            }
            if (StringUtils.isEmpty(pwdAgain)) {
                UIUtils.showToastSafe("请再次输入密码！");
                return false;
            }
            if (!StringUtils.equals(pwd, pwdAgain)) {
                UIUtils.showToastSafe("两次密码输入不一致");
                return false;
            }
        }

        if (verifyCode != null && StringUtils.isEmpty(verifyCode)) {
            UIUtils.showToastSafe("请输入验证码！");
            return false;
        }

        return true;
    }

    private void goToPersonalInfoActivity() {
        Intent intent = new Intent(this, PersonalInfoActivity.class);
        intent.putExtra(IntentConstant.EXTRA_MOBILE, et_phone.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                goToRegister();
                break;
        }
    }

    private void goToRegister() {
        String phone = et_phone.getText().toString().trim();
        String pwd = et_pwd.getText().toString();
        String pwdAgain = et_again_pwd.getText().toString();
        String verifyCode = et_verify_code.getText().toString();
        if (checkInsert(phone, pwd, pwdAgain, verifyCode)) {
            mControllerRegister.register(phone, pwd, pwdAgain, verifyCode);
        }
    }

}
