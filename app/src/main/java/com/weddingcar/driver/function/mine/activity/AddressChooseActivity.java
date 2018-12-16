package com.weddingcar.driver.function.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.map.GeoCoderUtil;
import com.weddingcar.driver.common.map.LatLngEntity;
import com.weddingcar.driver.common.map.LocationBean;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.mine.adapter.PoiListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressChooseActivity extends BaseActivity implements View.OnClickListener, Inputtips.InputtipsListener, AdapterView.OnItemClickListener
        , AMap.OnCameraChangeListener, AMapLocationListener, PoiSearch.OnPoiSearchListener {

    @BindView(R.id.act_keyword)
    AutoCompleteTextView act_keyword;
    @BindView(R.id.mv_map)
    MapView mv_map;
    @BindView(R.id.lv_poi)
    ListView lv_poi;
    @BindView(R.id.iv_location)
    ImageView iv_location;

    private AMap mAMap;
    private AMapLocationClient mLocationClient;
    List<LocationBean> locationList = new ArrayList<>();
    LocationBean currentLoc;
    PoiListAdapter poiListAdapter;
    boolean bottomItemClick = false;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_address_choose);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv_map.onCreate(savedInstanceState);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeftAndRight("常住地址");
        setTopRightText("确定");
    }

    @Override
    protected void initView() {
        super.initView();
        initMap();
        initLocation();
        mTopRight.setOnClickListener(this);
        iv_location.setOnClickListener(this);
        act_keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString().trim();
                if (!StringUtils.isEmpty(newText)) {
                    InputtipsQuery inputquery = new InputtipsQuery(newText, null);
                    Inputtips inputTips = new Inputtips(AddressChooseActivity.this, inputquery);
                    inputTips.setInputtipsListener(AddressChooseActivity.this);
                    inputTips.requestInputtipsAsyn();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        act_keyword.setOnItemClickListener(this);
        lv_poi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poiListAdapter.setSelectedIndex(position);
                currentLoc = poiListAdapter.getData().get(position);
                bottomItemClick = true;
                mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(poiListAdapter.getData().get(position).getLat(), poiListAdapter.getData().get(position).getLon()), 15));
            }
        });

        poiListAdapter = new PoiListAdapter(this);
        poiListAdapter.setData(new ArrayList<>());
        lv_poi.setAdapter(poiListAdapter);
    }

    private void initMap() {
        if (mAMap == null) {
            mAMap = mv_map.getMap();
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        UiSettings uiSettings = mAMap.getUiSettings();myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;
        mAMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        mAMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        uiSettings.setZoomControlsEnabled(false);  //隐藏缩放按钮
        mAMap.setOnCameraChangeListener(this); // 添加移动地图事件监听器
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(this);
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mLocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        //设置单次定位，3s内最精准的位置
        mLocationOption.setOnceLocationLatest(true);
        //设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mv_map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mv_map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mv_map.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv_map.onDestroy();
    }


    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {// 正确返回
            locationList.clear();
            List<String> listStr = new ArrayList<>();
            for (int i = 0; i < tipList.size(); i++) {
                LatLonPoint point = tipList.get(i).getPoint();
                if (point == null) {
                    break;
                }

                LocationBean bean = new LocationBean();
                bean.setTitle(tipList.get(i).getAddress());
                bean.setContent(tipList.get(i).getDistrict());
                bean.setLat(point.getLatitude());
                bean.setLon(point.getLongitude());

                locationList.add(bean);
                listStr.add(tipList.get(i).getName());
            }
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(
                    getApplicationContext(),
                    R.layout.item_location_list, listStr);
            act_keyword.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
        } else {
            UIUtils.showToastSafe(rCode);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right:
                if (currentLoc == null) {
                    UIUtils.showToastSafe("请选择地址!");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("ADDRESS", currentLoc);
                setResult(1, intent);
                finish();
                break;
            case R.id.iv_location:
                initMap();
                initLocation();
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        System.out.println("onCameraChangeFinish---->");
        LatLngEntity latLngEntity = new LatLngEntity(cameraPosition.target.latitude, cameraPosition.target.longitude);
        if (bottomItemClick) {
            bottomItemClick = false;
            return;
        }
        //地理反编码工具类，代码在后面
        GeoCoderUtil.getInstance(getApplicationContext()).geoAddress(latLngEntity, new GeoCoderUtil.GeoCoderAddressListener() {
            @Override
            public void onAddressResult(String result) {
                //地图的中心点位置改变后都开始poi的附近搜索
                PoiSearch.Query query = new PoiSearch.Query("", "", "");
                query.setPageNum(1);
                query.setPageSize(15);
                PoiSearch mSearch = new PoiSearch(mContext, query);
                mSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude), 2000));//设置周边搜索的中心点以及半径
                //设置异步监听
                mSearch.setOnPoiSearchListener(AddressChooseActivity.this);
                //查询POI异步接口
                mSearch.searchPOIAsyn();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        act_keyword.setText("");

        LocationBean bean = locationList.get(position);
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(bean.getLat(), bean.getLon()), 17));
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int errCode) {
        if(errCode == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {
                ArrayList<LocationBean> datas = new ArrayList<>();
                ArrayList<PoiItem> items = poiResult.getPois();
                for (PoiItem item : items) {
                    //获取经纬度对象
                    LatLonPoint llp = item.getLatLonPoint();
                    double lon = llp.getLongitude();
                    double lat = llp.getLatitude();
                    //获取标题
                    String title = item.getTitle();
                    //获取内容
                    String text = item.getSnippet();
                    LocationBean bean = new LocationBean();
                    bean.setLat(lat);
                    bean.setLon(lon);
                    bean.setTitle(title);
                    bean.setContent(text);
                    datas.add(bean);
                }
                poiListAdapter.setSelectedIndex(0);
                poiListAdapter.setData(datas);
                currentLoc = datas.get(0);
                //list view滑动到顶部
                poiListAdapter.notifyDataSetChanged();
                lv_poi.setSelection(0);
                mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(datas.get(0).getLat(), datas.get(0).getLon()), 17));
                lv_poi.setVisibility(datas.size() > 0 ? View.VISIBLE : View.GONE);
                return;
            }
        }

        UIUtils.showToastSafe("未搜索到相关地址");
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
