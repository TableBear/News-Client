package com.hzx.news.ui.uikit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/5/9 14:32
 * @param:
 * @return:
 * @throws:
 */
public class NewsDetialScrollView extends ScrollView {
    public NewsDetialScrollView(Context context) {
        super(context);
    }

    public NewsDetialScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsDetialScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewsDetialScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }
}
