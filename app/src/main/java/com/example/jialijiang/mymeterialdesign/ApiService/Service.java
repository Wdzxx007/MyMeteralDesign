package com.example.jialijiang.mymeterialdesign.ApiService;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhuxingxing on 17/11/27.
 */

public class Service {
    private volatile static Retrofit retrofitInstance = null;
    static final String url = "http://daimobile.sfdai.com/v1/api/";

    public static Retrofit initRetrofit() {
        if( null == retrofitInstance ) {
            synchronized(Retrofit.class) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl( url )
                    .addConverterFactory( buildGsonConverterFactory() )
                    .client( buildOkHttpClient() )
                    .build();
            }
        } return retrofitInstance;
    }
    /**
     * 创建 RetrofitManage 服务
     *
     * @return ApiService
     */
    public static <T> T createApi(Class<T> tClass){
        return initRetrofit().create(tClass);
    }

    /**
     * 构建OkHttp
     * @return
     */
    private static OkHttpClient buildOkHttpClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i( "RetrofitLog", "retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return   new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)                       // 添加日志拦截器
                .retryOnConnectionFailure(true)                           // 自动重连
                .connectTimeout( 15, TimeUnit.SECONDS)                     // 15秒连接超时
                .readTimeout(20, TimeUnit.SECONDS)                        // 20秒读取超时
                .writeTimeout(20, TimeUnit.SECONDS)                       // 20秒写入超时
                .build();
    }

    /**
     * 构建GSON转换器
     * @return GsonConverterFactory
     */
    private static GsonConverterFactory buildGsonConverterFactory(){
        GsonBuilder builder = new GsonBuilder();
        builder.setLenient();

        // 注册类型转换适配器
        builder.registerTypeAdapter( Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws

                    JsonParseException {
                return null == json ? null : new Date(json.getAsLong());
            }
        });

        Gson gson = builder.create();
        return GsonConverterFactory.create(gson);
    }
}
