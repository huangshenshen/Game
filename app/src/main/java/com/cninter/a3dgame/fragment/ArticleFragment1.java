package com.cninter.a3dgame.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.cninter.a3dgame.ArticleWebviewActivity;
import com.cninter.a3dgame.R;
import com.cninter.a3dgame.adpater.MainArticleFramentListViewPagerAdapter;
import com.cninter.a3dgame.adpater.MainArticleFramentViewPagerAdapter;
import com.cninter.a3dgame.adpater.MyAdapter;
import com.cninter.a3dgame.coustomview.MainArticleFragmentViewPager;
import com.cninter.a3dgame.coustomview.News;
import com.cninter.a3dgame.utils.NewsObj;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/7/9 0009.
 */
public class ArticleFragment1 extends Fragment{
    //定义文章的类型
    private int typeid;
    private MainArticleFramentViewPagerAdapter mainArticleFramentViewPagerAdapter;
    private MainArticleFramentListViewPagerAdapter mainArticleFramentListViewPagerAdapter;
    String sdpath= Environment.getExternalStorageDirectory().getAbsolutePath();
    String dbname="dangame.db";
    public ArticleFragment1(){}
    List<News> newsList;
    List<NewsObj> newsObjs;
    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    MyAdapter adapter;
    public ArticleFragment1(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //获得Fragment中整体布局
        View view = inflater.inflate(R.layout.activity_main_articlefragment1,null);
        //获得Fragment中的ViewPager
        MainArticleFragmentViewPager mainArticleFragmentViewPager = (MainArticleFragmentViewPager) view.findViewById(R.id.main_articlefragment_viewpager);

        //初始化ViewPager数据
        List<ImageView> imageViews = new ArrayList<>();



        ListView listView = (ListView) view.findViewById(R.id.activity_main_articlefragment1_lv);
        //从数据库读取等到数据　
        LoadSQLiteData(typeid);

        adapter = new MyAdapter(newsList,getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = newsList.get(i).getArcurl();
                Intent intent = new Intent(getContext(),ArticleWebviewActivity.class);

                intent.putExtra("url",url);
                startActivity(intent);



            }
        });

        return view;
    }

    public void LoadSQLiteData(int typeid) {
        newsList = new ArrayList<>();
        Log.i("aaa","开始打开数据库");
        //SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+ File.separator+dbname, null);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(sdpath + File.separator + dbname, null, 1);
        Cursor cursor = db.rawQuery("select * from games where typeid='"+typeid+"'", null);
        Log.i("aaa","加载");
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            Log.i("aaa", "展示标题" + title);

            String description = cursor.getString(cursor.getColumnIndex("description"));
            Log.i("aaa", "展示内容------" + description);

            String pathimg = cursor.getString(cursor.getColumnIndex("pathimg"));
            Log.i("aaa", "展示图片的path" + pathimg);
            String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
            Log.i("aaa", "展示图片的连接" + litpic);
            String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
            String pubdate = cursor.getString(cursor.getColumnIndex("pubdate"));
            String click = cursor.getString(cursor.getColumnIndex("click"));
            String typeid1 = cursor.getString(cursor.getColumnIndex("typeid"));
            News news = new News(title, description, pathimg, litpic, arcurl, pubdate, click,typeid1);
            newsList.add(news);
            Log.i("aaa", "展示图片的集合长度是" + newsList.size());
        }



    }
}
