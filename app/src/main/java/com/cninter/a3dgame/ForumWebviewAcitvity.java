package com.cninter.a3dgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cninter.a3dgame.R;

public class ForumWebviewAcitvity extends AppCompatActivity {
    String url= "http://bbs.3dmgame.com/forum.php";

    WebView webView;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_fragment);
        webView = (WebView) findViewById(R.id.webview_forum);
        webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setBlockNetworkImage(true);

        //Intent intent = getIntent();
       // final String url= intent.getStringExtra("typeurl");

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            //覆盖url的加载
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });



    }
}
