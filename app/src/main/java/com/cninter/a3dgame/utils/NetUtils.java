package com.cninter.a3dgame.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断当前网络是否联网
 * Created by ${jacksen-hss} on 2016/7/5 0005.
 */
public class NetUtils {
    public static boolean netConnect(Activity activity){
        boolean flag = false;
        //得到连接的管理对象
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
       if (connectivityManager==null){
           return flag;
       }
       // 根据连接的管理对象得到网络的信息对象
        NetworkInfo networkInfo =  connectivityManager.getActiveNetworkInfo();
        //如果说信息对象不为空，网络信息是活动的
        if (networkInfo!=null||networkInfo.isAvailable()){
            flag=true;
        }


        return  flag;
    }



}
