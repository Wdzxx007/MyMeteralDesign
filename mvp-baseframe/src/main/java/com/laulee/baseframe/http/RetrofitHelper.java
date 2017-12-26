package com.laulee.baseframe.http;

import com.laulee.baseframe.http.callback.DownLoadProgressListener;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by laulee on 17/3/1.
 */

@Singleton
public class RetrofitHelper {

    private HashMap<String, Object> serviceMap;

    @Inject
    public RetrofitHelper() {
        if( serviceMap == null ) {
            serviceMap = new HashMap<>( );
        }
    }

    /**
     * 获得集合service
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public <T> T getService( Class<T> serviceClass ) {
        if( serviceMap == null ) {
            serviceMap = new HashMap<>( );
        }
        T service = (T) serviceMap.get( serviceClass.getName( ) );
        if( service != null ) {
            return service;
        } else {
            service = ServiceGenerator.createService( serviceClass );
            serviceMap.put( serviceClass.getName( ), service );
            return service;
        }
    }

    /**
     * 获得集合service
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public <T> T getDownLoadService( Class<T> serviceClass,
            DownLoadProgressListener loadProgressListener ) {
        if( serviceMap == null ) {
            serviceMap = new HashMap<>( );
        }
        T service = (T) serviceMap.get( serviceClass.getName( ) );
        if( service != null ) {
            return service;
        } else {
            service = ServiceGenerator.createDownLoadService( serviceClass, loadProgressListener );
            serviceMap.put( serviceClass.getName( ), service );
            return service;
        }
    }
}
