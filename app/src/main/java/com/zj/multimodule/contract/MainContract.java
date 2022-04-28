package com.zj.multimodule.contract;

import java.io.FileNotFoundException;

public interface MainContract {
    interface Presenter{
        void getPic(String sort,String format);
        void attachView(View view);
        void detachView();
    }
    interface View{
        void showPic(String url) throws FileNotFoundException;
        void showFailInfo(String info);
        void startProgress();
        void stopProgress();
    }
    interface GetPicListenr{
        void success(String successMsg);
        void fail(String failMsg);
    }
}
