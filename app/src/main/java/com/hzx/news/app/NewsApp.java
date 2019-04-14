package com.hzx.news.app;

import com.hzx.news.BuildConfig;
import com.hzx.news.app.base.BaseApp;
import com.socks.library.KLog;

import org.litepal.LitePal;

public class NewsApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);//初始化KLog
        LitePal.initialize(getContext());
    }
}
