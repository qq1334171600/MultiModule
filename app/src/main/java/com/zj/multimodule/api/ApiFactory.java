package com.zj.multimodule.api;

public class ApiFactory {
    static volatile ApiService apiServiceSingleton=null;
    public static ApiService getApiServiceSingleton(){//单例模式
        if (apiServiceSingleton==null){
            synchronized (ApiFactory.class){
                if (apiServiceSingleton==null){
                    apiServiceSingleton=new ApiRetrofit().getApiService();
                }

            }
        }
        return apiServiceSingleton;
    }
}
