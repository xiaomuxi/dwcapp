package com.network.library.okhttp;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.network.library.utils.Logger;
import com.network.library.utils.RetrofitUtil;
import org.json.JSONObject;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private static volatile OkHttpUtils mInstance;//单利引用

    private final Handler okHttpHandler;
    private final OkHttpClient mOkHttpClient;

    private OkHttpUtils(Context context) {
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }

    /**
     * 获取单例引用
     *
     * @return
     */

    public static OkHttpUtils getInstance(Context context) {
        OkHttpUtils inst = mInstance;
        if (inst == null) {
            synchronized (OkHttpUtils.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new OkHttpUtils(context.getApplicationContext());
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * okHttp post异步请求
     *
     * @param params 请求参数
     * @param callBack   请求返回数据回调
     * @return
     */
    public void post(String params, String requestUrl, final OkHttpCallback callBack) {
        requestUrl = RetrofitUtil.getWeatherBaseUrl() + requestUrl;
        try {
            Logger.I("post params = " + params);
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            final Request request = addHeaders().url(requestUrl).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    onFailedCallBack(e, callBack);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            String responseStr = response.body().string();
                            Logger.I("onResponse responseStr = " + responseStr);
                            JSONObject oriData = new JSONObject(responseStr.trim());

                            onSuccessCallBack(oriData, callBack);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            Logger.E("Http post Exception = " + e.toString());
        }
    }

    private Request.Builder addHeaders() {
        return new Request.Builder()
                //addHeader，可添加多个请求头  header，唯一，会覆盖
                .addHeader("Connection", "Keep-Alive")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE);
    }

    private void onSuccessCallBack(final JSONObject oriData, final OkHttpCallback callBack) {
        //因为okhttp3 UI的处理不能在子线程中，要在主线程中，所以要这样写
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null != callBack) {
                    callBack.onSuccess(oriData);
                }
            }
        });
    }

    private void onFailedCallBack(final IOException e, final OkHttpCallback callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null != callBack) {
                    callBack.onFailure(e);
                }
            }
        });
    }
}
