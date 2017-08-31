package com.fujisoft.diancan;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;

/**
 * Created by 860617010 on 2017/5/24.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        //获取Context
        super.onCreate();
        context = getApplicationContext();
    }

    //返回
    public static Context getContext() {
        return context;
    }

    //跳转传递数据用(主要是传递对象数据，但是传递完成之后需要向下转型)
    public static HashMap<String, Object> globalHashMap = new HashMap<>();
}
