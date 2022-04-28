package com.zj.multimodule.component;

import android.app.Application;

import com.zj.multimodule.MyApplication;
import com.zj.multimodule.api.ApiService;
import com.zj.multimodule.module.ApiServiceModule;
import com.zj.multimodule.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    void inject(MyApplication application);

}
