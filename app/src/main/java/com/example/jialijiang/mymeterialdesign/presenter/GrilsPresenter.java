package com.example.jialijiang.mymeterialdesign.presenter;

import com.example.jialijiang.mymeterialdesign.http.api.SystemApi;
import com.example.jialijiang.mymeterialdesign.presenter.contact.GrilsContact;
import com.laulee.baseframe.mvp.presenter.RxPresenter;

import javax.inject.Inject;

/**
 * Created by zhuxingxing on 18/1/30.
 */

public class GrilsPresenter extends RxPresenter<GrilsContact.IView>implements GrilsContact.IPresenter  {
    private SystemApi systemApi;

    @Inject
    GrilsPresenter(SystemApi systemApi){
        this.systemApi = systemApi;
    }


    @Override
    public void refreshView() {

    }
}
