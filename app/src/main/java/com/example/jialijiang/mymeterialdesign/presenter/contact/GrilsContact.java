package com.example.jialijiang.mymeterialdesign.presenter.contact;

import com.laulee.baseframe.mvp.view.IBaseView;

/**
 * Created by zhuxingxing on 18/1/30.
 */

public interface GrilsContact {
    interface IView extends IBaseView {

        void showError( String message );

        void refreshView(  );


    }

    interface IPresenter{

        void refreshView();
    }
}
