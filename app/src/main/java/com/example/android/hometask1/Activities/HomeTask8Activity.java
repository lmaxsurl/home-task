package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hometask1.Fragments.TextFragment;
import com.example.android.hometask1.R;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class HomeTask8Activity extends AppCompatActivity {

    private PublishSubject<Integer> subject = PublishSubject.create();
    private PublishSubject<Integer> timerSubject = PublishSubject.create();
    private Integer lastNumber = 0;
    private Integer time = 0;
    private TextView timerTextView;
    private Disposable disposable;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, HomeTask8Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht8);
        initFragment();
        initTimer();
    }

    private void initFragment() {
        TextFragment fragment = new TextFragment();
        showFragment(fragment, R.id.ht8_container);
        findViewById(R.id.ht8_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastNumber = lastNumber + 1;
                subject.onNext(lastNumber);
            }
        });
        subject.subscribe(fragment);
    }

    private void initTimer() {
        timerTextView = findViewById(R.id.ht8_timer_tv);
        timerSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        timerTextView.setText("Timer: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(HomeTask8Activity.this, "Timer error", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(disposable != null){
                    time = time + 1;
                    timerSubject.onNext(time);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e("timer exception", e.getMessage());
                    }
                }
            }
        }).start();
    }

    private void showFragment(Fragment fragment, int containerId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subject.onComplete();
        timerSubject.onComplete();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
