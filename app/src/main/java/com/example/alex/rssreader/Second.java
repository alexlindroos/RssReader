package com.example.alex.rssreader;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
/**
 * Created by Alex on 24.9.2015.
 */
public class Second extends Activity {

    // In this class RSS Feed is showed in webview layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        WebView w1=(WebView)findViewById(R.id.webView);

        w1.loadUrl("http://www.iltalehti.fi/rss");
    }
}
