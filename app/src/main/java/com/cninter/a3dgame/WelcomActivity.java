package com.cninter.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.cninter.a3dgame.service.DownLoadService;
import com.cninter.a3dgame.utils.NetUtils;

import pl.droidsonroids.gif.GifImageView;

public class WelcomActivity extends AppCompatActivity {
    GifImageView gifImageView;
    Animation animation;
    boolean netOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcom);
        gifImageView = (GifImageView) findViewById(R.id.welcome_gif);
        //添加一个动画
        animation = new AlphaAnimation(0,1.0f);
        animation.setDuration(3000);
        gifImageView.startAnimation(animation);
        //给动画添加一个监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //判断网络
                netOpen = NetUtils.netConnect(WelcomActivity.this);
                if (netOpen){
                    Log.i("aaa","网络正常");
                    Intent downServerintent = new Intent(WelcomActivity.this, DownLoadService.class);
                    startService(downServerintent);
                }





            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!netOpen){
                    Toast.makeText(WelcomActivity.this,"请检查你的网络是否连接",Toast.LENGTH_SHORT).show();
                }
                //判断是否第一次登录
                isFristLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    //判断是否第一次登陆
    private void isFristLogin(){
        //创建sharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("isFristLogin", Context.MODE_PRIVATE);
        //获取sharedPreferences对象中的isLog属性
        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
        //如果是第一次登陆，就跳转到引导界面否则的话，跳转到主界面
        if(!isLogin){

            Intent guideIntent = new Intent(WelcomActivity.this,GuideActivity.class);
            startActivity(guideIntent);
            finish();
        }else {
            Intent mainIntent = new Intent(WelcomActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
        }


    }

}
