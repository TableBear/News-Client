package com.hzx.news.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nukc.stateview.StateView;
import com.hzx.news.R;
import com.hzx.news.model.entity.OptStatus;
import com.hzx.news.presenter.OperationPresenter;
import com.hzx.news.presenter.View.NewsDetailView;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity<OperationPresenter> implements NewsDetailView {

    public static final String URL = "url";
    public static final String NID = "nid";

    StateView stateView;

    String url;
    String nid;

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

    @BindView(R.id.iv_like)
    ImageView ivLike;

    private boolean isCollect = false;
    private boolean isLike = false;

    @Override
    protected OperationPresenter createPresenter() {
        return new OperationPresenter(this);
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
        url = getIntent().getStringExtra(URL);
        nid = getIntent().getStringExtra(NID);
        wvContent.loadUrl(url);
        presenter.click(nid);
        presenter.getOptStatus(nid);
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
        if (!isCollect) {
            presenter.collect(nid);
        } else {
            presenter.cancleCollect(nid);
        }
    }

    @OnClick(R.id.iv_share)
    public void onShareClicked() {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, wvContent.getUrl());
        startActivity(Intent.createChooser(textIntent, "分享"));

    }

    @OnClick(R.id.iv_like)
    public void onLikeClicked() {
        if (!isLike) {
            presenter.like(nid);
        } else {
            presenter.cancleLike(nid);
        }
    }

    @Override
    public void onCollectSuccess() {
        Toast.makeText(this, "点击了收藏", Toast.LENGTH_SHORT).show();
        ivCollect.setImageResource(R.mipmap.my_collect);
        isCollect = true;
    }

    @Override
    public void onCollectError() {
        Toast.makeText(this, "收藏失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLikeSuccess() {
        Toast.makeText(this, "点了一赞", Toast.LENGTH_SHORT).show();
        ivLike.setImageResource(R.mipmap.liked);
        isLike = true;
    }

    @Override
    public void onLikeError() {
        Toast.makeText(this, "点赞失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancleCollectSuccess() {
        Toast.makeText(this, "取消了收藏", Toast.LENGTH_SHORT).show();
        ivCollect.setImageResource(R.mipmap.new_love_tabbar);
        isCollect = false;
    }

    @Override
    public void onCancleCollectError() {
        Toast.makeText(this, "取消收藏失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancleLikeSuccess() {
        Toast.makeText(this, "取消点赞", Toast.LENGTH_SHORT).show();
        ivLike.setImageResource(R.mipmap.like);
        isLike = false;
    }

    @Override
    public void onCancleLikeError() {
        Toast.makeText(this, "取消点赞失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetStatusSuccess(OptStatus status) {
        isCollect = status.getIsCollect();
        if (isCollect) {
            ivCollect.setImageResource(R.mipmap.my_collect);
        }
        isLike = status.getIsLike();
        if (isLike) {
            ivLike.setImageResource(R.mipmap.liked);
        }
    }

    @Override
    public void onGetStatusError() {

    }
}
