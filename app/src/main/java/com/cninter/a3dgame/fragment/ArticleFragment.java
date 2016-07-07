package com.cninter.a3dgame.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cninter.a3dgame.R;
import com.cninter.a3dgame.adpater.MainArticleFramentViewPagerAdapter;
import com.cninter.a3dgame.adpater.MainFragmentPagerAdapter;
import com.cninter.a3dgame.coustomview.MainArticleFragmentViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/7/6 0006.
 */
public class ArticleFragment extends Fragment {
    //定义文章的类型
    private int typeid;
    private MainArticleFramentViewPagerAdapter mainArticleFramentViewPagerAdapter;
    public ArticleFragment(){}

    public ArticleFragment(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //获得Fragment中整体布局
        View view = inflater.inflate(R.layout.activity_main_articlefragment,null);
        //获得Fragment中的ViewPager
        MainArticleFragmentViewPager mainArticleFragmentViewPager = (MainArticleFragmentViewPager) view.findViewById(R.id.main_articlefragment_viewpager);
        int imageRsId [] = {R.drawable.default1,R.drawable.default2,R.drawable.default3};
        //初始化ViewPager数据
        List<ImageView> imageViews = new ArrayList<>();
        for (int i=0;i<3;i++){
            ImageView imageView = new ImageView(getActivity());
            //设置图片的缩放类型 铺满屏幕
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViews.add(imageView);



        }
        mainArticleFramentViewPagerAdapter = new MainArticleFramentViewPagerAdapter(imageViews);
        mainArticleFragmentViewPager.setAdapter(mainArticleFramentViewPagerAdapter);
        TextView tv = (TextView) view.findViewById(R.id.activity_main_articlefragment_tv);
        Log.i("aaa","typeid = "+typeid);
        tv.setText(typeid+"");



        return view;
    }
}
