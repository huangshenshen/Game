package com.cninter.a3dgame.adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cninter.a3dgame.R;
import com.cninter.a3dgame.utils.GridViewImgText;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/7/9 0009.
 */
public class GridViewAdapter extends BaseAdapter{
    List<GridViewImgText>  gridViewList;
    Context context;

    public GridViewAdapter(List<GridViewImgText> gridViewList, Context context) {
        this.gridViewList = gridViewList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gridViewList.size();
    }

    @Override
    public Object getItem(int i) {
        return gridViewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder=null;
        if (view==null){
            view=View.inflate(context, R.layout.gridview_img_text,null);
            holder=new ViewHolder();

            holder.gridview_img= (ImageView) view.findViewById(R.id.gridview_img);
            holder.gridview_text= (TextView) view.findViewById(R.id.gridview_text);

            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        Log.i("getView",gridViewList.size()+"");
        //赋值
        String str="imgText"+i;
        holder.gridview_text.setText(gridViewList.get(i).getShortTitle());


        String pathImg=gridViewList.get(i).getPathImag();
        Bitmap bitmap = BitmapFactory.decodeFile(pathImg);
        holder.gridview_img.setImageBitmap(bitmap);
        return view;
    }

    class ViewHolder{
        ImageView gridview_img;
        TextView gridview_text;

    }
/**
 *
 *
 */



}
