package com.cninter.a3dgame.adpater;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/7/6 0006.
 */
public class MainArticleFramentListViewPagerAdapter extends PagerAdapter{
    ListView listView;

    public MainArticleFramentListViewPagerAdapter(ListView listView) {
        this.listView = listView;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(listView);

        return listView;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listView);

    }
}
