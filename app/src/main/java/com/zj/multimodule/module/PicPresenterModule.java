package com.zj.multimodule.module;

import com.zj.multimodule.presenter.PicPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangjian
 */
@Module
public class PicPresenterModule {
    @Provides
    PicPresenter providePicPresenter(){
        return new PicPresenter();
    }
}
