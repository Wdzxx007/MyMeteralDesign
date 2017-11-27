package com.example.jialijiang.mymeterialdesign.base.params;

import android.content.Context;

import com.example.jialijiang.mymeterialdesign.util.SystemUtil;

/**
 * Created by sean on 16/11/19.
 */

public class TokenParams extends BaseParams {
    public String access_token;

    public TokenParams( Context context ) {
        super( context );
        access_token = SystemUtil.getToken( context );
    }
}
