package com.laulee.baseframe.http;

import com.laulee.baseframe.http.callback.DownLoadProgressListener;
import com.laulee.baseframe.http.interceptor.FileDownLoadInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * service生成类
 * Created by laulee on 17/11/16.
 */

public class ServiceGenerator {

    public static String apiBaseUrl = "http://192.168.10.12:8080/";
    private static Retrofit retrofit;

    private static Retrofit.Builder builder = new Retrofit.Builder( )
            .addConverterFactory( GsonConverterFactory.create( ) )
            .addCallAdapterFactory( RxJava2CallAdapterFactory.create( ) ).baseUrl( apiBaseUrl );

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder( );

    /**
     * 更换基地址
     *
     * @param newApiBaseUrl
     */
    public static void changeApiBaseUrl( String newApiBaseUrl ) {
        apiBaseUrl = newApiBaseUrl;
        builder = new Retrofit.Builder( ).addConverterFactory( GsonConverterFactory.create( ) )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create( ) ).baseUrl( apiBaseUrl );
    }

    /**
     * 创建service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService( Class<S> serviceClass ) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor( );
        loggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY );
        httpClientBuilder.addInterceptor( loggingInterceptor );
        return builder.client( httpClientBuilder.build( ) ).build( ).create( serviceClass );
    }

    /**
     * 创建service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createDownLoadService( Class<S> serviceClass,DownLoadProgressListener downLoadProgressListener ) {
        httpClientBuilder.addInterceptor( new FileDownLoadInterceptor(downLoadProgressListener) );
        return builder.client( httpClientBuilder.build( ) ).build( ).create( serviceClass );
    }

    /**
     * 创建service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService( Class<S> serviceClass, String apiBaseUrl ) {
        changeApiBaseUrl( apiBaseUrl );
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor( );
        loggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY );
        httpClientBuilder.addInterceptor( loggingInterceptor );
        return builder.client( httpClientBuilder.build( ) ).build( ).create( serviceClass );
    }
}
