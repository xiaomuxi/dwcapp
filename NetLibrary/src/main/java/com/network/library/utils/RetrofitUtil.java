package com.network.library.utils;

import android.content.Context;
import android.os.Environment;

import com.network.library.bean.BaiduOauthEntity;
import com.network.library.bean.BaseEntity;
import com.network.library.bean.LoginEntity;
import com.network.library.bean.RegisterEntity;
import com.network.library.bean.VerifyCodeEntity;
import com.network.library.bean.WeatherEntity;
import com.network.library.inter.BaiduOauthService;
import com.network.library.inter.DeviceGetWeatherService;
import com.network.library.inter.NetworkService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtil {

    private static final String WEATHER_BASE_URL = "http://jisutqybmf.market.alicloudapi.com/";
    private static final String BAIDU_OAUTH_URL = "https://aip.baidubce.com/oauth/2.0/";
    private static String NET_WORK_BASE_URL = "http://139.196.254.89:8080/";

    private static final int DEFAULT_TIMEOUT = 15;
    private static Context mContext;

    private Retrofit mWeatherRetrofit;
    private Retrofit mOauthRetrofit;
    private Retrofit mNetworkRetrofit;

    private DeviceGetWeatherService mWeatherService;
    private BaiduOauthService mBaiduOauthService;
    private NetworkService mNetworkService;

    private static RetrofitUtil mInstance;

    public static void getInstance(Context context) {
        mContext = context;
        String urlPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Comaiot/urlConfig/comaiot.txt";
        File file = new File(urlPath);
        if (!file.exists()) {
            try {
                File configDir = new File(file.getParent());
                if (!configDir.exists()) {
                    configDir.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                Logger.D("can not create url file " + e.toString());
            }
        } else {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(urlPath);
                byte[] buffer = new byte[256];
                fis.read(buffer);
                String configUrl = new String(buffer, "utf-8").trim();
                if (configUrl.isEmpty() || configUrl.length() > 50 || configUrl.length() < 10 || !configUrl.contains("http")) {
                    fis.close();
                } else {
                    NET_WORK_BASE_URL = configUrl;
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    if (null != fis)
                        fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Logger.D("configBaseUrl = " + NET_WORK_BASE_URL);

        if (null == mInstance) {
            synchronized (Object.class) {
                if (null == mInstance) {
                    mInstance = new RetrofitUtil();
                }
            }
        }
    }

    public static String getWeatherBaseUrl() {
        return NET_WORK_BASE_URL;
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static RetrofitUtil getInstance() {
        return mInstance;
    }

    private RetrofitUtil() {
        //-------------------------------Weather-------------------------------//
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Connection", "Keep-alive")
                                .addHeader("Authorization", "APPCODE 0cf6d4b52f16455e8218a76c1e34e627")
                                .build();
                        return chain.proceed(request);
                    }
                });
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        //------------------------------天气url-------------------------------//
        mWeatherRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(WEATHER_BASE_URL)
                .build();

        //------------------------------百度oauth-------------------------------//
        mOauthRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BAIDU_OAUTH_URL)
                .build();

        //-------------------------------后台接口-----------------------------//
        mNetworkRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NET_WORK_BASE_URL)
                .build();

        mWeatherService = mWeatherRetrofit.create(DeviceGetWeatherService.class);
        mBaiduOauthService = mOauthRetrofit.create(BaiduOauthService.class);
        mNetworkService = mNetworkRetrofit.create(NetworkService.class);
    }

    /**
     * 发送验证码
     *
     * @param subscriber
     * @param city       城市
     */
    public void getWeather(Subscriber<WeatherEntity> subscriber, String city) {
        mWeatherService.getWeather(city)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void baiduOauth(Subscriber<BaiduOauthEntity> subscriber, String client_id, String client_secret) {
        String grant_type = "client_credentials";
        mBaiduOauthService.BaiduOauthToken(grant_type, client_id, client_secret)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getOrderList(Subscriber<BaseEntity> subscriber, String interfaceCode, String customerId, String state) {
        mNetworkService.getOrderList(interfaceCode, customerId, state)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void login(Subscriber<LoginEntity> subscriber, String interfaceCode, String phone, String pwd) {
        mNetworkService.login(interfaceCode, phone, pwd)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void sendVerifyCode(Subscriber<VerifyCodeEntity> subscriber, String interfaceCode, String phone, String type) {
        mNetworkService.sendVerifyCode(interfaceCode, phone, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void register(Subscriber<RegisterEntity> subscriber, String interfaceCode, String phone, String pwd, String pwdAgain, String vCode, String id) {
        mNetworkService.register(interfaceCode, phone, pwd, pwdAgain, vCode, id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void modifyPassword(Subscriber<BaseEntity> subscriber, String interfaceCode, String phone, String pwd, String newPwd) {
        mNetworkService.modifyPassword(interfaceCode, phone, pwd, newPwd, "Android")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void modifyUserInfo(Subscriber<BaseEntity> subscriber, String interfaceCode, String phone, String sex, String name) {
        mNetworkService.modifyUserInfo(interfaceCode, phone, sex, name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
