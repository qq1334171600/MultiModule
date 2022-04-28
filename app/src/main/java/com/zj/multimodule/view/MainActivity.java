package com.zj.multimodule.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.zj.multimodule.MyApplication;
import com.zj.multimodule.R;
import com.zj.multimodule.contract.MainContract;
import com.zj.multimodule.model.PicResponse;
import com.zj.multimodule.presenter.PicPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private final PicPresenter presenter=new PicPresenter();
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_input) EditText etInput;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bt_ok) Button btOk;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_show) ImageView imgShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.attachView(this);


    }
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bt_ok)
    public void onViewClicked(){
        presenter.getPic(etInput.getText().toString(),"json");

    }

    @Override
    public void showPic(String url) {
        Glide.with(MyApplication.getContext())
                .load(url)
                .placeholder(R.drawable.none_pic)
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .into(imgShow);
    }

}