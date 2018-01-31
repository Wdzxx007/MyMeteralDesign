package com.example.jialijiang.mymeterialdesign.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhuxingxing on 18/1/23.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule( Fragment fragment ) {
        this.mFragment = fragment;
    }

    @Provides
    public Activity provideActivity() {
        return mFragment.getActivity( );
    }

    @Provides
    public Fragment provideFragment() {
        return mFragment;
    }
}
