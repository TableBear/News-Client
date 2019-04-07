package com.hzx.news.ui.base;

import com.hzx.news.api.ApiRetrofit;
import com.hzx.news.api.ApiService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;



public abstract class BasePresenter<V> {

    protected ApiService apiService = ApiRetrofit.getInstance().getApiService();
    private CompositeSubscription compositeSubscription;
    protected V view;

    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
        onUnsubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
}
