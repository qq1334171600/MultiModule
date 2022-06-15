package com.zj.multimodule.component;
import com.zj.multimodule.MyApplication;
import com.zj.multimodule.module.ApiServiceModule;
import com.zj.multimodule.module.AppModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 * @author zhangjian
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    /**
     * 将ApiService注入到Application中
     * @param application 自定义的application
     */
    void inject(MyApplication application);

}
