    package com.cninter.a3dgame.service;

    import android.app.Fragment;
    import android.app.IntentService;
    import android.app.Notification;
    import android.app.NotificationManager;
    import android.app.Service;
    import android.content.ContentValues;
    import android.content.Context;
    import android.content.Intent;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.os.Bundle;
    import android.os.Environment;
    import android.os.Handler;
    import android.os.IBinder;
    import android.os.Message;
    import android.os.Messenger;
    import android.os.Parcelable;
    import android.os.RemoteException;
    import android.support.annotation.Nullable;
    import android.support.v7.app.NotificationCompat;
    import android.util.Log;

    import com.cninter.a3dgame.MainActivity;
    import com.cninter.a3dgame.coustomview.News;
    import com.cninter.a3dgame.fragment.ArticleFragment;
    import com.cninter.a3dgame.utils.HttpUtils;
    import com.cninter.a3dgame.utils.JSONUtils;
    import com.cninter.a3dgame.utils.NewsObj;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Objects;


    public class MyIntentService extends IntentService {
        int yeshu=1;
        String sdpath=Environment.getExternalStorageDirectory().getAbsolutePath();
        static int f=0;
        String dbname="dangame.db";
        List<NewsObj> data=new ArrayList<>();

        public MyIntentService() {
            super("MyIntentService");
        }

        public List<NewsObj> getData() {
            return data;
        }
        public void setData(List<NewsObj> data) {
            this.data = data;
        }
        List<String> imgpath = new ArrayList<>();;
        List<String> dataimg;
        int typeid=192;
        String urlpath="http://www.3dmgame.com/sitemap/api.php?row=40&typeid="+typeid+"&paging=1&page=1";

        @Override
        protected void onHandleIntent(Intent intent) {
            Log.i("aaa","MyIntentService服务已开启");
            dataimg=new ArrayList<>();
            //Log.i("ttt","页数"+yeshu);
            Log.i("aaa","onHandleIntent方法"+Thread.currentThread().getName());

                byte[] buf= HttpUtils.request(urlpath);
                if (buf!=null){
                    try {
                        String json=new String(buf,"utf-8");

                        if (json!=null){
                            data= JSONUtils.getList(json);
                            Log.i("aaa",json);
                            for (int i=0;i<data.size();i++) {
                                Log.i("aaa", "集合" + data.get(i).getLitpic());
                                String imgurl=data.get(i).getLitpic();

                                dataimg.add(imgurl);
                                Log.i("aaa","-图片网址的集合长度"+dataimg.size());
                                Log.i("aaa", "图片这是网址" + imgurl);

                          /*  boolean bbs=saveFile(downimg,"new");
                            if (bbs){
                                Log.i("aaa","下载第"+(i+1)+"成功");

                            }else{
                                Log.i("aaa","下载第"+(i+1)+"失败");
                            }*/

                            }

                        }
                        downImg();
                        Log.i("aaa","SD卡的绝对路径"+Environment.getExternalStorageDirectory().getAbsolutePath());
                        runSQLiteDatabase();
                        Log.i("bb","数据库进行添加完了");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.i("aaa","网络异常");
                }



        }

        public void runSQLiteDatabase(){

            //打开创建数据库
            SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+File.separator+dbname, null);
            Log.i("aaa","数据库创建成功");

            //创建表
            db.execSQL("create table if not exists games(id varchar(50) primary key,typeid varchar(50) ,ismake varchar(50),channel varchar(50),click varchar(50), title varchar(50),shorttitle varchar(50)," +
                    "writer varchar(50),source varchar(50), litpic varchar(50),pubdate varchar(50)," +
                    "senddata varchar(50),mid varchar(50),keywords varchar(50),description varchar(150),dutyadmin varchar(50)," +
                    "weight varchar(50),typedir varchar(50),typename varchar(50),isdefault varchar(50),defaultname varchar(50)," +
                    "namerule varchar(50),namerule2 varchar(50),sitepath varchar(50),arcurl varchar(50),typeurl varchar(50),pathimg varchar(50))" );
            Log.i("aaa","数据库创建表成功");
            //插入数据
            for (int i=0;i<dataimg.size();i++){
                Log.i("aaa","data长度是"+data.size());
                String id=data.get(i).getId();
                String typeid=data.get(i).getTypeid();String ismake=data.get(i).getIsmake();
                String channel=data.get(i).getChannel();String click=data.get(i).getClick();
                String title=data.get(i).getTitle();String shorttitle=data.get(i).getShorttitle();
                String writer=data.get(i).getWriter();String source=data.get(i).getSource();

                String litpic=data.get(i).getLitpic();String pubdate=data.get(i).getPubdate();

                String senddata=data.get(i).getSenddate();String mid=data.get(i).getMid();

                String keywords=data.get(i).getKeywords();String description=data.get(i).getDescription();
                String dutyadmin=data.get(i).getDutyadmin();String weight=data.get(i).getWeight();
                String typedir=data.get(i).getTypedir();String typename=data.get(i).getTypename();
                String isdefault=data.get(i).getIsdefault();String defaultname=data.get(i).getDefaultname();
                String namerule=data.get(i).getNamerule();String sitepath=data.get(i).getSitepath();
                String arcurl=data.get(i).getArcurl();String typeurl=data.get(i).getTypeurl();
                String pathimg=imgpath.get(i);
                //插入成功
                ContentValues values = new ContentValues();
                values.put("id",id);  values.put("typeid",typeid);  values.put("ismake",ismake);
                values.put("channel",channel);values.put("click",click);  values.put("title",title);
                values.put("shorttitle",shorttitle);  values.put("writer",writer);
                values.put("source",source);  values.put("litpic",litpic);
                values.put("pubdate",pubdate);  values.put("senddata",senddata);
                values.put("mid",mid);
                values.put("keywords",keywords);  values.put("description",description);values.put("dutyadmin",dutyadmin);
                values.put("weight",weight);  values.put("typedir",typedir);
                values.put("typename",typename);  values.put("isdefault",isdefault);
                values.put("defaultname",defaultname);  values.put("namerule",namerule);values.put("sitepath",sitepath);
                  values.put("arcurl",arcurl);
                values.put("typeurl",typeurl);  values.put("pathimg",pathimg);

                db.insert("games",null,values);
                Log.i("aaa","插入数据成功");


            }



        }

        public void downImg() throws Exception {
            Log.i("aaa","downImg方法data====================================="+dataimg.size());
            int i=0;
           for(String str:dataimg) {
               Log.i("aaa", "图片网址str" + str);

               byte[] bt = HttpUtils.request(str);
               Log.i("sssss",  bt.length+ "");
               if (bt != null) {
                   Log.i("ttt", "保存图片的pp=" + yeshu);

                   boolean flag = saveFile(bt, "item" +typeid + i + ".jpg");
                   if (flag) {
                       Log.i("aaa", "保存图片成功" + i);
                   } else {
                       Log.i("aaa", "保存图片失败" + i);
                   }
                   i++;
               }

           }
        }

        //保存文件
        private boolean saveFile(byte[] data,String fileName)throws  Exception{
            boolean flag = false;
            //说明SD卡挂载成功
            Log.i("aaa","Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED="+(Environment.getExternalStorageState()+"===="+Environment.MEDIA_MOUNTED));
            //==数值  equals字符串进行比较

            if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                //得到SD卡的保存路径 /mnt/sdcard/donwnload
                File root =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                //创建一个写入文件  /mnt/sdcard/download/filename

                File file = new File(root,fileName);
                String imgpaths=file.getAbsolutePath();
                imgpath.add(imgpaths);
                Log.i("sssss","data"+data.length);
                //往文件中写数据
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(data,0,data.length);
                flag = true;
                fileOutputStream.close();
            }
            return flag;
        }



    }
