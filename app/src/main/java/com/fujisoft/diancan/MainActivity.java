package com.fujisoft.diancan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.fujisoft.diancan.bean.FragmentShowHideTag;
import com.fujisoft.diancan.factory.MainFragmentFactory;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private static final String HOME_FRAGMENT_TAG = "1";//"首页"标记
    private static final String TASK_HALL_FRAGMENT_TAG = "2";//"任务大厅"标记
    private static final String FLOWER_MALL_FRAGMENT_TAG = "3";//"鲜花商城"标记
    private static final String ME_FRAGMENT_TAG = "4";//"我的"标记
    private BottomBar bottomBar;
    private String unSelectPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明化状态栏
        //View decorview = getWindow().getDecorView();
        //if (Build.VERSION.SDK_INT >= 21) {//5.0以上的系统支持
        //    int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//表示让应用主题内容占据系统状态栏的空间
        //    decorview.setSystemUiVisibility(option);
        //    getWindow().setStatusBarColor(Color.parseColor("#00ffffff"));//设置状态栏颜色为透明
        //}

        initBottomBar();
        //注册EventBus事件
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 初始化底部导航控件
     */
    private void initBottomBar() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int i) {
                switch (i) {
                    case R.id.tab_home_page:
                        showFragment("1", MainFragmentFactory.HOME_FRAGMENT);
                        break;
                    case R.id.tab_task_hall:
                        showFragment("2", MainFragmentFactory.TASK_HALL_FRAMENT);
                        break;
                    case R.id.tab_flower_mall:
                        showFragment("3", MainFragmentFactory.FLOWER_MALL_FRAGMENT);
                        break;
                    case R.id.tab_me:
                        showFragment("4", MainFragmentFactory.ME_FRAGMENT);
                        break;
                }
            }
        });
    }

    /**
     * 显示当前被点击的tab所对应的Fragment
     *
     * @param showNow        即将显示的fragment的标记
     * @param whitchFragment 要显示的页面是哪一个
     */
    private void showFragment(String showNow, int whitchFragment) {
        //1 通过FragmentManager获取Fragment 2 如果不存在，就通过MainFragmentFactory创建 3 如果存在，显示
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(showNow);
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment == null) {
            //添加
            ft.add(R.id.contentContainer, MainFragmentFactory.createInstance(whitchFragment), showNow);
        } else {
            //显示
            ft.show(fragment);
        }
        //隐藏上一个页面
        if (unSelectPager != showNow && unSelectPager != null) {
            Fragment hideFragment = fm.findFragmentByTag(unSelectPager);
            ft.hide(hideFragment);
            EventBus.getDefault().post(new MessageEvent<FragmentShowHideTag>(new FragmentShowHideTag(showNow, unSelectPager)));
        }
        //标记当前显示的页面
        unSelectPager = showNow;
        ft.commit();
    }

    /**
     * 非粘性事件走此方法
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBus(MessageEvent<FragmentShowHideTag> messageEvent) {
        Log.i(TAG, "刚刚被隐藏的fragment是第: " + messageEvent.getMessage().hideTag + "页");
        Log.i(TAG, "现在显示的fragment是第: " + messageEvent.getMessage().showTag + "页");
    }


    //退出时的时间
    private long mExitTime;

    /**
     * 对返回键进行监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出点灿", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    private static final String TAG = "MainActivity";
}
