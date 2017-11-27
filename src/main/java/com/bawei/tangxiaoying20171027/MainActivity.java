package com.bawei.tangxiaoying20171027;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.tangxiaoying20171027.Chenjinshi.ChengjinshiCode;
import com.bawei.tangxiaoying20171027.Fragment.FragmentFaXian;
import com.bawei.tangxiaoying20171027.Fragment.FragmentJingxuan;
import com.bawei.tangxiaoying20171027.Fragment.FragmentWode;
import com.bawei.tangxiaoying20171027.Fragment.FragmentZhuanTi;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity{



    private RadioButton mRb1;
    private RadioButton mRb2;
    private RadioButton mRb3;
    private RadioButton mRb4;

    private RadioGroup mRg;
    private List<Fragment> list;
      //ButterKnife找控件，要导入依赖，可以不用此方法
     @InjectView(R.id.vp)
     ViewPager mVp;

    private TabLayout mTab;
	//设置自定义标题
    String[] title={"精选","专题","发现","我的"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //沉浸式
        ChengjinshiCode.getInstance().Immersive(getWindow(),getActionBar());


        //调用方法
        initView();
          //ButterKnife初始化 控件
        ButterKnife.inject(this);
        addFragment();
          //创建ViewPager适配器
        MyViewPager  adapter=new  MyViewPager (getSupportFragmentManager());
        mVp.setAdapter(adapter);

        //设置可以滑动
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
		// 关联TabLayout和ViewPager 
        mTab.setupWithViewPager(mVp);

         //默认选中状态
        mRg.check(R.id.rb1);
		 //设置RadioButton和ViewPager联动
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rb1:
                        mVp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        mVp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        mVp.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        mVp.setCurrentItem(3);
                        break;


                    default:
                        break;
                }
            }
        });

    }


   //初始化控件
    private void initView() {

        mTab= (TabLayout) findViewById(R.id.tab);
        mRb1 = (RadioButton) findViewById(R.id.rb1);
        mRb2 = (RadioButton) findViewById(R.id.rb2);
        mRb3 = (RadioButton) findViewById(R.id.rb3);
        mRb4 = (RadioButton) findViewById(R.id.rb4);

        mRg = (RadioGroup) findViewById(R.id.rg);
    }
     //添加Fragment
    private void addFragment() {
        list = new ArrayList<>();
        list.add(new FragmentJingxuan());
        list.add(new FragmentZhuanTi());
        list.add(new FragmentFaXian());
        list.add(new FragmentWode());
    }

   // ViewPager适配器
    class  MyViewPager extends FragmentPagerAdapter {


       public MyViewPager(FragmentManager fm) {
           super(fm);
       }

       @Override
       public Fragment getItem(int position) {
           return list.get(position);
       }

       @Override
       public int getCount() {
           return list.size();
       }
        //重写getPageTitle 
       @Override
       public CharSequence getPageTitle(int position) {
           return title[position];
       }
   }
}


