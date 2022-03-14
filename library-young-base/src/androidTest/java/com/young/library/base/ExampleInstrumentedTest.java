package com.young.library.base;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        timerMethod();
    }
    /**
     * timer 操作符
     */
    private void timerMethod() {
        final long[] a = {0};
        final long[] b = {0};
        Observable.timer(10, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        a[0] = System.currentTimeMillis();
                        Log.v("","onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.v("","onSubscribe" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("","onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        b[0] = System.currentTimeMillis();
                        Log.v("","程序执行时间： " + (b[0] - a[0]) + " 毫秒");
                        Log.v("","onComplete");
                    }
                });
    }
}
