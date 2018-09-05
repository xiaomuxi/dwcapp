package com.network.library.view;

import com.network.library.bean.BaseEntity;

public interface GetWeatherView extends BaseNetView {
    void onGetWeatherSuccess(BaseEntity baseEntity);
}
