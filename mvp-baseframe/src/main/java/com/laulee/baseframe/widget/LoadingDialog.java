package com.laulee.baseframe.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.laulee.baseframe.R;

/**
 * 加载提示框
 * Created by laulee on 17/12/6.
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog( @NonNull Context context ) {
        super( context, R.style.noTitleDialog );
    }

    public LoadingDialog( @NonNull Context context, int theme ) {
        super( context, R.style.noTitleDialog );
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.loading_dialog );
    }
}
