package com.weddingcar.driver.common.callback;

import com.network.library.bean.user.response.RobbingInfoEntity;

import java.util.List;

public interface FilterListener {
    void getFilterData(List<RobbingInfoEntity> data);
}
