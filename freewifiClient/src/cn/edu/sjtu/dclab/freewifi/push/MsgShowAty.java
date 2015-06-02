package cn.edu.sjtu.dclab.freewifi.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Yang on 2015/5/29.
 */
public class MsgShowAty extends Activity {
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_adweb);

        webView = (WebView) findViewById(R.id.adweb);
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
//            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            String urlJson = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String urlLink = urlJson.substring(urlJson.indexOf(":") + 2, urlJson.length() - 2);

            webView.loadUrl("http://" + urlLink);
            webView.setWebViewClient(new AdWebViewClient());
        }
    }

    private class AdWebViewClient extends WebViewClient {
        //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}