package com.network.library.inter;

import com.network.library.bean.WeatherEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DeviceGetWeatherService {

    @GET("weather/query")
    Observable<WeatherEntity> getWeather(@Query("city") String city);
}
