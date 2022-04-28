package com.zj.multimodule.model;

import android.util.Log;
import android.widget.Toast;

import com.zj.multimodule.MyApplication;
import com.zj.multimodule.api.ApiService;
import com.zj.multimodule.component.DaggerApiComponent;
import com.zj.multimodule.contract.MainContract;
import com.zj.multimodule.entity.PicResponse;
import com.zj.multimodule.presenter.PicPresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PicModel {
    @Inject
    public ApiService apiService;
    public PicModel(){
        DaggerApiComponent.builder().build().inject(this);
    }
    public void getPic(String sort, String format, MainContract.GetPicListenr listenr) {
        final String[] picUrl = new String[1];
        Call<PicResponse> call= apiService.loadData(sort,format);
        Log.d(this.getClass().getName(), "getPic: call的url："+call.request().url() );
        call.enqueue(new Callback<PicResponse>() {
            @Override
            public void onResponse(Call<PicResponse> call, Response<PicResponse> response) {
                picUrl[0] =response.body().getImgurl();
                Log.d(this.getClass().getName(), "onResponse: 发起请求的路径："+call.request().url().toString() );
                Log.d(this.getClass().getName(), "onResponse: 请求图片所返回的url"+picUrl[0] );
                if (picUrl[0]!=null&&!picUrl[0].isEmpty()){
                    listenr.success(picUrl[0]);
                    //view.showPic(picUrl[0]);

                }else listenr.fail("图片链接为空");
            }

            @Override
            public void onFailure(Call<PicResponse> call, Throwable t) {
                listenr.fail("网络错误");
                //Toast.makeText(MyApplication.getContext(),"读取图片失败",Toast.LENGTH_LONG).show();
                Log.d(getClass().getName(), "onFailure: "+t.toString() );

            }
        });
    }

}
