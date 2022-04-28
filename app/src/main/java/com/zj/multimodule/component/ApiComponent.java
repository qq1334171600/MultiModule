package com.zj.multimodule.component;

import com.zj.multimodule.model.PicModel;
import com.zj.multimodule.module.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiServiceModule.class)
public interface ApiComponent {
    void inject(PicModel picModel);
}
