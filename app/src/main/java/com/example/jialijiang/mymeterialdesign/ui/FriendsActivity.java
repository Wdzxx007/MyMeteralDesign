package com.example.jialijiang.mymeterialdesign.ui;

import android.widget.Button;

import com.example.jialijiang.mymeterialdesign.R;
import com.example.jialijiang.mymeterialdesign.app.GlobalAppComponent;
import com.example.jialijiang.mymeterialdesign.di.component.DaggerActivityComponent;
import com.example.jialijiang.mymeterialdesign.di.module.ActivityModule;
import com.example.jialijiang.mymeterialdesign.entity.UserInfoEntity;
import com.example.jialijiang.mymeterialdesign.presenter.FriendPresenter;
import com.example.jialijiang.mymeterialdesign.presenter.contact.FriendContact;
import com.laulee.baseframe.base.RxBaseActivity;
import com.laulee.baseframe.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhuxingxing on 18/1/16.
 */

public class FriendsActivity extends RxBaseActivity<FriendPresenter>
        implements FriendContact.IView {

    @BindView(R.id.btn_getAuth)
    Button btnGetAuth;

    @Override
    protected void initParams() {
        setTitle( "我的朋友" );
    }

    @Override
    protected int setContentViewId() {
        return R.layout.friends_layout;
    }

    @Override
    protected void inject() {
        DaggerActivityComponent.builder( ).appComponent( GlobalAppComponent.getAppComponent( ) )
                .activityModule( new ActivityModule( this ) ).build( ).inject( this );
    }

    @Override
    public void showError( String message ) {
        ToastUtil.showToast( message );
    }

    @Override
    public void refreshView( UserInfoEntity userInfoEntity ) {

    }

    @Override
    public void getAuthCodeSuccess() {
        ToastUtil.showToast( "验证码已发送" );
    }

    @OnClick(R.id.btn_getAuth)
    public void onViewClicked() {
        mPresenter.getMobilePhoneAuth( );
    }
}
