package com.example.jialijiang.mymeterialdesign.di.component;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.jialijiang.mymeterialdesign.di.module.FragmentModule;
import com.example.jialijiang.mymeterialdesign.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by zhuxingxing on 18/1/25.
 */
@FragmentScope
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface FragmentComponent {
    Activity provideActivity();

    Fragment provideFragment();
}
