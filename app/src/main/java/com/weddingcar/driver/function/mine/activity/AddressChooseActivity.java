package com.weddingcar.driver.function.mine.activity;

import android.widget.AutoCompleteTextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressChooseActivity extends BaseActivity{

    private AMap aMap;
    @BindView(R.id.act_keyword)
    AutoCompleteTextView act_keyword;
    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_address_choose);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setContentView(R.layout.common_top_bar);
        setTopTitleAndLeftAndRight("常住地址");
        setTopRightText("确定");
    }

    @Override
    protected void initView() {
        super.initView();

        if (aMap == null) {
            aMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
        }

    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
//        showProgressDialog();// 显示进度框
//        currentPage = 0;
//        query = new PoiSearch.Query(keyWord, "", editCity.getText().toString());// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
//        query.setPageSize(10);// 设置每页最多返回多少条poiitem
//        query.setPageNum(currentPage);// 设置查第一页
//
//        poiSearch = new PoiSearch(this, query);
//        poiSearch.setOnPoiSearchListener(this);
//        poiSearch.searchPOIAsyn();
    }
}
