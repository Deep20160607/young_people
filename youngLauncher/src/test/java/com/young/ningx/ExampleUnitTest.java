package com.young.ningx;

import android.util.Log;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        int[] a = new int[3];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        int[] b = Arrays.copyOf(a, 10);
        for (int i = 0; i < b.length; i++) {
            System.out.println("b.length"+b[i]);
        }
//        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        Observable<List<String>> observableOne =
//                Observable.create(new ObservableOnSubscribe<List<String>>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
//                        System.out.println("One--时间" + System.currentTimeMillis());
//                        Thread t = Thread.currentThread();
//                        String name = t.getName();
//                        System.out.println("Onename=" + name);
//                        List<String> listOne = new ArrayList<>();
//                        listOne.add("A");
//                        listOne.add("B");
//                        listOne.add("C");
//                        Thread.sleep(100);
//                        emitter.onNext(listOne);
//                        emitter.onError(new Throwable("one"));
//                    }
//                }).onErrorReturn(new Function<Throwable, List<String>>() {
//                    @Override
//                    public List<String> apply(Throwable throwable) {
//                        System.out.println("One--error");
//                        List<String> list = new ArrayList<>();
//                        list.add("ErrorA");
//                        list.add("ErrorB");
//                        list.add("ErrorC");
//                        return list;
//                    }
//                }).subscribeOn(Schedulers.newThread());
//
//        Observable<List<String>> observableTwo =
//                Observable.create(new ObservableOnSubscribe<List<String>>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
//                        System.out.println("Two--时间" + System.currentTimeMillis());
//                        Thread t = Thread.currentThread();
//                        String name = t.getName();
//                        System.out.println("Twoname=" + name);
//                        List<String> listTwo = new ArrayList<>();
//                        listTwo.add("E");
//                        listTwo.add("F");
//                        listTwo.add("G");
//
//                        emitter.onNext(listTwo);
//                    }
//                }).subscribeOn(Schedulers.newThread());
//
//        Observable.zip(observableOne, observableTwo, new BiFunction<List<String>, List<String>, String>() {
//            @Override
//            public String apply(List<String> strings, List<String> strings2) throws Exception {
//                System.out.println("结果--时间");
//                List<String> zipList = new ArrayList<>();
//                zipList.addAll(strings);
//                zipList.addAll(strings2);
//                System.out.println("zip--" + zipList.toString());
//                return zipList.toString();
//            }
//        }).subscribe();
    }

    public String addNumber(String str) {
        if(str == null) {
            return null;
        }
        LinkedList<Character> linkedList = new LinkedList<>();
//        for (int i = 0; i < str.length(); i++) {
//
//        }
        for (char ch : str.toCharArray()) {
            linkedList.add(ch);
        }
        System.out.println("sb--" + linkedList.size());
        StringBuffer sb = new StringBuffer();
        int number = 1;
        int index = 0;
        while(!linkedList.isEmpty()) {
            index = 0;
            while(index < number && !linkedList.isEmpty()) {
                char ch = linkedList.remove();
                System.out.println("ch--" + ch);
                sb.append(ch);
                index++;
            }
            if (index == number) {
                sb.append(number);
            }
            number++;
        }
        System.out.println("sb--" + sb.toString());
        return sb.toString();
    }

}