package com.example.franklin.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Franklin on 12/13/2015.
 */
public class OHPView extends Activity {
    private WebView webView;

    /**
     * Launches a new activity with a webview.
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        //sets the layout to a webview
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_web);
        //sets the url and javascript
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://stronglifts.com/overhead-press/");
    }
}

