package com.weddingcar.driver.function.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.network.library.controller.NetworkController;
import com.network.library.utils.Logger;
import com.network.library.view.BaseNetView;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

public class BaseFragment extends Fragment implements BaseNetView {

    public static final String TAG = "fragment_tag";

    public static final String ORDER = "order_fragment";
    public static final String MY = "my_fragment";

    public static final String RUNNING = "order_running_fragment";
    public static final String WAIT = "order_wait_fragment";
    public static final String COMPLETE = "order_complete_fragment";
    public static final String INVALID = "order_invalid_fragment";
    public Context mContext;

    public static BaseFragment newInstance(String tag) {
        if (tag.equals(ORDER)) {
            OrderFragment orderFragment = new OrderFragment();
            Bundle args = new Bundle();
            args.putString(TAG, tag);
            orderFragment.setArguments(args);
            return orderFragment;
        } else if (tag.equals(MY)) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putString(TAG, tag);
            myFragment.setArguments(args);
            return myFragment;
        } else if (tag.equals(RUNNING)) {
            OrderRunningFragment runningFragment = new OrderRunningFragment();
            Bundle args = new Bundle();
            args.putString(TAG, tag);
            runningFragment.setArguments(args);
            return runningFragment;
        } else if (tag.equals(WAIT)) {
            OrderWaitFragment waitFragment = new OrderWaitFragment();
            Bundle args = new Bundle();
            args.putString(TAG, tag);
            waitFragment.setArguments(args);
            return waitFragment;
        } else if (tag.equals(COMPLETE)) {
            OrderCompleteFragment completeFragment = new OrderCompleteFragment();
            Bundle args = new Bundle();
            args.putString(TAG, tag);
            completeFragment.setArguments(args);
            return completeFragment;
        } else if (tag.equals(INVALID)) {
            OrderInvalidFragment invalidFragment = new OrderInvalidFragment();
            Bundle args = new Bundle();
            args.putString(TAG, tag);
            invalidFragment.setArguments(args);
            return invalidFragment;
        } else {
            return null;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoading() {
        if (isVisible()) {
            BaseActivity activity = (BaseActivity) getActivity();
            if (null != activity) activity.showProcess("正在请求数据...");
        }
    }

    @Override
    public void hideLoading() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (null != activity) activity.hideProcess();
    }

    @Override
    public void onRequestSuccess() {

    }

    @Override
    public void onRequestError(String errorMsg, String methodName) {
        Logger.E("onRequestError from [ " + methodName + " ] , errorMsg = " + errorMsg);
        UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
    }
}
