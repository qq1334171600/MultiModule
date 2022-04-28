package com.zj.multimodule.contract;

public interface MainContract {
    interface Presenter{
        void getPic(String sort,String format);
        void attachView(View view);
        void detachView();
    }
    interface View{
        void showPic(String url);
    }
}
