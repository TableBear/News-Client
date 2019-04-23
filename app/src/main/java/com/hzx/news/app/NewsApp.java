package com.hzx.news.app;

import com.hzx.news.BuildConfig;
import com.hzx.news.app.base.BaseApp;
import com.hzx.news.model.entity.Token;
import com.socks.library.KLog;

import org.litepal.LitePal;

public class NewsApp extends BaseApp {

    public static Token token;



    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);//初始化KLog
        LitePal.initialize(getContext());//初始化LitePal
    }



}
