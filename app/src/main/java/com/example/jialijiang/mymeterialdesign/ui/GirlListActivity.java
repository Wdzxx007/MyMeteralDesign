package com.example.jialijiang.mymeterialdesign.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jialijiang.mymeterialdesign.R;
import com.example.jialijiang.mymeterialdesign.adapter.GirlsListAdapter;
import com.example.jialijiang.mymeterialdesign.app.GlobalAppComponent;
import com.example.jialijiang.mymeterialdesign.di.component.DaggerActivityComponent;
import com.example.jialijiang.mymeterialdesign.di.module.ActivityModule;
import com.example.jialijiang.mymeterialdesign.entity.GirlEntity;
import com.example.jialijiang.mymeterialdesign.presenter.GrilsPresenter;
import com.example.jialijiang.mymeterialdesign.presenter.contact.GrilsContact;
import com.laulee.baseframe.base.RxBaseActivity;
import com.laulee.baseframe.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class GirlListActivity extends RxBaseActivity<GrilsPresenter> implements GrilsContact.IView {


    @BindView(R.id.rv_girl_list)
    RecyclerView rvGirlList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private GirlsListAdapter adapter;
    private GirlEntity[] girls = { new GirlEntity( "AngleBaby", R.drawable.angle ),
            new GirlEntity( "古力娜扎", R.drawable.gulinazha ),
            new GirlEntity( "林允儿", R.drawable.linyuner ),
            new GirlEntity( "刘亦菲", R.drawable.liuyifei ), new GirlEntity( "孙俪", R.drawable.suili ),
            new GirlEntity( "佟丽娅", R.drawable.tongliya ), new GirlEntity( "杨幂", R.drawable.yangmi ),
            new GirlEntity( "赵丽颖", R.drawable.zhaoliyin ),
            new GirlEntity( "李冰冰", R.drawable.libingbing ),
            new GirlEntity( "唐嫣", R.drawable.tangyan ) };
    private List<GirlEntity> beautyList = new ArrayList<>( );

    @Override
    protected void initParams() {
        initData( ); setTitle( "朋友" ); adapter = new GirlsListAdapter( this, beautyList );
        rvGirlList.setLayoutManager( new LinearLayoutManager( this ) );
        rvGirlList.setAdapter( adapter );
        refreshLayout.setOnRefreshListener( new OnRefreshListener( ) {
            @Override
            public void onRefresh( RefreshLayout refreshlayout ) {
                refreshlayout.finishRefresh( 2000 );
                ToastUtil.showToast( "a" );
            }
        } );
        refreshLayout.setOnLoadmoreListener( new OnLoadmoreListener( ) {
            @Override
            public void onLoadmore( RefreshLayout refreshlayout ) {
                refreshlayout.finishLoadmore( 2000 );
            }
        } );
    }

    private void initData() {
        beautyList.clear( ); for( int i = 0; i < 50; i++ ) {
            Random random = new Random( ); int index = random.nextInt( girls.length );
            beautyList.add( girls[index] );
        }
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_girl_list;
    }

    @Override
    protected void inject() {
        DaggerActivityComponent.builder( ).appComponent( GlobalAppComponent.getAppComponent( ) )
                .activityModule( new ActivityModule( this ) ).build( ).inject( this );
    }

    @Override
    public void showError( String message ) {

    }

    @Override
    public void refreshView() {

    }


}
