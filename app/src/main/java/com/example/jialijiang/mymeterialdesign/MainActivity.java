package com.example.jialijiang.mymeterialdesign;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jialijiang.mymeterialdesign.adapter.GirlsAdapter;
import com.example.jialijiang.mymeterialdesign.entity.GirlEntity;
import com.example.jialijiang.mymeterialdesign.ui.FriendsActivity;
import com.example.jialijiang.mymeterialdesign.ui.GirlListActivity;
import com.example.jialijiang.mymeterialdesign.ui.GirlsActivity;
import com.example.jialijiang.mymeterialdesign.ui.SetActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GirlsAdapter.MYListener {
    private long exitTime = 0;
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
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
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
        imgSearch = (ImageView) findViewById(R.id.img_search);
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
                Toast.makeText(MainActivity.this, "头部图片被点击啦。。。。。。", Toast.LENGTH_SHORT).show();
            }
        });
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_item_color);
        mNavigationView.setItemTextColor(csl);
        /**设置MenuItem默认选中项**/
        mNavigationView.getMenu().getItem(0).setChecked(true);
        //给NavigationView设置item选择事件
        mNavigationView.setCheckedItem(R.id.nav_girl);
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
                    case R.id.nav_task:
                        Toast.makeText(MainActivity.this, "收藏被点击了", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_girl:
                        Intent intent2 = new Intent(MainActivity.this, GirlListActivity.class );
                        startActivity( intent2 );
                        break;
                    case R.id.nav_friends:
                     Intent intent = new Intent(MainActivity.this, FriendsActivity.class );
                     startActivity( intent );
                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this, "新闻被点击了", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this, "本地被点击了", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_version:
                        Intent intent3 = new Intent( MainActivity.this,SetActivity.class );
                        startActivity( intent3 );
                        break;
                    case R.id.nav_call:
                        // 检查是否获得了权限（Android6.0运行时权限）
                        if( ContextCompat.checkSelfPermission( MainActivity.this, android.Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED ) {
                            // 没有获得授权，申请授权
                            if( ActivityCompat.shouldShowRequestPermissionRationale( MainActivity.this,
                                                                                     Manifest.permission.CALL_PHONE ) ){
                                // 返回值：
                                //                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
                                //                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
                                //                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                                // 弹窗需要解释为何需要该权限，再次请求授权
                                Toast.makeText(MainActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                                // 帮跳转到该应用的设置界面，让用户手动授权
                                Intent intent1 = new Intent( Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts( "package", getPackageName(), null);
                                intent1.setData(uri);
                                startActivity(intent1);
                            }else {
                                // 不需要解释为何需要该权限，直接请求授权
                                ActivityCompat.requestPermissions(MainActivity.this,
                                                                  new String[]{Manifest.permission.CALL_PHONE},
                                                                  MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            }
                        }else {
                            // 已经获得授权，可以打电话
                            CallPhone();
                        }
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
                Toast.makeText(MainActivity.this, "a", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onOutMy(GirlEntity girlEntity) {
        Intent intent = new Intent(MainActivity.this, GirlsActivity.class);
        intent.putExtra(Commomd.GIRLSENTITY, girlEntity);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
        }
    }
    private void CallPhone() {
        String number = "18314834450";
        if ( TextUtils.isEmpty( number)) {
            // 提醒用户
            // 注意：在这个匿名内部类中如果用this则表示是View.OnClickListener类的对象，
            // 所以必须用MainActivity.this来指定上下文环境。
            Toast.makeText(MainActivity.this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 拨号：激活系统的拨号组件
            Intent intent = new Intent(); // 意图对象：动作 + 数据
            intent.setAction(Intent.ACTION_CALL); // 设置动作
            Uri data = Uri.parse("tel:" + number); // 设置数据
            intent.setData(data);
            startActivity(intent); // 激活Activity组件
        }
    }
    // 处理权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    CallPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }
}
