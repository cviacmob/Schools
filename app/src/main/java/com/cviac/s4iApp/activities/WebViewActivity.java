package com.cviac.s4iApp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.cviac.s4iApp.R;

public class WebViewActivity extends AppCompatActivity {
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        int pos = getIntent().getIntExtra("pos",0);

        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setBuiltInZoomControls(true);
        switch (pos) {
            case 0:
                mWebView.loadUrl("https://www.facebook.com/");
                break;
            case 1:
                mWebView.loadUrl("https://twitter.com/login");
                break;
            case 2:
                mWebView.loadUrl("https://www.google.com/gmail/");
                break;

            default:
                mWebView.loadUrl("file:///android_asset/History.html");
                break;
        }

      /*  int pos = getIntent().getIntExtra("pos",0);
        String s1,s2,s3;

        s1="https://www.facebook.com/";
        s2="https://twitter.com/login";
        s3="https://www.google.com/gmail/";
        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setBuiltInZoomControls(true);

        if (pos==0) {
            if
        }
        if (pos==1) {

        }
        if (pos==2) {

        }*/


     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int pos = getIntent().getIntExtra("pos",0);
        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setBuiltInZoomControls(true);
      mWebView.loadUrl("https://www.facebook.com/");*/






       /* switch (pos) {
            case 0:
                mWebView.loadUrl("https://www.facebook.com/");
                break;
            case 1:
                mWebView.loadUrl("https://www.google.com/gmail/");
                break;
            case 2:
                mWebView.loadUrl("https://twitter.com/login");
                break;
        }*/
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
