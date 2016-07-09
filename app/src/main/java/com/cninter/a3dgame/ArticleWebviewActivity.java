package com.cninter.a3dgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleWebviewActivity extends AppCompatActivity {
    WebView webview;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_webview);
        webview = (WebView) findViewById(R.id.webview_article);
        webSettings = webview.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setBlockNetworkImage(true);

        Intent intent = getIntent();
        final String url= intent.getStringExtra("url");
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient(){
            //覆盖url的加载
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });

    }
}
