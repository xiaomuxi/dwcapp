package com.weddingcar.driver.function.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.network.library.bean.mine.request.GetCarBrandsRequest;
import com.network.library.bean.mine.response.CarColorsEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.mine.adapter.CarColorListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarColorActivity extends BaseActivity{

    @BindView(R.id.lv_color)
    ListView lv_color;
    NetworkController mController;
    CarColorListAdapter colorListAdapter;
    List<CarColorsEntity.Data> colorList;

    private int currentCheckedIndex = -1;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_car_color);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeftAndRight("车辆颜色");
        setTopRightText("确定");
    }

    @Override
    protected void initView() {
        super.initView();

        mController = new NetworkController();
        mController.attachView(getCarColorView);

        colorListAdapter = new CarColorListAdapter(this);
        lv_color.setAdapter(colorListAdapter);

        lv_color.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentCheckedIndex == position) {
                    currentCheckedIndex = -1;
                    colorListAdapter.setCheckedIndex(-1);
                    return;
                }
                currentCheckedIndex = position;
                colorListAdapter.setCheckedIndex(position);

            }
        });
        mTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCheckedIndex == -1) {
                    UIUtils.showToastSafe("请选择车辆颜色");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("CAR_COLOR_ID", colorList.get(currentCheckedIndex).getKey());
                intent.putExtra("CAR_COLOR_VALUE", colorList.get(currentCheckedIndex).getValue());
                setResult(1, intent);
                finish();
            }
        });

        initData();
    }

    private void initData() {

        GetCarBrandsRequest req = new GetCarBrandsRequest();
        GetCarBrandsRequest.Query query = new GetCarBrandsRequest.Query();
        query.setApiId("HC010206");
        req.setQuery(query);

        mController.sendRequest(HttpAction.ACTION_GET_CAR_COLORS, req);
    }

    private NormalView<CarColorsEntity> getCarColorView = new NormalView<CarColorsEntity>() {
        @Override
        public void onSuccess(CarColorsEntity entity) {
            if (entity.getData() == null) {
                UIUtils.showToastSafe(ToastConstant.TOAST_REQUEST_ERROR);
                return;
            }

            colorList = entity.getData();
            colorListAdapter.setData(colorList);
        }

        @Override
        public void showLoading() {
            showProcess("正在获取车辆颜色...");
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
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };
}
