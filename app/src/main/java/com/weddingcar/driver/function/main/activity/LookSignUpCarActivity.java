package com.weddingcar.driver.function.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.bean.user.response.SignUpInfoEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.GlideUtils;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetSignUpListView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class LookSignUpCarActivity extends BaseActivity implements BaseNetView,
        GetSignUpListView {

    @BindView(R.id.order_number)
    TextView mOrderNumber;
    @BindView(R.id.order_cancel_sign)
    TextView mOrderCancelSign;
    @BindView(R.id.order_user_icon_view)
    CircleImageView mOrderUserIconView;
    @BindView(R.id.order_time_tx_view)
    TextView mOrderTimeTxView;
    @BindView(R.id.order_user_name)
    TextView mOrderUserName;
    @BindView(R.id.order_car_type_tx_view)
    TextView mOrderCarTypeTxView;
    @BindView(R.id.order_duration_tx_view)
    TextView mOrderDurationTxView;
    @BindView(R.id.order_road_tx_view)
    TextView mOrderRoadTxView;
    @BindView(R.id.order_space_tx_view)
    TextView mOrderSpaceTxView;
    @BindView(R.id.order_car_number)
    TextView mOrderCarNumber;
    @BindView(R.id.order_car_average)
    TextView mOrderCarAverage;
    @BindView(R.id.order_info_view)
    TextView mOrderInfoView;
    @BindView(R.id.sign_list)
    ListView mSignUpListView;
    private OrderWaitListEntity mOrderEntity;
    private NetworkController<BaseNetView> mController;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        mOrderEntity = (OrderWaitListEntity) getIntent().getSerializableExtra("orderNumber");
        mController = new NetworkController<>();
        mController.attachView(this);
        String userId = SPController.getInstance().getUserInfo().getUserId();
        if (null == userId || userId.isEmpty()) userId = "18616367480";
        String orderId = mOrderEntity.getCode();
        mController.getSignUpList("HC010311", userId, orderId);
    }

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_look_sign_up_car);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitle(UIUtils.getString(R.string.sign_up_car));
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mController.detachView();
    }

    @Override
    public void showLoading() {
        showProcess("查看报名车辆中");
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

    }

    @Override
    public void onGetSignUpListSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            String status = baseEntity.getStatus();
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                UIUtils.showToastSafe(msg);
                return;
            }
            List<SignUpInfoEntity> signUpInfoList = (List<SignUpInfoEntity>) baseEntity.getData();
            for (SignUpInfoEntity signUpInfoEntity : signUpInfoList) {
                String code = signUpInfoEntity.getCode();
                if (null != code && code.equals(mOrderEntity.getCode())) {
                    setSignUpInfoMsg(signUpInfoEntity);
                }
            }
            Logger.I("onGetSignUpListSuccess " + baseEntity.toString());
        }
    }

    private void setSignUpInfoMsg(SignUpInfoEntity signUpInfoEntity) {
        mOrderNumber.setText("订单号:" + signUpInfoEntity.getCode());
        String userHeadUrl = Config.getAppHtmlUrl() + "LJTP/CATP/" + signUpInfoEntity.getCustomerAvator();
        Logger.I("setSignUpInfoMsg userHeadUrl = " + userHeadUrl);
        GlideUtils.loadShow(this, userHeadUrl, mOrderUserIconView);
        mOrderTimeTxView.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(Long.parseLong(signUpInfoEntity.getTheWeddingDate()))));
        String sex = signUpInfoEntity.getCustomerSex();
        if (sex.equals("男")) {
            mOrderUserName.setText(signUpInfoEntity.getCustomerName() + "先生");
        } else if (sex.equals("女")) {
            mOrderUserName.setText(signUpInfoEntity.getCustomerName() + "女士");
        }
        mOrderCarTypeTxView.setText(signUpInfoEntity.getCarBrandName() + signUpInfoEntity.getCarModelName());
        mOrderDurationTxView.setText(signUpInfoEntity.getHourChoose() + "小时");
        mOrderRoadTxView.setText(signUpInfoEntity.getJourneyChoose() + "公里");
        mOrderSpaceTxView.setText(signUpInfoEntity.getAreaName());
        mOrderCarNumber.setText("当前报名车辆:" + signUpInfoEntity.getOfferCount() + "辆");
        mOrderCarAverage.setText("当前平均价:" + signUpInfoEntity.getAmountAverage() + "元");

    }

    @OnClick(R.id.order_cancel_sign)
    public void onCancelSignClicked() {

    }

    @OnClick(R.id.order_info_view)
    public void onOrderInfoViewClicked() {

    }
}
