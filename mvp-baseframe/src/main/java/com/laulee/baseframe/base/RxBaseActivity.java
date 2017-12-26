package com.laulee.baseframe.base;

import android.content.Context;
import android.os.Bundle;

import com.laulee.baseframe.mvp.presenter.RxPresenter;
import com.laulee.baseframe.mvp.view.IBaseView;
import com.laulee.baseframe.widget.LoadingDialog;

import javax.inject.Inject;

/**
 * 基类presenter activity
 * Created by laulee on 16/12/18.
 */

public abstract class RxBaseActivity<P extends RxPresenter> extends BaseActivity
        implements IBaseView {

    @Inject
    protected P mPresenter; //presenter 对象

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        inject( );
        if( mPresenter != null ) {
            mPresenter.attachView( this );
        }
        super.onCreate( savedInstanceState );
    }

    //dagger注入
    protected abstract void inject();

    @Override
    protected void onDestroy() {
        if( mPresenter != null )
            mPresenter.destroyView( );
        super.onDestroy( );
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoadingProgress() {
        if( loadingDialog == null ) {
            loadingDialog = new LoadingDialog( this );
        }
        loadingDialog.show( );
    }

    @Override
    public void hideLoadingProgress() {
        if( loadingDialog != null ) {
            loadingDialog.dismiss( );
        }
    }
}
