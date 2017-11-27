package com.example.jialijiang.mymeterialdesign.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jialijiang.mymeterialdesign.R;
import com.example.jialijiang.mymeterialdesign.constant.AppConfig;
import com.example.jialijiang.mymeterialdesign.util.SystemUtil;
import com.example.jialijiang.mymeterialdesign.util.ToastUtil;


/**
 * 基类page
 * Created by sean on 15/10/8.
 */
public abstract class PageActivity extends FragmentActivity implements View.OnClickListener {
    private TextView lblTitleText;
    private TextView lblTitleMore;
    private TextView lblTitleLeft;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( setContentViewId( ) );
        init( );
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int setContentViewId();

    /**
     * 初始化数据
     */
    private void init() {
        checkUserInfo( );
        //讲界面添加到activities中统一管理
        AppConfig.addActivity( this );

        if( findViewById( R.id.lbl_title_bar_left ) != null ) {
            findViewById( R.id.lbl_title_bar_left ).setOnClickListener( this );
            ( (TextView) findViewById( R.id.lbl_title_bar_left ) ).setText( "  " );
        }
        initView( );
        initParams( );
    }

    /**
     * 检查用户信息
     */
    private void checkUserInfo() {
        if( !SystemUtil.checkToken( this ) ) {
            return;
        }
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化参数
     */
    protected abstract void initParams();

    /**
     * 设置title
     *
     * @param title
     */
    protected void setTitle( String title ) {
        if( lblTitleText == null ) {
            lblTitleText = (TextView) findViewById( R.id.lblTitleText );
        }
        lblTitleText.setText( title );
    }

    /**
     * 设置返回按钮文字
     *
     * @param text
     */
    protected void setLeftText( String text ) {
        if( lblTitleLeft == null ) {
            lblTitleLeft = (TextView) findViewById( R.id.lbl_title_bar_left );
        }
        lblTitleLeft.setText( text );
    }

    /**
     * @param moreTitle
     */
    protected void setMoreTitle( String moreTitle ) {
        if( lblTitleMore == null ) {
            lblTitleMore = (TextView) findViewById( R.id.lblTitleMore );
        }
        lblTitleMore.setVisibility( View.VISIBLE );
        lblTitleMore.setText( moreTitle );
        lblTitleMore.setOnClickListener( this );
    }


    protected TextView getTitleText() {
        if( lblTitleText == null )
            lblTitleText = (TextView) findViewById( R.id.lblTitleText );
        return lblTitleText;
    }

    protected void setLocationIcon() {
        //        Drawable locationDrawable = getResources( ).getDrawable( R.mipmap
        // .common_icon_location );
        //        locationDrawable.setBounds( 0, 0, locationDrawable.getMinimumWidth( ),
        //                locationDrawable.getMinimumHeight( ) );
        //        lblMore.setCompoundDrawables( locationDrawable, null, null, null );
    }

    protected void setBackgroundResource( int color ) {
        if( findViewById( R.id.ll_title_bar_view ) != null )
            findViewById( R.id.ll_title_bar_view ).setBackgroundColor( color );
    }

    protected void setBackResource( int id ) {
        //        if( lblTitle == null )
        //            imgBack = (ImageView) findViewById( R.id.imgBack );
        //        imgBack.setImageResource( id );
        //        imgBack.setVisibility( View.VISIBLE );
    }

    @Override
    public void onClick( View v ) {
        if( v == null ) {
            return;
        }
        switch( v.getId( ) ) {
            case R.id.lbl_title_bar_left:
                doFinish( );
                break;

            case R.id.lblTitleMore:
                doMore( );
                break;
        }
    }

    /**
     *
     */
    protected void doFinish() {
        finish( );
    }

    /**
     *
     */
    protected void doMore() {}

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        AppConfig.removeActivity( this );
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed( );
        ToastUtil.cancel( );
    }
}
