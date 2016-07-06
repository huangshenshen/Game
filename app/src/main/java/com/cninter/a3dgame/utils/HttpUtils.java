package com.cninter.a3dgame.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.R.integer;

public class HttpUtils {
	
	public static byte[] request(String urlPath){
		

		ByteArrayOutputStream bos;
		
		try {
			//1������url����
			URL url =new URL(urlPath);
			//2������openConnection��������HttpURLConnection����
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			//4����
			connection.connect();
			if (connection.getResponseCode()==200) {
				InputStream is=connection.getInputStream();
				bos=new ByteArrayOutputStream();
				byte []buf=new byte[1024*4];
				int len=0;
				while((len=is.read(buf))!=-1){
					bos.write(buf, 0, len);
					
				}
				is.close();
				return bos.toByteArray();//���ڴ��е�����ת�����ֽ�����
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return null;

	
		
		
	}

}
