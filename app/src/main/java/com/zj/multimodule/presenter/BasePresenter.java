package com.zj.multimodule.presenter;

import android.content.Context;

import com.zj.multimodule.api.ApiFactory;
import com.zj.multimodule.api.ApiService;
import com.zj.multimodule.contract.MainContract;

public abstract class BasePresenter implements MainContract.Presenter {
    public MainContract.View view;
    public Context context;
    public static final ApiService API_SERVICE= ApiFactory.getApiServiceSingleton();
}
