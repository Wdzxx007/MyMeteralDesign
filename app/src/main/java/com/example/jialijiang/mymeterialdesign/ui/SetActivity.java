package com.example.jialijiang.mymeterialdesign.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jialijiang.mymeterialdesign.R;
import com.example.jialijiang.mymeterialdesign.app.App;
import com.example.jialijiang.mymeterialdesign.download.AppDownLoadHelper;
import com.example.jialijiang.mymeterialdesign.download.AppDownLoadManager;
import com.example.jialijiang.mymeterialdesign.download.AppProgressListener;
import com.example.jialijiang.mymeterialdesign.util.FileUtil;
import com.example.jialijiang.mymeterialdesign.util.SDCardUtil;
import com.example.jialijiang.mymeterialdesign.util.SystemUtil;
import com.laulee.baseframe.base.BaseActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {
    private static final String TAG = "SetActivity";
    public static final String MESSAGE_PROGRESS = "message_progress";
    @BindView(R.id.progress_state)
    TextView progressState;
    private LocalBroadcastManager bManager;
    @BindView(R.id.tv_app_version)
    TextView tvAppVersion;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.progress_text)
    TextView progressText;

    @Override
    protected void initParams() {
        tvAppVersion.setText( SystemUtil.getAPPVersion( SetActivity.this ) );

    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_set;
    }

    @OnClick(R.id.tv_app_version)
    public void onViewClicked() {
        AppDownLoadHelper helper = AppDownLoadManager.getInstance( ).getHelperByTag( "xx" );
        if( helper != null ) {
            AppDownLoadManager.getInstance( ).cancelHelperByTag( "xx" );
            progressState.setText( "0" );
            return;
        }
        new AppDownLoadHelper.Builder( )
                .setPath( SDCardUtil.getLogCacheDir( App.getContext( ) ) + "/mifengjucai.apk" )
                .setTag( "xx" ).setUrl( "https://www.beejc.cn/web/bee/apk/1.9.0/蜜蜂聚财_huawei.apk" )
                .setDownLoadListener( new AppProgressListener( ) {
                    @Override
                    public void onStart() {
                        Log.i( "tag", "========开始" );
                        progressState.setText( "0%" );
                    }

                    @Override
                    public void update( long bytesRead, long contentLength, boolean done ) {
                        int read = (int) ( bytesRead * 100f / contentLength );
                        Log.i( "tag", "========" + read );
                        progress.setProgress( read );
                        progressState.setText( read + "%" );
                        progressText.setText( FileUtil.bytes2kb( bytesRead ) + "/" + FileUtil
                                .bytes2kb( contentLength ) );
                    }

                    @Override
                    public void onCompleted() {
                        progressState.setText( "完成" );
                        Log.i( "tag", "========" + Thread.currentThread( ).getName( ) );
                        File file = new File( SDCardUtil.getLogCacheDir(
                                App.getContext( ) ) + "/mifengjucai.apk" );
                        Intent intent = new Intent( Intent.ACTION_VIEW );
                        intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                        intent.setDataAndType( Uri.fromFile( file ),
                                               "application/vnd.android.package-archive" );
                        startActivity( intent );

                    }

                    @Override
                    public void onError( String err ) {
                        progressState.setText( "失败" );
                        Log.i( "tag", "========失败" + err );
                    }
                } ).create( ).execute( );
    }



}