package com.zj.multimodule.presenter;

import com.zj.multimodule.component.DaggerPicPresenterComponent;
import com.zj.multimodule.contract.MainContract;
import java.io.FileNotFoundException;
/**
 * @author zhangjian
 */
public class PicPresenter extends BasePresenter {
    public PicPresenter(){
        DaggerPicPresenterComponent.builder().build().inject(this);
    }

    @Override
    public void getPic(String sort, String format) {
        model.getPic(sort,format,this);
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;

    }

    @Override
    public void success(String msg) {
        try {
            view.showPic(msg);
        } catch (FileNotFoundException e) {
            view.showFailInfo(e.getMessage());
        }
    }

    @Override
    public void fail(String msg) {
        view.showFailInfo(msg);

    }
}
