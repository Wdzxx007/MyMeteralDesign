package com.example.jialijiang.mymeterialdesign.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhuxingxing on 18/1/23.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule( Activity mActivity ) {
        this.mActivity = mActivity;
    }
@Provides
    public Activity provideActivity() {
        return mActivity;
    }
}
