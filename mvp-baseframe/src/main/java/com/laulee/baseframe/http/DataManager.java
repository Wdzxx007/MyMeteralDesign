package com.laulee.baseframe.http;

import com.laulee.baseframe.http.callback.DownLoadProgressListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by laulee on 17/11/22.
 */
@Singleton
public class DataManager {

    private RetrofitHelper retrofitHelper;

    @Inject
    public DataManager( RetrofitHelper retrofitHelper ) {
        this.retrofitHelper = retrofitHelper;
    }

    /**
     * 获得service
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public <T> T getService( Class<T> serviceClass ) {
        return retrofitHelper.getService( serviceClass );
    }

    /**
     * 获得service
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public <T> T getDownLoadService( Class<T> serviceClass,
            DownLoadProgressListener loadProgressListener ) {
        return retrofitHelper.getDownLoadService( serviceClass, loadProgressListener );
    }

    /**
     * 线程调度
     *
     * @param observable
     * @param consumer
     * @return
     */
    public <T> Disposable changeIOToMainThread( Observable<T> observable,
            DisposableObserver<T> consumer ) {
        return observable.subscribeOn( Schedulers.io( ) )
                .observeOn( AndroidSchedulers.mainThread( ) ).subscribeWith( consumer );
    }
}
