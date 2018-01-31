package com.example.jialijiang.mymeterialdesign.presenter;

import com.example.jialijiang.mymeterialdesign.base.params.AuthCodeParams;
import com.example.jialijiang.mymeterialdesign.http.api.SystemApi;
import com.example.jialijiang.mymeterialdesign.http.callBack.HttpRxObserver;
import com.example.jialijiang.mymeterialdesign.presenter.contact.FriendContact;
import com.laulee.baseframe.mvp.presenter.RxPresenter;

import javax.inject.Inject;

/**
 * Created by zhuxingxing on 18/1/30.
 */

public class FriendPresenter extends RxPresenter<FriendContact.IView>implements FriendContact.IPresenter  {
    private SystemApi systemApi;

    @Inject
    FriendPresenter(SystemApi systemApi){
        this.systemApi = systemApi;
    }


    @Override
    public void getMobilePhoneAuth() {
        AuthCodeParams params = new AuthCodeParams( );
        params.mobile_phone = "18314834450";
        addSubscrebe( systemApi.getAuthCode( params.obj2map( ) ), new HttpRxObserver<String>( ) {
            @Override
            public void onSuccess( String accessToken ) {
                mView.getAuthCodeSuccess( );
            }

            @Override
            public void onError( String message ) {
                mView.showError( message );
            }
        } );

    }
}
