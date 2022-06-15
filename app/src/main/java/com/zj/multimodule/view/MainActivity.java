package com.zj.multimodule.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zj.multimodule.MyApplication;
import com.zj.multimodule.R;
import com.zj.multimodule.component.DaggerMainActivityComponent;
import com.zj.multimodule.contract.MainContract;
import com.zj.multimodule.presenter.PicPresenter;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhangjian
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {
    @Inject
    public PicPresenter presenter;
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
        DaggerMainActivityComponent.builder().build().inject(this);
        presenter.attachView(this);


    }
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bt_ok)
    public void onViewClicked(){
        presenter.getPic(etInput.getText().toString(),"json");

    }

    @Override
    public void showPic(String url)  {
        Glide.with(MyApplication.getContext())
                .load(url)
                .placeholder(R.drawable.none_pic)
                .listener(requestListener)
                .dontAnimate()
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .into(imgShow);

    }
    private final RequestListener<Drawable> requestListener=new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            if (e != null) {
                presenter.fail(e.getMessage());
            }
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            return false;
        }


    };

    @Override
    public void showFailInfo(String info) {
        Toast.makeText(MyApplication.getContext(),info,Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgress() {

    }

    @Override
    public void stopProgress() {

    }

}