package cn.edu.sjtu.dclab.freewifi.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.tool.JsonTool;

/**JsonTestAty extends Activity
 * Created by Ernest on 2015/5/29.
 */
public class JsonTestAty extends Activity {
//    private static final String TAG = "JsonTestAty";

    TextView tvStatusCode, tvWifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_jsontest);

        tvStatusCode = (TextView) findViewById(R.id.tv_statuscode);
        tvWifiList = (TextView) findViewById(R.id.tv_wifilist);

        JSONObject statusJson = JsonTool.CreateStatusJson();
        int code = JsonTool.ParseStatusCodeJson(statusJson);
        tvStatusCode.setText("Original Json: " + statusJson.toString() +
                "\n\nParsing result: code = " + code + "\n\n\n");

        JSONObject wifilistJson = JsonTool.CreateWifiListJson();
        String s = JsonTool.ParseWifiListJson(wifilistJson);
        tvWifiList.setText("Original Json: " + wifilistJson.toString() +
                "\n\nParsing result: " + s + "\n\n\n");
    }

}