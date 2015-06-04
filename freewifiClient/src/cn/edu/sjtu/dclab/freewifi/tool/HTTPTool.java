package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * HTTPTool
 * Created by Ernest on 2015/5/29.
 */
public class HTTPTool {
    static final String TAG = "HTTPTool";

    private static final String URL_REG = "http://dclab.mybluemix.net/freewifiserver/user/register";
    private static final String URL_NOTIFY = "http://dclab.mybluemix.net/freewifiserver/user/notification";
    private static final String URL_GETWIFILIST = "http://dclab.mybluemix.net/freewifiserver/wifi/get";
    private static final String URL_ADCLIECKED = "http://dclab.mybluemix.net/freewifiserver/user/click";
    private static final String URL_ADCOLLECTED = "http://dclab.mybluemix.net/freewifiserver/user/collect";

    /**
     * 发送注册信息(POST)
     */
    public static void SendRegisterInfo(final Context context, String imei, String gender,
                                        String birthday, String tel, String education, String income) {
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.IMEI, imei);
        params.put(SharedDataTool.GENDER, SharedDataTool.GetGenderIndex(gender));
        params.put(SharedDataTool.BIRTHDAY, birthday);
        params.put(SharedDataTool.TEL, tel);
        params.put(SharedDataTool.EDUCATION, SharedDataTool.GetGenderIndex(education));
        params.put(SharedDataTool.INCOME, SharedDataTool.GetGenderIndex(income));

        Toast.makeText(context, "Sending Register Info...", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//创建客户端对象
        client.post(URL_REG, params, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    int status = JsonTool.ParseStatusCodeJson(response);
                    Log.i(TAG, "Received status: " + status);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "onFailure statusCode: " + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i(TAG, "onFailure statusCode: " + statusCode);
            }
        });
    }

    /**
     * wifi连接成功后，通知服务器(POST)
     *
     * @param context
     * @param imei    PhoneStateTool.GetIMEI()获取
     * @param wifiID
     */
    public static void SendConnectedInfo(final Context context, String imei, String wifiID) {
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.IMEI, imei);
        params.put(SharedDataTool.WIFIID, wifiID);

        Toast.makeText(context, "Sending Connected Info...", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//创建客户端对象
        client.post(URL_NOTIFY, params, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    int status = JsonTool.ParseStatusCodeJson(response);
                    Log.i(TAG, "Received status: " + status);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "onFailure statusCode: " + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i(TAG, "onFailure statusCode: " + statusCode);
            }
        });
    }

    /**
     * 获取wifi列表(GET)
     *
     * @param context
     * @param longitude
     * @param latitude
     */
    public static void SendRequestForWifiList(final Context context, String longitude, String latitude) {
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.LONGITUDE, longitude);
        params.put(SharedDataTool.LATITUDE, latitude);

        Toast.makeText(context, "Sending Request For WifiList...", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//创建客户端对象
        client.get(URL_GETWIFILIST, params, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    String result = JsonTool.ParseWifiListJson(response);
                    Log.i(TAG, "Received results: " + result);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i(TAG, "onFailure statusCode: " + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i(TAG, "onFailure statusCode: " + statusCode);
            }
        });
    }

    /**
     * 用户每次点击广告后，会通知服务，服务器收集点击数据，便于进行数据分析：
     * user/click
     * POST
     * 参数：
     * device_id：String
     * ad_id：String
     * 返回：json
     * { code：0/-1   0表示成功 }
     */
    public static void SendAdClickedInfo(final Context c, String deviceId, String adId) {
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.IMEI, deviceId);
        params.put(SharedDataTool.ADID, adId);

        Toast.makeText(c, "Sending AdClicked Info...", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//创建客户端对象
        client.post(URL_ADCLIECKED, params, new RespHandler());
    }

    /**
     * 用户点击收藏广告
     * user/collect
     * POST
     * 参数：
     * device_id：String
     * ad_id：String
     * 返回：json
     * { code：0/-1   0表示成功 }
     */
    public static void SendAdCollectedInfo(final Context c, String deviceId, String adId) {
        //TODO
        RequestParams params = new RequestParams();
        params.put(SharedDataTool.IMEI, deviceId);
        params.put(SharedDataTool.ADID, adId);

        Toast.makeText(c, "Sending AdCollected Info...", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//创建客户端对象
        client.post(URL_ADCOLLECTED, params, new RespHandler());
    }

    private static class RespHandler extends JsonHttpResponseHandler {
        @Override//返回JSONArray对象 | JSONObject对象
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            if (statusCode == 200) {
                //int status = JsonTool.ParseStatusCodeJson(response);
                String status = "OK";
                Log.i(TAG, "Received status: " + status);
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
            Log.i(TAG, "onFailure statusCode: " + statusCode);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
            Log.i(TAG, "onFailure statusCode: " + statusCode);
        }
    }
}
