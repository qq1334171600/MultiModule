package com.zj.multimodule.component;

import com.zj.multimodule.module.PicModelModule;
import com.zj.multimodule.presenter.PicPresenter;
import dagger.Component;

/**
 * @author zhangjian
 */
@Component(modules = PicModelModule.class)
public interface PicPresenterComponent {
    /**
     * 将PicModelModule注入到PicPresenter中
     * @param presenter
     */
    void inject(PicPresenter presenter);
}
