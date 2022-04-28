package com.zj.multimodule;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.zj.multimodule.api.ApiFactory;
import com.zj.multimodule.api.ApiService;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }


}
