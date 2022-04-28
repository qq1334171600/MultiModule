package com.zj.multimodule.component;

import com.zj.multimodule.module.PicPresenterModule;
import com.zj.multimodule.presenter.PicPresenter;
import com.zj.multimodule.scope.ActivityScope;
import com.zj.multimodule.view.MainActivity;

import javax.inject.Scope;

import dagger.Component;
@Component(modules = PicPresenterModule.class)
public interface MainActivityComponent extends AppComponent{
    void inject(MainActivity mainActivity);
}
