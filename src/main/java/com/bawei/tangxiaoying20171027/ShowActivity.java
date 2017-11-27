package com.bawei.tangxiaoying20171027;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.VideoView;

import com.bawei.tangxiaoying20171027.Adapter.ShowAdapter;
import com.bawei.tangxiaoying20171027.Bean.BeanTwo;
import com.bawei.tangxiaoying20171027.Chenjinshi.ChengjinshiCode;
import com.bawei.tangxiaoying20171027.Presenter.MyPresenter;
import com.bawei.tangxiaoying20171027.View.Iview;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ShowActivity extends AppCompatActivity implements Iview{

   // private VideoView mViewVideo;
     @InjectView(R.id.video_view)
     VideoView mViewVideo;

    @InjectView(R.id.recy2)
    RecyclerView recys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //沉浸式
        ChengjinshiCode.getInstance().Immersive(getWindow(),getActionBar());



        //隐藏标题
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        ButterKnife.inject(this);
        Uri rawUri = Uri.parse(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local2/adc.mp4");
        mViewVideo.setVideoURI(rawUri);
        mViewVideo.start();

        MyPresenter presenter=new MyPresenter(this);
        presenter.loadData();
        //创建布局管理器
        GridLayoutManager ll = new GridLayoutManager(this,2);
        recys.setLayoutManager(ll);


    }



    @Override
    public void setData(List<BeanTwo.RetBean.ListBean> beanList) {
        //创建适配器
        ShowAdapter showAdapter = new ShowAdapter(ShowActivity.this, beanList);
        recys.setAdapter(showAdapter);
    }
}
