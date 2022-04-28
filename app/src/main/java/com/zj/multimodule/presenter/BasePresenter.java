package com.zj.multimodule.presenter;

import android.content.Context;

import com.zj.multimodule.api.ApiFactory;
import com.zj.multimodule.api.ApiService;
import com.zj.multimodule.component.DaggerPicPresenterComponent;
import com.zj.multimodule.contract.MainContract;
import com.zj.multimodule.model.PicModel;

import javax.inject.Inject;

public abstract class BasePresenter implements MainContract.Presenter ,MainContract.GetPicListenr{
    public BasePresenter(){
    }
    public MainContract.View view;
    @Inject
    public PicModel model;
    public Context context;
}
