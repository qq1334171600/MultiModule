package com.zj.multimodule.component;

import com.zj.multimodule.module.PicPresenterModule;
import com.zj.multimodule.view.MainActivity;
import dagger.Component;
/**
 * @author zhangjian
 */
@Component(modules = PicPresenterModule.class)
public interface MainActivityComponent extends AppComponent{
    /**
     * 将PicPresenterModule注入到MainActivity中
     * @param mainActivity
     */
    void inject(MainActivity mainActivity);
}
