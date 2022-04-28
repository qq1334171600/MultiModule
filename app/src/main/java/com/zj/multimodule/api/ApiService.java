package com.zj.multimodule.api;

import com.zj.multimodule.model.PicResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/rand.img2")
    Call<PicResponse> loadData(@Query("sort") String sortName, @Query("format") String format);
}
