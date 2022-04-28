package com.zj.multimodule.presenter;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.zj.multimodule.MyApplication;
import com.zj.multimodule.R;
import com.zj.multimodule.contract.MainContract;
import com.zj.multimodule.model.PicResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PicPresenter extends BasePresenter{

    @Override
    public void getPic(String sort, String format) {
        final String[] picUrl = new String[1];
        Call<PicResponse> call=PicPresenter.API_SERVICE.loadData(sort,format);
        Log.e(this.getClass().getName(), "getPic: call的url："+call.request().url() );
        call.enqueue(new Callback<PicResponse>() {
            @Override
            public void onResponse(Call<PicResponse> call, Response<PicResponse> response) {
                picUrl[0] =response.body().getImgurl();
                Log.e(this.getClass().getName(), "onResponse: 发起请求的路径："+call.request().url().toString() );
                Log.e(this.getClass().getName(), "onResponse: 请求图片所返回的url"+picUrl[0] );
                if (picUrl[0]!=null&&!picUrl[0].isEmpty()){
                    view.showPic(picUrl[0]);

                }
            }

            @Override
            public void onFailure(Call<PicResponse> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"读取图片失败",Toast.LENGTH_LONG).show();
                Log.e(getClass().getName(), "onFailure: "+t.toString() );

            }
        });
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;

    }
}
