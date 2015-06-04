package cn.edu.sjtu.dclab.freewifi.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.edu.sjtu.dclab.freewifi.tool.Constants;

public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		final IWXAPI api = WXAPIFactory.createWXAPI(context, null);
		// 将该app注册到微信
		api.registerApp(Constants.WECHAT_APP_ID);
	}
}
