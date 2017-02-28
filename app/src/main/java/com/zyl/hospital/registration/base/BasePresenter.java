package com.zyl.hospital.registration.base;


import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends BaseView> {
    public T mView;
    private CompositeSubscription mcompositeSubscription;
    public BasePresenter(){}
    public BasePresenter(T view){
        //view不能为null
        if (view == null) {
            throw new NullPointerException("view can't be null");
        }
        this.mView = view;
    }

    public void addSubscription(Subscriber subscriber){
        if (mcompositeSubscription == null) {
            mcompositeSubscription = new CompositeSubscription();
        }
        mcompositeSubscription.add(subscriber);

    }

    public void detach(){
        mView = null;
        if (mcompositeSubscription != null) {
            mcompositeSubscription.unsubscribe();
        }
    }
}
