package com.zj.multimodule.component;

import com.zj.multimodule.contract.MainContract;
import com.zj.multimodule.module.PicModelModule;
import com.zj.multimodule.presenter.PicPresenter;

import dagger.Component;

@Component(modules = PicModelModule.class)
public interface PicPresenterComponent {
    void inject(PicPresenter presenter);
}
