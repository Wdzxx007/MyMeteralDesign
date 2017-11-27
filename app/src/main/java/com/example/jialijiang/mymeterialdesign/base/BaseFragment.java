package com.example.jialijiang.mymeterialdesign.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment基类防止视图重复加载
 * Created by sean on 15/12/23.
 */
public abstract class BaseFragment extends Fragment {
    private View rootView;
    private TextView lblTitle;

    protected abstract int setContentViewId();

    protected abstract void initView( View view );

    protected abstract void initParams();

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState ) {
        if( rootView == null ) {
            rootView = inflater.inflate( setContentViewId( ), null );
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent( );
        if( parent != null ) {
            parent.removeView( rootView );
        }
        initView( rootView );
        initParams( );
        return rootView;
    }

}
