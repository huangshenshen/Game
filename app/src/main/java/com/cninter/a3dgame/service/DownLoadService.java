package com.cninter.a3dgame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cninter.a3dgame.utils.HttpUtils;
import com.cninter.a3dgame.utils.JSONUtils;
import com.cninter.a3dgame.utils.NewsObj;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

/**
 * Created by ${jacksen-hss} on 2016/7/5 0005.
 */
public class DownLoadService  extends Service{
    String path="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=1";
    List<NewsObj> list;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用线程下载数据
      ;
        byte[] bt= HttpUtils.request(path);
        if (bt!=null){
            try {
                String json = new String(bt,"utf-8");
                if (json!=null){
                   list =  JSONUtils.getList(json);
                    Log.i("aaa","集合的大小"+list.size());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.i("aaa","网络连接失败");
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
