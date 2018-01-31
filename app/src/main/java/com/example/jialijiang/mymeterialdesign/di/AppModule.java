package com.example.jialijiang.mymeterialdesign.di;

import android.content.Context;
import android.util.Log;

import com.example.jialijiang.mymeterialdesign.http.api.SystemApi;
import com.example.jialijiang.mymeterialdesign.util.ProConfigUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zhuxingxing on 18/1/19.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule( Context context ) {
        this.context = context;
    }
    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }
    @Provides
    @Singleton
    public SystemApi provideSystemApi( Retrofit retrofit ) {
        return retrofit.create( SystemApi.class );
    }
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger( ) {
                    @Override
                    public void log( String message ) {
                        //打印retrofit日志
                        Log.e( "RetrofitLog", "retrofitBack = " + message );
                    }
                } );
        loggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY );
        //加密拦截器
        //        EncodeInterceptor encodeInterceptor = new EncodeInterceptor( );
        OkHttpClient okHttpClient = new OkHttpClient.Builder( )
                .addInterceptor( loggingInterceptor )
                .retryOnConnectionFailure( false )                           // 自动重连
                .connectTimeout( 3, TimeUnit.MINUTES )                     // 连接超时
                .readTimeout( 20, TimeUnit.SECONDS )                        // 读取超时
                .writeTimeout( 20, TimeUnit.SECONDS )                       // 秒写入超时
                .build( );
        return okHttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit( OkHttpClient okHttpClient ) {
        return new Retrofit.Builder( ).addCallAdapterFactory( RxJava2CallAdapterFactory.create( ) )
                .addConverterFactory( ScalarsConverterFactory.create( ) )
                .addConverterFactory( GsonConverterFactory.create( ) )
                .baseUrl( ProConfigUtil.getConfigKey( "BASE_URL" ) ).client( okHttpClient )
                .build( );
    }

}
