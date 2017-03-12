package com.example.joannapacia.test.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.joannapacia.test.R;

import java.util.HashMap;

public class WebViewActivity extends AppCompatActivity {

    private WebView browser;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init the UI elements
        initView();
    }

    private void initView() {
        // set the brand logo in the tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(com.example.joannapacia.test.R.drawable.brand_name);

        Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("HASHMAP");

        browser = (WebView) findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new ArticleWebViewClient());
        browser.loadUrl(hashMap.get("LINK"));
    }

    /*
     * Handling Page Navigation
     * open any java.net.URL inside the application and clicking on any link from that URl inside
     * loads the destination URL inside a
     */
    private class ArticleWebViewClient extends WebViewClient {

        final ProgressDialog dialog = ProgressDialog.show(WebViewActivity.this, "", "Please wait," +
                " Loading Page...", true);

        // this method will be triggered when the Page Started Loading
        @Override
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon){
            dialog.show();
        }

        // if you will not use this method url links are open in new browser not in webview
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        // this method will be triggered when the Page loading is completed
        @Override
        public void onPageFinished(WebView view, String url){
            if (dialog.isShowing()|| dialog!= null) {
                dialog.dismiss();
            }
        }

    }

    // navigating web page history
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {
            browser.goBack();
            return true;
        }
        // if it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

}

