package com.hzx.news.ui.activity;

import android.content.Intent;

import com.hzx.news.R;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;
import com.hzx.news.utils.UIUtils;

public class SplashActivity extends BaseActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        UIUtils.postTaskDelay(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
