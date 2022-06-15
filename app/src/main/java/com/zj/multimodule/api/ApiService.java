package com.zj.multimodule.api;

import com.zj.multimodule.entity.PicResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * @author zhangjian
 */
public interface ApiService {
    /**
     * 向接口发送获取图片请求
     * @param sortName 图片分类名称 "美女" "腿控" "二次元"
     * @param format   数据格式 "html" "json"
     * @return
     */
    @GET("api/rand.img2")
    Call<PicResponse> loadData(@Query("sort") String sortName, @Query("format") String format);
}
