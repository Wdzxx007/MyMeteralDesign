package com.example.jialijiang.mymeterialdesign.di.component;

import com.example.jialijiang.mymeterialdesign.di.module.ActivityModule;
import com.example.jialijiang.mymeterialdesign.di.scope.ActivityScope;
import com.example.jialijiang.mymeterialdesign.ui.FriendsActivity;
import com.example.jialijiang.mymeterialdesign.ui.GirlListActivity;

import dagger.Component;

/**
 * Created by zhuxingxing on 18/1/25.
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject( FriendsActivity friendsActivity );

    void inject (GirlListActivity girlListActivity);


}
