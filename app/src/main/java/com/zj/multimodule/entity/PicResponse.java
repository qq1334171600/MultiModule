package com.zj.multimodule.entity;

import androidx.annotation.NonNull;

/**
 * @author zhangjian
 */
public class PicResponse {
    private String code;
    private String imgUrl;
    private String msg;

    public PicResponse(String code, String imgUrl, String msg) {
        this.code = code;
        this.imgUrl = imgUrl;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgurl) {
        this.imgUrl = imgurl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @NonNull
    @Override
    public String toString() {
        return "PicResponse{" +
                "code='" + code + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
