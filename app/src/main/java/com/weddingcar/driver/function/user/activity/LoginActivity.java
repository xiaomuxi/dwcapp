package com.weddingcar.driver.function.user.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.network.library.bean.user.request.LoginRequest;
import com.network.library.bean.user.response.LoginEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.BaseNetView;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.IntentConstant;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.CheckUtils;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by inrokei on 2018/9/4.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, NormalView<LoginEntity>{

    private long exitTime = 0;

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_go)
    Button btn_go;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.tv_forget_password)
    TextView tv_forget_password;

    TextWatcher textWatcher;

    private NetworkController<BaseNetView> mController;
    private String mPhoneNum;

    @Override
    protected void init() {
        super.init();
        mPhoneNum = getIntent().getStringExtra(IntentConstant.EXTRA_MOBILE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
        btn_go.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);

        mController = new NetworkController<>();
        mController.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (0 < et_password.getText().toString().length() && 0 < et_phone.getText().toString().length()) {
                    btn_go.setEnabled(true);
                    btn_go.setSelected(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ("".equals(editable.toString())) {
                    btn_go.setEnabled(false);
                    btn_go.setSelected(false);
                }
            }
        };
        et_phone.addTextChangedListener(textWatcher);
        et_password.addTextChangedListener(textWatcher);
        et_phone.setText(StringUtils.isEmpty(mPhoneNum) ? SPController.getInstance().getString(SPController.USER_LAST_FILLED_PHONE, "") : mPhoneNum);
        et_phone.setSelection(et_phone.getText().length());
    }

    /**
     * 登录
     */
    public void toLogin() {
        if (!checkInsert()) {
            return;
        }

        LoginRequest req = new LoginRequest();
        LoginRequest.Request request = new LoginRequest.Request();
        request.setApiId("HC020103");
        request.setTel(et_phone.getText().toString().trim());
        request.setPassword(et_password.getText().toString());
        req.setQuery(request);
        mController.sendRequest(HttpAction.ACTION_LOGIN, req);
    }

    /**
     * 编辑框校验
     *
     * @return boolean值, 输入是否合法
     */
    private boolean checkInsert() {
        String phone = et_phone.getText().toString().trim();
        String pass = et_password.getText().toString();

        if ("".equals(phone)) {
            UIUtils.showToastSafe("手机号码不能为空！");
            return false;
        }

        if (!CheckUtils.checkMobile(phone, false)) {
            UIUtils.showToastSafe("无效的手机号码！");
            return false;
        }

        if ("".equals(pass)) {
            UIUtils.showToastSafe("密码不能为空！");
            return false;
        }

        return true;
    }

    /**
     * 跳转到注册界面
     */
    public void goToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Go to forget password activity
     */
    public void goToForgetPwdActivity() {
        Intent intent = new Intent(this, ForgetPwdActivity.class);
        startActivity(intent);
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go:
                toLogin();
                break;
            case R.id.tv_register:
                goToRegisterActivity();
                break;
            case R.id.tv_forget_password:
                goToForgetPwdActivity();
                break;
        }
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

    @Override
    public void onSuccess(LoginEntity entity) {
        if (StringUtils.equals("1", entity.getStatus()) && StringUtils.equals(entity.getCount(), "0")){
            UIUtils.showToastSafe("用户名或密码错误！");
            return;
        }

        LoginEntity.Data data = entity.getData().get(0);

        UserInfo info = new UserInfo();
        info.setUserId(data.getUserId());
        info.setSex(data.getSex());
        info.setName(data.getName());
        LogUtils.i(TAG, info.toString());
        SPController.getInstance().saveUserInfo(info);
        SPController.getInstance().putBoolean(SPController.IS_USER_AUTO_LOGIN, true);
        SPController.getInstance().putString(SPController.USER_LAST_FILLED_PHONE, et_phone.getText().toString().trim());

        goToHomeActivity();
    }

    @Override
    public void showLoading() {
        showProcess("正在登录...");
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
        LogUtils.i(TAG, errorMsg);
        UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
    }

}
