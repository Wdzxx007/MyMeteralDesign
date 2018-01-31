package com.example.jialijiang.mymeterialdesign.presenter.contact;

import com.example.jialijiang.mymeterialdesign.entity.UserInfoEntity;
import com.laulee.baseframe.mvp.view.IBaseView;

/**
 * Created by zhuxingxing on 18/1/30.
 */

public interface FriendContact {
    interface IView extends IBaseView {

        void showError( String message );

        void refreshView( UserInfoEntity userInfoEntity );

        void getAuthCodeSuccess();

    }

    interface IPresenter{


        void getMobilePhoneAuth();
    }
}
