package com.hzx.news.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nukc.stateview.StateView;
import com.hzx.news.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseFragment<T extends BasePresenter> extends LazyLoadFragment {

    protected T presenter;
    protected StateView stateView;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, view);
            stateView = StateView.inject(getStateRootView());
            if (stateView != null) {
                stateView.setLoadingResource(R.layout.base_loading);
                stateView.setRetryResource(R.layout.base_retry);
                initView(view);
                initData();
                initListener();
            } else {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
                    parent.removeView(view);
                }
            }
        }
        return view;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        // 当非fragment初始可见是加载数据
        loadDate();
    }

    public void initView(View view) {

    }

    public void initData() {

    }

    public void initListener() {

    }

    /**
     * 用来返回StateView的根布局
     */
    public View getStateRootView() {
        return view;
    }

    /**
     * 用来返回Fragment的布局文件ID
     */
    public abstract int getLayoutId();

    /**
     * 用来创建Fragment绑定的Presenter
     */
    protected abstract T createPresenter();

    /**
     * 加载数据
     */
    protected abstract void loadDate();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        view = null;
    }

    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }

}
