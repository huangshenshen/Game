package com.cninter.a3dgame.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/7/6 0006.
 */
public class MainArticleFramentViewPagerAdapter  extends PagerAdapter{
    List<ImageView> imageViews;

    public MainArticleFramentViewPagerAdapter(List<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));

        return imageViews.get(position);
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));

    }
}
