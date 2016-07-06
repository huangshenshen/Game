package com.cninter.a3dgame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ${jacksen-hss} on 2016/7/5 0005.
 */
public class DownLoadService  extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用线程下载数据
        Log.i("aaa","下载数据");
        return super.onStartCommand(intent, flags, startId);
    }
}
