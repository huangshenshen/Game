package com.cninter.a3dgame.adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cninter.a3dgame.R;
import com.cninter.a3dgame.coustomview.News;
import com.cninter.a3dgame.utils.NewsObj;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/6/25 0025.
 */
public class MyAdapter extends BaseAdapter{
    List<News> data;
    Context context;

    public MyAdapter(List<News> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            view=View.inflate(context, R.layout.item,null);
            holder=new ViewHolder();
            holder.img= (ImageView) view.findViewById(R.id.img);
            holder.tv_tile= (TextView) view.findViewById(R.id.tv_tile);
            holder.tv_content= (TextView) view.findViewById(R.id.tv_content);
            holder.tv_date= (TextView) view.findViewById(R.id.tv_date);
            holder.tv_click= (TextView) view.findViewById(R.id.tv_click);

            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        //赋值
        holder.tv_tile.setText(data.get(i).getTitle().toString());
        holder.tv_content.setText(data.get(i).getDescription().toString());
        Log.i("aaa","内容"+data.get(i).getDescription().toString());
        Log.i("aaa","标题"+data.get(i).getTitle().toString());
        Log.i("bbb","图片"+data.get(i).getPathimg().toString());
        holder.tv_click.setText(data.get(i).getClick().toString());
        holder.tv_date.setText(data.get(i).getPubdate());
         String pathString=data.get(i).getPathimg().toString();

             File file=new File(pathString);
            if (file!=null) {
                Bitmap bitmap = BitmapFactory.decodeFile(pathString);
                holder.img.setImageBitmap(bitmap);
            }else{
                Log.i("aaa","文件读取失败");
            }


        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView tv_tile;
        TextView tv_content;
        TextView tv_date;
        TextView tv_click;
    }



}
