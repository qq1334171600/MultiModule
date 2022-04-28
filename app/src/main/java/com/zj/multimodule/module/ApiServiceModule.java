package com.zj.multimodule.module;

import com.zj.multimodule.api.ApiRetrofit;
import com.zj.multimodule.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiServiceModule {
    @Provides
    @Singleton
    public ApiService provideApiService(ApiRetrofit apiRetrofit){
        return apiRetrofit.getApiService();
    }
}
