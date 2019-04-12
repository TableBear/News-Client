package com.hzx.news.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nukc.stateview.StateView;
import com.hzx.news.R;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity {

    public static final String URL = "url";

    StateView stateView;

    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.tv_author)
    TextView tvvTitle;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @BindView(R.id.wv_content)
    WebView wvContent;

    @BindView(R.id.fl_content)
    FrameLayout content;

    @BindView(R.id.iv_collect)
    ImageView ivCollect;

    @BindView(R.id.iv_share)
    ImageView ivShare;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView() {
        super.initView();
        stateView = StateView.inject(content);
        stateView.setLoadingResource(R.layout.page_loading);
        stateView.setRetryResource(R.layout.page_net_error);
    }

    @Override
    public void initData() {
        String url = getIntent().getStringExtra(URL);
        wvContent.loadUrl(url);
    }

//    final class InJavaScriptLocalObj {
//        @JavascriptInterface
//        public void showSource(String html) {
//            System.out.println("网页源码");
//            System.out.println(html);
//        }
//    }

    @Override
    public void initListener() {
        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
//        wvContent.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        settings.setPluginState(WebSettings.PluginState.ON);
        //支持视频播放
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
//        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setUseWideViewPort(true); // 关键点
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setSupportZoom(true); // 支持缩放
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("网页开始加载");
                pbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pbLoading.setVisibility(View.GONE);
                System.out.println("网页加载完成");
//                view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
//                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                String jsFun = "javascript:function startHide(){\n" +
                        "        document.getElementsByClassName('card-wrapper')[0].style.display='none';\n" +
                        "        document.getElementsByClassName('btn-bottom-wrapper')[0].style.display='none';\n" +
                        "};";
                //注入 js函数
                view.loadUrl(jsFun);
                //调用 js函数
                view.loadUrl("javascript:startHide();");
                stateView.showContent();
            }

            //加载完成的时候会回调
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                stateView.showRetry();
            }

            //加载完成的时候会回调
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                stateView.showRetry();
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

    @OnClick(R.id.iv_collect)
    public void onCollectClicked() {
        Toast.makeText(this, "点击了收藏", Toast.LENGTH_SHORT).show();
        ivCollect.setImageResource(R.mipmap.my_collect);
    }

    @OnClick(R.id.iv_share)
    public void onShareClicked() {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, wvContent.getUrl());
        startActivity(Intent.createChooser(textIntent, "分享"));

    }
}
