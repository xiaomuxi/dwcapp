package com.weddingcar.driver.function.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.network.library.bean.BaseEntity;
import com.network.library.bean.user.response.RobbingInfoEntity;
import com.network.library.controller.NetworkController;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.network.library.view.GetRobbingView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.callback.FilterListener;
import com.weddingcar.driver.common.callback.OnRecycleItemClick;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.main.adapter.OrderMyCarTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCarTypeFragment extends BaseFragment implements GetRobbingView, OnRecycleItemClick, FilterListener {

    @BindView(R.id.order_my_car_type)
    RecyclerView mMyCarTypeRecycleView;
    @BindView(R.id.empty_order_list_view)
    TextView mEmptyView;

    private String mFragmentTag;

    private Unbinder unbinder;
    private NetworkController<BaseNetView> mController;

    private OrderMyCarTypeAdapter mOrderMyCarAdapter;
    private List<RobbingInfoEntity> mOrderMyCarList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mFragmentTag = getArguments().getString(TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_car_type, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = new NetworkController<>();
        mController.attachView(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewAndData();
    }

    private void initViewAndData() {
        if (isVisible()) {
            if (null == mOrderMyCarAdapter)
                mOrderMyCarAdapter = new OrderMyCarTypeAdapter(mOrderMyCarList, this, this);
            mMyCarTypeRecycleView.setAdapter(mOrderMyCarAdapter);
            DividerItemDecoration divider = new DividerItemDecoration(UIUtils.getContext(), DividerItemDecoration.VERTICAL);
            divider.setDrawable(UIUtils.getDrawable(R.drawable.recycleview_divider));
            mMyCarTypeRecycleView.addItemDecoration(divider);
        }
        getMyCarTypeData();
    }

    private void getMyCarTypeData() {
        String userId = SPController.getInstance().getUserInfo().getUserId();
        String carModelId = SPController.getInstance().getUserInfo().getCarModelId();
        String carBrandId = SPController.getInstance().getUserInfo().getCarBrandId();
        if (null == userId || userId.isEmpty()) userId = "18616367480";
        mController.getRobbingList("HC010308", userId, carBrandId, carModelId, true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mController.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void visible() {
        initViewAndData();
    }

    public void dateClear() {

    }

    @Override
    public void onRequestError(String errorMsg, String methodName) {
        super.onRequestError(errorMsg, methodName);
        mMyCarTypeRecycleView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetRobbingSuccess(BaseEntity baseEntity) {
        if (null != baseEntity) {
            String msg = baseEntity.getMsg();
            String count = baseEntity.getCount();

            if (StringUtils.equals(count, "0")) {
                UIUtils.showToastSafe(msg);
                mMyCarTypeRecycleView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                return;
            }
            List<RobbingInfoEntity> robbingInfoEntities = (List<RobbingInfoEntity>) baseEntity.getData();
            mOrderMyCarList.clear();
            mOrderMyCarList.addAll(robbingInfoEntities);
            mOrderMyCarAdapter.notifyDataSetChanged();
            Logger.I("onGetMyCarTypeDataSuccess : " + baseEntity.toString());
        }
    }

    @Override
    public void onRecycleItemClick(int position) {
        RobbingInfoEntity infoEntity = mOrderMyCarList.get(position);
        if (null != infoEntity) {
            Logger.I("MyCarTypeFragment onRecycleItemClick \n" + infoEntity.toString());
        }
    }

    @Override
    public void getFilterData(List<RobbingInfoEntity> data) {
        Logger.I("MyCarType getFilterData " + data.toString());
    }

    public void filter(String searchId) {
        if (null != mOrderMyCarAdapter) {
            mOrderMyCarAdapter.getFilter().filter(searchId);
        }
    }
}
