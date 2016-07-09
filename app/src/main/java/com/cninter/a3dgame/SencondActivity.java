package com.cninter.a3dgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.cninter.a3dgame.adpater.MyAdapter;
import com.cninter.a3dgame.coustomview.News;
import com.cninter.a3dgame.service.MyIntentService;
import com.cninter.a3dgame.utils.NewsObj;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/6/26 0026.
 */
public class SencondActivity extends AppCompatActivity {

    String sdpath = Environment.getExternalStorageDirectory().getAbsolutePath();
    String dbname = "dangame.db";
    Intent intent ;
    private ListView lv;

    List<News> Nlist = null;
    int page=1;

    String urlpath="http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_game_actitiy);
        initstartsever();
        lv = (ListView) this.findViewById(R.id.lv);


        //LoadSQLiteData();
        MyAdapter myAdapter = new MyAdapter(Nlist, this);
        lv.setAdapter(myAdapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean isbottom=false;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                    if (i==0&&isbottom){
                        page++;
                        Intent intent=new Intent(SencondActivity.this,MyIntentService.class);
                        String strpath=urlpath+page;
                        intent.putExtra("urlpath",strpath);
                        startService(intent);

                    }

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i+i1==i2){
                    isbottom=true;
                }else{
                    isbottom=false;
                }

            }
        });


    }

    public void LoadSQLiteData() {
        Nlist = new ArrayList<>();
        //SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+ File.separator+dbname, null);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(sdpath + File.separator + dbname, null, 1);
        Cursor cursor = db.rawQuery("select * from news", null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            Log.i("aaa", "展示标题" + title);

            String description = cursor.getString(cursor.getColumnIndex("description"));
            Log.i("aaa", "展示内容------" + description);

            String pathimg = cursor.getString(cursor.getColumnIndex("pathimg"));
            Log.i("ttt", "展示图片的path" + pathimg);
            String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
            Log.i("ttt", "展示图片的连接" + litpic);
            String arcul = cursor.getString(cursor.getColumnIndex("arcul"));
            String pubdate = cursor.getString(cursor.getColumnIndex("pubdate"));
            String click = cursor.getString(cursor.getColumnIndex("click"));

            News news =new News(title,description,pathimg,litpic,arcul,pubdate,click);
           // Nlist.add(news);
            Log.i("aaa", "展示图片的集合长度是" + Nlist.size());
        }


    }
    public  void initstartsever(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                synchronized (this) {
                    for (int i = 1; i == 1; i++) {
                        intent = new Intent(SencondActivity.this, MyIntentService.class);
                        intent.putExtra("pages", i);
                        startService(intent);
                    }
                }


            }
        }.start();
    }






}
