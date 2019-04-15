package com.hzx.news.ui.activity;

import com.hzx.news.R;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;

import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_message;
    }

    @OnClick(R.id.iv_back)
    public void backClick() {
        finish();
    }
}
