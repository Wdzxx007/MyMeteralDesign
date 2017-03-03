package com.example.jialijiang.mymeterialdesign;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jialijiang.mymeterialdesign.Adapter.GirlsAdapter;
import com.example.jialijiang.mymeterialdesign.Entity.GirlEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GirlsAdapter.MYListener {
    private CircleImageView circleImageView;
    private Toolbar mToolbar;
    private FloatingActionButton fab;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView mRecyclerView;
    private ImageView imgSearch;
    private GirlEntity myGirlEntity;
    private SwipeRefreshLayout mRefreshLayout;
    private GirlEntity[] girls = {new GirlEntity("AngleBaby", R.drawable.angle), new GirlEntity("古力娜扎", R.drawable.gulinazha),
            new GirlEntity("林允儿", R.drawable.linyuner), new GirlEntity("刘亦菲", R.drawable.liuyifei),
            new GirlEntity("孙俪", R.drawable.suili), new GirlEntity("佟丽娅", R.drawable.tongliya),
            new GirlEntity("杨幂", R.drawable.yangmi), new GirlEntity("赵丽颖", R.drawable.zhaoliyin),
            new GirlEntity("李冰冰", R.drawable.libingbing), new GirlEntity("唐嫣", R.drawable.tangyan)};
    private List<GirlEntity> beautyList = new ArrayList<>();
    private GirlsAdapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_settings);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_left);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        imgSearch = (ImageView)findViewById(R.id.img_search);
        imgSearch.setOnClickListener(this);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_main);

        mAdpter = new GirlsAdapter(MainActivity.this, beautyList);
        mAdpter.setMyListener(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mAdpter);


//        setSupportActionBar(mToolbar);
        //NavigationView头部点击事件绑定
        View drawview = mNavigationView.inflateHeaderView(R.layout.nav_header);
        circleImageView = (CircleImageView) drawview.findViewById(R.id.icon_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "头部图片被点击", Toast.LENGTH_SHORT).show();
            }
        });
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_item_color);
        mNavigationView.setItemTextColor(csl);
        /**设置MenuItem默认选中项**/
        mNavigationView.getMenu().getItem(0).setChecked(true);
        //给NavigationView设置item选择事件
        mNavigationView.setCheckedItem(R.id.nav_call);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
//                Snackbar snackbar = Snackbar.make(getCurrentFocus(), item.getTitle(), Snackbar.LENGTH_SHORT);
//                snackbar.setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDrawerLayout.closeDrawers();
//                        Toast.makeText(MainActivity.this, String.valueOf(item.getItemId()),Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//                snackbar.setActionTextColor(Color.RED);
//                snackbar.show();
                switch (item.getItemId()) {
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this, "妹子被点击了", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this, "段子被点击了", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this, "新闻被点击了", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this, "本地被点击了", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(MainActivity.this, "收藏被点击了", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        //给打开左侧菜单的按钮是指特效
        mToggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawerLayout,
                mToolbar,
                R.string.open, R.string.close);
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);

        mRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    /***************************
     * 创建菜单
     ***************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /***************************
     * 给菜单设置点击事件
     ***************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(MainActivity.this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private void initData() {
        beautyList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(girls.length);
            beautyList.add(girls[index]);
        }
    }

    private void refreshData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        mAdpter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                final Snackbar snackbar = Snackbar.make(findViewById(R.id.rv_main), "Snakebar", Snackbar.LENGTH_SHORT);
                snackbar.setAction("close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.setActionTextColor(Color.RED);
                snackbar.show();
                break;
            case R.id.img_search:
                Toast.makeText(MainActivity.this,"a",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onOutMy(GirlEntity girlEntity) {
        myGirlEntity = girlEntity;
        Intent intent = new Intent(MainActivity.this, GirlsActivity.class);
        intent.putExtra(Commomd.GIRLSENTITY, girlEntity);
        startActivity(intent);
    }
}
