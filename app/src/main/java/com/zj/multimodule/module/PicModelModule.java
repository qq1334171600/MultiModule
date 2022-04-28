package com.zj.multimodule.module;

import com.zj.multimodule.model.PicModel;
import com.zj.multimodule.presenter.PicPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PicModelModule {
    @Provides
    PicModel providePicModel(){
        return new PicModel();
    }
}
