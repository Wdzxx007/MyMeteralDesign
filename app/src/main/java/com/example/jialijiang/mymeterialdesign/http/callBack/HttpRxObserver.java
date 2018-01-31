package com.example.jialijiang.mymeterialdesign.http.callBack;

import com.example.jialijiang.mymeterialdesign.app.AppConfig;
import com.laulee.baseframe.http.error.ApiException;
import com.laulee.baseframe.http.error.ErrorException;
import com.laulee.baseframe.rx.BaseObserver;
import com.laulee.baseframe.utils.ToastUtil;

/**
 * Created by laulee on 17/11/16.
 */

public abstract class HttpRxObserver<T> extends BaseObserver<T> {

    private final String TOKEN_ERROR = "当前账户登入权限已过期,请重新登入";

    @Override
    public void onApiException( ApiException exception ) {
        switch( exception.getCode( ) ) {
            case HttpCode.ERROR:
                ToastUtil.showToast( exception.getMessage( ) );
                onError( exception.getMessage( ) );
                break;

            case HttpCode.TOKEN_ERROR:
                ToastUtil.showToast( TOKEN_ERROR );
                AppConfig.closePage( );
                break;
            default:
                onError( exception.getMessage( ) );
                break;
        }
    }

    @Override
    public void onException( ErrorException exception ) {
        onError( exception.getMessage( ) );
    }
}
