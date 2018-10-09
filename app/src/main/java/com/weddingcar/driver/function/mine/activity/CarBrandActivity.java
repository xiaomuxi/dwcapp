package com.weddingcar.driver.function.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.network.library.bean.mine.request.GetCarBrandsRequest;
import com.network.library.bean.mine.response.GetCarBrandsEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.mine.adapter.BrandChildrenListAdapter;
import com.weddingcar.driver.function.mine.adapter.CarBrandListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarBrandActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.lv_parent)
    ListView lv_parent;
    @BindView(R.id.lv_children)
    ListView lv_children;

    private NetworkController mController;
    private List<GetCarBrandsEntity.Data> carBrandsList;
    private List<GetCarBrandsEntity.Data.ModelsBean> carBrandsChildrenList;
    private CarBrandListAdapter brandParentAdapter;
    private BrandChildrenListAdapter brandChildrenAdapter;

    private int brandParentIndex = 0;
    private int brandChildrenIndex = 0;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_car_brand);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeftAndRight("车辆品牌型号");
        setTopRightText("确定");
    }

    @Override
    protected void initView() {
        super.initView();
        mTopRight.setOnClickListener(this);
        mController = new NetworkController();
        mController.attachView(getCarBrandsView);

        brandParentAdapter = new CarBrandListAdapter(this);
        lv_parent.setAdapter(brandParentAdapter);
        brandChildrenAdapter = new BrandChildrenListAdapter(this);
        lv_children.setAdapter(brandChildrenAdapter);
        lv_parent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (brandParentIndex != position) {
                    brandParentIndex = position;
                    brandParentAdapter.setSelectedItem(position);
                    brandChildrenIndex = 0;
                    carBrandsChildrenList = carBrandsList.get(position).getModels();
                    brandChildrenAdapter.setData(carBrandsChildrenList);
                    brandChildrenAdapter.setSelectedItem(brandChildrenIndex);
                }
            }
        });
        lv_children.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (brandChildrenIndex != position) {
                    brandChildrenIndex = position;
                    brandChildrenAdapter.setSelectedItem(position);
                }
            }
        });

        initData();
    }

    private void initData() {
        GetCarBrandsRequest req = new GetCarBrandsRequest();
        GetCarBrandsRequest.Query query = new GetCarBrandsRequest.Query();
        query.setApiId("HC010201");
        req.setQuery(query);
        mController.sendRequest(HttpAction.ACTION_GET_CAR_BRANDS, req);
    }

    private NormalView<GetCarBrandsEntity> getCarBrandsView = new NormalView<GetCarBrandsEntity>() {
        @Override
        public void onSuccess(GetCarBrandsEntity entity) {
            if (entity.getData() == null) {
                UIUtils.showToastSafe(ToastConstant.TOAST_SERVER_IS_BUSY);
                return;
            }
            if (entity.getData().size() == 0) {
                UIUtils.showToastSafe("暂时没有车辆品牌信息");
                return;
            }
            carBrandsList = entity.getData();
            carBrandsChildrenList = carBrandsList.get(0).getModels();

            brandParentAdapter.setData(carBrandsList);
            brandChildrenAdapter.setData(carBrandsChildrenList);
        }

        @Override
        public void showLoading() {
            showProcess("正在获取车辆品牌型号...");
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

    public void goBackAndBringResult() {
        String brandKey = carBrandsChildrenList.get(brandChildrenIndex).getKey();
        String brandValue = carBrandsChildrenList.get(brandChildrenIndex).getValue();
        Intent intent = new Intent();
        intent.putExtra("CAR_MODEL_ID", brandKey);
        intent.putExtra("CAR_MODEL_VALUE", brandValue);
        setResult(1, intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right:
                goBackAndBringResult();
                break;
        }
    }
}
