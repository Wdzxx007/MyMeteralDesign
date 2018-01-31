package com.example.jialijiang.mymeterialdesign.di.component;

import android.content.Context;

import com.example.jialijiang.mymeterialdesign.di.AppModule;
import com.example.jialijiang.mymeterialdesign.http.api.SystemApi;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by zhuxingxing on 18/1/25.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();

    SystemApi getSystemApi();

    OkHttpClient getOkhttpClient();

    Retrofit getOkRetrofit();
}
