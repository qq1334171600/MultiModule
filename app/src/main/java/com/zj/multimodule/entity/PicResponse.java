package com.zj.multimodule.entity;

import androidx.annotation.NonNull;

public class PicResponse {
    private String code;
    private String imgurl;
    private String msg;

    public PicResponse(String code, String imgurl, String msg) {
        this.code = code;
        this.imgurl = imgurl;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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
                ", imgurl='" + imgurl + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
