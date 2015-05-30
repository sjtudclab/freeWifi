package cn.edu.sjtu.dclab.freewifi.tool;

import android.os.Handler;

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
	 size:
	 data: wifilist
	 }
	 * @return
	 */
	public static JSONObject CreateWifiListJson(){
		JSONObject root = new JSONObject();
		try {
			root.put("code", "0");
			root.put("size", "2kb");
			root.put("data", "wifi1, wifi2, wifi3, wifi4, ...");
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
	 data: wifilist
	 }
	 * @param root
	 */
	public static String ParseWifiListJson(JSONObject root){
		int code = -404;
		String size = "", wifilist = "";
		try {
			code = root.getInt("code");
			size = root.getString("size");
			wifilist = root.getString("data");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "code = " + code + " size = " + size + " data = " + wifilist;
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
