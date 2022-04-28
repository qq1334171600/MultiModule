package com.zj.multimodule.api;

import javax.inject.Inject;
import javax.inject.Singleton;

public class ApiFactory {
   // @Inject @Singleton
   // volatile ApiService apiServiceSingleton;
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
