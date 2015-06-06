package cn.edu.sjtu.dclab.freewifi.tool;

import android.os.Handler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
//		return "code = " + code + " size = " + size + " data = " + wifilist;
		String str = wifilist.substring(1,wifilist.length()-2);
		return str;
	}

	/**
     * List 我们使用的Gson中的
     * public <T> T fromJson(String json, Type typeOfT)
     * 这边我们需要取到List<T>中不同的对象，普通的实现方式就如上一讲中org.Json库来解析JSON的方式一样(读者阅读上一讲内容)，
     * 这里我们通过 Gson中的 TypeToken类是简便操作：这边typeOfT的用法是通过反射机制把T里面的对象的属性的值映射出来，然后通过将json字符串取得的值赋值上去就可以了。
     * getType()的意思就是表示将jsonString的字符串解析出来之后封装到List集合中，然后分别从T里面取出类型将其复制。
     */
	public static <T> List<T> getObjectListFromJson(String jsonStr, Class<T> cls){
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonStr,
					new TypeToken<List<T>>(){}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
