package com.zj.multimodule.module;

import android.app.Application;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * @author zhangjian
 */
@Module(includes = ApiServiceModule.class)
public class AppModule {
    private final Application application;
    public AppModule(Application application){
        this.application=application;
    }
    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

}
