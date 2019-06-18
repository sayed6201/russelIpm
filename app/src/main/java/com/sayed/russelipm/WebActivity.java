package com.sayed.russelipm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

//        webView = findViewById(R.id.webview);

        String url = getIntent().getStringExtra("url");

//        String doc="<iframe src='http://docs.google.com/viewer?url="+url+"' width='100%' height='100%' style='border: none;'></iframe>";

//        Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();

        WebView  wv = (WebView)findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowFileAccess(true);
        wv.loadUrl(url);
//        wv.loadData( doc , "text/html",  "UTF-8");
        wv.setWebViewClient(new WebViewClient());

//        //enablingjavascript
//        WebSettings webSettings=webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        webView.loadUrl(doc);
//        webView.setWebViewClient(new WebViewClient());

    }
}
