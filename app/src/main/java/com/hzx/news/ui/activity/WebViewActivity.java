package com.hzx.news.ui.activity;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hzx.news.R;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {

    public static final String URL = "url";

    @BindView(R.id.iv_back)
    ImageView mIvBack;

    @BindView(R.id.tv_author)
    TextView mTvTitle;

    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;

    @BindView(R.id.wv_content)
    WebView mWvContent;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web_view;
    }
}
