package com.cninter.a3dgame.utils;


import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
	public static List<NewsObj>  getList(String json){

		List<NewsObj> list=new ArrayList<>();

		
		try {
				JSONObject root=new JSONObject(json);
				JSONObject dataobj=root.getJSONObject("data");
				for (int i=0;i<dataobj.length();i++) {
					NewsObj newobj=new NewsObj();
					JSONObject data1 = dataobj.getJSONObject(i+"");
					String id = data1.getString("id");
					newobj.setId(id);
					String typeid = data1.getString("typeid");
					newobj.setTypeid(typeid);
					String typeid2 = data1.getString("typeid2");
					newobj.setTypeid2(typeid2);
					String sortrank = data1.getString("sortrank");
					newobj.setSortrank(sortrank);
					String flag = data1.getString("flag");
					newobj.setFlag(flag);
					String ismake = data1.getString("ismake");
					newobj.setIsmake(ismake);
					String channel = data1.getString("channel");
					newobj.setChannel(channel);
					String arcrank = data1.getString("arcrank");
					newobj.setArcrank(arcrank);
					String click = data1.getString("click");
					newobj.setClick(click);
					String money = data1.getString("money");
					newobj.setMoney(money);
					String title = data1.getString("title");
					Log.i("aaa","标题："+title);
					newobj.setTitle(title);
					String shorttitle = data1.getString("shorttitle");
					newobj.setShorttitle(shorttitle);
					String color = data1.getString("color");
					newobj.setColor(color);
					String writer = data1.getString("writer");
					newobj.setWriter(writer);
					String source = data1.getString("source");
					newobj.setSource(source);

					String litpic = "http://www.3dmgame.com"+data1.getString("litpic");
					Log.i("ttt","解析中图片的网址"+litpic);
					newobj.setLitpic(litpic);
					String pubdate = data1.getString("pubdate");
					newobj.setPubdate(pubdate);
					String senddate = data1.getString("senddate");
					newobj.setSenddate(senddate);
					String mid = data1.getString("mid");
					newobj.setMid(mid);
					String keywords = data1.getString("keywords");
					newobj.setKeywords(keywords);
					String lastpost = data1.getString("lastpost");
					newobj.setLastpost(lastpost);
					String scores = data1.getString("scores");
					newobj.setScores(scores);
					String goodpost = data1.getString("goodpost");
					newobj.setGoodpost(goodpost);
					String badpost = data1.getString("badpost");
					newobj.setBadpost(badpost);
					String voteid = data1.getString("voteid");
					newobj.setVoteid(voteid);
					String notpost = data1.getString("notpost");
					newobj.setNotpost(notpost);
					String description = data1.getString("description");
					Log.i("aaa","内容："+description);
					newobj.setDescription(description);
					String filename = data1.getString("filename");
					newobj.setFilename(filename);
					String dutyadmin = data1.getString("dutyadmin");
					newobj.setDutyadmin(dutyadmin);
					String tackid = data1.getString("tackid");
					newobj.setTackid(tackid);
					String mtype = data1.getString("mtype");
					newobj.setMtype(mtype);
					String weight = data1.getString("weight");
					newobj.setWeight(weight);
					String fby_id = data1.getString("fby_id");
					newobj.setFby_id(fby_id);
					String game_id = data1.getString("game_id");
					newobj.setGame_id(game_id);
					String feedback = data1.getString("feedback");
					newobj.setFeedback(feedback);
					String typedir = data1.getString("typedir");
					newobj.setTypedir(typedir);
					String typename = data1.getString("typename");
					newobj.setTypename(typename);
					String corank = data1.getString("corank");
					newobj.setCorank(corank);
					String isdefault = data1.getString("isdefault");
					newobj.setIsdefault(isdefault);
					String defaultname = data1.getString("defaultname");
					newobj.setDefaultname(defaultname);
					String namerule = data1.getString("namerule");
					newobj.setNamerule(namerule);
					String namerule2 = data1.getString("namerule2");
					newobj.setNamerule2(namerule2);
					String ispart = data1.getString("ispart");
					newobj.setIspart(ispart);
					String moresite = data1.getString("moresite");
					newobj.setMoresite(moresite);
					String siteurl = data1.getString("siteurl");
					newobj.setSiteurl(siteurl);
					String sitepath = data1.getString("sitepath");
					newobj.setSitepath(sitepath);
					String arcurl = data1.getString("arcurl");
					newobj.setArcurl(arcurl);
					String typeurl = data1.getString("typeurl");
					newobj.setTypeurl(typeurl);
					list.add(newobj);

				}
			Log.i("aaa","集合长度"+list.size());


				return list;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}

}
