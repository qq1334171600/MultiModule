package com.zj.multimodule.api;

import android.util.Log;

import com.zj.multimodule.MyApplication;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @author zhangjian
 */
@Singleton
public class ApiRetrofit {

    public ApiService apiService;
    public static final String BASE_URL ="https://api.uomg.com/";
    public ApiService getApiService(){
        return apiService;
    }
    @Inject
    public ApiRetrofit(){
        File httpCacheDirectory=new File(MyApplication.getContext().getCacheDir(),"response");
        Log.e(MyApplication.getContext().getPackageName(), "ApiRetrofit: 缓存目录为"+httpCacheDirectory);
        int cacheSize=500*1024*1024;
        Cache cache=new Cache(httpCacheDirectory,cacheSize);
        OkHttpClient client=new OkHttpClient.Builder().cache(cache).build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //retrofit初始化时创建全局服务
        apiService=retrofit.create(ApiService.class);
    }
}
