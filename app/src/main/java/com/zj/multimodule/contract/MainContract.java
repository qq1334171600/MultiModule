package com.zj.multimodule.contract;

import java.io.FileNotFoundException;

/**
 * @author zhangjian
 */
public interface MainContract {
    interface Presenter{
        /**
         * 根据分类和数据格式获取图片
         * @param sort 分类
         * @param format 数据格式 "json" "html"
         */
        void getPic(String sort,String format);

        /**
         * MVP架构中presenter与view绑定
         * @param view
         */
        void attachView(View view);

        /**
         * MVP架构中presenter和view解绑
         */
        void detachView();
    }
    interface View{
        /**
         * 根据url显示图片
         * @param url
         * @throws FileNotFoundException
         */
        void showPic(String url) throws FileNotFoundException;

        /**
         * Toast显示错误信息
         * @param info
         */
        void showFailInfo(String info);

        /**
         * 开始进度条
         */
        void startProgress();

        /**
         * 停止进度条
         */
        void stopProgress();
    }
    interface GetPicListener {
        /**
         * 获取图片监听成功回调
         * @param successMsg
         */
        void success(String successMsg);

        /**
         * 获取图片监听失败回调
         * @param failMsg
         */
        void fail(String failMsg);
    }
}
