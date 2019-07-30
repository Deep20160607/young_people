package com.young.library.base.mvp.presenter;

import android.app.Activity;

import com.young.library.base.mvp.model.BaseModel;
import com.young.library.base.mvp.model.IModel;
import com.young.library.base.mvp.view.IView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author dupeng
 * @version 1.0.0, 2019/2/1  3:26 PM
 * @since android young
 */
public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<V>{

    private CompositeDisposable compositeDisposable;

    protected V mView;
    private  M mModel;

    public BasePresenter() {
        this.mModel =createModel();
    }

    public M getModel() {
        return mModel;
    }

    /**
     * 获取 Model
     * @return
     */
    protected abstract M createModel();

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */
    @Override
    public void detachView() {
        unDispose();
        this.mView = null;
    }

    /**
     * 检查View是否存在
     */
    protected boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务，避免内存泄漏(框架已自行处理)
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);// 将所有 Disposable 放入集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();// 保证 Activity 结束时取消所有正在执行的订阅
        }
    }

}
