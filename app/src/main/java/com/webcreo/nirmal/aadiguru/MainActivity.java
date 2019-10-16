package com.webcreo.nirmal.aadiguru;

import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    String google = "https://www.google.com";
    String facebook = "https://www.facebook.com";
    String youtube = "https://www.youtube.com";
    String aadiguru = "https://www.webcreo.in/Aadiguru/";

    String currentUrl = youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        //mProgressbar = (ProgressBar) findViewById(R.id.progress_bar);
        //mNestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mWebView = (WebView) findViewById(R.id.web_view);
        //initNestedScroll();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.loadUrl(currentUrl);
            }
        });

        initWebView();
    }

    ProgressBar mProgressbar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    WebView mWebView;
    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(false);

        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setSaveFormData(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mWebView.getSettings().setSupportZoom(true);
        //mWebView.setWebViewClient(new WebViewClient());

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished (WebView view, String url){
                mSwipeRefreshLayout.setRefreshing(false);
                //currentUrl = url;
                super.onPageFinished(view, url);
            }
        });
        mWebView.setClickable(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl(currentUrl);
        /////////////////////////////////////////////////////
        /*getWindow().requestFeature(Window.FEATURE_PROGRESS);
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    setTitle(R.string.app_name);
            }
        });*/
    }

    private NestedScrollView mNestedScrollView;
    private void initNestedScroll() {
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
