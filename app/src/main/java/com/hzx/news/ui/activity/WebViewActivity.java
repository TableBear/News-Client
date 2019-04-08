package com.hzx.news.ui.activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hzx.news.R;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    public static final String URL = "url";

    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.tv_author)
    TextView tvvTitle;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @BindView(R.id.wv_content)
    WebView wvContent;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData() {
        String url = getIntent().getStringExtra(URL);
        wvContent.loadUrl(url);
    }

    @Override
    public void initListener() {
        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);


        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pbLoading.setVisibility(View.GONE);
            }
        });

        wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pbLoading.setProgress(newProgress);
            }
        });

        wvContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvContent.canGoBack()) {  //表示按返回键
                        wvContent.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
