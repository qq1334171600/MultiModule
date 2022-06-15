package com.zj.multimodule.presenter;

import com.zj.multimodule.contract.MainContract;
import com.zj.multimodule.model.PicModel;
import javax.inject.Inject;

/**
 * @author zhangjian
 */
public abstract class BasePresenter implements MainContract.Presenter , MainContract.GetPicListener {
    public BasePresenter(){
    }
    public MainContract.View view;
    @Inject
    public PicModel model;
}
