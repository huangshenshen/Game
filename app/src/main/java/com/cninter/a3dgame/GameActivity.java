package com.cninter.a3dgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cninter.a3dgame.adpater.GridViewAdapter;
import com.cninter.a3dgame.utils.GridViewImgText;
import com.cninter.a3dgame.utils.NewsObj;
import com.cninter.a3dgame.utils.SQLiteDatabasehelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    Spinner spinner;
    List<NewsObj> data;
    List<String> shortTitleList;
    GridView gridview_game;
    List<String> pathImagList;
    int  typid=0;
    List<GridViewImgText>  gridViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        spinner = (Spinner) findViewById(R.id.game_spinner);
        gridview_game = (GridView) findViewById(R.id.gridview_game);
        final String games[] = {"动作(ACT)","射击(FPS)","角色扮演(RPG)","养成(GAL)","益智(PUZ)","即时战略(RTS)",
                "策略(SLG)","体育(SPG)","模拟经营(SIM)","赛车(RAC)","冒险(AVG)","动作角色(ARPG)"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GameActivity.this,R.layout.games_item,games);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(GameActivity.this,"位置"+i,Toast.LENGTH_SHORT).show();
                typid = 181+i;
                data = SQLiteDatabasehelper.LoadSQLiteDataGrid(typid);
                Log.i("aaa","data"+data.size());
                gridViewList = new ArrayList<GridViewImgText>();

                for (int j=0;j<data.size();j++){
                    String shortTitle = data.get(j).getShorttitle();
                    String pathImag= data.get(j).getPathimg();
                    GridViewImgText gridViewImgText = new GridViewImgText(shortTitle,pathImag);
                    gridViewList.add(gridViewImgText);

                }

                gridview_game.setAdapter(new GridViewAdapter(gridViewList,GameActivity.this));
                Log.i("aaa","适配器完成");
                gridview_game.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Toast.makeText(adapter.getContext(),"weizhi"+i,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GameActivity.this,GameDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position",i);
                    bundle.putSerializable("data", (Serializable) data);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });






            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


}
