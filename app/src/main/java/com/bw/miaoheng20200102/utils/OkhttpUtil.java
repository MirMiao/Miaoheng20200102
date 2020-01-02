package com.bw.miaoheng20200102.utils;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 时间 :2020/1/2  8:42
 * 作者 :苗恒
 * 功能 :
 */
public class OkhttpUtil {
    Handler handler=new Handler();
    private static OkhttpUtil okhttpUtil;

    private OkhttpUtil() {
    }

    public static OkhttpUtil getInstance() {
        if(okhttpUtil==null){
            synchronized (OkhttpUtil.class){
                if(okhttpUtil==null){
                    okhttpUtil=new OkhttpUtil();
                }
            }
        }
        return okhttpUtil;
    }
    public void doGet(String url, final OkCallBack okCallBack){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okCallBack.failur(e);
                        }
                    });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            okCallBack.success(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public interface OkCallBack{
        void success(String response);
        void failur(Throwable throwable);
    }
}
