package com.fujisoft.diancan.factory;

import android.support.v4.app.Fragment;

import com.fujisoft.diancan.main_frament.FlowerMallFrament;
import com.fujisoft.diancan.main_frament.HomeFrament;
import com.fujisoft.diancan.main_frament.MeFrament;
import com.fujisoft.diancan.main_frament.TaskHallFrament;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 860617010 on 2017/8/23.
 */

public class MainFragmentFactory {
    public static final int HOME_FRAGMENT = 1;
    public static final int TASK_HALL_FRAMENT = 2;
    public static final int FLOWER_MALL_FRAGMENT = 3;
    public static final int ME_FRAGMENT = 4;


    /** 用于缓存Fragment的实例 */
    public static Map<Integer, Fragment> mCacheFragments = new HashMap<>();
    public static Fragment createInstance(int type) {
        Fragment fragment = null;
        //优先缓存集合中取出来
        if (mCacheFragments.containsKey(type)) {
            fragment = mCacheFragments.get(type);
            return fragment;
        }
        switch (type) {
            case HOME_FRAGMENT:
                fragment = new HomeFrament();
                break;
            case TASK_HALL_FRAMENT:
                fragment = new TaskHallFrament();
                break;
            case FLOWER_MALL_FRAGMENT:
                fragment = new FlowerMallFrament();
                break;
            case ME_FRAGMENT:
                fragment = new MeFrament();
                break;
        }
        //保存Fragment到集合中
        mCacheFragments.put(type, fragment);
        return fragment;
    }
}
