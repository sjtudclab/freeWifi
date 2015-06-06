package cn.edu.sjtu.dclab.freewifi.push;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.tool.*;
import cn.jpush.android.api.JPushInterface;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.Map;

/**
 * Created by Yang on 2015/5/29.
 */
public class MsgShowAty extends Activity implements View.OnClickListener, IWXAPIEventHandler {
    private WebView webView;
    private ImageView collectAdBtn;
    private ImageView collectMerBtn;
    private ImageView shareBtn;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_adweb);
        webView = (WebView) findViewById(R.id.adweb);
        collectAdBtn = (ImageView) findViewById(R.id.collectAdbtn);
        collectMerBtn = (ImageView) findViewById(R.id.collectMerbtn);
        shareBtn = (ImageView) findViewById(R.id.imgShare);

        collectAdBtn.setOnClickListener(this);
        collectMerBtn.setOnClickListener(this);
        shareBtn.setOnClickListener(this);

        webView.getSettings().setJavaScriptEnabled(true);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constants.WECHAT_APP_ID, false);
        // 将该app注册到微信
        api.registerApp(Constants.WECHAT_APP_ID);
//        Toast.makeText(getApplicationContext(), "Registered to Wechat.", Toast.LENGTH_SHORT).show();

        //在WXEntryActivity中将接收到的intent及实现了IWXAPIEventHandler接口的对象传递给IWXAPI接口的handleIntent方法
        api.handleIntent(getIntent(), this);
    }

    private String shareURl = null;

    private String adId;
    private String title;
    private String description;
    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String IMEIString = PhoneStateTool.GetIMEI(getApplicationContext());
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
//            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            String urlJson = bundle.getString(JPushInterface.EXTRA_EXTRA);
            ClassParse parse = new ClassParse();
            Map<String,Object> map = parse.string2Map(urlJson);
            adId = (String)map.get("URL");
            String adStr = (String)map.get("AD");
            Ad ad = parse.string2Ad(adStr);
            title = ad.getName();
            description = ad.getMerchant().getName();

            //adId = urlJson.substring(urlJson.indexOf(":") + 2, urlJson.length() - 2);
            String urlLink = getResources().getString(R.string.server)+"/ad/mobile?id="+adId;
            shareURl = urlLink+"&device_id="+ IMEIString;
            webView.loadUrl(urlLink);
            webView.setWebViewClient(new AdWebViewClient());
        }

        //TODO 用户每次点击广告后，会通知服务，服务器收集点击数据

        //String adID = "TEST";
        //HTTPTool.SendAdClickedInfo(getApplication(), IMEIString, adID);
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**微信发送请求到第三方应用时，会回调到该方法
     * @param req
     */
    @Override
    public void onReq(BaseReq req) {
    }

    /**第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {
        int result = 0;

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        this.finish();
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }
    private void sendWebToWechat(String url, Boolean toMoments){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = description;
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        msg.thumbData = Tools.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = toMoments ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);

        this.finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String imei = PhoneStateTool.GetIMEI(getApplicationContext());
        switch (id){
            case R.id.collectAdbtn:
                //Toast.makeText(MsgShowAty.this,"click ad",Toast.LENGTH_SHORT).show();
                HTTPTool.SendAdCollectedInfo(MsgShowAty.this,imei,adId);
                break;
            case R.id.collectMerbtn:
                //Toast.makeText(MsgShowAty.this,"click mer",Toast.LENGTH_SHORT).show();
                HTTPTool.SendMerCollectedInfo(MsgShowAty.this,imei,adId);
                break;
            case R.id.imgShare:


                sendWebToWechat(shareURl, true);
                break;
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