package com.example.jialijiang.mymeterialdesign.ui;

import android.content.Intent;

import com.example.jialijiang.mymeterialdesign.MainActivity;
import com.example.jialijiang.mymeterialdesign.R;
import com.laulee.baseframe.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhuxingxing on 18/1/29.
 */

public class WelcomePage extends BaseActivity {
    private static final long delay = 2000;

    @Override
    protected void initParams() {
        delayView( );
    }

    private void delayView() {
        Observable.timer( delay, TimeUnit.MILLISECONDS ).subscribe( new Observer<Long>( ) {
            @Override
            public void onSubscribe( Disposable d ) {

            }

            @Override
            public void onNext( Long aLong ) {

            }

            @Override
            public void onError( Throwable e ) {
                startActivity( );
            }

            @Override
            public void onComplete() {
                startActivity( );
            }
        } );
    }

    /**
     *
     */
    private void startActivity() {
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
        finish( );
    }

    @Override
    protected int setContentViewId() {
        return R.layout.splash_activity;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }
}
