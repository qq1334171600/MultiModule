package com.zj.multimodule.component;

import com.zj.multimodule.model.PicModel;
import com.zj.multimodule.module.ApiServiceModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 * @author zhangjian
 */
@Singleton
@Component(modules = ApiServiceModule.class)
public interface ApiComponent {
    /**
     * 将ApiService注入到PicModel中
     * @param picModel mvp架构的m层
     */
    void inject(PicModel picModel);
}
