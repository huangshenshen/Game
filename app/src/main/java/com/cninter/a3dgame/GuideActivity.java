package com.cninter.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cninter.a3dgame.adpater.GuideViewPagerAdpater;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager guide_viewpager;
    List<View> views;
    LayoutInflater layoutInflater;
    GuideViewPagerAdpater adpater ;
    ImageView[] dots;
    int currentIndex;//当前的页面索引
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDot();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        //设置底部显示点的颜色
        if(position<0||position+1>views.size()){
            return;
        }
        //设置当前位置为选中状态
        Log.i("aaa","当前的position的值"+position);
        dots[position].setEnabled(false);
        //设置之前的位置为非选中状态
        dots[currentIndex] .setEnabled(true);

        currentIndex = position;
        Log.i("aaa","当前的currentIndex的值"+currentIndex);

        //添加最后一个引导界面的Button监听
        if(position==views.size()-1){
            Button btn = (Button) views.get(position).findViewById(R.id.guide_page3_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //保存一个登陆过的记录
                    Log.i("aaa","按钮点击了");
                    setGuide();
                    //跳转
                    Intent mainIntent = new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            });
        }



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //保存登陆过的信息
    private void setGuide(){
        SharedPreferences sharedPreferences = getSharedPreferences("isFistLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",true);
        Log.i("aaa","判断是否登录");
        editor.commit();


    }
    //初始化View
    private   void initView (){
        guide_viewpager = (ViewPager) this.findViewById(R.id.guide_viewpager);
        views = new ArrayList<View>();
        layoutInflater = LayoutInflater.from(this);
        View view1 = layoutInflater.inflate(R.layout.activity_guide_pagercf1,null);
        View view2 = layoutInflater.inflate(R.layout.activity_guide_pagercf2,null);
        View view3 = layoutInflater.inflate(R.layout.activity_guide_pagercf3,null);
        views.add(view1);views.add(view2);views.add(view3);
        adpater = new GuideViewPagerAdpater(views);
        guide_viewpager.setAdapter(adpater);
        guide_viewpager.addOnPageChangeListener(this);

    }
    //初始化所有的点
    private void initDot(){
        LinearLayout ll = (LinearLayout)findViewById(R.id.guide_dot_ll);
        dots = new ImageView[views.size()];
        //得到线性布局下面的所有的点对象
        for(int i=0;i<views.size();i++){
            dots[i]= (ImageView)ll.getChildAt(i);
            dots[i].setEnabled(true);
        }
        //初始化当前所在page的索引值
        currentIndex =0;
        //设置当前的Pager是白色
        dots[currentIndex].setEnabled(false);

    }




}
