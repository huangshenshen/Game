package com.cninter.a3dgame.coustomview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ${jacksen-hss} on 2016/7/6 0006.
 */
public class MainArticleFragmentViewPager  extends ViewPager {

    public MainArticleFragmentViewPager(Context context) {
        super(context);
    }

    public MainArticleFragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //不允许父类拦截触摸时间
        getParent().requestDisallowInterceptTouchEvent(true);

        return super.dispatchTouchEvent(ev);


    }

}
