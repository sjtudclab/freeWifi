package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.Header;
import org.json.JSONObject;

/**HTTPTool
 * Created by Ernest on 2015/5/29.
 */
public class HTTPTool {
    static final String TAG = "HTTPTool";

    private static final String URL_REG = "user/register";
    private static final String URL_NOTIFY = "user/notification";
    private static final String URL_GETWIFILIST = "wifi/get";

    /**发送注册信息(POST)
     */
    public static void SendRegisterInfo(final Context context, String imei, String gender,
                                        String birthday, String tel, String education, String income){
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.IMEI, imei);
        params.put(SharedDataTool.GENDER, gender);
        params.put(SharedDataTool.BIRTHDAY, birthday);
        params.put(SharedDataTool.TEL, tel);
        params.put(SharedDataTool.EDUCATION, education);
        params.put(SharedDataTool.INCOME, income);

        Toast.makeText(context, "Sending Register Info...", Toast.LENGTH_LONG).show();
        SyncHttpClient client = new SyncHttpClient();//创建客户端对象
        client.post(URL_REG, params, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    int status = JsonTool.ParseStatusCodeJson(response);
                    Toast.makeText(context, "status: " + status, Toast.LENGTH_LONG);
                }
            }
        });
    }

    /**wifi连接成功后，通知服务器(POST)
     * @param context
     * @param imei      PhoneStateTool.GetIMEI()获取
     * @param wifiID
     */
    public static void SendConnectedInfo(final Context context, String imei, String wifiID){
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.IMEI, imei);
        params.put(SharedDataTool.WIFIID, wifiID);

        Toast.makeText(context, "Sending Connected Info...", Toast.LENGTH_LONG).show();
        SyncHttpClient client = new SyncHttpClient();//创建客户端对象
        client.post(URL_NOTIFY, params, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    int status = JsonTool.ParseStatusCodeJson(response);
                    Toast.makeText(context, "status: " + status, Toast.LENGTH_LONG);
                }
            }
        });
    }

    /**获取wifi列表(GET)
     * @param context
     * @param longitude
     * @param latitude
     */
    public static void SendRequestForWifiList(final Context context, String longitude, String latitude){
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.LONGITUDE, longitude);
        params.put(SharedDataTool.LATITUDE, latitude);

        Toast.makeText(context, "Sending Request For WifiList...", Toast.LENGTH_LONG).show();
        SyncHttpClient client = new SyncHttpClient();//创建客户端对象
        client.get(URL_GETWIFILIST, params, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    String result = JsonTool.ParseWifiListJson(response);
                    Toast.makeText(context, "Result: " + result, Toast.LENGTH_LONG);
                }
            }
        });
    }

}
