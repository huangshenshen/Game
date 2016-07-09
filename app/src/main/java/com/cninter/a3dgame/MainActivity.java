package com.cninter.a3dgame;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.cninter.a3dgame.adpater.MainArticleFramentViewPagerAdapter;
import com.cninter.a3dgame.adpater.MainFragmentPagerAdapter;
import com.cninter.a3dgame.adpater.MyAdapter;
import com.cninter.a3dgame.coustomview.News;
import com.cninter.a3dgame.fragment.ArticleFragment;
import com.cninter.a3dgame.fragment.ArticleFragment1;
import com.cninter.a3dgame.service.DownLoadService;
import com.cninter.a3dgame.service.MyIntentService;
import com.cninter.a3dgame.utils.HttpUtils;
import com.cninter.a3dgame.utils.JSONUtils;
import com.cninter.a3dgame.utils.NewsObj;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    HorizontalScrollView horizontalScrollView_top;
    RadioGroup radioGroup_top;
    RadioButton rb01_top, rb02_top, rb03_top, rb04_top, rb05_top, rb06_top, rb07_top, rb08_top, rb09_top, rb10_top;
    ViewPager viewPager;
    RadioGroup radioGroup_bottom;
    RadioButton rb01_bottom, rb02_bottom, rb03_bottom;
    List<Fragment> fragments;
    MainFragmentPagerAdapter mainFragmentPagerAdapter;
   // String sdpath = Environment.getExternalStorageDirectory().getAbsolutePath();
   // String dbname = "3Dgame.db";
   View.OnClickListener cl ;
    private ListView listView;

    List<NewsObj> data;
   // MyReceiver receiver;
    int page = 1;
   // String urlpath = "http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           // initStart();
            initView();
            initListener();
            initData();



    }

    private void initStart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,MyIntentService.class);
                startService(intent);

            }
        }).start();
    }


    private void initData() {
        data = new MyIntentService().getData();
        fragments = new ArrayList<>();
        //添加Fragment
        ArticleFragment f1 = new ArticleFragment(2);
        ArticleFragment1 f2 = new ArticleFragment1(2);

        ArticleFragment1 f3 = new ArticleFragment1(151);
        ArticleFragment1 f4 = new ArticleFragment1(152);
        ArticleFragment1 f5 = new ArticleFragment1(153);
        ArticleFragment1 f6 = new ArticleFragment1(154);
        ArticleFragment1 f7 = new ArticleFragment1(196);
        ArticleFragment1 f8 = new ArticleFragment1(197);
        ArticleFragment1 f9 = new ArticleFragment1(199);
        ArticleFragment1 f10= new ArticleFragment1(25);


        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        fragments.add(f5);
        fragments.add(f6);
        fragments.add(f7);
        fragments.add(f8);
        fragments.add(f9);
        fragments.add(f10);

        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(mainFragmentPagerAdapter);

    }

    private void initListener() {
        radioGroup_bottom.setOnCheckedChangeListener(this);
        radioGroup_top.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);

        cl = new View.OnClickListener(){
            @Override
            public void onClick(View v){
               switch (v.getId()){
                   case R.id.main_bottom_rb01:
                       rb01_bottom.setBackgroundResource(R.drawable.main_buttom_articleclick);

                       break;
                   case R.id.main_bottom_rb02:
                       rb02_bottom.setBackgroundResource(R.drawable.main_buttom_forumclick);
                       break;
                   case R.id.main_bottom_rb03:
                       rb03_bottom.setBackgroundResource(R.drawable.main_buttom_gameclick);
                       break;

               }


            }

        };
        rb01_bottom.setOnClickListener(cl);
        rb02_bottom.setOnClickListener(cl);
        rb03_bottom.setOnClickListener(cl);
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
        switch (i) {
            case R.id.main_top_rb1:
                viewPager.setCurrentItem(0);
                Toast.makeText(MainActivity.this, "文章首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb2:
                viewPager.setCurrentItem(1);
                Toast.makeText(MainActivity.this, "热点新闻", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb3:
                viewPager.setCurrentItem(2);
                Toast.makeText(MainActivity.this, "游戏杂谈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb4:
                viewPager.setCurrentItem(3);
                Toast.makeText(MainActivity.this, "硬件信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb5:
                viewPager.setCurrentItem(4);
                Toast.makeText(MainActivity.this, "游戏前瞻", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb6:
                viewPager.setCurrentItem(5);
                Toast.makeText(MainActivity.this, "游戏评测", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb7:
                viewPager.setCurrentItem(6);
                Toast.makeText(MainActivity.this, "原创精品", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb8:
                viewPager.setCurrentItem(7);
                Toast.makeText(MainActivity.this, "游戏盘点", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb9:
                viewPager.setCurrentItem(8);
                Toast.makeText(MainActivity.this, "时事焦点", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_top_rb10:
                viewPager.setCurrentItem(9);
                Toast.makeText(MainActivity.this, "攻略中心", Toast.LENGTH_SHORT).show();
                break;


            case R.id.main_bottom_rb01:
                viewPager.setCurrentItem(0);

                Toast.makeText(MainActivity.this, "bottom rb01", Toast.LENGTH_SHORT).show();
                horizontalScrollView_top.smoothScrollTo(0, 0);
                break;
            case R.id.main_bottom_rb02:
               // String typeurl = data.get(0).getTypeurl();
                Intent intent = new Intent(MainActivity.this,ForumWebviewAcitvity.class);
               // intent.putExtra("typeurl",typeurl);
                startActivity(intent);
                break;
            case R.id.main_bottom_rb03:
                Intent intent1 = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent1);
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
        horizontalScrollView_top.setSelected(true);
        radioGroup_top.setVisibility(View.VISIBLE);

        //获得当前ViewPager对应的RadioButton
        RadioButton radioButton = (RadioButton) radioGroup_top.getChildAt(position);

        radioButton.setChecked(true);
        //让顶部的RadioButton随着ViewPager一起滚动
        int left = radioButton.getLeft();
        horizontalScrollView_top.smoothScrollTo(left, 0);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
