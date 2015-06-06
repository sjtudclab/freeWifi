package cn.edu.sjtu.dclab.freewifi.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.tool.Constants;
import cn.edu.sjtu.dclab.freewifi.tool.Tools;

/**WXEntryActivity extends Activity implements IWXAPIEventHandler
 * 接收微信的请求及返回值；
 *
 * 如果你的程序需要接收微信发送的请求，或者接收发送到微信请求的响应结果，需要下面3步操作：
 * a. 在你的包名相应目录下新建一个wxapi目录，并在该wxapi目录下新增一个WXEntryActivity类，该类继承自Activity；
 * 并在manifest文件里面加上exported属性，设置为true；
 * b. 实现IWXAPIEventHandler接口，微信发送的请求将回调到onReq方法，发送到微信请求的响应结果将回调到onResp方法；
 * c. 在WXEntryActivity中将接收到的intent及实现了IWXAPIEventHandler接口的对象传递给IWXAPI接口的handleIntent方法，
 * 如api.handleIntent(getIntent(), this);
 *
 * 当微信发送请求到你的应用，将通过IWXAPIEventHandler接口的onReq方法进行回调，类似的，应用请求微信的响应结果将通过onResp回调。
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
//	private static final String TAG = "WXEntryActivity";

    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
    private static final String MSG_DEFAULT = "Message from 3rd app.";
    private static final int THUMB_SIZE = 150;

//    private Context context = WXEntryActivity.this;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    private Button btnShareToWechatSession, btnShareToWecahtMoments, btnCheckMomentsSupport;
//    private boolean toMoments = false;

//    private void handleIntent(Intent paraIntent){ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_wechatshare);
        btnShareToWechatSession = (Button) findViewById(R.id.btn_shareToWechatSession);
        btnShareToWecahtMoments = (Button) findViewById(R.id.btn_shareToWechatMoments);
        btnCheckMomentsSupport = (Button) findViewById(R.id.btn_checkWechatMoments);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constants.WECHAT_APP_ID, false);
        // 将该app注册到微信
        api.registerApp(Constants.WECHAT_APP_ID);
//        Toast.makeText(getApplicationContext(), "Registered to Wechat.", Toast.LENGTH_SHORT).show();

        //在WXEntryActivity中将接收到的intent及实现了IWXAPIEventHandler接口的对象传递给IWXAPI接口的handleIntent方法
        api.handleIntent(getIntent(), this);
    }

    /**单击事件处理
     * @param v
     */
    public void onClickProcess(View v) {
        if (v.getId() == R.id.btn_shareToWechatSession) {
            sendTextToWechat(MSG_DEFAULT, false);
        } else if(v.getId() == R.id.btn_shareToWechatMoments){
            sendTextToWechat(MSG_DEFAULT, true);
        } else if(v.getId() == R.id.btn_checkWechatMoments){
            int wxSdkVersion = api.getWXAppSupportAPI();
            if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
                Toast.makeText(getApplicationContext(), "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline supported", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline not supported", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void sendTextToWechat(String textMsg, Boolean toMoments){
        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = textMsg;
        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = textMsg;// 发送文本类型的消息时，title字段不起作用，msg.title = "Will be ignored";
        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = toMoments? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        // 调用api接口发送数据到微信
        api.sendReq(req);

        this.finish();
    }

    private void sendImageToWechat(int imgResId, Boolean toMoments){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), imgResId);
        WXImageObject imgObj = new WXImageObject(bmp);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Tools.bmpToByteArray(thumbBmp, true);  // 设置缩略图

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = toMoments ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);

        this.finish();
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

}