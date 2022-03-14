package com.young.ningx.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.young.library.base.mvp.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;

/**
 * @author dupeng
 * @version 2.6.0, 2019/3/30  10:01 PM
 * @since android 17MiddleTeacher
 */
public class MultipleNetActivity extends BaseActivity{



    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void initData(Context mContext) {
        Observable<List<String>> observableOne =
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> listOne = new ArrayList<>();
                        listOne.add("A");
                        listOne.add("B");
                        listOne.add("C");
                        emitter.onNext(listOne);
                    }
                });

        Observable<List<String>> observableTwo =
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> listTwo = new ArrayList<>();
                        listTwo.add("E");
                        listTwo.add("F");
                        listTwo.add("G");
                        emitter.onNext(listTwo);
                    }
                });

        Observable.zip(observableOne, observableTwo, new BiFunction<List<String>, List<String>, String>() {
            @Override
            public String apply(List<String> strings, List<String> strings2) throws Exception {
                List<String> zipList = new ArrayList<>();
                zipList.addAll(strings);
                zipList.addAll(strings2);
                Log.v("tag", zipList.toString());
                return zipList.toString();
            }
        });
    }

}
