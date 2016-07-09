package com.cninter.a3dgame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cninter.a3dgame.utils.NewsObj;
import com.cninter.a3dgame.utils.SQLiteDatabasehelper;

import java.util.List;

public class GameDetails extends AppCompatActivity {
    List<NewsObj> data;
    TextView game_details_shorttitle,game_details_typename,game_details_made_company,game_details_release_date,game_details_release_company;
    TextView game_details_websit,game_details_terrace,game_details_keywords,game_details_description,game_details_writer;
    ImageView game_details_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        data = (List<NewsObj>) getIntent().getSerializableExtra("data");

        int i = getIntent().getIntExtra("position",0);


        Log.i("bbb","-----游戏类型"+data.get(i).getTypename());
        Log.i("bbb","-----开发厂商"+data.get(i).getMid());
        Log.i("bbb","-----发售时间"+data.get(i).getWeight());


        Log.i("bbb","data长度"+data.size());
        initView();
        initGetData();
        game_details_shorttitle.setText(data.get(i).getShorttitle());
        game_details_typename.setText(data.get(i).getTypename());
        game_details_keywords.setText(data.get(i).getKeywords());

        game_details_writer.setText(data.get(i).getWriter());
        game_details_description.setText(data.get(i).getDescription());
        game_details_release_date.setText(data.get(i).getPubdate());


        Bitmap bitmap = BitmapFactory.decodeFile(data.get(i).getPathimg());
        game_details_img.setImageBitmap(bitmap);

    }

    private void initGetData() {


    }

    private void initView() {

        game_details_shorttitle = (TextView) findViewById(R.id.game_details_shorttitle);
        game_details_typename = (TextView) findViewById(R.id.game_details_typename);
        game_details_made_company = (TextView) findViewById(R.id.game_details_made_company);
        game_details_release_date = (TextView) findViewById(R.id.game_details_release_date);
        game_details_release_company = (TextView) findViewById(R.id.game_details_release_company);
        game_details_websit = (TextView) findViewById(R.id.game_details_websit);
        game_details_terrace = (TextView) findViewById(R.id.game_details_terrace);
        game_details_keywords = (TextView) findViewById(R.id.game_details_keywords);
        game_details_description = (TextView) findViewById(R.id.game_details_description);
        game_details_writer = (TextView) findViewById(R.id.game_details_writer);
        game_details_img = (ImageView) findViewById(R.id.game_details_img);
    }
}
