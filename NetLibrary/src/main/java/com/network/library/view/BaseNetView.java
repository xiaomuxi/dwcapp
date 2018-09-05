package com.network.library.view;

public interface BaseNetView {
    void showLoading();

    void hideLoading();

    void onRequestSuccess();

    void onRequestError(String errorMsg, String methodName);
}
