package cn.edu.sjtu.dclab.freewifi.test;

import android.app.Activity;
import android.os.Bundle;
import cn.edu.sjtu.dclab.freewifi.R;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Yang on 2015/5/22.
 */
public class WXLogin extends Activity {
    private static final String APP_DD = "";
    private IWXAPI wxapi;

    private void regToWX(){
        wxapi = WXAPIFactory.createWXAPI(this, APP_DD, true);
        wxapi.registerApp(APP_DD);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        regToWX();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wxlogin);
    }
}