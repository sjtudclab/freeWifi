package cn.edu.sjtu.dclab.freewifi.tool;

import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**JsonTool
 * json操作（创建、解析）测试；
 * @author Eugene
 * @data 2015-1-25
 */
public class JsonTool {
	static final String TAG = "JsonTool";
	
	public static final int RECEIVE_JSON_STRING = 101;
	
	private static Handler handler;
	
	public static void SetHandler(Handler handler){
		JsonTool.handler = handler;
	}
	
	/**创建json；
	 * json示例：{ code：0/-1   0表示成功 }
	 * @return
	 */
	public static JSONObject CreateStatusJson(){
		JSONObject root = new JSONObject();
		try {
			root.put("code", "0");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return root;
	}

	/**创建json；
	 * json示例：{
	 code：0/-1
	 size: 8
	 data: [{"id":1,"ssid":"sun","longitude":122,"latitude":31,"password":"18702107120"}]
	 }
	 * @return
	 */
	public static JSONObject CreateWifiListJson(){
		JSONObject root = new JSONObject();
		JSONObject wifiElement1 = new JSONObject();
		JSONArray data = new JSONArray();
		try {
			root.put("code", "0");
			root.put("size", 8);
			wifiElement1.put("id", 1);
			wifiElement1.put("ssid", "sun");
			wifiElement1.put("longitude", 122);
			wifiElement1.put("latitude", 31);
			wifiElement1.put("password", "18702107120");
			data.put(wifiElement1);
			root.put("data", data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return root;
	}

	/**解析接收到的json
	 * 格式示例：
	 * { code：0/-1   0表示成功 }
	 * @param root
	 */
	public static int ParseStatusCodeJson(JSONObject root){
		int code = -404;
		try {
			code = root.getInt("code");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return code;
	}

	/**解析接收到的json
	 * 格式示例： {
	 code：0/-1
	 size:
	 data: [{"id":1,"ssid":"sun","longitude":122,"latitude":31,"password":"18702107120"}]
	 }
	 * @param root
	 */
	public static String ParseWifiListJson(JSONObject root){
		int code = -404;
		String size = "", data = "";
		JSONArray wifiArray = null;
		try {
			code = root.getInt("code");
			size = root.getString("size");
			data = root.getString("data");
			wifiArray = root.getJSONArray("data");
			for(int i = 0; i < wifiArray.length(); i++){
				//TODO
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "code = " + code + " size = " + size + " data = " + data;
	}

//	public static LatLng ParseJsonForLocation(JSONObject root){
//		LatLng location = null;
//		try {
//			JSONObject bodyRoot = root.getJSONObject("body");
//			String lat, lng;
//			lat = bodyRoot.getString("latitude");
//			lng = bodyRoot.getString("longitude");
//			if (lat == "" || lng == "") {
//				return null;
//			}
//			location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return location;
//	}
	
}
