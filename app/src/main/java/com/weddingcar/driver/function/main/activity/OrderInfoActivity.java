package com.weddingcar.driver.function.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.OrderInfoEntity;
import com.network.library.bean.user.response.OrderRunningListEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.bean.user.response.RobbingInfoEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.GlideUtils;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetOrderInfoView;
import com.network.library.view.SignUpOrderView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.MaterialDialog;
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

public class OrderInfoActivity extends BaseActivity implements BaseNetView, GetOrderInfoView,
        View.OnClickListener, SignUpOrderView {

    @BindView(R.id.order_user_icon_view)
    CircleImageView orderUserIconView;
    @BindView(R.id.order_info_name)
    TextView orderInfoName;
    @BindView(R.id.order_info_time)
    TextView orderInfoTime;
    @BindView(R.id.order_info_id)
    TextView orderInfoId;
    @BindView(R.id.order_info_car_type)
    TextView orderInfoCarType;
    @BindView(R.id.order_info_duration_time)
    TextView orderInfoDurationTime;
    @BindView(R.id.order_info_km)
    TextView orderInfoKm;
    @BindView(R.id.order_info_space)
    TextView orderInfoSpace;
    @BindView(R.id.order_info_map_view)
    MapView orderInfoMapView;
    @BindView(R.id.order_info_price)
    TextView orderInfoPrice;
    @BindView(R.id.order_info_sign_up_button)
    Button orderInfoSignUpButton;
    @BindView(R.id.order_info_service_button)
    ImageButton orderInfoServiceButton;
    @BindView(R.id.order_info_map_1)
    TextView orderInfoMap1;
    @BindView(R.id.order_info_map_2)
    TextView orderInfoMap2;
    @BindView(R.id.order_info_map_3)
    TextView orderInfoMap3;
    @BindView(R.id.order_info_note)
    TextView orderInfoNote;
    @BindView(R.id.order_info_more_price)
    TextView orderInfoMorePrice;
    @BindView(R.id.order_info_bottom_view)
    LinearLayout mOrderInfoBottomView;
    @BindView(R.id.order_info_title)
    TextView mOrderInfoTitle;
    @BindView(R.id.order_info_space_view)
    LinearLayout mOrderInfoSpaceView;

    TextView orderMyPrice;
    ImageButton orderPriceMinus10;
    ImageButton orderPriceAdd10;
    ImageButton orderPriceMinus100;
    ImageButton orderPriceAdd100;
    ImageButton orderPriceMinus1000;
    ImageButton orderPriceAdd1000;
    Button orderPriceReset;
    Button orderPriceConfirm;

    private Unbinder unbinder;
    private NetworkController<BaseNetView> mController;
    private RobbingInfoEntity mRobbingInfo;

    private MaterialDialog mSignUpDialog;

    private OrderInfoEntity mOrderInfoEntity;
    private int mInitPrice;

    private MaterialDialog mConfirmDialog;

    private String mType;
    private OrderRunningListEntity mHomeRunningEntity;
    private OrderWaitListEntity mHomeWaitEntity;
    private OrderWaitListEntity mHomeCompleteEntity;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_order_info);
        unbinder = ButterKnife.bind(this);
        orderInfoMapView.onCreate(null);

        UiSettings uiSettings = orderInfoMapView.getMap().getUiSettings();
        uiSettings.setScaleControlsEnabled(true);
        uiSettings.setZoomControlsEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mController = new NetworkController<>();
        mController.attachView(this);

        mType = getIntent().getStringExtra("type");
        if (mType.equals("running") || mType.equals("complete") || mType.equals("invalid")) {
            orderInfoMapView.setVisibility(View.GONE);
            mOrderInfoSpaceView.setVisibility(View.GONE);
            orderInfoServiceButton.setVisibility(View.GONE);
            mOrderInfoBottomView.setVisibility(View.GONE);
            mOrderInfoTitle.setText("订单详情");
            mOrderInfoTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orderInfoMapView.setVisibility(orderInfoMapView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });
            if (mType.equals("running")) {
                mHomeRunningEntity = (OrderRunningListEntity) getIntent().getSerializableExtra(mType);
            } else if (mType.equals("invalid")) {
                mHomeWaitEntity = (OrderWaitListEntity) getIntent().getSerializableExtra(mType);
            } else if (mType.equals("complete")) {
                mHomeCompleteEntity = (OrderWaitListEntity) getIntent().getSerializableExtra(mType);
            }
        } else {
            mRobbingInfo = (RobbingInfoEntity) getIntent().getSerializableExtra("RobbingInfo");
            Logger.I("OrderInfoActivity onCreate RobbingInfo : " + mRobbingInfo.toString());
            initRobingData(mRobbingInfo.getID());
        }
    }

    private void initRobingData(String orderId) {
        String avator = mRobbingInfo.getCustomerAvator();
        String customerName = mRobbingInfo.getCustomerName();
        String theWeddingDate = mRobbingInfo.getTheWeddingDate();
        String id = mRobbingInfo.getID();
        String carBrandName = mRobbingInfo.getCarBrandName();
        String carModelName = mRobbingInfo.getCarModelName();
        String carType = carBrandName + carModelName;
        String hourChoose = mRobbingInfo.getHourChoose() + "小时";
        String km = mRobbingInfo.getJourneyChoose() + "公里";
        String areaName = mRobbingInfo.getAreaName();

        String userHeadUrl = Config.getAppHtmlUrl() + "/LJTP/CATP/" + avator;
        GlideUtils.loadShow(this, userHeadUrl, orderUserIconView);

        orderInfoName.setText(customerName);
        orderInfoTime.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(Long.parseLong(theWeddingDate))));
        orderInfoId.setText(id);
        orderInfoCarType.setText(carType);
        orderInfoDurationTime.setText(hourChoose);
        orderInfoKm.setText(km);
        orderInfoSpace.setText(areaName);
        getOrderInfoById(orderId);
    }

    private void getOrderInfoById(String orderId){
        mController.getOrderInfo("HC010303", orderId, true);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopLeftImage(R.drawable.nav_back);
        setTopRightImage(R.drawable.icon_share);
        setTopTitle(UIUtils.getString(R.string.order_info_title));

        getTopLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getTopRightImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO share
                share();
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mController.detachView();
    }

    private void share() {
        Toast.makeText(mContext, "Share Order Info", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showProcess("查看订单详情中");
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
        if (methodName.equals("signUpOrder")) {
            UIUtils.showToastSafe(errorMsg);
            mConfirmDialog.dismiss();
            mSignUpDialog.dismiss();
        }
    }

    @OnClick(R.id.order_info_map_view)
    public void onOrderInfoMapViewClicked() {

    }

    @OnClick(R.id.order_info_sign_up_button)
    public void onOrderInfoSignUpButtonClicked() {
        if (null == mOrderInfoEntity) {
            UIUtils.showToastSafe("获取订单信息错误，请稍后再试");
            return;
        }
        if (null == mSignUpDialog) {
            mSignUpDialog = new MaterialDialog(this);
            View dialogView = UIUtils.inflate(this, R.layout.order_info_sign_up);
            orderMyPrice = dialogView.findViewById(R.id.order_my_price);
            orderPriceMinus10 = dialogView.findViewById(R.id.order_price_minus_10);
            orderPriceMinus100 = dialogView.findViewById(R.id.order_price_minus_100);
            orderPriceMinus1000 = dialogView.findViewById(R.id.order_price_minus_1000);
            orderPriceAdd10 = dialogView.findViewById(R.id.order_price_add_10);
            orderPriceAdd100 = dialogView.findViewById(R.id.order_price_add_100);
            orderPriceAdd1000 = dialogView.findViewById(R.id.order_price_add_1000);
            orderPriceReset = dialogView.findViewById(R.id.order_price_reset);
            orderPriceConfirm = dialogView.findViewById(R.id.order_price_confirm);
            orderPriceMinus10.setOnClickListener(this);
            orderPriceMinus100.setOnClickListener(this);
            orderPriceMinus1000.setOnClickListener(this);
            orderPriceAdd10.setOnClickListener(this);
            orderPriceAdd100.setOnClickListener(this);
            orderPriceAdd1000.setOnClickListener(this);
            orderPriceReset.setOnClickListener(this);
            orderPriceConfirm.setOnClickListener(this);
            mSignUpDialog.setContentView(dialogView);
            orderMyPrice.setText(mInitPrice + "元");
            mSignUpDialog.show();
        } else {
            mSignUpDialog.show();
        }
    }

    private void signUpOrder() {
        if (mConfirmDialog == null) {
            mConfirmDialog = new MaterialDialog(this);
            mConfirmDialog.setTitle("确认报价");
            mConfirmDialog.setMessage("您的报价为:" + mInitPrice + "元\n确认后不可更改");
            mConfirmDialog.setPositiveButton("确认", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mConfirmDialog.dismiss();
                    signUp();
                }
            });
            mConfirmDialog.setNegativeButton("修改", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mConfirmDialog.dismiss();
                    onOrderInfoSignUpButtonClicked();
                }
            });
            mConfirmDialog.show();
        } else {
            mConfirmDialog.show();
        }
    }

    private void signUp() {
        String customerId = SPController.getInstance().getUserInfo().getUserId();
        if (null == customerId || customerId.isEmpty()) customerId = "18616367480";
        mController.signUpOrder("HC020310", customerId, mRobbingInfo.getID(), mInitPrice, true);
    }

    @OnClick(R.id.order_info_service_button)
    public void onOrderInfoServiceButtonClicked() {

    }

    @Override
    public void onGetOrderInfoSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                UIUtils.showToastSafe(msg);
                return;
            }

            List<OrderInfoEntity> orderInfoEntities = (List<OrderInfoEntity>) baseEntity.getData();
            OrderInfoEntity orderInfoEntity = orderInfoEntities.get(0);
            this.mOrderInfoEntity = orderInfoEntity;
            mInitPrice = orderInfoEntity.getAmountAverage();

            List<OrderInfoEntity.MapInfos> mapInfos = orderInfoEntity.getMapInfos();
            OrderInfoEntity.MapInfos mapInfos_1 = mapInfos.get(0);
            OrderInfoEntity.MapInfos mapInfos_2 = mapInfos.get(1);
            OrderInfoEntity.MapInfos mapInfos_3 = mapInfos.get(2);

            LatLng latLng_1 = new LatLng(Double.valueOf(mapInfos_1.getLatitude()), Double.valueOf(mapInfos_1.getLongitude()));
            MarkerOptions maker_1 = new MarkerOptions().position(latLng_1).title("新郎家");
            maker_1.draggable(false);
            maker_1.visible(true);
            orderInfoMapView.getMap().addMarker(maker_1);

            LatLng latLng_2 = new LatLng(Double.valueOf(mapInfos_2.getLatitude()), Double.valueOf(mapInfos_2.getLongitude()));
            MarkerOptions maker_2 = new MarkerOptions().position(latLng_2).title("新娘家");
            maker_2.draggable(false);
            maker_2.visible(true);
            orderInfoMapView.getMap().addMarker(maker_2);

            LatLng latLng_3 = new LatLng(Double.valueOf(mapInfos_3.getLatitude()), Double.valueOf(mapInfos_3.getLongitude()));
            MarkerOptions maker_3 = new MarkerOptions().position(latLng_3).title("结束地");
            maker_3.draggable(false);
            maker_3.visible(true);
            orderInfoMapView.getMap().addMarker(maker_3);

            orderInfoMapView.getMap().moveCamera(CameraUpdateFactory.newLatLng(latLng_1));
            orderInfoMapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(17));

            orderInfoMap1.setText(mapInfos_1.getCoordinateName());
            orderInfoMap2.setText(mapInfos_2.getCoordinateName());
            orderInfoMap3.setText(mapInfos_3.getCoordinateName());

            int priceBaseTimeout = orderInfoEntity.getPriceBaseTimeout();
            int priceBaseDistance = orderInfoEntity.getPriceBaseDistance();

            orderInfoMorePrice.setText("当天超出费用: " + priceBaseTimeout + "/小时 " + priceBaseDistance + "/公里");
            orderInfoPrice.setText(orderInfoEntity.getAmountAverage() + "元");
            orderInfoNote.setText(orderInfoEntity.getNote());
            Logger.I("onGetOrderInfoSuccess : " + baseEntity.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_price_minus_10:
                mInitPrice = mInitPrice - 10 < 0 ? 0 : mInitPrice - 10;
                mInitPrice = mInitPrice < mOrderInfoEntity.getAmountAverage() ? mOrderInfoEntity.getAmountAverage() : mInitPrice;
                orderMyPrice.setText(mInitPrice + "元");
                break;
            case R.id.order_price_add_10:
                mInitPrice = mInitPrice + 10;
                orderMyPrice.setText(mInitPrice + "元");
                break;
            case R.id.order_price_minus_100:
                mInitPrice = mInitPrice - 100 < 0 ? 0 : mInitPrice - 100;
                mInitPrice = mInitPrice < mOrderInfoEntity.getAmountAverage() ? mOrderInfoEntity.getAmountAverage() : mInitPrice;
                orderMyPrice.setText(mInitPrice + "元");
                break;
            case R.id.order_price_add_100:
                mInitPrice = mInitPrice + 100;
                orderMyPrice.setText(mInitPrice + "元");
                break;
            case R.id.order_price_minus_1000:
                mInitPrice = mInitPrice - 1000 < 0 ? 0 : mInitPrice - 1000;
                mInitPrice = mInitPrice < mOrderInfoEntity.getAmountAverage() ? mOrderInfoEntity.getAmountAverage() : mInitPrice;
                orderMyPrice.setText(mInitPrice + "元");
                break;
            case R.id.order_price_add_1000:
                mInitPrice = mInitPrice + 1000;
                orderMyPrice.setText(mInitPrice + "元");
                break;
            case R.id.order_price_reset:
                mInitPrice = mOrderInfoEntity.getAmountAverage();
                orderMyPrice.setText(mInitPrice + "元");
                break;
            case R.id.order_price_confirm:
                String priceString = orderMyPrice.getText().toString();
                mInitPrice = Integer.valueOf(priceString.substring(0, priceString.length() - 1));
                signUpOrder();
                break;
        }
    }

    @Override
    public void onSignUpOrderSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            String status = baseEntity.getStatus();
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                UIUtils.showToastSafe(msg);
                return;
            }
        }
    }
}
