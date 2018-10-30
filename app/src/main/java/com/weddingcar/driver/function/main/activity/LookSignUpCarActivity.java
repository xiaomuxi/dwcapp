package com.weddingcar.driver.function.main.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.OrderInfoEntity;
import com.network.library.bean.user.response.OrderWaitListEntity;
import com.network.library.bean.user.response.SignUpInfoEntity;
import com.network.library.controller.NetworkController;
import com.network.library.eventbus.CancelSignEvent;
import com.network.library.utils.GlideUtils;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.CancelSignUpView;
import com.network.library.view.GetOrderInfoView;
import com.network.library.view.GetSignUpListView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.MaterialDialog;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.adapter.SignUpAdapter;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class LookSignUpCarActivity extends BaseActivity implements BaseNetView,
        GetSignUpListView, CancelSignUpView ,GetOrderInfoView {

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
    @BindView(R.id.order_info_map_view)
    MapView orderInfoMapView;
    @BindView(R.id.order_info_map_1)
    TextView orderInfoMap1;
    @BindView(R.id.order_info_map_2)
    TextView orderInfoMap2;
    @BindView(R.id.order_info_map_3)
    TextView orderInfoMap3;
    @BindView(R.id.order_info_more_price)
    TextView orderInfoMorePrice;
    @BindView(R.id.order_info_note)
    TextView orderInfoNote;
    @BindView(R.id.order_info_space_view)
    LinearLayout orderInfoSpaceView;
    @BindView(R.id.look_sign_up_order_info)
    LinearLayout lookSignUpOrderInfo;

    private OrderWaitListEntity mOrderEntity;
    private NetworkController<BaseNetView> mController;

    private List<SignUpInfoEntity.OrderOffer> mSignUpList = new ArrayList<>();

    private Unbinder unbinder;
    private SignUpAdapter mSignUpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrderEntity = (OrderWaitListEntity) getIntent().getSerializableExtra("orderNumber");
        mController = new NetworkController<>();
        mController.attachView(this);

        mSignUpAdapter = new SignUpAdapter(this, mSignUpList);
        mSignUpListView.setAdapter(mSignUpAdapter);

        String userId = SPController.getInstance().getUserInfo().getUserId();
        if (null == userId || userId.isEmpty()) userId = "18616367480";
        String orderId = mOrderEntity.getID();
        mController.getSignUpList("HC010311", userId, orderId);
    }

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_look_sign_up_car);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopLeftImage(R.drawable.nav_back);
        setTopRightImage(R.drawable.icon_share);
        setTopTitle(UIUtils.getString(R.string.sign_up_car));

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
                String code = signUpInfoEntity.getID();
                if (null != code && code.equals(mOrderEntity.getID())) {
                    setSignUpInfoMsg(signUpInfoEntity);
                    mController.getOrderInfo("HC010303", code, false);
                }
            }
            Logger.I("onGetSignUpListSuccess " + baseEntity.toString());
        }
    }

    private void setSignUpInfoMsg(SignUpInfoEntity signUpInfoEntity) {
        mOrderNumber.setText("订单号:" + signUpInfoEntity.getID());
        String userHeadUrl = Config.getAppHtmlUrl() + "LJTP/CATP/" + signUpInfoEntity.getCustomerAvator();
        Logger.I("setSignUpInfoMsg userHeadUrl = " + userHeadUrl);
        GlideUtils.loadShow(this, userHeadUrl, mOrderUserIconView);
        mOrderTimeTxView.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(Long.parseLong(signUpInfoEntity.getTheWeddingDate()))));
        String sex = signUpInfoEntity.getCustomerSex();
        mOrderUserName.setVisibility(View.GONE);
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

        List<SignUpInfoEntity.OrderOffer> orderOffers = signUpInfoEntity.getOrderOffers();
        mSignUpList.clear();
        mSignUpList.addAll(orderOffers);
        mSignUpAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.order_cancel_sign)
    public void onCancelSignClicked() {
        final MaterialDialog materialDialog = new MaterialDialog(this);
        materialDialog.setCancelable(true);
        materialDialog.setBackground(UIUtils.getDrawable(R.drawable.material_card));
        materialDialog.setTitle("取消报名");
        materialDialog.setMessage("取消后不得对此订单进行二次报价!");
        materialDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDialog.dismiss();
                cancelSignUp();
            }
        });
        materialDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDialog.dismiss();
            }
        });
        materialDialog.show();
    }

    private void cancelSignUp() {
        String customerId = SPController.getInstance().getUserInfo().getUserId();
        if (null == customerId || customerId.isEmpty()) customerId = "18616367480";
        mController.cancelSignUp("HC020311", customerId, mOrderEntity.getID(), true);
    }

    @OnClick(R.id.order_info_view)
    public void onOrderInfoViewClicked() {
        lookSignUpOrderInfo.setVisibility(lookSignUpOrderInfo.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        Drawable left = getResources().getDrawable(R.drawable.tab_order);
        left.setBounds(0, 0, left.getIntrinsicWidth(), left.getIntrinsicHeight());
        if (lookSignUpOrderInfo.getVisibility() == View.VISIBLE) {
            Drawable right = getResources().getDrawable(R.drawable.icon_more_up);
            right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
            mOrderInfoView.setCompoundDrawables(left, null, right, null);
        } else {
            Drawable right = getResources().getDrawable(R.drawable.icon_more_down);
            right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
            mOrderInfoView.setCompoundDrawables(left, null, right, null);
        }
    }

    private void share() {
        Toast.makeText(mContext, "Share Order Info", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelSignUpSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                UIUtils.showToastSafe(msg);
                return;
            }
            EventBus.getDefault().post(new CancelSignEvent());
            finish();
        }
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
            orderInfoNote.setText(orderInfoEntity.getNote());
        }
    }
}
