package com.cninter.a3dgame;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.cninter.a3dgame.adpater.MainArticleFramentViewPagerAdapter;
import com.cninter.a3dgame.adpater.MainFragmentPagerAdapter;
import com.cninter.a3dgame.fragment.ArticleFragment;
import com.cninter.a3dgame.utils.HttpUtils;
import com.cninter.a3dgame.utils.JSONUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    HorizontalScrollView horizontalScrollView_top;
    RadioGroup radioGroup_top;
    RadioButton rb01_top, rb02_top, rb03_top, rb04_top, rb05_top, rb06_top, rb07_top, rb08_top, rb09_top, rb10_top;
    ViewPager viewPager;
    RadioGroup radioGroup_bottom;
    RadioButton rb01_bottom, rb02_bottom, rb03_bottom;
    List<Fragment> fragments;
    MainFragmentPagerAdapter  mainFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();



    }

    private void initData() {
        fragments = new ArrayList<>();
        //添加Fragment
        ArticleFragment f1 = new ArticleFragment(1);
        ArticleFragment f2 = new ArticleFragment(2);
        ArticleFragment f3 = new ArticleFragment(3);
        ArticleFragment f4 = new ArticleFragment(4);
        ArticleFragment f5 = new ArticleFragment(5);
        ArticleFragment f6 = new ArticleFragment(6);
        ArticleFragment f7 = new ArticleFragment(7);
        ArticleFragment f8 = new ArticleFragment(8);

        fragments.add(f1);fragments.add(f2);fragments.add(f3);
        fragments.add(f4);fragments.add(f5);fragments.add(f6);
        fragments.add(f7);fragments.add(f8);
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(mainFragmentPagerAdapter);

    }

    private void initListener() {
        radioGroup_bottom.setOnCheckedChangeListener(this);
        radioGroup_top.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    private void initView() {

        horizontalScrollView_top = (HorizontalScrollView) findViewById(R.id.main_top_hsv);
        radioGroup_top = (RadioGroup) findViewById(R.id.main_top_rg);
        rb01_top = (RadioButton) findViewById(R.id.main_top_rb1);
        rb02_top = (RadioButton) findViewById(R.id.main_top_rb2);
        rb03_top = (RadioButton) findViewById(R.id.main_top_rb3);
        rb04_top = (RadioButton) findViewById(R.id.main_top_rb4);
        rb05_top = (RadioButton) findViewById(R.id.main_top_rb5);
        rb06_top = (RadioButton) findViewById(R.id.main_top_rb6);
        rb07_top = (RadioButton) findViewById(R.id.main_top_rb7);
        rb08_top = (RadioButton) findViewById(R.id.main_top_rb8);
        rb09_top = (RadioButton) findViewById(R.id.main_top_rb9);
        rb10_top = (RadioButton) findViewById(R.id.main_top_rb10);
        rb01_top.setChecked(true);
        viewPager = (ViewPager) findViewById(R.id.main_center_vp);
        radioGroup_bottom = (RadioGroup) findViewById(R.id.main_bottom_rg);
        rb01_bottom = (RadioButton) findViewById(R.id.main_bottom_rb01);
        rb02_bottom = (RadioButton) findViewById(R.id.main_bottom_rb02);
        rb03_bottom = (RadioButton) findViewById(R.id.main_bottom_rb03);


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.main_top_rb1:
                viewPager.setCurrentItem(0);
                Toast.makeText(MainActivity.this, "top rb01", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb2:
                viewPager.setCurrentItem(1);
                Toast.makeText(MainActivity.this, "top rb02", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb3:
                viewPager.setCurrentItem(2);
                Toast.makeText(MainActivity.this, "top rb03", Toast.LENGTH_SHORT).show();
                break;

            case R.id.main_bottom_rb01:
                viewPager.setCurrentItem(3);
                Toast.makeText(MainActivity.this, "bottom rb01", Toast.LENGTH_SHORT).show();
                horizontalScrollView_top.smoothScrollTo(0,0);

                break;

        }



    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //顶部的滚动条出现移动效果
        horizontalScrollView_top.setVisibility(View.VISIBLE);
        radioGroup_top.setVisibility(View.VISIBLE);
        //获得当前ViewPager对应的RadioButton
        RadioButton radioButton = (RadioButton) radioGroup_top.getChildAt(position);

        radioButton.setChecked(true);
        //让顶部的RadioButton随着ViewPager一起滚动
        int left = radioButton.getLeft();
        horizontalScrollView_top.smoothScrollTo(left,0);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
