package cn.edu.sjtu.dclab.freewifi.push;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.tool.HTTPTool;
import cn.edu.sjtu.dclab.freewifi.tool.PhoneStateTool;
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
    }

    @Override
    public void onStart() {
        super.onStart();
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

        //TODO 用户每次点击广告后，会通知服务，服务器收集点击数据
        String IMEIString = PhoneStateTool.GetIMEI(getApplicationContext());
        String adID = "TEST";
        HTTPTool.SendAdClickedInfo(getApplication(), IMEIString, adID);
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "onBackPressed()", Toast.LENGTH_SHORT).show();
        dialog();
        //super.onBackPressed();
    }

    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MsgShowAty.this);
        builder.setMessage("是否保存该推荐");
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //TODO 用户点击收藏广告
                HTTPTool.SendAdCollectedInfo(getApplication(), "Test", "Test");
                MsgShowAty.this.finish();
            }
        });
        builder.setNegativeButton("不保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                MsgShowAty.this.finish();
            }
        });
        builder.create().show();
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