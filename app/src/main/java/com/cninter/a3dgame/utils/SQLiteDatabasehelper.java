package com.cninter.a3dgame.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.cninter.a3dgame.coustomview.News;
import com.cninter.a3dgame.service.MyIntentService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/7/8 0008.
 */
public class SQLiteDatabasehelper {
   static String sdpath= Environment.getExternalStorageDirectory().getAbsolutePath();
   static String dbname="3Dgame.db";
   static List<NewsObj> Nlist;
    List<String> pathimg;

    public List<String> getPathimg() {
        return pathimg;
    }

    public void setPathimg(List<String> pathimg) {
        this.pathimg = pathimg;
    }

    public List<NewsObj> getNlist() {
        return Nlist;
    }

    public void setNlist(List<NewsObj> nlist) {
        Nlist = nlist;
    }

    public static List<NewsObj> LoadSQLiteData() {
        Nlist = new ArrayList<>();

        //SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+ File.separator+dbname, null);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(sdpath + File.separator + dbname, null, 1);
        Cursor cursor = db.rawQuery("select * from news", null);


        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex("id"));
            String typeid = cursor.getString(cursor.getColumnIndex("typeid"));
            String ismake = cursor.getString(cursor.getColumnIndex("ismake"));
            String channel = cursor.getString(cursor.getColumnIndex("channel"));
            String click = cursor.getString(cursor.getColumnIndex("click"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String shorttitle = cursor.getString(cursor.getColumnIndex("shorttitle"));
            String writer = cursor.getString(cursor.getColumnIndex("writer"));
            String source = cursor.getString(cursor.getColumnIndex("source"));
            String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
            String pubdate = cursor.getString(cursor.getColumnIndex("pubdate"));
            String senddata = cursor.getString(cursor.getColumnIndex("senddata"));
            String mid = cursor.getString(cursor.getColumnIndex("mid"));
            String keywords = cursor.getString(cursor.getColumnIndex("keywords"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String dutyadmin = cursor.getString(cursor.getColumnIndex("dutyadmin"));
            String weight = cursor.getString(cursor.getColumnIndex("weight"));
            String typedir = cursor.getString(cursor.getColumnIndex("typedir"));
            String typename = cursor.getString(cursor.getColumnIndex("typename"));
            String isdefault = cursor.getString(cursor.getColumnIndex("isdefault"));
            String defaultname = cursor.getString(cursor.getColumnIndex("defaultname"));
            String namerule = cursor.getString(cursor.getColumnIndex("namerule"));
            String namerule2 = cursor.getString(cursor.getColumnIndex("namerule2"));
            String sitepath = cursor.getString(cursor.getColumnIndex("sitepath"));
            String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
            String typeurl = cursor.getString(cursor.getColumnIndex("typeurl"));
            String pathimg = cursor.getString(cursor.getColumnIndex("pathimg"));
            NewsObj newsObj = new NewsObj( typeid,  id,   null,   null,   null,   ismake,   channel,   null,   click,   null,   title,   shorttitle,   null,   writer,   source,   litpic,   pubdate,   senddata,   mid,   keywords,   null,   null,   null,   null,   null,   null,   description,   null,   dutyadmin,   null,   null,   weight,   null,   null,   null,   typedir,   typename,   null,   isdefault,   defaultname,   namerule,   namerule2,   null,   null,   null,   sitepath,   arcurl,   typeurl,pathimg) ;

            Nlist.add(newsObj);


            Log.i("aaa", "展示图片的集合长度是" + Nlist.size());
        }
        return Nlist;


    }



}
