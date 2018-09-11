package com.weddingcar.driver.function.user.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.CheckUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by inrokei on 2018/9/4.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private long exitTime = 0;

    Unbinder unbinder;

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


    @Override
    protected void init() {
        super.init();
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
    }

    /**
     * 登录
     */
    public void toLogin() {
        if (!checkInsert()) {
            return;
        }
        // 登录请求
//        UserNetManager.loginRequest(mLoginTask, et_phone.getText().toString().trim(),et_password.getText().toString(),tv_other_account.isSelected());
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
}
